package ee.grabber.app.powernext;

import ee.grabber.app.powernext.domain.TTF;
import ee.grabber.app.powernext.service.GrabberService;
import ee.grabber.app.powernext.service.TTFService;
import ee.grabber.app.powernext.util.TTFParser;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PowernextApplication {

  public static void main(String[] args) {
    SpringApplication.run(PowernextApplication.class, args);
  }

}
