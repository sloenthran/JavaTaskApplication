package pl.nogacz.tasks.integration.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.nogacz.tasks.integration.config.WebDriverConfig;
import pl.nogacz.tasks.integration.util.DriverType;

public class Ebay {
    public static void main(String[] args) {
        WebDriver webDriver = WebDriverConfig.getDriver(DriverType.CHROME);
        webDriver.get("https://www.ebay.pl/");

        WebElement webElement = webDriver.findElement(By.id("gh-ac"));
        webElement.sendKeys("Laptop");
        webElement.submit();
    }
}
