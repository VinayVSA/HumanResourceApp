package com.example.hra.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TotalCommissionDTO {
    private BigDecimal departmentId;
    private BigDecimal sum;
}
