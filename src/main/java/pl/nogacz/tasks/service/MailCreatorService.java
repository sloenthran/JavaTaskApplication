package pl.nogacz.tasks.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.nogacz.tasks.config.AdminConfig;
import pl.nogacz.tasks.config.CompanyConfig;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MailCreatorService {
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    private AdminConfig adminConfig;
    private CompanyConfig companyConfig;
    private DbService dbService;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allow sending tasks to Trello");

        Context context = createInitContext(message);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card", context);
    }

    public String buildNumberOfTasksMail(String message) {
        List<String> tasks = new ArrayList<>();
        dbService.getAllTasks().stream().forEach(task -> tasks.add(task.getTitle()));

        Context context = createInitContext(message);
        context.setVariable("tasks_list", tasks);
        return templateEngine.process("mail/number-tasks-mail", context);
    }

    private Context createInitContext(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://sloenthran.github.io/JavaTaskApplication/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "See you later...");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_mail", companyConfig.getCompanyMail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);

        return context;
    }
}
