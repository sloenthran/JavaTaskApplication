package pl.nogacz.tasks.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.nogacz.tasks.controller.TaskNotFoundException;
import pl.nogacz.tasks.domain.Task;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DbServiceTest {
    @Autowired
    private DbService dbService;

    @Test
    public void testGetAllTasks() {
       //When
        List<Task> taskList = dbService.getAllTasks();

        //Then
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testCreateTask() {
        //Given
        Task task = new Task(1L, "Task", "Content");

        //When
        Task saveTask = dbService.saveTask(task);

        //Then
        assertEquals("Task", task.getTitle());
        assertEquals("Content", task.getContent());

        //CleanUp
        dbService.deleteTask(saveTask.getId());
    }

    @Test
    public void testGetTask() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "Task", "Content");
        dbService.saveTask(task);

        //When
        Task saveTask = dbService.getTask(1L).orElseThrow(TaskNotFoundException::new);

        //Then
        assertEquals("Task", saveTask.getTitle());
        assertEquals("Content", saveTask.getContent());

        //CleanUp
        dbService.deleteTask(saveTask.getId());
    }
}