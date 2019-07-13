package pl.nogacz.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Dawid Nogacz on 03.07.2019
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {
    private Long id;
    private String title;
    private String content;
}
