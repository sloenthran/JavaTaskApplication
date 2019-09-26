package pl.nogacz.tasks.trello.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.nogacz.tasks.controller.BoardsNotFoundException;
import pl.nogacz.tasks.domain.TrelloBoard;
import pl.nogacz.tasks.domain.TrelloCard;
import pl.nogacz.tasks.domain.dto.CreatedTrelloCardDto;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.domain.dto.TrelloCardDto;
import pl.nogacz.tasks.mapper.TrelloMapper;
import pl.nogacz.tasks.service.TrelloService;
import pl.nogacz.tasks.trello.validator.TrelloValidator;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TrelloFacade {
    private TrelloService trelloService;
    private TrelloMapper trelloMapper;
    private TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoardList(trelloService.getTrelloBoards());

        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        return trelloMapper.mapToBoardListDto(filteredBoards);
    }

    public List<TrelloBoardDto> getSpecificTrelloBoard(String boardName) throws BoardsNotFoundException {
        List<TrelloBoardDto> boards = trelloService.getTrelloBoards().stream()
                .filter(board -> board.getName().contains(boardName))
                .collect(Collectors.toList());

        if(boards.isEmpty()) {
            throw new BoardsNotFoundException();
        }

        return boards;
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        trelloValidator.validateCard(trelloCard);

        return trelloService.createNewCard(trelloMapper.mapToCardDto(trelloCard));
    }
}
