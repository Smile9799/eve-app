package com.eversmile.eve.app.web.repository.finance;

import com.eversmile.eve.app.web.model.account.AppGroup;
import com.eversmile.eve.app.web.model.account.AppUserGroup;
import com.eversmile.eve.app.web.model.finance.Budget;
import com.eversmile.eve.app.web.repository.IAppRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public interface IBudgetRepository extends IAppRepository<Budget, Long> {

    Page<Budget> findAllByBeginsAt(Instant date, Pageable pageable);
    Page<Budget> findAllByEndsAt(Instant date, Pageable pageable);
    Page<Budget> findAllBySuccessIs(boolean success, Pageable pageable);
    Page<Budget> findAllByDoneIs(boolean done, Pageable pageable);
    Page<Budget> findAllByGroupIn(Set<AppUserGroup> groups, Pageable pageable);
}
