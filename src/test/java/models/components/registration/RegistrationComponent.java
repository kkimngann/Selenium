package models.components.registration;

import models.components.Component;
import models.components.ComponentCSSSelector;
import models.components.ComponentXpathSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@ComponentXpathSelector("//div[@class= \"page registration-page\"]")
public class RegistrationComponent extends Component {
    private static final By inputGenderSel = By.cssSelector(".gender");
    private static final By inputGenderClickableSel = By.cssSelector("input");
    private static final By inputFirstNameSel = By.cssSelector("#FirstName");
    private static final By inputLastNameSel = By.cssSelector("#LastName");
    private static final By inputEmailSel = By.cssSelector("#Email");
    private static final By inputPasswordSel = By.cssSelector("#Password");
    private static final By inputConfirmPasswordSel = By.cssSelector("#ConfirmPassword");
    private static final By buttonRegisterSel = By.cssSelector("#register-button");

    public RegistrationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void inputGender(String gender){
        List<WebElement> genderOptionElem = component.findElements(inputGenderSel);
        for (WebElement webElement : genderOptionElem) {
            if(webElement.getText().trim().equals(gender)){
                webElement.findElement(inputGenderClickableSel).click();
                break;
            }
        }
    }

    public void inputFirstName(String name){
        component.findElement(inputFirstNameSel).sendKeys(name);
    }

    public void inputLastName(String name){
        component.findElement(inputLastNameSel).sendKeys(name);
    }

    public void inputEmail(String email){
        component.findElement(inputEmailSel).sendKeys(email);
    }

    public void inputPassword(String password){
        component.findElement(inputPasswordSel).sendKeys(password);
    }

    public void inputConfirmPassword(String password){
        component.findElement(inputConfirmPasswordSel).sendKeys(password);
    }

    public void selectRegister(){
        component.findElement(buttonRegisterSel).click();
    }

    public String getErrorMessage(String messageType){
        return component.findElement(By.cssSelector(".field-validation-error [for=\""  + messageType +"\"]")).getText().trim();
    }

}
