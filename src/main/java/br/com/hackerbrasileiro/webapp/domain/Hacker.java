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
