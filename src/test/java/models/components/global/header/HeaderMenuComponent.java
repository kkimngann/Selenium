package models.components.global.header;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCSSSelector(".header")
public class HeaderMenuComponent extends Component {

    public HeaderMenuComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickShoppingCart(){
        component.findElement(By.cssSelector("#topcartlink")).click();
        try{Thread.sleep(5000);}
        catch (Exception ignore){}
    }

    public void waitingUntilExistSucessfullMessage() {
        String successMsg = "The product has been added to your shopping cart";
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#bar-notification"), successMsg));
        } catch (TimeoutException ignore) {
        }
    }

    public void clickRegister(){
        component.findElement(By.cssSelector(".ico-register")).click();
    }

    public void clickLogin(){
        component.findElement(By.cssSelector(".ico-login")).click();
    }
}
