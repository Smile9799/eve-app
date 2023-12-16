package com.eversmile.eve.app.web.model.account;

import com.eversmile.eve.app.web.model.AppEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_users")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AppUser extends AppEntity {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isLocked;
    @CreationTimestamp(source = SourceType.DB)
    private Instant joinedAt;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
    private Set<AppUserRole> userRoles = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "users")
    private Set<AppUserGroup> userGroups = new HashSet<>();
}