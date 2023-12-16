package com.eversmile.eve.app.repository.finance;

import com.eversmile.eve.app.model.finance.BudgetItem;
import com.eversmile.eve.app.repository.IAppRepository;

import java.util.List;

public interface IBudgetItemRepository extends IAppRepository<BudgetItem,Long> {

    List<BudgetItem> findAllByBudget_Uuid(String budgetId);
}
