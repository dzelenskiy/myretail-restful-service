package com.github.dzelenskiy.myretailrestfulservice.facades;

import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import com.github.dzelenskiy.myretailrestfulservice.exceptions.ProductDetailsNotFoundException;

public interface ProductPriceFacade {

    Product getProductWithCurrentPriceById(int id) throws ProductDetailsNotFoundException;
    void updateProductCurrentPrice(Product product) throws ProductDetailsNotFoundException;

}
