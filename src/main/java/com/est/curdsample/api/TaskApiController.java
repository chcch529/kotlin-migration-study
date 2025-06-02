package com.est.curdsample.api;


import com.est.curdsample.app.TaskService;
import com.est.curdsample.dto.GeneralApiResponse;
import com.est.curdsample.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskApiController {

    private final TaskService taskService;

    @PatchMapping("/{code}/status")
    public GeneralApiResponse<TaskDto> updateTaskStatus(@PathVariable String code) {

        TaskDto taskDto = taskService.checkTaskByCode(code);

        return GeneralApiResponse.<TaskDto>builder()
            .data(taskDto)
            .msg("성공적으로 변경되었습니다.")
            .build();
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<Void> deleteTask(@PathVariable String code) {
    public GeneralApiResponse<Void> deleteTask(@PathVariable String code) {
        taskService.removeByCode(code);

//        return ResponseEntity.noContent().build();
        return GeneralApiResponse.<Void>builder()
            .msg("성공적으로 삭제되었습니다.")
            .build();
    }
}
