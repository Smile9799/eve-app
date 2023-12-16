package com.eversmile.eve.app.payload.response.finance;

import com.eversmile.eve.app.payload.response.account.AppUserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BudgetResponse {

    private String id;
    private String budgetName;
    private String budgetDescription;
    private BigDecimal totalDebits;
    private BigDecimal totalCredits;
    private BigDecimal expectedTotalCredits;
    private BigDecimal expectedTotalDebits;
    private String budgetType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isActive;
    private AppUserResponse user;
}
