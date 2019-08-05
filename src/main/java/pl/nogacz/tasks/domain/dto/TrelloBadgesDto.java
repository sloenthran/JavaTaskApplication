package pl.nogacz.tasks.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Dawid Nogacz on 05.08.2019
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBadgesDto {
    private int votes;

    @JsonProperty("attachmentsByType.trello.board")
    private int board;

    @JsonProperty("attachmentsByType.trello.card")
    private int card;
}
