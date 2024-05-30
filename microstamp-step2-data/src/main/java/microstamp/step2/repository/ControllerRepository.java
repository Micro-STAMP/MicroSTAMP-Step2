package microstamp.step2.repository;

import microstamp.step2.data.Controller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControllerRepository extends JpaRepository<microstamp.step2.data.Controller, Long> {

    @Query(value = "SELECT * FROM components c WHERE c.control_structure_id = ?1 AND dtype = 'Controller'", nativeQuery = true)
    List<Controller> findControllersByControlStructureId(long id);

}
