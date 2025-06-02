package com.est.curdsample.dao;

import com.est.curdsample.domain.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByCode(String code);

    @Query ("SELECT t FROM Task t " +
    "WHERE t.endTime = CURRENT_DATE " +
    "ORDER BY t.priority DESC " +
    "LIMIT 10")
    List<Task> findTenTasksDueToToday();

}
