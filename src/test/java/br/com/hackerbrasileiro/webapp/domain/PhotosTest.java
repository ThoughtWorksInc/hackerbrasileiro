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

package br.com.hackerbrasileiro.webapp.domain;

import br.com.hackerbrasileiro.webapp.util.FileHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhotosTest {

    @Mock private FileHelper fileHelper;

    private Photos photos;

    @Before
    public void setUp() {
        initMocks(this);
        photos = new Photos(fileHelper);
    }

    @Test
    public void saveTest() throws IOException {
        doNothing().when(fileHelper).createFor(new byte[anyByte()], anyString());
        photos.save("baseImage64");

        verify(fileHelper).createFor(new byte[anyByte()], anyString());
    }

    @Test
    public void getImageAsByteArrayTest() throws Exception {
        Photos photos = new Photos(fileHelper);
        byte[] imageByteArray = photos.getImageAsByteArray("src/test/resources/result.png");

        assertThat(imageByteArray.length > 0, is(true));
    }
}
