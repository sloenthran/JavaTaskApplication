package pl.nogacz.tasks.trello.facade;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.nogacz.tasks.controller.BoardsNotFoundException;
import pl.nogacz.tasks.domain.TrelloBoard;
import pl.nogacz.tasks.domain.TrelloList;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.domain.dto.TrelloListDto;
import pl.nogacz.tasks.mapper.TrelloMapper;
import pl.nogacz.tasks.service.TrelloService;
import pl.nogacz.tasks.trello.validator.TrelloValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {
    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void fetchTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("AAA", "test", true));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("AAA", "test", trelloLists));

        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("AAA", "test", true));

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("AAA", "test", trelloListsDto));

        Mockito.when(trelloService.getTrelloBoards()).thenReturn(trelloBoardsDto);
        Mockito.when(trelloMapper.mapToBoardList(trelloBoardsDto)).thenReturn(trelloBoards);
        Mockito.when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> getBoards = trelloFacade.fetchTrelloBoards();

        //Then
        assertEquals(0, getBoards.size());
    }

    @Test
    public void getSpecificTrelloBoard() throws BoardsNotFoundException {
        //Given

        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("AAA", "test", true));

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("AAA", "test", trelloListsDto));
        trelloBoardsDto.add(new TrelloBoardDto("AAA", "test", trelloListsDto));
        trelloBoardsDto.add(new TrelloBoardDto("AAA", "kurczaczki", trelloListsDto));

        Mockito.when(trelloService.getTrelloBoards()).thenReturn(trelloBoardsDto);

        //When
        List<TrelloBoardDto> getBoards = trelloFacade.getSpecificTrelloBoard("kurczaczki");

        //Then
        assertEquals(1, getBoards.size());
    }
}