package com.est.curdsample.app

import com.est.curdsample.dao.TaskRepository
import com.est.curdsample.domain.Task
import com.est.curdsample.domain.toDescription
import com.est.curdsample.domain.toDto
import com.est.curdsample.dto.TaskDescription
import com.est.curdsample.dto.TaskDto
import com.est.curdsample.dto.TaskPageDto
import com.est.curdsample.dto.toEntity
import com.est.curdsample.exception.TaskNotFoundException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class TaskService(
    private val taskRepository: TaskRepository
) {

    @Transactional
    fun checkTaskByCode(code: String): TaskDto {
        val task = findByCode(code)

        task.updateCheck()

        return task.toDto()
    }

    fun getTaskList(pageNum: Int): TaskPageDto {
        val SIZE = 5

        val pageRequest = PageRequest.of(pageNum, SIZE, Sort.Direction.DESC, "createdAt")
        val tasks: Slice<Task> = taskRepository.findAll(pageRequest)

        return TaskPageDto(
            hasNext = tasks.hasNext(),
            data = tasks.content.map { it.toDto() }
        )
    }

    fun getDescriptionByCode(code: String): TaskDescription {
        return findByCode(code).toDescription()
    }

    fun getTasksDueToToday(): List<TaskDto> {

        return taskRepository.findTenTasksDueToToday().map { it.toDto() }
    }

    @Transactional
    fun saveTask(taskDto: TaskDto): TaskDto {
        taskDto.code = genCode()

        taskRepository.save(taskDto.toEntity())

        return taskDto
    }

    private fun findByCode(code: String): Task {
        return taskRepository.findByCode(code)
            ?: throw TaskNotFoundException()
    }

    private fun genCode(): String {
        return UUID.randomUUID().toString()
    }

    fun getByCode(code: String): TaskDto {
        val task = findByCode(code)

        return task.toDto()
    }

    @Transactional
    fun removeByCode(code: String): String {
        val task = findByCode(code)

        taskRepository.delete(task)

        return code
    }

    @Transactional
    fun update(taskDto: TaskDto): TaskDto {
        val task = findByCode(taskDto.code)

        task.update(taskDto)

        return taskDto
    }
}
