package pl.nogacz.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dawid Nogacz on 08.08.2019
 */
@Getter
@AllArgsConstructor
public class Mail {
    private String mailTo;
    private String toCc;
    private String subject;
    private String message;
}
