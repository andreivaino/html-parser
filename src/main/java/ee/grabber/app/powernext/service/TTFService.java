package ee.grabber.app.powernext.service;

import ee.grabber.app.powernext.domain.TTF;
import ee.grabber.app.powernext.repository.TTFRepository;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class TTFService {

  private final TTFRepository ttfRepository;

  public TTFService(TTFRepository ttfRepository) {
    this.ttfRepository = ttfRepository;
  }

  public void saveList(ArrayList<TTF> ttfList) {
    ttfList.stream()
        .filter(ttf -> !ttfRepository.existsById(ttf.getDate()))
        .forEach(ttfRepository::saveAndFlush);
  }

}
