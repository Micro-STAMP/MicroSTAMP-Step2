package Step2FormTest.repositories;

import Step2FormTest.models.ControlledProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlledProcessRepository extends JpaRepository<ControlledProcess, Long> {

    @Query(value = "SELECT * FROM component c WHERE c.control_structure_id = ?1 AND dtype = 'ControlledProcess'", nativeQuery = true)
    List<ControlledProcess> findControlledProcessesByControlStructureId(long id);

}
