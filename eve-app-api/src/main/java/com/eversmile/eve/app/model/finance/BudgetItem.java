package com.eversmile.eve.app.model.finance;

import com.eversmile.eve.app.model.AppEntity;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tbl_budget_item")
public class BudgetItem extends AppEntity {

    private BigDecimal expectedAmount;
    private BigDecimal amount;
    private String type;
    private String itemName;
    private Instant expectedDate;
    private Instant actualDate;
    private String orgGroup;
    private String orgValue;

    @ManyToOne
    private Budget budget;
    @OneToMany(mappedBy = "budgetItem", cascade = CascadeType.ALL)
    private Set<BudgetItemDetails> budgetItemsDetails = new HashSet<>();
}
