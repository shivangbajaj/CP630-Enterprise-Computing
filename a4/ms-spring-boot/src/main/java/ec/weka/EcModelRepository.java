package ec.weka;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcModelRepository extends JpaRepository<EcModel, Long> {
    EcModel findByName(String name);
}
