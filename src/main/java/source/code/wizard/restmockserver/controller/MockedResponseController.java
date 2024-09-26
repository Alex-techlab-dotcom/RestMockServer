package source.code.wizard.restmockserver.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import source.code.wizard.restmockserver.model.dto.MockApiConfigDto;
import source.code.wizard.restmockserver.service.MockedResponseService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MockedResponseController {

    private final MockedResponseService mockedResponseService;
    @RequestMapping(path = "/mock/**")
    public ResponseEntity<Object> respondToRequest(final HttpServletRequest httpServletRequest) throws IOException {
        final MockApiConfigDto mockedResponse = mockedResponseService.produceMockedResponse(httpServletRequest);
        return ResponseEntity
                .status(mockedResponse.statusCode())
                .body(mockedResponse.response());
    }
}
