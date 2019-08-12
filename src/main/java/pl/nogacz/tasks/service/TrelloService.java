package pl.nogacz.tasks.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nogacz.tasks.client.TrelloClient;
import pl.nogacz.tasks.config.AdminConfig;
import pl.nogacz.tasks.domain.CreatedTrelloCard;
import pl.nogacz.tasks.domain.Mail;
import pl.nogacz.tasks.domain.dto.TrelloBoardDto;
import pl.nogacz.tasks.domain.dto.TrelloCardDto;

import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * @author Dawid Nogacz on 12.08.2019
 */
@Service
@AllArgsConstructor
public class TrelloService {
    private TrelloClient trelloClient;
    private SimpleEmailService emailService;
    private AdminConfig adminConfig;

    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCard createNewCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(
                adminConfig.getAdminMail(),
                null,
                "JavaTaskApplication: New Trello card",
                "New card: " + card.getName() + " has been created on your Trello account"
        )));

        return newCard;
    }
}
