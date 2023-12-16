package com.eversmile.eve.app.payload.response.finance;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BudgetItemDetailResponse {

    private String id;
    private BigDecimal amount;
    private BigDecimal expectedAmount;
    private String itemName;
    private LocalDateTime expectedDate;
    private LocalDateTime actualDate;

    private BudgetItemResponse budgetItem;
}
