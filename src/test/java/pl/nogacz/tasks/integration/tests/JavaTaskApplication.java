package pl.nogacz.tasks.integration.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pl.nogacz.tasks.integration.config.WebDriverConfig;
import pl.nogacz.tasks.integration.util.DriverType;

public class JavaTaskApplication {
    public static void main(String[] args) {
        WebDriver webDriver = WebDriverConfig.getDriver(DriverType.CHROME);
        webDriver.get("https://sloenthran.github.io/JavaTaskApplication");

        WebElement taskName = webDriver.findElement(By.xpath("/html/body/main/section/form/fieldset/input"));
        taskName.sendKeys("Test task (Selenium)");

        WebElement taskContent = webDriver.findElement(By.xpath("/html/body/main/section/form/fieldset[2]/textarea"));
        taskContent.sendKeys("Test content (Selenium)");

        taskContent.submit();

        while(!webDriver.findElement(By.xpath("//select[1]")).isDisplayed());

        WebElement selectCombo = webDriver.findElement(By.xpath("//div[contains(@class, \"tasks-container\")]/form/div/fieldset/select[1]"));
        Select selectBoard = new Select(selectCombo);
        selectBoard.selectByIndex(1);
    }
}