package com.eversmile.eve.app.repository.finance;

import com.eversmile.eve.app.model.finance.BudgetItemDetails;
import com.eversmile.eve.app.repository.IAppRepository;

import java.util.List;

public interface IBudgetItemDetailsRepository extends IAppRepository<BudgetItemDetails, Long> {

    List<BudgetItemDetails> findAllByBudgetItem_Uuid(String uuid);
}
