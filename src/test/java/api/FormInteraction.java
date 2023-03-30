package api;

import Driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormInteraction {
    public static void main(String[] args) {
        //get a chrome session
        WebDriver driver = DriverFactory.getChromeDriver();

        //navigate to the page
        driver.get("https://the-internet.herokuapp.com/login");


        // find element
        WebElement usernameElem = driver.findElement(By.id("username"));
        WebElement passwordElem = driver.findElement(By.id("password"));


        //interation

        //quit
        driver.quit();
    }
}
