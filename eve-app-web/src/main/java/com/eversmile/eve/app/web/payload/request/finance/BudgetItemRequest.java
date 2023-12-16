package com.eversmile.eve.app.web.payload.request.finance;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class BudgetItemRequest {

    private String budgetId;
    private String uuid;
    private String itemName;
    private String expectedDate;
    private String actualDate;
    private BigDecimal expectedAmount;
    private BigDecimal actualAmount;
    private boolean isSuccess;
    private String notes;
    private boolean planned;
    private boolean done;

    public BudgetItemRequest(String budgetId){
        this.budgetId = budgetId;
    }
}
