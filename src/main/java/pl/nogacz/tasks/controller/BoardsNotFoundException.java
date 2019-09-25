package pl.nogacz.tasks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Dawid Nogacz on 05.08.2019
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Boards not found")
public class BoardsNotFoundException extends Exception {
}
