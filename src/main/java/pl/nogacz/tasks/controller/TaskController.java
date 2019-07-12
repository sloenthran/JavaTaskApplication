package pl.nogacz.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.nogacz.tasks.domain.Task;
import pl.nogacz.tasks.domain.TaskDto;
import pl.nogacz.tasks.mapper.TaskMapper;
import pl.nogacz.tasks.service.DbService;
import java.util.List;

/**
 * @author Dawid Nogacz on 05.07.2019
 */
@RestController
@RequestMapping("v1/task")
public class TaskController {
    @Autowired
    private DbService dbService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping(value = "tasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(dbService.getAllTasks());
    }

    @GetMapping(value = "task")
    public TaskDto getTask(@RequestParam("id") Long taskId) {
        Task task = dbService.getTask(taskId);

        if(task == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "This ID is not found in this database!"
            );
        } else {
            return taskMapper.mapToTaskDto(task);
        }
    }

    @DeleteMapping(value = "task")
    public void deleteTask(@RequestParam("id") Long taskId) {}

    @PutMapping(value = "task")
    public TaskDto updateTask(@RequestParam("task") TaskDto task) {
        return null;
    }

    @PostMapping(value = "task")
    public void createTask(@RequestParam("task") TaskDto task) {}
}
