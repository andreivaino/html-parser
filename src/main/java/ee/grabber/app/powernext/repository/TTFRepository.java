package ee.grabber.app.powernext.repository;

import ee.grabber.app.powernext.domain.TTF;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TTFRepository extends JpaRepository<TTF, LocalDate> {

}
