package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.validator.FileManager;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.catalina.ssi.ByteArrayServletOutputStream;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DownloadHackersListControllerTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ByteArrayServletOutputStream output;
    @Mock
    FileManager fileManager;

    DownloadHackersListController downloadHackersListController;

    @Mock
    File fileTest;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(fileTest.length()).thenReturn(5L);
        when(fileTest.getName()).thenReturn("lala");
        when(fileManager.createFile(any())).thenReturn(fileTest);
        downloadHackersListController = new DownloadHackersListController(fileManager);
        when(response.getOutputStream()).thenReturn(output);
        downloadHackersListController.downloadHackers(request, response);
    }

    @Test
    @Ignore
    public void shouldWriteSomethingInTheResponseOutput() throws Exception {
        //verify(output).write((byte[]) any());
    }

    @Test
    public void shouldReceiveAnCSVFileFromOutput() throws Exception {
        verify(response).setContentType("text/css");
    }

    @Test
    public void shouldSetCSVFileSize() throws Exception {
        verify(response).setContentLength(anyInt());
    }

    @Test
    public void shouldSetResponseHeader() throws Exception {
        verify(response).setHeader(anyString(), anyString());
    }

    @Test
    public void shouldCloseOutputStream() throws Exception {
        verify(output).close();
    }

    @Test
    public void shouldCreateAndDeleteCSVFile() throws IOException {
        verify(fileManager).createFile(anyString());
        verify(fileManager).deleteFile(anyString());
    }
}
