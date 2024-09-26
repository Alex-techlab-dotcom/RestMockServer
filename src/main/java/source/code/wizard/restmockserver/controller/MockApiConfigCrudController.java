package source.code.wizard.restmockserver.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source.code.wizard.restmockserver.model.dto.MockApiConfigDto;
import source.code.wizard.restmockserver.service.MockApiConfigCrudService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/crud")
@RequiredArgsConstructor
public class MockApiConfigCrudController {

    private final MockApiConfigCrudService mockApiConfigService;
    @PostMapping
    public ResponseEntity<MockApiConfigDto> save(final @RequestBody MockApiConfigDto mockApiConfigDto) throws IOException {
        return ResponseEntity.ok(mockApiConfigService.createAndSaveMockApiConfig(mockApiConfigDto));
    }
    @GetMapping("/getAllByMethod")
    public ResponseEntity<List<MockApiConfigDto>> getAllByMethod(final @RequestParam String methodType){
        return ResponseEntity.ok(mockApiConfigService.getAllMockApiConfigByMethod(methodType));
    }
    @GetMapping("/getAllByStatusCode")
    public ResponseEntity<List<MockApiConfigDto>> getAllByStatusCode(final @RequestParam Integer statusCode){
        return ResponseEntity.ok(mockApiConfigService.getAllMockApiConfigByStatusCode(statusCode));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<MockApiConfigDto>> getAllByStatusCode(){
        return ResponseEntity.ok(mockApiConfigService.getAllMockApiConfig());
    }
    @DeleteMapping
    public ResponseEntity<String> deleteByID(final @RequestParam Long id){
        mockApiConfigService.deleteByID(id);
        return ResponseEntity.ok("Deletion was successful.");
    }

}
