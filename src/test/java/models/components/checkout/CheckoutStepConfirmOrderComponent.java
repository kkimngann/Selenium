package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector("#checkout-step-confirm-order")
public class CheckoutStepConfirmOrderComponent extends Component {
    private static final By buttonContinueSel = By.cssSelector("input[class$=\"confirm-order-next-step-button\"]");
    public CheckoutStepConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public void selectBtnContinue(){
        component.findElement(buttonContinueSel).click();
    }

    public ConfirmOrderBillingInfoComponent confirmOrderBillingInfoComponent(){
        return findComponent(ConfirmOrderBillingInfoComponent.class, driver);
    }
    public ConfirmOrderShippingInfoComponent confirmOrderShippingInfoComponent(){
        return findComponent(ConfirmOrderShippingInfoComponent.class,driver);
    }


}
