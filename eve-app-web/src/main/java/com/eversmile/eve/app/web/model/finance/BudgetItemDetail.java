package com.eversmile.eve.app.web.model.finance;

import com.eversmile.eve.app.web.model.AppEntity;
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

@Entity
@Table(name = "tbl_budget_item_details")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class BudgetItemDetail extends AppEntity {

    private String name;
    private Instant expectedDate;
    private Instant actualDate;
    private BigDecimal expectedAmount;
    private BigDecimal actualAmount;
    private boolean isSuccess;
    private String notes;
    private boolean planned;
    private boolean done;

    @ManyToOne
    private BudgetItem budgetItem;
}
