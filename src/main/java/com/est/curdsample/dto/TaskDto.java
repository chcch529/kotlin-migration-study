package com.est.curdsample.dto;

import com.est.curdsample.domain.Task;
import com.est.curdsample.util.PriorityResolver;
import com.est.curdsample.util.TimeFormatter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskDto {

    private String code;

    @NotBlank(message = "작업 이름은 반드시 입력되어야 합니다.")
    private String title;

    private String description;

    @Min(value = 0)
    private Integer priority;

    private boolean completeStatus;

    @NotBlank(message = "날짜는 반드시 입력되어야 합니다.")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$", message = "올바른 날짜를 입력하여 주시기 바랍니다.")
    private String startTime;

    @NotBlank(message = "날짜는 반드시 입력되어야 합니다.")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$", message = "올바른 날짜를 입력하여 주시기 바랍니다.")
    private String endTime;

    public static TaskDto from(Task task) {

        TaskDto taskDto = new TaskDto();

        taskDto.setCode(task.getCode());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setPriority(task.getPriority());
        taskDto.setCompleteStatus(task.isCompleteStatus());
        taskDto.setStartTime(TimeFormatter.convertToString(task.getStartTime()));
        taskDto.setEndTime(TimeFormatter.convertToString(task.getEndTime()));


        return taskDto;
    }

    public String getPriorityLevel() {
        return PriorityResolver.resolve(priority);
    }
}
