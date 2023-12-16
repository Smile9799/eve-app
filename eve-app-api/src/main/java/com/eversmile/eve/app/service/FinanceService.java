package com.eversmile.eve.app.service;

import com.eversmile.eve.app.common.AppConstants;
import com.eversmile.eve.app.common.AppHelper;
import com.eversmile.eve.app.common.ResponseMapper;
import com.eversmile.eve.app.model.finance.Budget;
import com.eversmile.eve.app.model.finance.BudgetItem;
import com.eversmile.eve.app.model.finance.BudgetItemDetails;
import com.eversmile.eve.app.model.user.AppUser;
import com.eversmile.eve.app.payload.request.finance.BudgetItemDetailRequest;
import com.eversmile.eve.app.payload.request.finance.BudgetItemRequest;
import com.eversmile.eve.app.payload.request.finance.BudgetRequest;
import com.eversmile.eve.app.payload.response.finance.BudgetItemDetailResponse;
import com.eversmile.eve.app.payload.response.finance.BudgetItemResponse;
import com.eversmile.eve.app.payload.response.finance.BudgetResponse;
import com.eversmile.eve.app.repository.finance.IBudgetItemDetailsRepository;
import com.eversmile.eve.app.repository.finance.IBudgetItemRepository;
import com.eversmile.eve.app.repository.finance.IBudgetRepository;
import com.eversmile.eve.app.repository.user.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceService {

    @Autowired private AppHelper appHelper;
    @Autowired private IBudgetItemRepository budgetItemRepository;
    @Autowired private IBudgetItemDetailsRepository itemDetailsRepository;
    @Autowired private IBudgetRepository budgetRepository;
    @Autowired private IAppUserRepository userRepository;
    @Autowired private ResponseMapper responseMapper;

    public BudgetResponse createBudget(BudgetRequest budgetRequest){
        AppUser user = userRepository.findAppUserByEmail(appHelper.getUsername()).orElse(null);
        Budget budget = Budget.builder()
                .uuid(appHelper.generateUuid())
                .startDate(appHelper.getInstant(budgetRequest.getStartDate(), AppConstants.DATE_FORMAT_YYYY_MM_DD))
                .endDate(appHelper.getInstant(budgetRequest.getEndDate(), AppConstants.DATE_FORMAT_YYYY_MM_DD))
                .budgetDescription(budgetRequest.getBudgetDescription())
                .budgetName(budgetRequest.getBudgetName())
                .expectedTotalCredits(budgetRequest.getExpectedTotalCredits())
                .expectedTotalDebits(budgetRequest.getExpectedTotalDebits())
                .totalCredits(budgetRequest.getTotalCredits())
                .totalDebits(budgetRequest.getTotalDebits())
                .budgetType(budgetRequest.getBudgetType())
                .isActive(budgetRequest.isActive())
                .user(user)
                .build();
        budgetRepository.save(budget);
        return responseMapper.fromBudget(budget);
    }

    public BudgetItemResponse createBudgetItem(BudgetItemRequest itemRequest, String budgetId){
        Budget budget = budgetRepository.findByUuid(budgetId).orElse(null);
        BudgetItem budgetItem = BudgetItem.builder()
                .actualDate(appHelper.getInstant(itemRequest.getActualDate(), AppConstants.DATE_FORMAT_YYYY_MM_DD))
                .expectedDate(appHelper.getInstant(itemRequest.getExpectedDate(),AppConstants.DATE_FORMAT_YYYY_MM_DD))
                .amount(itemRequest.getAmount())
                .expectedAmount(itemRequest.getExpectedAmount())
                .itemName(itemRequest.getItemName())
                .type(itemRequest.getType())
                .isActive(itemRequest.isActive())
                .uuid(appHelper.generateUuid())
                .budget(budget)
                .build();
        budgetItemRepository.save(budgetItem);
        return responseMapper.fromBudgetItem(budgetItem);
    }

    public BudgetItemDetailResponse createBudgetItemDetail(BudgetItemDetailRequest detailRequest, String budgetItemId){
        BudgetItem budgetItem = budgetItemRepository.findByUuid(budgetItemId).orElse(null);
        BudgetItemDetails itemDetails = BudgetItemDetails.builder()
                .budgetItem(budgetItem)
                .uuid(appHelper.generateUuid())
                .itemName(detailRequest.getItemName())
                .expectedAmount(detailRequest.getExpectedAmount())
                .amount(detailRequest.getAmount())
                .isActive(detailRequest.isActive())
                .actualDate(appHelper.getInstant(detailRequest.getActualDate(), AppConstants.DATE_FORMAT_YYYY_MM_DD))
                .expectedDate(appHelper.getInstant(detailRequest.getExpectedDate(), AppConstants.DATE_FORMAT_YYYY_MM_DD))
                .build();
        itemDetailsRepository.save(itemDetails);
        return responseMapper.fromBudgetItemDetail(itemDetails);
    }

    public List<BudgetResponse> findAllBudgets(){
        return budgetRepository
                .findAll()
                .stream()
                .map(budget ->
                        responseMapper.fromBudget(budget))
                .collect(Collectors.toList());
    }

    public List<BudgetItemResponse> findAllBudgetItems(String budgetId){
        return budgetItemRepository
                .findAllByBudget_Uuid(budgetId)
                .stream()
                .map(budgetItem ->
                        responseMapper.fromBudgetItem(budgetItem))
                .collect(Collectors.toList());
    }

    public List<BudgetItemDetailResponse> findAllBudgetItemDetails(String budgetItemId){
        return itemDetailsRepository
                .findAllByBudgetItem_Uuid(budgetItemId)
                .stream().
                map(itemDetails ->
                        responseMapper.fromBudgetItemDetail(itemDetails))
                .collect(Collectors.toList());
    }

    public BudgetResponse findBudgetById(String budgetId){
        Budget budget = budgetRepository.findByUuid(budgetId).orElse(null);
        return responseMapper.fromBudget(budget);
    }

    public BudgetItemResponse findBudgetItem(String budgetItemId){
        BudgetItem budgetItem = budgetItemRepository.findByUuid(budgetItemId).orElse(null);
        return responseMapper.fromBudgetItem(budgetItem);
    }

    public BudgetItemDetailResponse findItemDetail(String itemDetailId){
        BudgetItemDetails itemDetails = itemDetailsRepository.findByUuid(itemDetailId).orElse(null);
        return responseMapper.fromBudgetItemDetail(itemDetails);
    }
}
