package com.est.curdsample.domain;

import com.est.curdsample.dto.TaskDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "tasks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String code;

    private String title;
    private String description;

    private Integer priority;

    private boolean completeStatus = false;

    private LocalDate startTime;
    private LocalDate endTime;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void update(TaskDto taskDto) {

        this.title = taskDto.getTitle();
        this.description = taskDto.getDescription();
        this.priority = taskDto.getPriority();
        this.completeStatus = taskDto.isCompleteStatus();

        this.startTime = taskDto.getStartTime();
        this.endTime = taskDto.getEndTime();
    }

    public static Task of(TaskDto taskDto) {
        Task task = new Task();

        task.code = taskDto.getCode();
        task.title = taskDto.getTitle();
        task.description = taskDto.getDescription();
        task.completeStatus = taskDto.isCompleteStatus();
        task.startTime = taskDto.getStartTime();
        task.endTime = taskDto.getEndTime();

        return task;
    }

}
