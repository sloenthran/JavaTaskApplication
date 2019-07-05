package pl.nogacz.tasks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.nogacz.tasks.domain.TaskDto;

import java.util.List;

/**
 * @author Dawid Nogacz on 05.07.2019
 */
@RestController
@RequestMapping("v1/task")
public class TaskController {
    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public List<TaskDto> getTasks() {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "task")
    public TaskDto getTask(@RequestParam("id") Long taskId) {
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam("id") Long taskId) {}

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestParam("task") TaskDto task) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(@RequestParam("task") TaskDto task) {}
}
