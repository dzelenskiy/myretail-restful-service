package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dzelenskiy.myretailrestfulservice.annotations.ValidCurrencyCode;
import lombok.*;

import javax.validation.constraints.*;
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

    @NotNull(message = "value cannot be null")
    @Digits(
            integer = 5,
            fraction = 2,
            message = "value must have no more than 5 integral digits and no more than 2 fractional digits")
    @DecimalMax(value = "99999.99", message = "value may not exceed 99999.99")
    @DecimalMin(value = "0.01", message = "value may not be less than 0.01")
    @DynamoDBAttribute
    private BigDecimal value;

    @NotBlank(message = "currency_code must not be blank")
    @ValidCurrencyCode(message = "currency_code must be a valid ISO Currency Code")
    @DynamoDBAttribute
    @JsonProperty("currency_code")
    private String currencyCode;

}
