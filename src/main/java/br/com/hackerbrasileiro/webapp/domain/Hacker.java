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

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s,", this.firstName, this.lastName,  this.email);
    }
}
