package Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Driver {

    public WebDriver driver;

    public Driver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        this.driver = driver;
    }

    public WebElement findByXpath(String xpath){
        return this.driver.findElement(new By.ByXPath(xpath));
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public String getAttribute(WebElement element, String attributeName){
        return element.getAttribute(attributeName);
    }

    public void click(WebElement element){
        element.click();
    }

    public void setText(WebElement element,String text){
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void selectOption(WebElement selectBox, String textSelected){
        selectBox.click();
        String textSelectedXpath = "//*[contains(text(),\"" + textSelected + "\")]";
        WebElement textSelectedElement = selectBox.findElement(new By.ByXPath(textSelectedXpath));
        textSelectedElement.click();
    }

    public void scrollToElement (WebElement element){
        Actions actions = new Actions(this.driver);
        actions.moveToElement(element);
        actions.perform();
    }

}
