package com.est.curdsample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "tasks")
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
