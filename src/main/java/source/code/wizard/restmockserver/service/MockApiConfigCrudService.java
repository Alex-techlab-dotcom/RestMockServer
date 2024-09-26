package source.code.wizard.restmockserver.service;

import jakarta.servlet.http.HttpServletRequest;
import source.code.wizard.restmockserver.model.dto.MockApiConfigDto;

import java.io.IOException;
import java.util.List;

public interface MockApiConfigCrudService {
    MockApiConfigDto createAndSaveMockApiConfig(final MockApiConfigDto mockApiConfigDto) throws IOException;
    List<MockApiConfigDto> getAllMockApiConfigByMethod(final String method);
    List<MockApiConfigDto> getAllMockApiConfigByStatusCode(final Integer statusCode);
    List<MockApiConfigDto> getAllMockApiConfig();
    void deleteByID(final Long id);
}
