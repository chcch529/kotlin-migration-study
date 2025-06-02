package com.est.curdsample.dto;

import com.est.curdsample.domain.Task;
import com.est.curdsample.util.PriorityResolver;
import com.est.curdsample.util.TimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDescription {

    private String code;

    private String title;
    private String description;

    private Integer priority;
    private boolean completeStatus;

    private String startDate;
    private String endDate;

    private String createdAt;
    private String updatedAt;

    public static TaskDescription from(Task task) {

        return new TaskDescription(
            task.getCode(),
            task.getTitle(),
            task.getDescription(),
            task.getPriority(),
            task.isCompleteStatus(),
            TimeFormatter.convertToString(task.getStartTime()),
            TimeFormatter.convertToString(task.getEndTime()),
            TimeFormatter.convertToString(task.getCreatedAt()),
            TimeFormatter.convertToString(task.getUpdatedAt())
        );
    }

}

