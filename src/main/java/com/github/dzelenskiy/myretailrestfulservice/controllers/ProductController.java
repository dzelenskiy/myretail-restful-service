package com.github.dzelenskiy.myretailrestfulservice.controllers;

import com.github.dzelenskiy.myretailrestfulservice.dtos.ErrorResponseBody;
import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import com.github.dzelenskiy.myretailrestfulservice.exceptions.ProductDetailsNotFoundException;
import com.github.dzelenskiy.myretailrestfulservice.exceptions.ProductIDMismatchException;
import com.github.dzelenskiy.myretailrestfulservice.facades.ProductPriceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductPriceFacade productPriceFacade;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable int id) throws Exception {

        return productPriceFacade.getProductWithCurrentPriceById(id);

    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateProductPrice(@PathVariable int id, @Valid @RequestBody Product product) throws Exception {

        if(id != product.getId())
            throw new ProductIDMismatchException(
                    "path id " + id + " and request body id " + product.getId() + " do not match");
        productPriceFacade.updateProductCurrentPrice(product);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseBody handleValidationExceptions(MethodArgumentNotValidException e) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody();
        Set<String> errors = new TreeSet<String>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(error.getDefaultMessage());
        });

        errorResponseBody.setErrors(errors);

        return errorResponseBody;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductIDMismatchException.class)
    public ErrorResponseBody handleProductIDMismatchException(ProductIDMismatchException e) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody();
        Set<String> errors = new TreeSet<String>();

        errors.add(e.getMessage());

        errorResponseBody.setErrors(errors);

        return errorResponseBody;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductDetailsNotFoundException.class)
    public ErrorResponseBody handleProductDetailsNotFoundException(ProductDetailsNotFoundException e) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody();
        Set<String> errors = new TreeSet<String>();

        errors.add(e.getMessage());

        errorResponseBody.setErrors(errors);

        return errorResponseBody;
    }

}
