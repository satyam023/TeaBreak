package org.java.teabreak.wrapper;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.java.teabreak.model.TeaBreakStatus;

public record UpdateTeaBreakRequest(
        @NotBlank @Size(max = 100) String title,
        @Size(max = 1000) String description,
        @NotNull TeaBreakStatus status
) {
}
