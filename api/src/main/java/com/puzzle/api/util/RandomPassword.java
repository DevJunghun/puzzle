package com.puzzle.api.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomPassword {
    private final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public String pwd() {
        final var salt = new StringBuilder();
        final var rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }

        return salt.toString();
    }
}
