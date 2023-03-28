package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(".billing-info")
public class ConfirmOrderBillingInfoComponent extends Component {
    private static final By nameSel  =By.cssSelector(".name");
    private static final By emailSel  =By.cssSelector(".email");
    private static final By phoneSel  =By.cssSelector(".phone");
    private static final By faxSel  =By.cssSelector(".fax");
    private static final By address1Sel  =By.cssSelector(".address1");
    private static final By cityStateZipSel  =By.cssSelector(".city-state-zip");
    private static final By countrySel  =By.cssSelector(".country");
    private static final By paymentMethodSel  =By.cssSelector(".payment-method");
    public ConfirmOrderBillingInfoComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public String getName(){
        return component.findElement(nameSel).getText().trim();
    }
    public String getEmail(){
        return component.findElement(emailSel).getText().trim();
    }
    public String getPhone(){
        return component.findElement(phoneSel).getText().trim();
    }
    public String getFax(){
        return component.findElement(faxSel).getText().trim();
    }
    public String getAddress1(){
        return component.findElement(address1Sel).getText().trim();
    }
    public String getCityStateZip(){
        return component.findElement(cityStateZipSel).getText().trim();
    }
    public String getCountry(){
        return component.findElement(countrySel).getText().trim();
    }
    public String getPaymentMethod(){
        return component.findElement(paymentMethodSel).getText().trim();
    }
}
