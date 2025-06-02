package com.est.curdsample.app;

import com.est.curdsample.dao.TaskRepository;
import com.est.curdsample.domain.Task;
import com.est.curdsample.dto.TaskDescription;
import com.est.curdsample.dto.TaskDto;
import com.est.curdsample.dto.TaskPageDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public TaskDto checkTaskByCode(String code) {

        Task task = findByCode(code);

        task.updateCheck();

        return TaskDto.from(task);
    }

    public TaskPageDto getTaskList(int pageNum) {

        final int SIZE = 5;

        PageRequest pageRequest = PageRequest.of(pageNum, SIZE, Direction.DESC, "createdAt");
        Page<Task> tasks = taskRepository.findAll(pageRequest);

        return new TaskPageDto(
            tasks.hasNext(),
            tasks.stream()
                .map(TaskDto::from)
                .toList());
    }

    public TaskDescription getDescriptionByCode(String code) {
        return TaskDescription.from(findByCode(code));
    }

    public List<TaskDto> getTasksDueToToday() {
        return taskRepository.findTenTasksDueToToday()
            .stream()
            .map(TaskDto::from)
            .toList();
    }

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
