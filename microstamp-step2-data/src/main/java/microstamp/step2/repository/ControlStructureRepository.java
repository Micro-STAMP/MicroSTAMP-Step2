package microstamp.step2.repository;

import microstamp.step2.data.ControlStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlStructureRepository extends JpaRepository<ControlStructure, Long> {

    @Query(value = "SELECT * FROM control_structures c WHERE c.user_id = ?1", nativeQuery = true)
    List<ControlStructure> findByUserId(long id);

    @Query(value = "SELECT * FROM control_structures c WHERE c.user_id = 3", nativeQuery = true)
    List<ControlStructure> findControlStructuresForGuests();
}
