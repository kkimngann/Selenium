package api;

import Driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class IFrame implements Urls {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get(baseUrl + iframe);
            By iframeSel = By.cssSelector("[id$=\"ifr\"]");
            WebElement iframeElem = driver.findElement(iframeSel);

            //Switch to iframe and interaction
            driver.switchTo().frame(iframeElem);
            WebElement editorInputElement = driver.findElement(By.id("tinymce"));
            editorInputElement.click();
            editorInputElement.clear();
            editorInputElement.sendKeys("New text value");
            Thread.sleep(1000);

            //Switch back to parent frame
            driver.switchTo().defaultContent();
            driver.findElement(By.linkText("Elemental Selenium")).click();
            Thread.sleep(1000);

            driver.quit();
        }

        catch(Exception e){
            e.printStackTrace();
            driver.quit();
        }

    }
}
