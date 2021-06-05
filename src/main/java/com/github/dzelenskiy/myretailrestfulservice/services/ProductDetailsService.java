package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.dtos.ProductDetails;

public interface ProductDetailsService {

    ProductDetails getProductDetailsById(int id);

}
