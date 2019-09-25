package pl.nogacz.tasks.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nogacz.tasks.domain.dto.CreatedTrelloCardDto;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.domain.dto.TrelloCardDto;
import pl.nogacz.tasks.trello.facade.TrelloFacade;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "v1/trello", produces = "application/json")
@AllArgsConstructor
public class TrelloController {
    private TrelloFacade trelloFacade;

    @GetMapping(value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

    @GetMapping(value = "getSpecificTrelloBoards")
    public List<TrelloBoardDto> getSpecificTrelloBoards(@RequestParam String boardName) throws BoardsNotFoundException  {
        return trelloFacade.getSpecificTrelloBoard(boardName);
    }

    @PostMapping(value = "createTrelloCard", consumes = "application/json")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }
}
