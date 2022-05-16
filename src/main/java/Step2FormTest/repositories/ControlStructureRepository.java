package Step2FormTest.repositories;

import Step2FormTest.models.Component;
import Step2FormTest.models.ControlStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlStructureRepository extends JpaRepository<ControlStructure, Long> {

    @Query(value = "SELECT * FROM control_structure c WHERE c.user_id = ?1", nativeQuery = true)
    List<ControlStructure> findControlStructuresByUserId(long id);

    @Query(value = "SELECT * FROM control_structure c WHERE c.user_id = 3", nativeQuery = true)
    List<ControlStructure> findControlStructuresForGuests();
}
