package pl.nogacz.tasks.integration.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.nogacz.tasks.integration.util.DriverType;
import pl.nogacz.tasks.integration.util.Resources;

public class WebDriverConfig {
    public static WebDriver getDriver(DriverType driverType) {
        System.setProperty("webdriver.gecko.driver", Resources.getPath("geckodriver.exe"));
        System.setProperty("webdriver.chrome.driver", Resources.getPath("chromedriver.exe"));

        if(driverType.isChrome()) {
            return new ChromeDriver();
        } else if(driverType.isFirefox()) {
            return new FirefoxDriver();
        } else {
            return null;
        }
    }
}
