package com.eversmile.eve.app.web.repository;

import com.eversmile.eve.app.web.model.account.AppRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IAppRepository<T,ID> extends JpaRepository<T, ID> {

    Optional<T> findByUuid(String id);
    Page<T> findByActiveTrue(Pageable pageable);
    Page<T> findByActiveFalse(Pageable pageable);
    Page<T> findByUuidIn(List<String> ids, Pageable pageable);
}
