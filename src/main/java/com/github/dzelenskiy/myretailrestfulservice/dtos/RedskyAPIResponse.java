package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RedskyAPIResponse {

    @JsonProperty("product")
    private ProductDetails productDetails;

}
