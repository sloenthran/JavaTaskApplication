package pl.nogacz.tasks.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.nogacz.tasks.domain.dto.*;
import pl.nogacz.tasks.trello.facade.TrelloFacade;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void getTrelloBoards() throws Exception {
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();

        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("AAA", "BBB", true));

        trelloBoards.add(new TrelloBoardDto("AAA", "BBB", trelloLists));
        trelloBoards.add(new TrelloBoardDto("AAA", "BBB", trelloLists));
        trelloBoards.add(new TrelloBoardDto("AAA", "BBB", trelloLists));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When & Then
        mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // is isOk()
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void getEmptyTrelloBoards() throws Exception {
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When & Then
        mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // is isOk()
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getSpecificTrelloBoards() throws Exception {
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();

        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("AAA", "BBB", true));

        trelloBoards.add(new TrelloBoardDto("AAA", "AAA", trelloLists));

        when(trelloFacade.getSpecificTrelloBoard("AAA")).thenReturn(trelloBoards);

        //When & Then
        mockMvc.perform(get("/v1/trello/getSpecificTrelloBoards?boardName=AAA").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // is isOk()
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("AAA")))
                .andExpect(jsonPath("$[0].lists", hasSize(1)))
                .andExpect(jsonPath("$[0].lists[0].name", is("BBB")));
    }

    @Test
    public void createTrelloCard() throws Exception {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "AAA",
                "BBB",
                "CCC",
                "DDD"
        );

        TrelloBadgesDto trelloBadgesDto = new TrelloBadgesDto(
                1,
                2,
                3
        );

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "AAA",
                "BBB",
                "CCC",
                trelloBadgesDto
        );

        when(trelloFacade.createCard(ArgumentMatchers.any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(trelloCardDto);

        //When & Then
        mockMvc.perform(post("/v1/trello/createTrelloCard")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is("AAA")))
                .andExpect(jsonPath("$.name", is("BBB")))
                .andExpect(jsonPath("$.shortUrl", is("CCC")));
    }
}