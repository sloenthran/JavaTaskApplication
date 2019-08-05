package pl.nogacz.tasks.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Dawid Nogacz on 05.08.2019
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloCardDto {
    private String name;
    private String description;
    private String position;
    private String listId;
}
