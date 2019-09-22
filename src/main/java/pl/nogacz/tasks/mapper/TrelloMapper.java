package pl.nogacz.tasks.mapper;

import org.springframework.stereotype.Component;
import pl.nogacz.tasks.domain.TrelloBoard;
import pl.nogacz.tasks.domain.TrelloList;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.domain.dto.TrelloListDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

    public List<TrelloBoard> mapToBoardList(final List<TrelloBoardDto> trelloBoardsDto) {
        return trelloBoardsDto.stream()
                .map(this::mapToBoard)
                .collect(Collectors.toList());
    }

    public List<TrelloBoardDto> mapToBoardListDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(this::mapToBoardDto)
                .collect(Collectors.toList());
    }

    public TrelloBoard mapToBoard(final TrelloBoardDto trelloBoardDto) {
        return new TrelloBoard(
                trelloBoardDto.getId(),
                trelloBoardDto.getName(),
                this.mapToList(trelloBoardDto.getLists())
        );
    }

    public TrelloBoardDto mapToBoardDto(final TrelloBoard trelloBoard) {
        return new TrelloBoardDto(
                trelloBoard.getId(),
                trelloBoard.getName(),
                this.mapToListDto(trelloBoard.getLists())
        );
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(trelloList -> new TrelloList(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(trelloList -> new TrelloListDto(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(Collectors.toList());
    }
}
