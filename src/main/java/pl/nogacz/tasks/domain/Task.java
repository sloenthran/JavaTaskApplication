package pl.nogacz.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dawid Nogacz on 03.07.2019
 */

@Getter
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String content;
}
