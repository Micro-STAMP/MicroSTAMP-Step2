package Step2FormTest.repositories;

import Step2FormTest.models.Responsibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibilitiesRepository extends JpaRepository<Responsibility, Long> {
}
