package org.java.teabreak.model;

import java.time.Instant;
import java.util.UUID;

public record TeaBreak(
        UUID id,
        String title,
        String description,
        TeaBreakStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
