package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.AllHackers;
import br.com.hackerbrasileiro.webapp.util.FileHelper;
import br.com.hackerbrasileiro.webapp.util.FileManager;
import br.com.hackerbrasileiro.webapp.util.StreamInfo;
import org.apache.catalina.ssi.ByteArrayServletOutputStream;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class DownloadHackersControllerTest {

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
    @Mock
    FileHelper fileHelper;

    @Mock
    AllHackers allHackers;

    DownloadHackersController downloadHackersController;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(fileManager.getStreamInfo(any())).thenReturn((streamInfo));
        when(streamInfo.getFileSize()).thenReturn(5L);
        when(streamInfo.getFileName()).thenReturn("lala");
        when(streamInfo.getInputStream()).thenReturn(fileInputStream);
        when(fileInputStream.read(any())).thenReturn(-1);
        downloadHackersController = new DownloadHackersController(fileManager, allHackers, fileHelper);
        when(response.getOutputStream()).thenReturn(output);
        downloadHackersController.downloadHackers(response);
    }

    @Test
    @Ignore
    public void shouldWriteSomethingInTheResponseOutput() throws Exception {
        verify(output, times(0)).write((byte[]) any());
    }

    @Test
    public void shouldReceiveAnCSVFileFromOutput() throws Exception {
        verify(response).setContentType("text/csv");
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
    public void shouldCallSaveAllHackers() throws Exception {
        verify(allHackers).generateCSVFile();

    }
}
