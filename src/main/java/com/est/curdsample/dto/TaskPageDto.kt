package com.est.curdsample.dto

data class TaskPageDto(
    val hasNext: Boolean,
    val data: List<TaskDto> = mutableListOf()
)
