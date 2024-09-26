package source.code.wizard.restmockserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.code.wizard.restmockserver.model.entity.MockApiConfigurationEntity;

import java.util.Optional;

@Repository
public interface MockApiConfigRepository extends JpaRepository<MockApiConfigurationEntity, Long> {
    Optional<MockApiConfigurationEntity> findByRequestPathUrlAndMethod(final String requestPathUrl, final String method);
    Optional<MockApiConfigurationEntity> findAllByMethod (final String method);
    Optional<MockApiConfigurationEntity> findAllByStatusCode (final Integer statusCode);
}
