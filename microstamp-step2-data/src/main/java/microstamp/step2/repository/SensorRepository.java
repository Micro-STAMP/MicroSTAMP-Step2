package microstamp.step2.repository;

import microstamp.step2.data.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query(value = "SELECT * FROM components c WHERE c.control_structure_id = ?1 AND dtype = 'Sensor'", nativeQuery = true)
    List<Sensor> findByControlStructureId(long id);

}
