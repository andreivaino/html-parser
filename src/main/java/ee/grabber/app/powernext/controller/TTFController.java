package ee.grabber.app.powernext.controller;

import ee.grabber.app.powernext.domain.TTF;
import ee.grabber.app.powernext.service.GrabberService;
import ee.grabber.app.powernext.service.TTFService;
import ee.grabber.app.powernext.util.TTFParser;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
@RequestMapping("")
public class TTFController {

  private final GrabberService grabberService;
  private final TTFService ttfService;
  private final TTFParser ttfParser;

  public TTFController(GrabberService grabberService,
      TTFService ttfService, TTFParser ttfParser) {
    this.grabberService = grabberService;
    this.ttfService = ttfService;
    this.ttfParser = ttfParser;
  }

  @GetMapping("/")
  public ModelAndView getAllTTF()
      throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
    String refreshed = LocalDateTime.now().toString();
    ArrayList<ArrayList<String>> data = grabberService.getDataTTF();
    ArrayList<TTF> ttfs = ttfParser.parseArrayListOfStringArrayListsToTTFArrayList(data);
    ttfService.saveList(ttfs);
    ModelAndView index = new ModelAndView("index");
    log.info(String.format("Refreshed: %s", refreshed));
    index.addObject("refresh", refreshed);
    List<TTF> ttfListFromBase = ttfService.getList();
    Collections.reverse(ttfListFromBase);
    index.addObject("list", ttfListFromBase);
    return index;
  }

}
