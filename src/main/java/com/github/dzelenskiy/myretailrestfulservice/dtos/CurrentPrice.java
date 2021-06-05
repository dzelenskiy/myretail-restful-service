package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DynamoDBDocument
public class CurrentPrice {

    @DynamoDBAttribute
    private BigDecimal value;

    @DynamoDBAttribute
    @JsonProperty("currency_code")
    private String currencyCode;

}
