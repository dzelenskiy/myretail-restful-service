package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;

public interface CurrentPriceService {

    CurrentPrice getCurrentPriceByProductId(int productId);
    void updateCurrentPrice(CurrentPrice currentPrice);

}
