package microstamp.step2.repository;

import microstamp.step2.data.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {

    @Query(value = "SELECT * FROM components c WHERE c.control_structure_id = ?1", nativeQuery = true)
    List<Component> findByControlStructureId(long id);

    @Query(value = "SELECT * FROM components c WHERE c.father_id = ?1", nativeQuery = true)
    List<Component> findChildrenByComponentId(long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE components SET dtype = ?2 WHERE id = ?1", nativeQuery = true)
    void updateType(long id, String type);
}
