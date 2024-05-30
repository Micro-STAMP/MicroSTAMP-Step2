package microstamp.step2.repository;

import microstamp.step2.data.Responsibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibilitiesRepository extends JpaRepository<Responsibility, Long> {
}
