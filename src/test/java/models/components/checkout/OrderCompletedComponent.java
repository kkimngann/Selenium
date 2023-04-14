package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitMoreThanOneTab;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ComponentCSSSelector("div[class$=\"order-completed\"]")
public class OrderCompletedComponent extends Component {
    private static final By orderNumberSel = By.cssSelector(".details li");
    private static final By orderLinkSel = By.cssSelector("a[href^=\"/orderdetails\"]");
    public OrderCompletedComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public String getOrderNumber() {
        String orderNumber = "";
        String strOrderNumberFull = component.findElement(orderNumberSel).getText();
        String pattern = ".*:(.*)";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(strOrderNumberFull);
        if(matcher.find()){
            orderNumber = matcher.group(1);
        }
        return orderNumber;
    }

    public void selectViewOrderDetailAndSwitchTab(){
        component.findElement(orderLinkSel).click();
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                wait.until(new WaitMoreThanOneTab());
                break;
            }
        }
    }
}
