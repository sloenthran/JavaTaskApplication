package pl.nogacz.tasks.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskDtoTest {
    private TaskDto taskDto;

    @Before
    public void prepareData() {
        taskDto = new TaskDto(1L, "title", "content");
    }

    @Test
    public void getId() {
        //When
        Long id = taskDto.getId();

        //Then
        assertEquals(1L, id, 0);
    }

    @Test
    public void getTitle() {
        //When
        String title = taskDto.getTitle();

        //Then
        assertEquals("title", title);
    }

    @Test
    public void getContent() {
        //When
        String content = taskDto.getContent();

        //Then
        assertEquals("content", content);
    }

    @Test
    public void setId() {
        //Given
        taskDto.setId(2L);

        //When
        Long id = taskDto.getId();

        //Then
        assertEquals(2L, id, 0);
    }

    @Test
    public void setTitle() {
        //Given
        taskDto.setTitle("titleTwo");

        //When
        String title = taskDto.getTitle();

        //Then
        assertEquals("titleTwo", title);
    }

    @Test
    public void setContent() {
        //Given
        taskDto.setContent("contentTwo");

        //When
        String content = taskDto.getContent();

        //Then
        assertEquals("contentTwo", content);
    }
}