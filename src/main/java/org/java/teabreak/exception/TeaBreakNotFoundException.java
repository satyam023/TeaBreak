package org.java.teabreak.exception;

import java.util.UUID;

public class TeaBreakNotFoundException extends RuntimeException {
    public TeaBreakNotFoundException(UUID id) {
        super("Tea break not found: " + id);
    }
}
