package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@ComponentCSSSelector("#checkout-step-shipping-method")
public class CheckoutStepShippingMethodComponent extends Component {
    private static final By buttonContinueSel = By.cssSelector("input[class$=\"shipping-method-next-step-button\"]");
    private static final By radioOptionShippingMethodSel = By.cssSelector("input[id^=\"shippingoption\"]");
    public CheckoutStepShippingMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public void selectBtnContinue(){
        component.findElement(buttonContinueSel).click();
    }
    public void selectShippingMethod(String text){
        List<WebElement> listOptionElement = component.findElements(radioOptionShippingMethodSel);
        for (WebElement webElement : listOptionElement) {
            if(webElement.getAttribute("value").contains(text.replace(" ",""))){
                if(!webElement.getAttribute("checked").trim().equals("checked")){
                    webElement.click();
                    return;
                }
            }
        }
    }
}
