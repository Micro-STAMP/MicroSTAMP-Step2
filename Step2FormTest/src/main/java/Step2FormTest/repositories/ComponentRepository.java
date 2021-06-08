package Step2FormTest.repositories;

import Step2FormTest.models.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long>{

    @Query("SELECT c FROM Component c WHERE c.father = null")
    List<Component> findComponentsWithoutFather();
/*
    @Query("SELECT c FROM Component c WHERE c.control_structure = id") control_structure nao eh atributo de Component
    List<Component> findComponentsByControlStructureId(long id);
*/
    @Query(value = "SELECT * FROM component c WHERE c.control_structure_id = ?1", nativeQuery = true)
    List<Component> findComponentsByControlStructureId(long id);

    @Query(value = "SELECT dtype FROM component c WHERE c.id = ?1", nativeQuery = true)
    String findComponentType(long id);
}
