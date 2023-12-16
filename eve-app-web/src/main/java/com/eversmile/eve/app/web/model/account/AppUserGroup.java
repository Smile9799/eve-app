package com.eversmile.eve.app.web.model.account;

import com.eversmile.eve.app.web.model.AppEntity;
import com.eversmile.eve.app.web.model.finance.Budget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "tbl_user_groups")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AppUserGroup extends AppEntity {

    @ManyToMany
    private Set<AppUser> users;
    @ManyToOne
    private AppGroup appGroup;
    private Instant validFrom;
    private Instant validTill;
    @OneToMany(mappedBy = "group")
    private Set<Budget> budgets;
}
