package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(".customer-blocks")
public class CustomerBlocksComponent extends Component {
    private static final By btnCheckoutAsGuestSel = By.cssSelector("input[class$=\"checkout-as-guest-button\"]");
    private static final By btnRegisterSel = By.cssSelector("input[class$=\"register-button\"]");

    public CustomerBlocksComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCheckoutAsGuest(){
        component.findElement(btnCheckoutAsGuestSel).click();
    }

    public void selectRegister(){
        component.findElement(btnRegisterSel).click();
    }


}
