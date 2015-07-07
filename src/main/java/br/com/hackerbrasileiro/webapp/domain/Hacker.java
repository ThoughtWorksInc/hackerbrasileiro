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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Hacker {
    @Getter @Setter String firstName;
    @Getter @Setter String lastName;
    @Getter @Setter String email;
    @Getter @Setter String imageData;

    public Hacker(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static Hacker buildWith(String line) {
        String[] hackerLine = line.split(",");
        String firstName = hackerLine[0].trim();
        String lastName = hackerLine[1].trim();
        String email = hackerLine[2].trim();

        return new Hacker(firstName, lastName, email);
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s,", this.firstName, this.lastName,  this.email);
    }
}
