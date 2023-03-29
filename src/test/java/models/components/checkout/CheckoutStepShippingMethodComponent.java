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
    private static final By radioOptionShippingMethodSel = By.cssSelector(".method-name");
    private static final By radioOptionShippingMethodInputSel = By.cssSelector("input[id^=\"shippingoption\"]");
    private static final By radioOptionShippingMethodLabelSel = By.cssSelector("label[for^=\"shippingoption\"]");
    public CheckoutStepShippingMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public void selectBtnContinue(){
        component.findElement(buttonContinueSel).click();
    }
    public void selectShippingMethod(String text){
        List<WebElement> listOptionElement = component.findElements(radioOptionShippingMethodSel);
        for (WebElement webElement : listOptionElement) {
            WebElement inputElem = webElement.findElement(radioOptionShippingMethodInputSel);
            WebElement labelElem = webElement.findElement(radioOptionShippingMethodLabelSel);
            if(labelElem.getText().replace(" ", "").contains(text.replace(" ",""))){
                if(!inputElem.getAttribute("checked").trim().equals("checked")){
                    inputElem.click();
                    return;
                }
            }
        }
    }

    public String getShippingMethod() {
        String shippingMethod="";
        List<WebElement> listOptionElement = component.findElements(radioOptionShippingMethodSel);
        for (WebElement webElement : listOptionElement) {
            if(webElement.findElement(radioOptionShippingMethodInputSel).getAttribute("checked")!= null &&
                    webElement.findElement(radioOptionShippingMethodInputSel).getAttribute("checked").equals("checked")){
                shippingMethod = webElement.findElement(radioOptionShippingMethodLabelSel).getText().trim();
            }
        }
        return shippingMethod;
    }
}
