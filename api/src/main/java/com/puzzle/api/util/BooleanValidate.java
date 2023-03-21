package com.puzzle.api.util;

public enum BooleanValidate {
    TRUE(true),
    FALSE(false);

    private boolean b;

    public boolean isTrue() {
        return this.b;
    }

    BooleanValidate(boolean b) {
        this.b = b;
    }
}
