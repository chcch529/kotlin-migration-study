package com.est.curdsample.dao;

import com.est.curdsample.domain.Task;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByCode(String code);

}
