package api;

import Driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.SelectEx;
import support.ui.WaitMoreThanOneTab;
import url.Urls;

import java.time.Duration;

public class ExplicitWait implements Urls {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try{
            driver.get(baseUrl + login);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(new WaitMoreThanOneTab());

            driver.quit();
        }
        catch(Exception e){
            e.printStackTrace();
            driver.quit();
        }
    }
}
