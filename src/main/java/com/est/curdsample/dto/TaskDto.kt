package com.est.curdsample.dto

import com.est.curdsample.domain.Task
import com.est.curdsample.util.PriorityResolver
import com.est.curdsample.util.TimeFormatter
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import lombok.Data

@Data
class TaskDto {
    private val code: String? = null

    private val title: @NotBlank(message = "작업 이름은 반드시 입력되어야 합니다.") String? = null

    private val description: String? = null

    private val priority: @Min(value = 0) Int? = null

    private val completeStatus = false

    private val startTime: @NotBlank(message = "날짜는 반드시 입력되어야 합니다.") @Pattern(
        regexp = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$",
        message = "올바른 날짜를 입력하여 주시기 바랍니다."
    ) String? = null

    private val endTime: @NotBlank(message = "날짜는 반드시 입력되어야 합니다.") @Pattern(
        regexp = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$",
        message = "올바른 날짜를 입력하여 주시기 바랍니다."
    ) String? = null

    val priorityLevel: String
        get() = PriorityResolver.resolve(priority)

    companion object {
        fun from(task: Task): TaskDto {
            val taskDto = TaskDto()

            taskDto.setCode(task.code)
            taskDto.setTitle(task.title)
            taskDto.setDescription(task.description)
            taskDto.setPriority(task.priority)
            taskDto.setCompleteStatus(task.isCompleteStatus)
            taskDto.setStartTime(TimeFormatter.convertToString(task.startTime))
            taskDto.setEndTime(TimeFormatter.convertToString(task.endTime))


            return taskDto
        }
    }
}
