package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;

public interface ProductService {

    Product getProductById(int id);

}
