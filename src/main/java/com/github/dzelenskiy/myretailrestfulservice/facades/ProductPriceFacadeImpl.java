package com.github.dzelenskiy.myretailrestfulservice.facades;

import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;
import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import com.github.dzelenskiy.myretailrestfulservice.dtos.ProductDetails;
import com.github.dzelenskiy.myretailrestfulservice.services.CurrentPriceService;
import com.github.dzelenskiy.myretailrestfulservice.services.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceFacadeImpl implements ProductPriceFacade {

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private CurrentPriceService currentPriceService;

    @Override
    public Product getProductWithCurrentPriceById(int id) {

        ProductDetails productDetails = productDetailsService.getProductDetailsById(id);
        CurrentPrice currentPrice = currentPriceService.getCurrentPriceByProductId(id);

        Product product = new Product();
        product.setId(id);
        product.setName(productDetails.getItem().getProductDescription().getTitle());
        product.setCurrentPrice(currentPrice);

        return product;
    }
}
