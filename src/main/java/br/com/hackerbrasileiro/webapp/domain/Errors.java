package br.com.hackerbrasileiro.webapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Errors {
    @Getter @Setter String url;
    @Getter @Setter Exception exception;
}
