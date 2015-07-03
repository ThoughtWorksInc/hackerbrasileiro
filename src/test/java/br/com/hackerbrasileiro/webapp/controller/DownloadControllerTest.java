package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hackers;
import br.com.hackerbrasileiro.webapp.util.FileHelper;
import br.com.hackerbrasileiro.webapp.util.StreamHelper;
import org.apache.catalina.ssi.ByteArrayServletOutputStream;
import org.junit.Before;
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

public class DownloadControllerTest {

    @Mock HttpServletRequest request;
    @Mock HttpServletResponse response;
    @Mock ByteArrayServletOutputStream output;
    @Mock StreamHelper streamHelper;
    @Mock FileInputStream fileInputStream;
    @Mock FileHelper fileHelper;
    @Mock Hackers hackers;

    DownloadController downloadController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(fileHelper.getStreamFor(any())).thenReturn((streamHelper));
        when(streamHelper.getFileSize()).thenReturn(5L);
        when(streamHelper.getFileName()).thenReturn("lala");
        when(streamHelper.getInputStream()).thenReturn(fileInputStream);
        when(fileInputStream.read(any())).thenReturn(-1);
        downloadController = new DownloadController(hackers, fileHelper);
        when(response.getOutputStream()).thenReturn(output);
        downloadController.downloadHackers(response);
    }

    @Test
    public void receiveCsvFileFromOutputTest() throws Exception {
        verify(response).setContentType("text/csv");
    }

    @Test
    public void setCsvFileSizeTest() throws Exception {
        verify(response).setContentLength(anyInt());
    }

    @Test
    public void setResponseHeaderTest() throws Exception {
        verify(response).setHeader(anyString(), anyString());
    }

    @Test
    public void closeOutputStreamTest() throws Exception {
        verify(output).close();
    }

    @Test
    public void generateCsvTest() throws Exception {
        verify(hackers).generateCsvFile();
    }
}
