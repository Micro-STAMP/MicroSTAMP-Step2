package Step2FormTest.repositories;

import Step2FormTest.models.Actuator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActuatorRepository extends JpaRepository<Actuator, Long> {

    @Query(value = "SELECT * FROM component c WHERE c.control_structure_id = ?1 AND dtype = 'Actuator'", nativeQuery = true)
    List<Actuator> findActuatorsByControlStructureId(long id);

}
