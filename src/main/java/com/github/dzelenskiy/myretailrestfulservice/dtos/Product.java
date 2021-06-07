package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    private int id;
    private String name;

    @JsonProperty("current_price")
    @NotNull(message = "current_price cannot be null")
    @Valid
    private CurrentPrice currentPrice;

}
