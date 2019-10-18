package pl.nogacz.tasks.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.nogacz.tasks.config.AdminConfig;

@Service
@AllArgsConstructor
public class MailCreatorService {
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.mail}")
    private String companyMail;

    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://sloenthran.github.io/JavaTaskApplication/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "See you later...");
        context.setVariable("company_name", companyName);
        context.setVariable("company_mail", companyMail);
        return templateEngine.process("mail/created-trello-card", context);
    }
}
