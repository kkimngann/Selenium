package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.ui.SelectEx;

@ComponentCSSSelector("#checkout-step-payment-info")
public class CheckoutStepPaymentInfoComponent extends Component {
    private static final By buttonContinueSel = By.cssSelector("input[class$=\"payment-info-next-step-button\"]");
    private static final By creditCardTypeSel = By.cssSelector("#CreditCardType");
    private static final By cardholderNameSel = By.cssSelector("#CardholderName");
    private static final By cardNumberSel = By.cssSelector("#CardNumber");
    private static final By expireMonthSel = By.cssSelector("#ExpireMonth");
    private static final By expireYearSel = By.cssSelector("#ExpireYear");

    private static final By cardCodeSel = By.cssSelector("#CardCode");
    public CheckoutStepPaymentInfoComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectBtnContinue(){
        component.findElement(buttonContinueSel).click();
    }

    public void selectCardType(String text){
        WebElement dropdownElem = component.findElement(creditCardTypeSel);
        SelectEx select = new SelectEx(dropdownElem);
        select.selectByVisibleText(text);
    }

    public void inputCardholderName(String text){
        component.findElement(cardholderNameSel).sendKeys(text);
    }

    public void inputCardNumber(String text){
        component.findElement(cardNumberSel).sendKeys(text);
    }
    public void selectMonth(String text){
        WebElement dropdownElem = component.findElement(expireMonthSel);
        SelectEx select = new SelectEx(dropdownElem);
        select.selectByVisibleText(text);
    }
    public void selectYear(String text){
        WebElement dropdownElem = component.findElement(expireYearSel);
        SelectEx select = new SelectEx(dropdownElem);
        select.selectByVisibleText(text);
    }
    public void inputCardCode(String text){
        component.findElement(cardCodeSel).sendKeys(text);
    }
}
