package microstamp.step2.repository;

import microstamp.step2.data.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

    @Query(value = "SELECT * FROM labels l WHERE l.connection_id = ?1", nativeQuery = true)
    List<Label> findByConnectionId(long id);

}
