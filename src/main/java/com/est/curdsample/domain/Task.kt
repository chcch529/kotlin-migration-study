package com.est.curdsample.domain

import com.est.curdsample.dto.TaskDto
import com.est.curdsample.util.TimeFormatter
import jakarta.persistence.*
import lombok.Setter
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "tasks")
class Task(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Setter
    @Column(nullable = false, unique = true)
    var code: String,

    var title: String,

    var description: String,

    var priority: Int,

    var completeStatus: Boolean,

    var startTime: LocalDate,

    var endTime: LocalDate,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    var updatedAt: LocalDateTime = LocalDateTime.now()

) {

    fun updateCheck() {
        this.completeStatus = !this.completeStatus
        this.updatedAt = LocalDateTime.now()
    }

    fun update(taskDto: TaskDto) {
        this.title = taskDto.title
        this.description = taskDto.description
        this.priority = taskDto.priority
        this.completeStatus = taskDto.completeStatus

        this.startTime = TimeFormatter.convertToLocalDate(taskDto.startTime)
        this.endTime = TimeFormatter.convertToLocalDate(taskDto.endTime)
    }

    companion object {
        @JvmStatic
        fun of(taskDto: TaskDto): Task {

            return Task(
                code = taskDto.code,
                title = taskDto.title,
                description = taskDto.description,
                priority = taskDto.priority,
                completeStatus = taskDto.completeStatus,
                startTime = TimeFormatter.convertToLocalDate(taskDto.startTime),
                endTime = TimeFormatter.convertToLocalDate(taskDto.endTime)
            )
        }
    }
}
