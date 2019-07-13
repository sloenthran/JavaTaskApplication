package pl.nogacz.tasks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Dawid Nogacz on 13.07.2019
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Task not found")
class TaskNotFoundException extends Exception {
}
