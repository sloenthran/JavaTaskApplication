package pl.nogacz.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrelloCard {
    private String name;
    private String description;
    private String position;
    private String listId;
}
