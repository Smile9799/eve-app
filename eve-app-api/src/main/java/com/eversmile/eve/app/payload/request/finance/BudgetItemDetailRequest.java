package com.eversmile.eve.app.payload.request.finance;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BudgetItemDetailRequest {
    private BigDecimal amount;
    private BigDecimal expectedAmount;
    private String itemName;
    private String expectedDate;
    private String actualDate;
    private boolean isActive;
}
