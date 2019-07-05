package com.works.financeservice.mappingservice;

import com.works.financeservice.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mali.sahin
 * @since 7/5/19.
 */
@Service
public class ExchangeMappingService {

  @Autowired
  private ExchangeService exchangeService;

  public Double getExchangeValueByCurrencyUnit(Long exchangeId) {
    return exchangeService.getExchangeValueByCurrencyUnit(exchangeId);
  }
}
