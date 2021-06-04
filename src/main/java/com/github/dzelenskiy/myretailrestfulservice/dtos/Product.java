package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DynamoDBTable(tableName = "Product")
public class Product {

    @DynamoDBHashKey(attributeName="id")
    private int id;

    @DynamoDBAttribute(attributeName="name")
    private String name;

}
