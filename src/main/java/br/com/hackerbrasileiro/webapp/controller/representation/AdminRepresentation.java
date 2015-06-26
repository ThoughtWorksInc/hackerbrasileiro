package br.com.hackerbrasileiro.webapp.controller.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AdminRepresentation {
    @Getter @Setter int numberOfPhotos;
    @Getter @Setter String name;
    @Getter @Setter String email;

    public AdminRepresentation(int numberOfPhotos) {
        this.numberOfPhotos = numberOfPhotos;
    }
}
