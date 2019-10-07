package pl.nogacz.tasks.integration.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pl.nogacz.tasks.integration.config.WebDriverConfig;
import pl.nogacz.tasks.integration.util.DriverType;

public class Facebook {
    public static void main(String[] args) {
        WebDriver webDriver = WebDriverConfig.getDriver(DriverType.CHROME);
        webDriver.get("https://facebook.com/r.php");

        WebElement firstname = webDriver.findElement(By.xpath("//input[@id='u_0_p']"));
        firstname.sendKeys("Dawid");

        WebElement lastname = webDriver.findElement(By.xpath("//input[@id='u_0_r']"));
        lastname.sendKeys("Nogacz");

        WebElement email = webDriver.findElement(By.xpath("//input[@id='u_0_u']"));
        email.sendKeys("sloenthran@gmail.com");

        WebElement day = webDriver.findElement(By.xpath("//select[@id='day']"));
        Select selectedDay = new Select(day);
        selectedDay.selectByValue("15");

        WebElement month = webDriver.findElement(By.xpath("//select[@id='month']"));
        Select selectedMonth = new Select(month);
        selectedMonth.selectByValue("9");

        WebElement year = webDriver.findElement(By.xpath("//select[@id='year']"));
        Select selectedYear = new Select(year);
        selectedYear.selectByValue("1995");
    }
}
