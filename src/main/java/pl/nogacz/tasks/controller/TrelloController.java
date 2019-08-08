package pl.nogacz.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.nogacz.tasks.client.TrelloClient;
import pl.nogacz.tasks.domain.CreatedTrelloCard;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.domain.dto.TrelloCardDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dawid Nogacz on 02.08.2019
 */
@RestController
@CrossOrigin("*")
@RequestMapping(value = "v1/trello", produces = "application/json")
public class TrelloController {
    @Autowired
    private TrelloClient trelloClient;

    @GetMapping(value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    @GetMapping(value = "getSpecificTrelloBoards")
    public List<TrelloBoardDto> getSpecificTrelloBoards(@RequestParam String boardName) throws BoardsNotFoundException  {
        List<TrelloBoardDto> boards = trelloClient.getTrelloBoards().stream()
                .filter(board -> board.getName().contains(boardName))
                .collect(Collectors.toList());

        if(boards.isEmpty()) {
            throw new BoardsNotFoundException();
        }

        return boards;
    }

    @PostMapping(value = "createTrelloCard", consumes = "application/json")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}
