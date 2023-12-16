package com.eversmile.eve.app.payload.response.finance;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BudgetItemResponse {

    private BigDecimal expectedAmount;
    private BigDecimal amount;
    private String type;
    private String itemName;
    private LocalDateTime expectedDate;
    private LocalDateTime actualDate;
    private boolean isActive;
    private String id;
    private BudgetResponse budget;
}
