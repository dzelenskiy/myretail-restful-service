package com.github.dzelenskiy.myretailrestfulservice.dtos;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ErrorResponseBody {

    Set<String> errors;

}
