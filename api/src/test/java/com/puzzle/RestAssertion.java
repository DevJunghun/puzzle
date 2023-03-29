package com.puzzle;

import org.junit.jupiter.api.Assertions;

public class RestAssertion {
    public static void assertionFail(final Class actualClass, final String expectedExceptionCode){
        final var actualExceptionCode = actualClass.getSimpleName();

        if(actualExceptionCode.equals(expectedExceptionCode)){
            Assertions.assertTrue(true);

        } else {
            Assertions.fail();
        }

    }
}
