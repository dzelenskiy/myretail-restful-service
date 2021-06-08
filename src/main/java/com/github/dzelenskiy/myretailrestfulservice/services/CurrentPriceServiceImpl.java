package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;
import com.github.dzelenskiy.myretailrestfulservice.enums.CurrencyCode;
import com.github.dzelenskiy.myretailrestfulservice.repos.CurrentPriceMongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CurrentPriceServiceImpl implements CurrentPriceService {

    private static final String DEFAULT_PRICE = "9.99";
    private static final String DEFAULT_CURRENCY_CODE = CurrencyCode.USD.toString();

    @Autowired
    private CurrentPriceMongoRepo currentPriceMongoRepo;

    @Override
    public CurrentPrice getCurrentPriceByProductId(int productId) {
        List<CurrentPrice> currentPriceList = currentPriceMongoRepo.findByProductId(productId);
        if(currentPriceList.isEmpty()) {
            CurrentPrice currentPrice =
                    new CurrentPrice(
                            productId, new BigDecimal(DEFAULT_PRICE), DEFAULT_CURRENCY_CODE);
            currentPriceMongoRepo.save(currentPrice);
            return currentPrice;
        } else {
            return currentPriceList.get(0);
        }
    }

    @Override
    public void updateCurrentPrice(CurrentPrice currentPrice) {

        // make sure formatted to 2 decimal places before saving
        currentPrice.setValue(currentPrice.getValue().setScale(2,BigDecimal.ROUND_HALF_UP));

        // at this point currency code has been validated but must make sure it's in uppercase
        currentPrice.setCurrencyCode(currentPrice.getCurrencyCode().toUpperCase());

        currentPriceMongoRepo.save(currentPrice);
    }


}
