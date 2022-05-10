package Step2FormTest.repositories;

import Step2FormTest.models.Variable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariableRepository extends JpaRepository<Variable, Long> {

    @Query(value = "SELECT * FROM variable v WHERE v.component_id = ?1", nativeQuery = true)
    List<Variable> findVariablesByComponentId(long id);

}
