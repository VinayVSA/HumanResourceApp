package com.example.hra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TotalCommissionDTO {
    private BigDecimal departmentId;
    private BigDecimal sum;
}
