package com.eversmile.eve.app.web.service;

import com.eversmile.eve.app.web.common.AppHelper;
import com.eversmile.eve.app.web.model.account.AppGroup;
import com.eversmile.eve.app.web.model.account.AppUser;
import com.eversmile.eve.app.web.model.account.AppUserGroup;
import com.eversmile.eve.app.web.model.finance.Budget;
import com.eversmile.eve.app.web.model.finance.BudgetItem;
import com.eversmile.eve.app.web.payload.request.finance.BudgetItemRequest;
import com.eversmile.eve.app.web.payload.request.finance.BudgetRequest;
import com.eversmile.eve.app.web.payload.response.finance.BudgetResponse;
import com.eversmile.eve.app.web.repository.account.IUserRepository;
import com.eversmile.eve.app.web.repository.finance.IBudgetItemRepository;
import com.eversmile.eve.app.web.repository.finance.IBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FinanceService {

    @Autowired
    private IBudgetRepository budgetRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private AppHelper appHelper;
    @Autowired
    private IBudgetItemRepository itemRepository;

    public List<BudgetResponse> getAllBudgets(String username){
        AppUser user = getUser(username);
        Set<AppUserGroup> appGroups = getUserGroups(user);
        Pageable pageable = Pageable.ofSize(50);
        Page<Budget> budgetPage = budgetRepository.findAllByGroupIn(appGroups, pageable);
        return budgetPage.getContent().stream().map(singleBudget ->{
            return BudgetResponse.builder()
                    .done(singleBudget.isDone())
                    .isSuccess(singleBudget.isSuccess())
                    .actualAmount(singleBudget.getActualAmount())
                    .expectedAmount(singleBudget.getExpectedAmount())
                    .uuid(singleBudget.getUuid())
                    .isActive(singleBudget.isActive())
                    .beginsAt(singleBudget.getBeginsAt().atZone(ZoneId.of("Africa/Johannesburg")).toLocalDateTime())
                    .endsAt(singleBudget.getEndsAt().atZone(ZoneId.of("Africa/Johannesburg")).toLocalDateTime())
                    .budgetComment(singleBudget.getBudgetComment())
                    .budgetType(singleBudget.getBudgetType())
                    .build();
        }).collect(Collectors.toList());
    }

    public Set<AppUserGroup> getUserGroups(AppUser user){
        return user.getUserGroups();
    }

    public AppUser getUser(String username){
        Optional<AppUser> userOptional = userRepository.findByEmail(username);
        return userOptional.orElse(null);
    }

    public Budget addNewBudget(BudgetRequest budgetRequest, String username){
        Set<AppUserGroup> userGroups = getUser(username).getUserGroups();
        AppUserGroup userGroup = userGroups.stream()
                .filter(group -> group.getAppGroup().getUuid().equalsIgnoreCase(budgetRequest.getBudgetGroup()))
                .findFirst()
                .orElse(null);
        Budget budget = Budget.builder()
                .budgetComment(budgetRequest.getBudgetComment())
                .budgetType(budgetRequest.getBudgetType())
                .budgetName(budgetRequest.getBudgetName())
                .actualAmount(BigDecimal.ZERO)
                .expectedAmount(BigDecimal.ZERO)
                .endsAt(appHelper.convertHtmlDateToInstant(budgetRequest.getEndsAt(), true))
                .beginsAt(appHelper.convertHtmlDateToInstant(budgetRequest.getBeginsAt(), false))
                .isActive(true)
                .group(userGroup)
                .uuid(UUID.randomUUID().toString())
                .build();
        budgetRepository.save(budget);
        return budget;
    }

    public Budget findBudgetById(String id){
        return budgetRepository.findByUuid(id).orElse(null);
    }

    public BudgetItem addNewItem(BudgetItemRequest itemRequest){
        Budget budget = findBudgetById(itemRequest.getBudgetId());
        BudgetItem budgetItem = BudgetItem.builder()
                .budget(budget)
                .expectedDate(appHelper.convertHtmlDateToInstant(itemRequest.getExpectedDate(), false))
                .expectedAmount(itemRequest.getExpectedAmount())
                .notes(itemRequest.getNotes())
                .isActive(true)
                .planned(itemRequest.isPlanned())
                .build();
        itemRepository.save(budgetItem);
        return budgetItem;
    }
}
