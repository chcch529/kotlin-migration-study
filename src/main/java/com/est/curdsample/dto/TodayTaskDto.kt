package com.est.curdsample.dto

import java.util.stream.Collectors

data class TodayTaskDto(
    var uncompletedTasks: MutableList<TaskDto?>? = ArrayList(),
    var completedTasks: MutableList<TaskDto?>? = ArrayList()
) {

    companion object {
        fun from(tasks:  MutableList<TaskDto?>): TodayTaskDto {
            val result = tasks.stream()
                .collect(Collectors.partitioningBy(TaskDto::completeStatus)
                )

            return TodayTaskDto(
                result[false],
                result[true]
            )
        }
    }
}
