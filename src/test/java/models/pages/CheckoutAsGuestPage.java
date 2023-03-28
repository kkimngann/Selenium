package models.pages;

import models.components.checkout.*;
import models.components.global.LoginComponent;
import org.openqa.selenium.WebDriver;

public class CheckoutAsGuestPage extends BasePage{
    public CheckoutAsGuestPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutStepBillingComponent checkoutStepBillingComponent(){
        return findComponent(CheckoutStepBillingComponent.class, driver);
    }
    public CheckoutStepConfirmOrderComponent checkoutStepConfirmOrderComponent(){
        return findComponent(CheckoutStepConfirmOrderComponent.class, driver);
    }
    public CheckoutStepPaymentInfoComponent checkoutStepPaymentInfoComponent(){
        return findComponent(CheckoutStepPaymentInfoComponent.class, driver);
    }
    public CheckoutStepPaymentMethodComponent checkoutStepPaymentMethodComponent(){
        return findComponent(CheckoutStepPaymentMethodComponent.class, driver);
    }
    public CheckoutStepShippingComponent checkoutStepShippingComponent(){
        return findComponent(CheckoutStepShippingComponent.class, driver);
    }
    public CheckoutStepShippingMethodComponent checkoutStepShippingMethodComponent(){
        return findComponent(CheckoutStepShippingMethodComponent.class, driver);
    }
    public OrderCompletedComponent orderCompletedComponent(){
        return findComponent(OrderCompletedComponent.class, driver);
    }
}
