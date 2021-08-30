package Step2FormTest.repositories;

import Step2FormTest.models.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ComponentRepository extends JpaRepository<Component, Long>{

    @Query(value = "SELECT * FROM component c WHERE c.control_structure_id = ?1", nativeQuery = true)
    List<Component> findComponentsByControlStructureId(long id);

    @Query(value = "SELECT * FROM component c WHERE c.father_id = ?1", nativeQuery = true)
    List<Component> findComponentsChildren(long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE component SET dtype = ?2 WHERE id = ?1", nativeQuery = true)
    void updateComponentType(long id, String type);
}
