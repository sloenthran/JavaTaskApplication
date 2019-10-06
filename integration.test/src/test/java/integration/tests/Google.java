package integration.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import integration.config.WebDriverConfig;
import integration.util.DriverType;

public class Google {
    @Test
    public void chromeTest() {
        testWebsite(DriverType.CHROME);
    }

    @Test
    public void firefoxTest() {
        testWebsite(DriverType.FIREFOX);
    }

    private void testWebsite(DriverType driverType) {
        WebDriver webDriver = WebDriverConfig.getDriver(driverType);
        webDriver.get("https://google.com");

        WebElement searchField = webDriver.findElement(By.cssSelector(".gLFyf"));
        searchField.sendKeys("sloenthran");
        searchField.submit();
    }
}
