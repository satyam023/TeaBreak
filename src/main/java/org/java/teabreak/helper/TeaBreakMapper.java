package org.java.teabreak.helper;

import org.java.teabreak.model.TeaBreak;
import org.java.teabreak.model.TeaBreakStatus;
import org.java.teabreak.wrapper.CreateTeaBreakRequest;
import org.java.teabreak.wrapper.UpdateTeaBreakRequest;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class TeaBreakMapper {
    public TeaBreak create(CreateTeaBreakRequest request) {
        Instant now = Instant.now();
        return new TeaBreak(
                UUID.randomUUID(),
                request.title().trim(),
                normalizeDescription(request.description()),
                request.status() == null ? TeaBreakStatus.PLANNED : request.status(),
                now,
                now
        );
    }

    public TeaBreak update(TeaBreak existing, UpdateTeaBreakRequest request) {
        return new TeaBreak(
                existing.id(),
                request.title().trim(),
                normalizeDescription(request.description()),
                request.status(),
                existing.createdAt(),
                Instant.now()
        );
    }

    private String normalizeDescription(String description) {
        return description == null || description.isBlank() ? null : description.trim();
    }
}
