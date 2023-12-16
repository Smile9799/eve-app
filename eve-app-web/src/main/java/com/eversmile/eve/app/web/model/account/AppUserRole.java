package com.eversmile.eve.app.web.model.account;

import com.eversmile.eve.app.web.model.AppEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Entity
@Table(name = "tbl_user_roles")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AppUserRole extends AppEntity {

    @ManyToOne
    private AppUser user;
    @ManyToOne
    private AppRole role;
    private Instant validFrom;
    private Instant validTill;
}
