package integration.tests;

import integration.config.WebDriverConfig;
import integration.util.DriverType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Ebay {
    @Test
    public void testChrome() {
        testWebsite(DriverType.CHROME);
    }

    @Test
    public void testFirefox() {
        testWebsite(DriverType.FIREFOX);
    }

    private void testWebsite(DriverType driverType) {
        WebDriver webDriver = WebDriverConfig.getDriver(driverType);
        webDriver.get("https://www.ebay.pl/");

        WebElement webElement = webDriver.findElement(By.id("gh-ac"));
        webElement.sendKeys("Laptop");
        webElement.submit();
    }
}
