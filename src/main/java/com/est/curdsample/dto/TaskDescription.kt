package com.est.curdsample.dto

import com.est.curdsample.domain.Task
import com.est.curdsample.util.PriorityResolver
import com.est.curdsample.util.TimeFormatter
import lombok.AllArgsConstructor
import lombok.Data

data class TaskDescription(

    val code: String? = null,

    val title: String? = null,

    val description: String? = null,

    val priority: Int? = null,

    val completeStatus: Boolean = false,

    val startDate: String? = null,

    val dueDate: String? = null,

    val createdAt: String? = null,

    val updatedAt: String? = null

) {

    val priorityLevel: String
        get() = PriorityResolver.resolve(priority)

    companion object {
        @JvmStatic
        fun from(task: Task): TaskDescription {
            return TaskDescription(
                task.code,
                task.title,
                task.description,
                task.priority,
                task.completeStatus,
                TimeFormatter.convertToString(task.startTime),
                TimeFormatter.convertToString(task.endTime),
                TimeFormatter.convertToString(task.createdAt),
                TimeFormatter.convertToString(task.updatedAt)
            )
        }
    }
}

