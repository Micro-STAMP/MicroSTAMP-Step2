package Step2FormTest.repositories;

import Step2FormTest.models.Controller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControllerRepository extends JpaRepository<Step2FormTest.models.Controller, Long> {

    @Query(value = "SELECT * FROM component c WHERE c.control_structure_id = ?1 AND dtype = 'Controller'", nativeQuery = true)
    List<Controller> findControllersByControlStructureId(long id);

}
