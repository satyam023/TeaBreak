package org.java.teabreak.client;

import org.java.teabreak.model.TeaBreak;
import org.java.teabreak.wrapper.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Component
public class TeaBreakClient {
    private static final String RESOURCE_PATH = "/api/v1/tea-breaks";
    private static final ParameterizedTypeReference<ApiResponse<List<TeaBreak>>> TEA_BREAK_LIST =
            new ParameterizedTypeReference<>() {};
    private static final ParameterizedTypeReference<ApiResponse<TeaBreak>> TEA_BREAK =
            new ParameterizedTypeReference<>() {};

    private final RestClient restClient;

    public TeaBreakClient(RestClient.Builder builder, @Value("${teabreak.client.base-url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public List<TeaBreak> findAll() {
        ApiResponse<List<TeaBreak>> response = restClient.get()
                .uri(RESOURCE_PATH)
                .retrieve()
                .body(TEA_BREAK_LIST);
        return response == null ? List.of() : response.data();
    }

    public TeaBreak findById(UUID id) {
        ApiResponse<TeaBreak> response = restClient.get()
                .uri(RESOURCE_PATH + "/{id}", id)
                .retrieve()
                .body(TEA_BREAK);
        return response == null ? null : response.data();
    }
}
