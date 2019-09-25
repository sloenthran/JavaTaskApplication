package pl.nogacz.tasks.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.nogacz.tasks.domain.TrelloBoard;
import pl.nogacz.tasks.domain.TrelloCard;
import pl.nogacz.tasks.domain.TrelloList;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.domain.dto.TrelloCardDto;
import pl.nogacz.tasks.domain.dto.TrelloListDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TrelloMapperTest {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardList() {
        //Given
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();

        trelloBoardsDto.add(new TrelloBoardDto("Aaa", "BBB", new ArrayList<>()));
        trelloBoardsDto.add(new TrelloBoardDto("Aaa", "BBB", new ArrayList<>()));
        trelloBoardsDto.add(new TrelloBoardDto("Aaa", "BBB", new ArrayList<>()));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoardList(trelloBoardsDto);

        //Then
        assertEquals(3, trelloBoards.size());
    }

    @Test
    public void mapToBoardListDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();

        trelloBoards.add(new TrelloBoard("Aaa", "BBB", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("Aaa", "BBB", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("Aaa", "BBB", new ArrayList<>()));

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardListDto(trelloBoards);

        //Then
        assertEquals(3, trelloBoardsDto.size());
    }

    @Test
    public void mapToBoard() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("Aaa", "BBB", new ArrayList<>());

        //When
        TrelloBoard trelloBoard = trelloMapper.mapToBoard(trelloBoardDto);

        //Then
        assertEquals(trelloBoardDto.getId(), trelloBoard.getId());
        assertEquals(trelloBoardDto.getName(), trelloBoard.getName());
    }

    @Test
    public void mapToBoardDto() {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard("Aaa", "BBB", new ArrayList<>());

        //When
        TrelloBoardDto trelloBoardDto = trelloMapper.mapToBoardDto(trelloBoard);

        //Then
        assertEquals(trelloBoard.getId(), trelloBoardDto.getId());
        assertEquals(trelloBoard.getName(), trelloBoardDto.getName());
    }

    @Test
    public void mapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();

        trelloListsDto.add(new TrelloListDto("1", "B", true));
        trelloListsDto.add(new TrelloListDto("1", "B", true));
        trelloListsDto.add(new TrelloListDto("1", "B", true));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //Then
        assertEquals(3, trelloLists.size());
    }

    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();

        trelloLists.add(new TrelloList("1", "B", true));
        trelloLists.add(new TrelloList("1", "B", true));
        trelloLists.add(new TrelloList("1", "B", true));

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(3, trelloListsDto.size());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("AAA", "BBB", "CCC", "DDD");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getPosition(), trelloCard.getPosition());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("AAA", "BBB", "CCC", "DDD");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getPosition(), trelloCard.getPosition());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }
}