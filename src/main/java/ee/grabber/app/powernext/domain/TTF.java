package ee.grabber.app.powernext.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TTF")
public class TTF {

  @Id
  @Column(name = "trading_day")
  private LocalDate date;

  @Column(name = "month_plus_1")
  private Double monthPlusOne;

  @Column(name = "month_plus_2")
  private Double monthPlusTwo;

  @Column(name = "month_plus_3")
  private Double monthPlusThree;

  @Column(name = "month_plus_4")
  private Double monthPlusFour;

  @Column(name = "month_plus_5")
  private Double monthPlusFive;

  @Column(name = "quarter_plus_1")
  private Double quarterPlusOne;

  @Column(name = "quarter_plus_2")
  private Double quarterPlusTwo;

  @Column(name = "quarter_plus_3")
  private Double quarterPlusThree;

  @Column(name = "quarter_plus_4")
  private Double quarterPlusFour;

}
