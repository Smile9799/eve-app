package com.eversmile.eve.app.web.model.finance;

import com.eversmile.eve.app.web.model.AppEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "tbl_budget_items")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class BudgetItem extends AppEntity {

    private String itemName;
    private Instant expectedDate;
    private Instant actualDate;
    private BigDecimal expectedAmount;
    private BigDecimal actualAmount;
    private boolean isSuccess;
    private String notes;
    private boolean planned;
    private boolean done;

    @ManyToOne
    private Budget budget;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "budgetItem")
    private Set<BudgetItemDetail> budgetItemDetails = new HashSet<>();
}
