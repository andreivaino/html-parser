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
public class PowernextApplication implements CommandLineRunner {

  private final GrabberService grabberService;
  private final TTFService ttfService;
  private final TTFParser ttfParser;

  public PowernextApplication(GrabberService grabberService,
      TTFService ttfService, TTFParser ttfParser) {
    this.grabberService = grabberService;
    this.ttfService = ttfService;
    this.ttfParser = ttfParser;
  }

  public static void main(String[] args) {
    SpringApplication.run(PowernextApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    while (true) {
      log.info(String.format("Refreshed: %s", LocalDateTime.now().toString()));
      ArrayList<ArrayList<String>> data = grabberService.getData();
      ArrayList<TTF> ttfs = ttfParser.parseArrayListOfStringArrayListsToTTFArrayList(data);
      ttfService.saveList(ttfs);
      try {
        Thread.sleep(20000);
      }catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }
}
