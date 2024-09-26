package source.code.wizard.restmockserver.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "MOCK_API_CONFIGURATION_ENTITY")
@AllArgsConstructor
@NoArgsConstructor
public class MockApiConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer statusCode;
    private String method;
    private String requestPathUrl;
    private String response;
}
