package com.eversmile.eve.app.model.user;

import com.eversmile.eve.app.model.AppEntity;
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

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<AppRole> userRoles = new HashSet<>();
}
