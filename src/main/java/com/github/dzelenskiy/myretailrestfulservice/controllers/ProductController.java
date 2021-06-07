package com.github.dzelenskiy.myretailrestfulservice.controllers;

import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import com.github.dzelenskiy.myretailrestfulservice.exceptions.ProductDetailsNotFoundException;
import com.github.dzelenskiy.myretailrestfulservice.facades.ProductPriceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductPriceFacade productPriceFacade;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable int id) {
        try {
            return productPriceFacade.getProductWithCurrentPriceById(id);
        } catch (ProductDetailsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product found with id of " + id + ".");
        }
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateProductPrice(@PathVariable int id, @Valid @RequestBody Product product) {
        try {
            if(id != product.getId())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Path ID " + id + " does not match ID " + product.getId() + " in Request Body.");
            productPriceFacade.updateProductCurrentPrice(product);
        } catch (ProductDetailsNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product found with id of " + id + ".");
        }
    }

}
