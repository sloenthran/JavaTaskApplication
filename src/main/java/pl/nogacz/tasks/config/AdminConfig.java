package pl.nogacz.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Dawid Nogacz on 12.08.2019
 */
@Component
@Getter
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;
}
