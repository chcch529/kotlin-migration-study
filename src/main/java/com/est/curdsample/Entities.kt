package com.est.curdsample

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

        this.startTime = convertToLocalDate(taskDto.startTime)
        this.endTime = convertToLocalDate(taskDto.endTime)
    }

}

