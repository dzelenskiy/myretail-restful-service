package com.github.dzelenskiy.myretailrestfulservice.repos.dynamodb;

import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentPriceRepo extends CrudRepository<CurrentPrice, Integer> {

    List<CurrentPrice> findById(int id);

}
