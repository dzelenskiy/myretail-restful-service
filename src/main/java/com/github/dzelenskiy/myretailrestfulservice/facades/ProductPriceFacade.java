package com.github.dzelenskiy.myretailrestfulservice.facades;

import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;

public interface ProductPriceFacade {

    Product getProductWithCurrentPriceById(int id);

}
