package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@ComponentCSSSelector("#checkout-step-payment-method")
public class CheckoutStepPaymentMethodComponent extends Component {
    private static final By buttonContinueSel = By.cssSelector("input[class$=\"payment-method-next-step-button\"]");
    private static final By radioOptionPaymentMethodSel = By.cssSelector(".payment-details");
    private static final By radioOptionPaymentMethodInputSel = By.cssSelector("input[id^=\"paymentmethod\"]");
    private static final By radioOptionPaymentMethodLabelSel = By.cssSelector("label[for^=\"paymentmethod\"]");
    public CheckoutStepPaymentMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public void selectBtnContinue(){
        component.findElement(buttonContinueSel).click();
    }
    public void selectPaymentMethod(String text){
        List<WebElement> listOptionElement = component.findElements(radioOptionPaymentMethodSel);
        for (WebElement webElement : listOptionElement) {
            if(webElement.findElement(radioOptionPaymentMethodLabelSel).getText().replace(" ","").contains(text.replace(" ",""))){
                if(webElement.findElement(radioOptionPaymentMethodInputSel).getAttribute("checked") == null){
                    webElement.click();
                    return;
                }
            }
        }
    }

    public String getPaymentMethod() {
        String paymentMethod="";
        List<WebElement> listOptionElement = component.findElements(radioOptionPaymentMethodSel);
        for (WebElement webElement : listOptionElement) {
            if(webElement.findElement(radioOptionPaymentMethodInputSel).getAttribute("checked")!= null &&
                    webElement.findElement(radioOptionPaymentMethodInputSel).getAttribute("checked").equals("checked")){
                paymentMethod = webElement.findElement(radioOptionPaymentMethodLabelSel).getText().trim();
            }
        }
        return paymentMethod;
    }
}
