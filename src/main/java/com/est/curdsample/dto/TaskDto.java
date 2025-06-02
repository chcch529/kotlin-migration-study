package com.est.curdsample.dto;

import com.est.curdsample.domain.Task;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskDto {

    private String code;

    private String title;
    private String description;

    private Integer priority;

    private boolean completeStatus;

    private LocalDate startTime;
    private LocalDate endTime;

    public static TaskDto from(Task task) {
        TaskDto dto = new TaskDto();
        dto.setCode(task.getCode());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority());
        dto.setCompleteStatus(task.isCompleteStatus());
        dto.setStartTime(task.getStartTime());
        dto.setEndTime(task.getEndTime());
        return dto;
    }
}
