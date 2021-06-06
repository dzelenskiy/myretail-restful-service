package com.github.dzelenskiy.myretailrestfulservice.controllers;

import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import com.github.dzelenskiy.myretailrestfulservice.exceptions.ProductDetailsNotFoundException;
import com.github.dzelenskiy.myretailrestfulservice.facades.ProductPriceFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ProductController {

    @Autowired
    private ProductPriceFacade productPriceFacade;

    @GetMapping(path = "v1/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable int id) {
        try {
            return productPriceFacade.getProductWithCurrentPriceById(id);
        } catch (ProductDetailsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product found with id of " + id + ".");
        }
    }

}
