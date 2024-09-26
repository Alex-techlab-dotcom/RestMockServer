package source.code.wizard.restmockserver.service;

import jakarta.servlet.http.HttpServletRequest;
import source.code.wizard.restmockserver.model.dto.MockApiConfigDto;

import java.io.IOException;


public interface MockedResponseService {
    MockApiConfigDto produceMockedResponse(final HttpServletRequest httpServletRequest) throws IOException;
}
