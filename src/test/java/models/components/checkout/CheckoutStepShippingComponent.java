package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector("#checkout-step-shipping")
public class CheckoutStepShippingComponent extends Component {
    private static final By buttonContinueSel = By.cssSelector("input[class$=\"new-address-next-step-button\"]");
    private static final By checkboxPickInStoreSel = By.cssSelector("#PickUpInStore");
    public CheckoutStepShippingComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectPickUpInStore(){
        WebElement agreeTermOfServiceElement = component.findElement(checkboxPickInStoreSel);
        if(!agreeTermOfServiceElement.isSelected()){
            agreeTermOfServiceElement.click();
        }
    }

    public void selectBtnContinue(){
        component.findElement(buttonContinueSel).click();
    }
}
