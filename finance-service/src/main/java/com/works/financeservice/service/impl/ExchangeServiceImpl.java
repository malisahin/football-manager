package com.works.financeservice.service.impl;

import com.works.financeservice.domain.Currency;
import com.works.financeservice.service.ExchangeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mali.sahin
 * @since 7/5/19.
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

  private static List<Currency> currencyList;

  static {
    currencyList = new ArrayList<>();
    currencyList.add(new Currency(1L, 1.0, "Dollar"));
    currencyList.add(new Currency(2L, 5.6, "Turkish Lira"));
    currencyList.add(new Currency(3L, 0.89, "Euro"));
  }

  @Override
  public Double getExchangeValueByCurrencyUnit(Long exchangeId) {
    return null;
  }
}
