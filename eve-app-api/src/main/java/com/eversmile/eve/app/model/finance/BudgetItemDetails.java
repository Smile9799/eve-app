package com.eversmile.eve.app.model.finance;

import com.eversmile.eve.app.model.AppEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tbl_budget_item_details")
public class BudgetItemDetails extends AppEntity {

    private BigDecimal amount;
    private BigDecimal expectedAmount;
    private String itemName;
    private Instant expectedDate;
    private Instant actualDate;
    private String orgGroup;
    private String orgValue;

    @ManyToOne
    private BudgetItem budgetItem;
}
