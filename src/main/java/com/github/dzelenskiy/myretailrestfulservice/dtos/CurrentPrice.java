package com.github.dzelenskiy.myretailrestfulservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dzelenskiy.myretailrestfulservice.annotations.ValidCurrencyCode;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document
public class CurrentPrice {

    @JsonIgnore
    @Id
    private int productId;

    @NotNull(message = "value cannot be null")
    @Digits(
            integer = 5,
            fraction = 2,
            message = "value must have no more than 5 integral digits and no more than 2 fractional digits")
    @DecimalMax(value = "99999.99", message = "value may not exceed 99999.99")
    @DecimalMin(value = "0.01", message = "value may not be less than 0.01")
    private BigDecimal value;

    @NotBlank(message = "currency_code must not be blank")
    @ValidCurrencyCode(message = "currency_code must be a valid ISO Currency Code")
    @JsonProperty("currency_code")
    private String currencyCode;

}
