package com.github.dzelenskiy.myretailrestfulservice.repos.dynamodb;

import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {

    List<Product> findById(int id);

}
