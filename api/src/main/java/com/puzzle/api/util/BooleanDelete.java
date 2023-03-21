package com.puzzle.api.util;

public enum BooleanDelete {
    TRUE(true),
    FALSE(false);

    private boolean b;

    public boolean isTrue() {
        return this.b;
    }

    BooleanDelete(boolean b) {
        this.b = b;
    }
}
