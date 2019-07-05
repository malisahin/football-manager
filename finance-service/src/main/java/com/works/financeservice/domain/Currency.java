package com.works.financeservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mali.sahin
 * @since 7/5/19.
 */
@Getter
@Setter
@AllArgsConstructor
public class Currency {
  private Long currencyId;
  private Double exchangeRatioByDollar;
  private String name;

}
