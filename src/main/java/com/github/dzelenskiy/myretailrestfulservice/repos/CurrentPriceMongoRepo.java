package com.github.dzelenskiy.myretailrestfulservice.repos;

import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentPriceMongoRepo extends MongoRepository<CurrentPrice, Integer> {

    List<CurrentPrice> findByProductId(int productId);
    List<CurrentPrice> findAll();

}
