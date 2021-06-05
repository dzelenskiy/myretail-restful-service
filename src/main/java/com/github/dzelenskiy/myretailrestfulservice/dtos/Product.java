package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DynamoDBTable(tableName = "Product")
public class Product {

    @DynamoDBHashKey
    private int id;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    @JsonProperty("current_price")
    private CurrentPrice currentPrice;

}
