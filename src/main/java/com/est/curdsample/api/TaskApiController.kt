package com.est.curdsample.api

import com.est.curdsample.app.TaskService
import com.est.curdsample.dto.GeneralApiResponse
import com.est.curdsample.dto.TaskDto
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/tasks")
class TaskApiController(
    private val taskService: TaskService
) {

    @PatchMapping("/{code}/status")
    fun updateTaskStatus(@PathVariable code: String): GeneralApiResponse<TaskDto> {

        val taskDto = taskService.checkTaskByCode(code)

        return GeneralApiResponse(
            data = taskDto,
            msg = "성공적으로 변경되었습니다."
        )

    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable code: String): GeneralApiResponse<Void> {
        taskService.removeByCode(code)

        return GeneralApiResponse(
            msg = "성공적으로 삭제되었습니다."
        )
    }
}
