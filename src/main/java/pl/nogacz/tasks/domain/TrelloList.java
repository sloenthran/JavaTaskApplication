package pl.nogacz.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrelloList {
    private String id;
    private String name;
    private boolean isClosed;
}
