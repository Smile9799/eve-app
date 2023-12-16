package com.eversmile.eve.app.web.model.finance;

import com.eversmile.eve.app.web.model.AppEntity;
import com.eversmile.eve.app.web.model.account.AppGroup;
import com.eversmile.eve.app.web.model.account.AppUserGroup;
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
@Table(name = "tbl_budgets")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Budget extends AppEntity {

    private BigDecimal expectedAmount;
    private BigDecimal actualAmount;
    private String budgetName;
    private String budgetComment;
    private Instant beginsAt;
    private Instant endsAt;
    private String budgetType;
    private boolean isSuccess;
    private boolean done;

    @ManyToOne
    private AppUserGroup group;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "budget")
    private Set<BudgetItem> budgetItems = new HashSet<>();
}
