package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.ui.SelectEx;

@ComponentCSSSelector("#checkout-step-billing")
public class CheckoutStepBillingComponent extends Component {

    private static final String NEW_ADDRESS_TXT = "New Address";
    private static final By billingAddressSelectSel = By.cssSelector("#billing-address-select");
    private static final By billingNewAddress_FirstNameSel = By.cssSelector("#BillingNewAddress_FirstName");
    private static final By billingNewAddress_LastNameSel = By.cssSelector("#BillingNewAddress_LastName");
    private static final By billingNewAddress_EmailSel = By.cssSelector("#BillingNewAddress_Email");
    private static final By billingNewAddress_CompanySel = By.cssSelector("#BillingNewAddress_Company");
    private static final By billingNewAddress_CountryIdSel = By.cssSelector("#BillingNewAddress_CountryId");
    private static final By billingNewAddress_StateProvinceIdSel = By.cssSelector("#BillingNewAddress_StateProvinceId");
    private static final By billingNewAddress_CitySel = By.cssSelector("#BillingNewAddress_City");
    private static final By billingNewAddress_Address1Sel = By.cssSelector("#BillingNewAddress_Address1");
    private static final By billingNewAddress_Address2Sel = By.cssSelector("#BillingNewAddress_Address2");
    private static final By billingNewAddress_ZipPostalCodeSel = By.cssSelector("#BillingNewAddress_ZipPostalCode");
    private static final By billingNewAddress_PhoneNumberSel = By.cssSelector("#BillingNewAddress_PhoneNumber");
    private static final By billingNewAddress_FaxNumberSel = By.cssSelector("#BillingNewAddress_FaxNumber");
    private static final By buttonContinueSel = By.cssSelector("input[class$=\"new-address-next-step-button\"]");
    public CheckoutStepBillingComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectAddNewAddress(){
        WebElement dropdownElem = component.findElement(billingAddressSelectSel);
        SelectEx select = new SelectEx(dropdownElem);
        select.selectByVisibleText(NEW_ADDRESS_TXT);
    }

    public void inputFirstName(String text){
        component.findElement(billingNewAddress_FirstNameSel).sendKeys(text);
    }
    public void inputLastName(String text){
        component.findElement(billingNewAddress_LastNameSel).sendKeys(text);
    }
    public void inputEmail(String text){
        component.findElement(billingNewAddress_EmailSel).sendKeys(text);
    }
    public void inputCompany(String text){
        component.findElement(billingNewAddress_CompanySel).sendKeys(text);
    }
    public void inputCity(String text){
        component.findElement(billingNewAddress_CitySel).sendKeys(text);
    }
    public void inputAddress1(String text){
        component.findElement(billingNewAddress_Address1Sel).sendKeys(text);
    }
    public void inputAddress2(String text){
        component.findElement(billingNewAddress_Address2Sel).sendKeys(text);
    }
    public void inputZipPostalCode(String text) {
        component.findElement(billingNewAddress_ZipPostalCodeSel).sendKeys(text);
    }
    public void inputPhoneNumber(String text){
        component.findElement(billingNewAddress_PhoneNumberSel).sendKeys(text);
    }
    public void inputFaxNumber(String text){
        component.findElement(billingNewAddress_FaxNumberSel).sendKeys(text);
    }
    public void selectCountry(String text){
        WebElement dropdownElem = component.findElement(billingNewAddress_CountryIdSel);
        SelectEx select = new SelectEx(dropdownElem);
        select.selectByVisibleText(text);
    }
    public void selectStateProvide(String text)
    {
        WebElement dropdownElem = component.findElement(billingNewAddress_StateProvinceIdSel);
        SelectEx select = new SelectEx(dropdownElem);
        select.selectByVisibleText(text);
    }
    public void selectBtnContinue(){
        component.findElement(buttonContinueSel).click();
    }
}
