package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;
import com.github.dzelenskiy.myretailrestfulservice.repos.dynamodb.CurrentPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CurrentPriceServiceImpl implements CurrentPriceService {

    private static final String DEFAULT_PRICE = "9.99";
    private static final String DEFAULT_CURRENCY_CODE = "USD";

    @Autowired
    CurrentPriceRepo currentPriceRepo;

    @Override
    public CurrentPrice getCurrentPriceByProductId(int productId) {
        List<CurrentPrice> currentPriceList = currentPriceRepo.findByProductId(productId);
        if(currentPriceList.isEmpty()) {
            CurrentPrice currentPrice =
                    new CurrentPrice(
                            productId, new BigDecimal(DEFAULT_PRICE), DEFAULT_CURRENCY_CODE);
            currentPriceRepo.save(currentPrice);
            return currentPrice;
        } else {
            return currentPriceList.get(0);
        }
    }
}