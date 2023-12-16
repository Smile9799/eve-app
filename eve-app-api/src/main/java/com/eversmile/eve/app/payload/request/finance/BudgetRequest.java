package com.eversmile.eve.app.payload.request.finance;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BudgetRequest {
    private String budgetName;
    private String budgetDescription;
    private BigDecimal totalDebits;
    private BigDecimal totalCredits;
    private BigDecimal expectedTotalCredits;
    private BigDecimal expectedTotalDebits;
    private String budgetType;
    private String startDate;
    private String endDate;
    private boolean isActive;
}
