package pl.nogacz.tasks.mapper;

import org.springframework.stereotype.Component;
import pl.nogacz.tasks.domain.Task;
import pl.nogacz.tasks.domain.TaskDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dawid Nogacz on 12.07.2019
 */
@Component
public class TaskMapper {
    public Task mapToTask(final TaskDto taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getContent()
        );
    }

    public TaskDto mapToTaskDto(final Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getContent()
        );
    }

    public List<TaskDto> mapToTaskDtoList(final List<Task> taskList) {
        return taskList.stream()
                .map(entry -> new TaskDto(
                        entry.getId(),
                        entry.getTitle(),
                        entry.getContent()
                ))
                .collect(Collectors.toList());
    }
}
