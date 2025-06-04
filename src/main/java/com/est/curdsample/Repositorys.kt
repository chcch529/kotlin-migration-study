package com.est.curdsample

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TaskRepository : JpaRepository<Task, Long> {
    fun findByCode(code: String?): Task?

    @Query(
        "SELECT t FROM Task t " +
                "WHERE t.endTime = CURRENT_DATE " +
                "ORDER BY t.priority DESC " +
                "LIMIT 10"
    )
    fun findTenTasksDueToToday(): List<Task>
}
