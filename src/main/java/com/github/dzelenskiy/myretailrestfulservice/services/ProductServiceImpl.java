package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import org.springframework.web.client.RestTemplate;

public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(int id) {

        RestTemplate restTemplate = new RestTemplate();

        return null;
    }

}
