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

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE)
public class RandomHacker {

    Hackers hackers;

    @Autowired
    public RandomHacker(Hackers hackers) {
        this.hackers = hackers;
    }

    public Hacker getRandomHacker() throws IOException {
        List<Hacker> hackers = this.hackers.getHackersDaily();
        if (hackers == null || hackers.isEmpty())
            return new Hacker();

        Random randomNumber = new Random();
        int hackerIndex = randomNumber.nextInt(hackers.size());

        return hackers.get(hackerIndex);
    }
}
