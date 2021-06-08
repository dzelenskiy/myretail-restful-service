package com.github.dzelenskiy.myretailrestfulservice.loaders;

import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;
import com.github.dzelenskiy.myretailrestfulservice.enums.CurrencyCode;
import com.github.dzelenskiy.myretailrestfulservice.repos.CurrentPriceMongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitialDataLoader {

    @Autowired
    private CurrentPriceMongoRepo currentPriceMongoRepo;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        currentPriceMongoRepo.deleteAll();

        List<CurrentPrice> currentPriceList = new ArrayList<CurrentPrice>();

        CurrentPrice currentPriceOne =
                new CurrentPrice(13860428, new BigDecimal("7.99"), CurrencyCode.USD.toString());

        CurrentPrice currentPriceTwo =
                new CurrentPrice(54456119, new BigDecimal("3.29"), CurrencyCode.USD.toString());

        CurrentPrice currentPriceThree =
                new CurrentPrice(13264003, new BigDecimal("5.99"), CurrencyCode.USD.toString());

        CurrentPrice currentPriceFour =
                new CurrentPrice(12954218, new BigDecimal("0.99"), CurrencyCode.USD.toString());

        currentPriceList.add(currentPriceOne);
        currentPriceList.add(currentPriceTwo);
        currentPriceList.add(currentPriceThree);
        currentPriceList.add(currentPriceFour);

        currentPriceMongoRepo.saveAll(currentPriceList);

    }


}
