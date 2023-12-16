package com.eversmile.eve.app.web.controller;

import com.eversmile.eve.app.web.common.AppComponents;
import com.eversmile.eve.app.web.model.account.AppGroup;
import com.eversmile.eve.app.web.model.account.AppUserGroup;
import com.eversmile.eve.app.web.model.finance.Budget;
import com.eversmile.eve.app.web.model.finance.BudgetItem;
import com.eversmile.eve.app.web.payload.request.finance.BudgetItemRequest;
import com.eversmile.eve.app.web.payload.request.finance.BudgetRequest;
import com.eversmile.eve.app.web.payload.response.finance.BudgetResponse;
import com.eversmile.eve.app.web.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.eversmile.eve.app.web.common.AppComponents.*;

@Controller
@RequestMapping("/finance/budget")
public class FinanceController {

    @Autowired
    private FinanceService financeService;
    @GetMapping()
    public String home(Model model, Principal principal){
        List<BudgetResponse> budgets = financeService.getAllBudgets(principal.getName());
        model.addAttribute("budgets", budgets);
        model.addAttribute(COMPONENT, BUDGET_LIST_COMPONENT);
        model.addAttribute(FRAGMENT, BUDGET_LIST_FRAGMENT);
        return "index";
    }

    @GetMapping("/add")
    public String addBudget(Model model, Principal principal){
        Set<AppUserGroup> userGroups = financeService.getUserGroups(financeService.getUser(principal.getName()));
        List<AppGroup> groups = userGroups.stream().map(AppUserGroup::getAppGroup).toList();
        model.addAttribute("userGroups", groups);
        model.addAttribute("budgetRequest", new BudgetRequest());
        model.addAttribute(COMPONENT, CREATE_BUDGET_COMPONENT);
        model.addAttribute(FRAGMENT, CREATE_BUDGET_FRAGMENT);
        return "index";
    }

    @PostMapping("/add")
    public String addBudget(Model model, Principal principal,
                            @ModelAttribute("budgetRequest") BudgetRequest budgetRequest,
                            BindingResult bindingResult){
        financeService.addNewBudget(budgetRequest, principal.getName());
        model.addAttribute(COMPONENT, CREATE_BUDGET_COMPONENT);
        model.addAttribute(FRAGMENT, CREATE_BUDGET_FRAGMENT);
        return "redirect:/finance/budget";
    }


    @GetMapping("/items/{id}")
    public String budgetItems(Model model, @PathVariable("id") String id){
        model.addAttribute("budget", financeService.findBudgetById(id));
        model.addAttribute(COMPONENT, BUDGET_ITEM_LIST_COMPONENT);
        model.addAttribute(FRAGMENT, BUDGET_ITEM_LIST_FRAGMENT);
        return "index";
    }

    @GetMapping("/items/add/{id}")
    public String budgetItemsAdd(Model model,@PathVariable("id") String id){
        model.addAttribute("budgetItemRequest", new BudgetItemRequest(id));
        model.addAttribute(COMPONENT, CREATE_BUDGET_ITEM_COMPONENT);
        model.addAttribute(FRAGMENT, CREATE_BUDGET_ITEM_FRAGMENT);
        return "index";
    }

    @PostMapping("/items/add")
    public String budgetItemsAdd(Model model,
                                 @ModelAttribute("budgetItemRequest")BudgetItemRequest itemRequest){
        BudgetItem budgetItem = financeService.addNewItem(itemRequest);
        model.addAttribute(COMPONENT, CREATE_BUDGET_ITEM_COMPONENT);
        model.addAttribute(FRAGMENT, CREATE_BUDGET_ITEM_FRAGMENT);
        return "redirect:/finance/budget/items/" + budgetItem.getBudget().getUuid();
    }

    @GetMapping("/item/details/{id}")
    public String budgetItemsDetails(Model model, @PathVariable("id") String id){
        model.addAttribute(COMPONENT, BUDGET_ITEM_DETAILS_LIST_COMPONENT);
        model.addAttribute(FRAGMENT, BUDGET_ITEM_DETAILS_LIST_FRAGMENT);
        return "index";
    }
}
