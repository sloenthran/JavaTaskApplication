package pl.nogacz.tasks.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.nogacz.tasks.config.AdminConfig;
import pl.nogacz.tasks.domain.Mail;
import pl.nogacz.tasks.repository.TaskRepository;
import pl.nogacz.tasks.service.SimpleEmailService;

/**
 * @author Dawid Nogacz on 12.08.2019
 */
@Component
@AllArgsConstructor
public class EmailScheduler {
    private SimpleEmailService simpleEmailService;
    private TaskRepository taskRepository;
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();

        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                null,
                "JavaTask: Once a dau email",
                "Currently in database you got: " + size + " " + getCorrectTipTask(size)
        ));
    }

    public String getCorrectTipTask(long number) {
        return number == 1 ? "task" : "tasks";
    }
}
