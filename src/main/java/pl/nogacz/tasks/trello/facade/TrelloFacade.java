package pl.nogacz.tasks.trello.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.nogacz.tasks.domain.TrelloBoard;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.mapper.TrelloMapper;
import pl.nogacz.tasks.service.TrelloService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    private TrelloService trelloService;
    private TrelloMapper trelloMapper;

    public TrelloFacade(TrelloService trelloService, TrelloMapper trelloMapper) {
        this.trelloService = trelloService;
        this.trelloMapper = trelloMapper;
    }

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoardList(trelloService.getTrelloBoards());

        LOGGER.info("Starting filtering boards...");

        List<TrelloBoard> filteredBoards = trelloBoards.stream()
                .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());

        LOGGER.info("Boards have been filtered. Current list size: " + filteredBoards.size());

        return trelloMapper.mapToBoardListDto(filteredBoards);

    }
}
