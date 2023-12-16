package com.eversmile.eve.app.controller;

import com.eversmile.eve.app.payload.request.finance.BudgetItemDetailRequest;
import com.eversmile.eve.app.payload.request.finance.BudgetItemRequest;
import com.eversmile.eve.app.payload.request.finance.BudgetRequest;
import com.eversmile.eve.app.payload.response.finance.BudgetItemDetailResponse;
import com.eversmile.eve.app.payload.response.finance.BudgetItemResponse;
import com.eversmile.eve.app.payload.response.finance.BudgetResponse;
import com.eversmile.eve.app.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Autowired private FinanceService financeService;

    @PostMapping("/create")
    public ResponseEntity<BudgetResponse> createBudget(@RequestBody BudgetRequest budgetRequest){
        BudgetResponse response = financeService.createBudget(budgetRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<BudgetItemResponse> createBudgetItem(@RequestBody BudgetItemRequest budgetItemRequest, @PathVariable("id") String budgetId){
        BudgetItemResponse response = financeService.createBudgetItem(budgetItemRequest, budgetId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/item/{id}/add")
    public ResponseEntity<BudgetItemDetailResponse> createItemDetail(@PathVariable("id") String budgetItemId, @RequestBody BudgetItemDetailRequest itemDetailRequest){
        BudgetItemDetailResponse response = financeService.createBudgetItemDetail(itemDetailRequest, budgetItemId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/budgets")
    public ResponseEntity<List<BudgetResponse>> findAllBudgets(){
        return ResponseEntity.ok(financeService.findAllBudgets());
    }

    @GetMapping("/budget/{id}")
    public ResponseEntity<BudgetResponse> findBudgetId(@PathVariable("id") String budgetId){
        return ResponseEntity.ok(financeService.findBudgetById(budgetId));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<List<BudgetItemResponse>> findAllBudgetItem(@PathVariable("id") String budgetId){
        return ResponseEntity.ok(financeService.findAllBudgetItems(budgetId));
    }

    @GetMapping("/item/details/{id}")
    public ResponseEntity<List<BudgetItemDetailResponse>> findAllBudgetItemDetails(@PathVariable("id") String budgetItemId){
        return ResponseEntity.ok(financeService.findAllBudgetItemDetails(budgetItemId));
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<BudgetItemResponse> findBudgetItem(@PathVariable("id") String budgetItemId){
        return ResponseEntity.ok(financeService.findBudgetItem(budgetItemId));
    }

    @GetMapping("/item/detail/{id}")
    public ResponseEntity<BudgetItemDetailResponse> findBudgetItemDetail(@PathVariable("id") String budgetItemDetailId){
        return ResponseEntity.ok(financeService.findItemDetail(budgetItemDetailId));
    }
}
