package org.java.teabreak.controller;

import jakarta.validation.Valid;
import org.java.teabreak.model.TeaBreak;
import org.java.teabreak.service.TeaBreakService;
import org.java.teabreak.wrapper.ApiResponse;
import org.java.teabreak.wrapper.CreateTeaBreakRequest;
import org.java.teabreak.wrapper.UpdateTeaBreakRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tea-breaks")
public class TeaBreakController {
    private final TeaBreakService teaBreakService;

    public TeaBreakController(TeaBreakService teaBreakService) {
        this.teaBreakService = teaBreakService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TeaBreak>> create(@Valid @RequestBody CreateTeaBreakRequest request) {
        TeaBreak created = teaBreakService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location)
                .body(ApiResponse.success(created, "Tea break created"));
    }

    @GetMapping
    public ApiResponse<List<TeaBreak>> findAll() {
        return ApiResponse.success(teaBreakService.findAll(), "Tea breaks retrieved");
    }

    @GetMapping("/{id}")
    public ApiResponse<TeaBreak> findById(@PathVariable UUID id) {
        return ApiResponse.success(teaBreakService.findById(id), "Tea break retrieved");
    }

    @PutMapping("/{id}")
    public ApiResponse<TeaBreak> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTeaBreakRequest request
    ) {
        return ApiResponse.success(teaBreakService.update(id, request), "Tea break updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        teaBreakService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
