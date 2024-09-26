package source.code.wizard.restmockserver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import source.code.wizard.restmockserver.model.dto.MockApiConfigDto;
import source.code.wizard.restmockserver.model.entity.MockApiConfigurationEntity;
import source.code.wizard.restmockserver.repository.MockApiConfigRepository;
import source.code.wizard.restmockserver.service.MockApiConfigCrudService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MockApiConfigServiceImpl implements MockApiConfigCrudService {
    private final MockApiConfigRepository mockApiConfigRepository;
    private final ObjectMapper jsonMapper;

    @Override
    public List<MockApiConfigDto> getAllMockApiConfigByMethod(final String method) {
        return Optional.ofNullable(method)
                .map(m -> mockApiConfigRepository.findAllByMethod(m).stream()
                        .map(this::entityToDTO)
                        .toList())
                .orElse(Collections.emptyList());
    }

    @Override
    public List<MockApiConfigDto> getAllMockApiConfigByStatusCode(final Integer statusCode) {
        return Optional.ofNullable(statusCode)
                .map(r -> mockApiConfigRepository.findAllByStatusCode(r).stream()
                        .map(this::entityToDTO)
                        .toList())
                .orElse(Collections.emptyList());
    }

    @Override
    public List<MockApiConfigDto> getAllMockApiConfig() {
        return mockApiConfigRepository.findAll().stream()
                .map(this::entityToDTO)
                .toList();
    }

    @Override
    public void deleteByID(final Long id) {
        Optional.ofNullable(id)
                .orElseThrow(()->new RuntimeException("ID must not be null"));
        mockApiConfigRepository.deleteById(id);
    }

    @Override
    public MockApiConfigDto createAndSaveMockApiConfig(final MockApiConfigDto mockApiConfigDto) throws IOException {
        final MockApiConfigurationEntity mockApiConfigurationEntity = MockApiConfigurationEntity.builder()
                .method(mockApiConfigDto.method())
                .requestPathUrl(mockApiConfigDto.requestUrl())
                .statusCode(mockApiConfigDto.statusCode())
                .response(jsonMapper.writeValueAsString(mockApiConfigDto.response()))
                .build();
        mockApiConfigRepository.save(mockApiConfigurationEntity);
        return mockApiConfigDto;
    }
    private MockApiConfigDto entityToDTO(MockApiConfigurationEntity mockApiConfigurationEntity) {
        try {
            return MockApiConfigDto.builder()
                    .response(jsonMapper.readValue(mockApiConfigurationEntity.getResponse(), Object.class))
                    .statusCode(mockApiConfigurationEntity.getStatusCode())
                    .method(mockApiConfigurationEntity.getMethod())
                    .requestUrl(mockApiConfigurationEntity.getRequestPathUrl())
                    .id(mockApiConfigurationEntity.getId())
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
