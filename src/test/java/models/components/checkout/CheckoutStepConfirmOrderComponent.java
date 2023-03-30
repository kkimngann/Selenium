package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import models.components.cart.TotalComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@ComponentCSSSelector("#checkout-step-confirm-order")
public class CheckoutStepConfirmOrderComponent extends Component {
    private static final By buttonContinueSel = By.cssSelector("input[class$=\"confirm-order-next-step-button\"]");
    private static final By buttonContinueThankYouSel = By.cssSelector("input[class$=\"order-completed-continue-button\"]");
    public CheckoutStepConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public void selectBtnContinue(){
        component.findElement(buttonContinueSel).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonContinueThankYouSel));

    }

    public ConfirmOrderBillingInfoComponent confirmOrderBillingInfoComponent(){
        return findComponent(ConfirmOrderBillingInfoComponent.class, driver);
    }
    public ConfirmOrderShippingInfoComponent confirmOrderShippingInfoComponent(){
        return findComponent(ConfirmOrderShippingInfoComponent.class,driver);
    }
    public TotalComponent totalComponent(){
        return findComponent(TotalComponent.class, driver);
    }


}
