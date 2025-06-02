package com.est.curdsample.app;

import com.est.curdsample.dao.TaskRepository;
import com.est.curdsample.domain.Task;
import com.est.curdsample.dto.TaskDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public TaskDto saveTask(TaskDto taskDto) {
        taskDto.setCode(genCode());

        Task task = Task.of(taskDto);
        taskRepository.save(task);

        return taskDto;
    }

    private Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    private Task findByCode(String code) {
        return taskRepository.findByCode(code).orElse(null);
    }

    private String genCode() {
        return UUID.randomUUID().toString();
    }

    public TaskDto getByCode(String code) {
        Task task = findByCode(code);

        return TaskDto.from(task);
    }

    @Transactional
    public String removeByCode(String code) {

        Task task = findByCode(code);

        taskRepository.delete(task);

        return code;
    }

    @Transactional
    public TaskDto update(TaskDto taskdto) {

        Task task = findByCode(taskdto.getCode());

        task.update(taskdto);

        return taskdto;
    }

}
