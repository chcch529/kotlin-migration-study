package com.est.curdsample.dto

import com.est.curdsample.domain.Task
import com.est.curdsample.util.PriorityResolver
import com.est.curdsample.util.TimeFormatter

data class TaskDescription(

    val code: String,
    val title: String,
    val description: String,
    val priority: Int,
    val completeStatus: Boolean,
    val startDate: String,
    val dueDate: String,
    val createdAt: String,
    val updatedAt: String

) {

    val priorityLevel: String
        get() = PriorityResolver.resolve(priority)
}

