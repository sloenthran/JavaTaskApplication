package pl.nogacz.tasks.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nogacz.tasks.client.TrelloClient;
import pl.nogacz.tasks.domain.CreatedTrelloCard;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.domain.dto.TrelloCardDto;
import pl.nogacz.tasks.service.TrelloService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dawid Nogacz on 02.08.2019
 */
@RestController
@CrossOrigin("*")
@RequestMapping(value = "v1/trello", produces = "application/json")
@AllArgsConstructor
public class TrelloController {
    private TrelloService trelloService;

    @GetMapping(value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.getTrelloBoards();
    }

    @GetMapping(value = "getSpecificTrelloBoards")
    public List<TrelloBoardDto> getSpecificTrelloBoards(@RequestParam String boardName) throws BoardsNotFoundException  {
        List<TrelloBoardDto> boards = trelloService.getTrelloBoards().stream()
                .filter(board -> board.getName().contains(boardName))
                .collect(Collectors.toList());

        if(boards.isEmpty()) {
            throw new BoardsNotFoundException();
        }

        return boards;
    }

    @PostMapping(value = "createTrelloCard", consumes = "application/json")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createNewCard(trelloCardDto);
    }
}
