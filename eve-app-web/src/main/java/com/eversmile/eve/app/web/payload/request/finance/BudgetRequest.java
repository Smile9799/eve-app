package com.eversmile.eve.app.web.payload.request.finance;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class BudgetRequest {

    private BigDecimal expectedAmount;
    private BigDecimal actualAmount;
    private String budgetName;
    private String budgetComment;
    private String beginsAt;
    private String endsAt;
    private String budgetType;
    private String budgetGroup;
    private boolean isSuccess;
    private boolean done;
    private boolean isActive;
}
