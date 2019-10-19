package pl.nogacz.tasks.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.nogacz.tasks.domain.Task;
import pl.nogacz.tasks.domain.TaskDto;
import pl.nogacz.tasks.mapper.TaskMapper;
import pl.nogacz.tasks.service.DbService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void getTasks() throws Exception {
        //Given
        List<TaskDto> tasksDto = new ArrayList<>();

        tasksDto.add(new TaskDto(1L, "AAA", "BBB"));
        tasksDto.add(new TaskDto(1L, "AAA", "BBB"));
        tasksDto.add(new TaskDto(1L, "AAA", "BBB"));

        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task(1L, "AAA", "BBB"));
        tasks.add(new Task(1L, "AAA", "BBB"));
        tasks.add(new Task(1L, "AAA", "BBB"));

        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);
        when(dbService.getAllTasks()).thenReturn(tasks);

        //When & Then
        mockMvc.perform(get("/v1/tasks"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void getTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "AAA", "BBB");
        Task task = new Task(1L, "AAA", "BBB");

        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1"))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteTask() throws Exception {
        //Given
        Task task = new Task(1L, "AAA", "BBB");

        dbService.saveTask(task);

        //When
        mockMvc.perform(delete("/v1/tasks/1"))
                .andExpect(status().is(200));
    }

    @Test
    public void updateTask() throws Exception {
        // Given
        Task task = new Task(1L, "AAA", "BBB");
        TaskDto taskDto = new TaskDto(1L, "AAA", "BBB");

        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("AAA")))
                .andExpect(jsonPath("$.content", is("BBB")));
    }

    @Test
    public void createTask() throws Exception {
        //Given
        Task task = new Task(1L, "AAA", "BBB");
        TaskDto taskDto = new TaskDto(1L, "AAA", "BBB");

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }
}