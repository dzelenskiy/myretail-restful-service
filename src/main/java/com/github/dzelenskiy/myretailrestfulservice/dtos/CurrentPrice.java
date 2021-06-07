package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
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

    @DecimalMax("99999.99")
    @DecimalMin("0.01")
    @Digits(integer=5, fraction=2)
    @DynamoDBAttribute
    private BigDecimal value;

    @NotBlank
    @DynamoDBAttribute
    @JsonProperty("currency_code")
    private String currencyCode;

}
