package com.eversmile.eve.app.web.payload.response.finance;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class BudgetResponse {

    protected String uuid;
    protected boolean isActive;
    private BigDecimal expectedAmount;
    private BigDecimal actualAmount;
    private String budgetName;
    private String budgetComment;
    private LocalDateTime beginsAt;
    private LocalDateTime endsAt;
    private String budgetType;
    private boolean isSuccess;
    private boolean done;
}
