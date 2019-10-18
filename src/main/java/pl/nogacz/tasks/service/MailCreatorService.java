package pl.nogacz.tasks.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.nogacz.tasks.config.AdminConfig;
import pl.nogacz.tasks.config.CompanyConfig;

@Service
@AllArgsConstructor
public class MailCreatorService {
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    private AdminConfig adminConfig;
    private CompanyConfig companyConfig;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://sloenthran.github.io/JavaTaskApplication/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "See you later...");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_mail", companyConfig.getCompanyMail());
        return templateEngine.process("mail/created-trello-card", context);
    }
}
