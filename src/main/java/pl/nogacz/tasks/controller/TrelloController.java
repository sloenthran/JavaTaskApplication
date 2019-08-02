package pl.nogacz.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.nogacz.tasks.client.TrelloClient;
import pl.nogacz.tasks.domain.TrelloBoardDto;

import java.util.List;

/**
 * @author Dawid Nogacz on 02.08.2019
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "v1/trello", produces = "application/json")
public class TrelloController {
    @Autowired
    private TrelloClient trelloClient;

    @GetMapping(value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }
}
