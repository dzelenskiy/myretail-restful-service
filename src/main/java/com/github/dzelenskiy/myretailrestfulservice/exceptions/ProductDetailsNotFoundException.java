package com.github.dzelenskiy.myretailrestfulservice.exceptions;

public class ProductDetailsNotFoundException extends Exception {

    public ProductDetailsNotFoundException(String errorMessage) {

        super(errorMessage);

    }

}
