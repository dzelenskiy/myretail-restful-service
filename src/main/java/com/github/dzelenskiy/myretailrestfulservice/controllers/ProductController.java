package com.github.dzelenskiy.myretailrestfulservice.controllers;

import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import com.github.dzelenskiy.myretailrestfulservice.facades.ProductPriceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductPriceFacade productPriceFacade;

    @GetMapping(path = "v1/products/{id}", produces = "application/json")
    public Product getProductById(@PathVariable int id) {
        return productPriceFacade.getProductWithCurrentPriceById(id);
    }

}
