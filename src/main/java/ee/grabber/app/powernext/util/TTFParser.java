package ee.grabber.app.powernext.util;

import ee.grabber.app.powernext.domain.TTF;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TTFParser {

  public ArrayList<TTF> parseArrayListOfStringArrayListsToTTFArrayList(
      ArrayList<ArrayList<String>> list) {
    return (ArrayList<TTF>) list.stream()
        .map(this::parseArrayListToTTF)
        .collect(Collectors.collectingAndThen(Collectors.toList(), l -> {
          Collections.reverse(l);
          return l;
        }));

  }

  private TTF parseArrayListToTTF(ArrayList<String> list) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter dateTimeFormatter = dtf.withLocale(Locale.ENGLISH);
    return TTF.builder()
        .date(LocalDate.parse(list.get(0), dateTimeFormatter))
        .monthPlusOne(Double.parseDouble(list.get(1)))
        .monthPlusTwo(Double.parseDouble(list.get(2)))
        .monthPlusThree(Double.parseDouble(list.get(3)))
        .monthPlusFour(Double.parseDouble(list.get(4)))
        .monthPlusFive(Double.parseDouble(list.get(5)))
        .quarterPlusOne(Double.parseDouble(list.get(6)))
        .quarterPlusTwo(Double.parseDouble(list.get(7)))
        .quarterPlusThree(Double.parseDouble(list.get(8)))
        .quarterPlusFour(Double.parseDouble(list.get(9)))
        .build();
  }

}
