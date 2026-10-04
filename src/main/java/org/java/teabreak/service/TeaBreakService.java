package org.java.teabreak.service;

import org.java.teabreak.exception.TeaBreakNotFoundException;
import org.java.teabreak.helper.TeaBreakMapper;
import org.java.teabreak.model.TeaBreak;
import org.java.teabreak.wrapper.CreateTeaBreakRequest;
import org.java.teabreak.wrapper.UpdateTeaBreakRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class TeaBreakService {
    private final ConcurrentMap<UUID, TeaBreak> teaBreaks = new ConcurrentHashMap<>();
    private final TeaBreakMapper mapper;

    public TeaBreakService(TeaBreakMapper mapper) {
        this.mapper = mapper;
    }

    public TeaBreak create(CreateTeaBreakRequest request) {
        TeaBreak teaBreak = mapper.create(request);
        teaBreaks.put(teaBreak.id(), teaBreak);
        return teaBreak;
    }

    public List<TeaBreak> findAll() {
        return teaBreaks.values().stream()
                .sorted(Comparator.comparing(TeaBreak::createdAt).reversed())
                .toList();
    }

    public TeaBreak findById(UUID id) {
        TeaBreak teaBreak = teaBreaks.get(id);
        if (teaBreak == null) {
            throw new TeaBreakNotFoundException(id);
        }
        return teaBreak;
    }

    public TeaBreak update(UUID id, UpdateTeaBreakRequest request) {
        return teaBreaks.compute(id, (key, existing) -> {
            if (existing == null) {
                throw new TeaBreakNotFoundException(id);
            }
            return mapper.update(existing, request);
        });
    }

    public void delete(UUID id) {
        if (teaBreaks.remove(id) == null) {
            throw new TeaBreakNotFoundException(id);
        }
    }
}
