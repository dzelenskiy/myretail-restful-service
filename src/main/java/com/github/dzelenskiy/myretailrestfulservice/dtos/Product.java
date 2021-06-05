package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    private int id;
    private String name;

    @JsonProperty("current_price")
    private CurrentPrice currentPrice;

}
