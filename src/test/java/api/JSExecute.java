package api;

import Driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.ui.SelectEx;
import url.Urls;

public class JSExecute implements Urls {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try{
            driver.get(baseUrl + floatingMenu);
            //driver.get("https://learn.sdetpro.com/");

            //Scroll to bottom
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000);

            //Scroll up
            javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight,0);");
            Thread.sleep(2000);

            driver.quit();
        }
        catch(Exception e){
            e.printStackTrace();
            driver.quit();
        }
    }
}
