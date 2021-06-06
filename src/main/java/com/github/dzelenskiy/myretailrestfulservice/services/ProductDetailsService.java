package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.dtos.ProductDetails;
import com.github.dzelenskiy.myretailrestfulservice.exceptions.ProductDetailsNotFoundException;

public interface ProductDetailsService {

    ProductDetails getProductDetailsById(int id) throws ProductDetailsNotFoundException;

}
