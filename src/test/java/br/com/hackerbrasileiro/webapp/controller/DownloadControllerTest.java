/*
Copyright (C) 2015  ThoughtWorks, Inc.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.com.hackerbrasileiro.webapp.controller;

import br.com.hackerbrasileiro.webapp.domain.Hackers;
import br.com.hackerbrasileiro.webapp.domain.Photos;
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
    @Mock Photos photos;

    DownloadController downloadController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(fileHelper.getStreamFor(any())).thenReturn((streamHelper));
        when(streamHelper.getFileSize()).thenReturn(5L);
        when(streamHelper.getFileName()).thenReturn("lala");
        when(streamHelper.getInputStream()).thenReturn(fileInputStream);
        when(fileInputStream.read(any())).thenReturn(-1);
        downloadController = new DownloadController(hackers, fileHelper, photos);
        when(response.getOutputStream()).thenReturn(output);
        downloadController.downloadCsv(response);
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
