package source.code.wizard.restmockserver.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import source.code.wizard.restmockserver.model.dto.MockApiConfigDto;
import source.code.wizard.restmockserver.model.entity.MockApiConfigurationEntity;
import source.code.wizard.restmockserver.repository.MockApiConfigRepository;
import source.code.wizard.restmockserver.service.MockedResponseService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MockedResponseServiceImpl implements MockedResponseService {
    private final MockApiConfigRepository mockApiConfigRepository;
    private final ObjectMapper jsonMapper;

    @Override
    public MockApiConfigDto produceMockedResponse(HttpServletRequest httpServletRequest) throws IOException {
        final String requestURL = removeHostFromUrl(String.valueOf(httpServletRequest.getRequestURL()));
        final String requestMethod = httpServletRequest.getMethod();
        final MockApiConfigurationEntity mockApiConfigurationEntity = mockApiConfigRepository.findByRequestPathUrlAndMethod(requestURL.toString(), requestMethod)
                .orElseThrow(() -> new RuntimeException("Not found."));
        return MockApiConfigDto.builder()
                .response(jsonMapper.readValue(mockApiConfigurationEntity.getResponse(), Object.class))
                .statusCode(mockApiConfigurationEntity.getStatusCode())
                .build();
    }

    private String removeHostFromUrl(final String OriginalUrl) {
        return OriginalUrl.replace("http://localhost:8080/mock", "");
    }

    private static String getRequestBodyAsString(HttpServletRequest httpServletRequest) throws IOException {
        try (BufferedReader reader = httpServletRequest.getReader()) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
