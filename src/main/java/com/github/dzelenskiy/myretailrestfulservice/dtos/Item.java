package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Item {

    private int tcin;

    @JsonProperty("product_description")
    private ProductDescription productDescription;

}
