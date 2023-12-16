package com.eversmile.eve.app.payload.request.finance;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BudgetItemRequest {

    private BigDecimal expectedAmount;
    private BigDecimal amount;
    private String type;
    private String itemName;
    private String expectedDate;
    private String actualDate;
    private boolean isActive;
}
