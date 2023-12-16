package com.eversmile.eve.app.model.user;

import com.eversmile.eve.app.model.AppEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_roles")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AppRole extends AppEntity {

    private String roleName;

    @ManyToMany(mappedBy = "userRoles")
    private Set<AppUser> users = new HashSet<>();
}
