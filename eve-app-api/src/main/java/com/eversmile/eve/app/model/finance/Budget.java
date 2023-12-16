package com.eversmile.eve.app.model.finance;

import com.eversmile.eve.app.model.AppEntity;
import com.eversmile.eve.app.model.user.AppUser;
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
@Table(name = "tbl_budgets")
public class Budget extends AppEntity {

    private String budgetName;
    private String budgetDescription;
    private BigDecimal totalDebits;
    private BigDecimal totalCredits;
    private BigDecimal expectedTotalCredits;
    private BigDecimal expectedTotalDebits;
    private String budgetType;
    private Instant startDate;
    private Instant endDate;
    private String orgGroup;
    private String orgValue;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private Set<BudgetItem> budgetItems = new HashSet<>();
    @ManyToOne
    private AppUser user;
}
