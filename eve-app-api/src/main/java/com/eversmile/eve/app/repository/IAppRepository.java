package com.eversmile.eve.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface IAppRepository<T, ID> extends JpaRepository<T, ID> {

    Optional<T> findByUuid(String uuid);
    Page<T> findByActiveTrue(Pageable pageable);
    Page<T> findByActiveFalse(Pageable pageable);
}
