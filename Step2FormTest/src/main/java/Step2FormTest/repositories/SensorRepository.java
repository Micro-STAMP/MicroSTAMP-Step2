package Step2FormTest.repositories;

import Step2FormTest.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query(value = "SELECT * FROM component c WHERE c.control_structure_id = ?1 AND dtype = 'Sensor'", nativeQuery = true)
    List<Sensor> findSensorsByControlStructureId(long id);

}
