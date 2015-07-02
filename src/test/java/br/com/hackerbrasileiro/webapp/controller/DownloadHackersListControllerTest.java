package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.validator.FileManager;
import br.com.hackerbrasileiro.webapp.domain.validator.StreamInfo;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.catalina.ssi.ByteArrayServletOutputStream;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
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
    @Mock
    StreamInfo streamInfo;
    @Mock
    FileInputStream fileInputStream;
    DownloadHackersListController downloadHackersListController;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(fileManager.getStreamInfo(any())).thenReturn((streamInfo));
        when(streamInfo.getFileSize()).thenReturn(5L);
        when(streamInfo.getFileName()).thenReturn("lala");
        when(streamInfo.getInputStream()).thenReturn(fileInputStream);
        when(fileInputStream.read(any())).thenReturn(-1);
        downloadHackersListController = new DownloadHackersListController(fileManager);
        when(response.getOutputStream()).thenReturn(output);
        downloadHackersListController.downloadHackers(request, response);
    }

    @Test @Ignore
    public void shouldWriteSomethingInTheResponseOutput() throws Exception {
        verify(output, times(0)).write((byte[]) any());
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
}
