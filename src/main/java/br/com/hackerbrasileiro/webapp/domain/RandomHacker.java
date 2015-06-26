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
            return null;

        Random randomNumber = new Random();
        int hackerIndex = randomNumber.nextInt(hackers.size());

        return hackers.get(hackerIndex);
    }
}
