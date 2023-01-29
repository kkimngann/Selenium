package api;

import Driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.ui.SelectEx;
import url.Urls;

public class Dropdown implements Urls {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try{
            driver.get(baseUrl + dropdown);
            By dropdownSel = By.id("dropdown");
            WebElement dropdownElem = driver.findElement(dropdownSel);
            SelectEx select = new SelectEx(dropdownElem);
            select.selectByVisibleText("Option 1");
            Thread.sleep(1000);

            select.selectByIndex(2);
            Thread.sleep(1000);

            select.selectByValue("1");
            Thread.sleep(1000);

            select.selectOption1();
            driver.quit();
        }
        catch(Exception e){
            e.printStackTrace();
            driver.quit();
        }


    }
}
