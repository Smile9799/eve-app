package com.eversmile.eve.app.web.model.account;

import com.eversmile.eve.app.web.model.AppEntity;
import com.eversmile.eve.app.web.model.finance.Budget;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_groups")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AppGroup extends AppEntity {

    private String groupName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "appGroup")
    private Set<AppUserGroup> userGroups = new HashSet<>();
}
