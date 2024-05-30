package microstamp.step2.repository;

import microstamp.step2.data.Variable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariableRepository extends JpaRepository<Variable, Long> {

    @Query(value = "SELECT * FROM variable v WHERE v.component_id = ?1", nativeQuery = true)
    List<Variable> findVariablesByComponentId(long id);

    @Query(value = "SELECT * FROM component as c JOIN variable as v ON c.id = v.component_id WHERE control_structure_id = ?1", nativeQuery = true)
    List<Variable> findVariablesByControlStructureId(long id);

}
