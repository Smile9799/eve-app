package com.eversmile.eve.app.common;

import com.eversmile.eve.app.model.finance.Budget;
import com.eversmile.eve.app.model.finance.BudgetItem;
import com.eversmile.eve.app.model.finance.BudgetItemDetails;
import com.eversmile.eve.app.model.user.AppUser;
import com.eversmile.eve.app.payload.response.account.AppUserResponse;
import com.eversmile.eve.app.payload.response.finance.BudgetItemDetailResponse;
import com.eversmile.eve.app.payload.response.finance.BudgetItemResponse;
import com.eversmile.eve.app.payload.response.finance.BudgetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    @Autowired private AppHelper appHelper;

    public BudgetResponse fromBudget(Budget budget){
        return BudgetResponse.builder()
                .id(budget.getUuid())
                .budgetDescription(budget.getBudgetDescription())
                .budgetName(budget.getBudgetName())
                .budgetType(budget.getBudgetType())
                .totalDebits(budget.getTotalDebits())
                .totalCredits(budget.getTotalCredits())
                .expectedTotalCredits(budget.getExpectedTotalCredits())
                .expectedTotalDebits(budget.getExpectedTotalDebits())
                .isActive(budget.isActive())
                .startDate(appHelper.getDateTime(budget.getStartDate()))
                .endDate(appHelper.getDateTime(budget.getEndDate()))
                .user(fromAppUser(budget.getUser()))
                .build();
    }

    public BudgetItemResponse fromBudgetItem(BudgetItem budgetItem){
        return BudgetItemResponse.builder()
                .actualDate(appHelper.getDateTime(budgetItem.getActualDate()))
                .expectedDate(appHelper.getDateTime(budgetItem.getExpectedDate()))
                .amount(budgetItem.getAmount())
                .expectedAmount(budgetItem.getExpectedAmount())
                .itemName(budgetItem.getItemName())
                .type(budgetItem.getType())
                .budget(fromBudget(budgetItem.getBudget()))
                .id(budgetItem.getUuid())
                .isActive(budgetItem.isActive())
                .build();
    }

    public AppUserResponse fromAppUser(AppUser user){
        return AppUserResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getUuid())
                .joinedAt(appHelper.getDateTime(user.getJoinedAt()))
                .build();
    }

    public BudgetItemDetailResponse fromBudgetItemDetail(BudgetItemDetails itemDetails){
        return BudgetItemDetailResponse.builder()
                .budgetItem(fromBudgetItem(itemDetails.getBudgetItem()))
                .itemName(itemDetails.getItemName())
                .expectedAmount(itemDetails.getExpectedAmount())
                .amount(itemDetails.getAmount())
                .id(itemDetails.getUuid())
                .actualDate(appHelper.getDateTime(itemDetails.getActualDate()))
                .expectedDate(appHelper.getDateTime(itemDetails.getExpectedDate()))
                .build();
    }
}
