package com.works.financeservice.controller;

import com.works.financeservice.mappingservice.ExchangeMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mali.sahin
 * @since 7/5/19.
 */
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

  @Autowired
  private ExchangeMappingService exchangeMappingService;

  @GetMapping(path = "/{exchangeId}")
  public ResponseEntity<Double> getExchangeValueByCurrencyUnit(@PathVariable("exchangeId") Long exchangeId) {
    return  new ResponseEntity<>(exchangeMappingService.getExchangeValueByCurrencyUnit(exchangeId), HttpStatus.OK);
  }


}
