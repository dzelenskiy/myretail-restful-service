package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DynamoDBTable(tableName = "CurrentPrice")
public class CurrentPrice {

    @DynamoDBHashKey
    @JsonIgnore
    private int productId;

    @DynamoDBAttribute
    private BigDecimal value;

    @DynamoDBAttribute
    @JsonProperty("currency_code")
    private String currencyCode;

}
