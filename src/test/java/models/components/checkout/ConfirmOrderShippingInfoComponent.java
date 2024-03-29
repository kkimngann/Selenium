package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(".shipping-info")
public class ConfirmOrderShippingInfoComponent extends Component {
    private static final By nameSel  =By.cssSelector(".name");
    private static final By emailSel  =By.cssSelector(".email");
    private static final By phoneSel  =By.cssSelector(".phone");
    private static final By faxSel  =By.cssSelector(".fax");
    private static final By address1Sel  =By.cssSelector(".address1");
    private static final By address2Sel  =By.cssSelector(".address2");
    private static final By companySel  =By.cssSelector(".company");
    private static final By cityStateZipSel  =By.cssSelector(".city-state-zip");
    private static final By countrySel  =By.cssSelector(".country");
    private static final By shippingMethodSel  =By.cssSelector(".shipping-method");
    public ConfirmOrderShippingInfoComponent(WebDriver driver, WebElement component) {
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
    public String getAddress2(){
        return component.findElement(address2Sel).getText().trim();
    }
    public String getCityStateZip(){
        return component.findElement(cityStateZipSel).getText().trim();
    }
    public String getCountry(){
        return component.findElement(countrySel).getText().trim();
    }
    public String getShippingMethod(){
        return component.findElement(shippingMethodSel).getText().trim();
    }

    public String getCompany() {
        return component.findElement(companySel).getText().trim();
    }
}
