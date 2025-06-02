package com.est.curdsample.dto;

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


}
