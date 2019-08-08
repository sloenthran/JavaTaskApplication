package pl.nogacz.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.nogacz.tasks.domain.TaskDto;
import pl.nogacz.tasks.mapper.TaskMapper;
import pl.nogacz.tasks.service.DbService;
import java.util.List;

/**
 * @author Dawid Nogacz on 05.07.2019
 */
@RestController
@CrossOrigin("*")
@RequestMapping(value = "v1/task", produces = "application/json")
public class TaskController {
    @Autowired
    private DbService dbService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping(value = "getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(dbService.getAllTasks());
    }

    @GetMapping(value = "getTask")
    public TaskDto getTask(@RequestParam("id") Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(dbService.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping(value = "deleteTask")
    public void deleteTask(@RequestParam("id") Long taskId) {
        dbService.deleteTask(taskId);
    }

    @PutMapping(value = "updateTask", consumes = "application/json")
    public TaskDto updateTask(@RequestBody TaskDto task) {
        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(task)));
    }

    @PostMapping(value = "createTask", consumes = "application/json")
    public void createTask(@RequestBody TaskDto task) {
        dbService.saveTask(taskMapper.mapToTask(task));
    }
}
