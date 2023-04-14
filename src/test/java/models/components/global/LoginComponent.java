package models.components.global;

import models.components.Component;
import models.components.ComponentCSSSelector;
import models.components.ComponentXpathSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentXpathSelector("//form[contains(@action, \"/login\")]")
public class LoginComponent extends Component {

    private static final By inputEmailSelector =By.cssSelector("#Email");
    private static final By inputPasswordSelector =By.cssSelector("#Password");
    private static final By buttonLoginSelector =By.cssSelector("[type=\"submit\"]");
    private static final By loginErrorMessageSelector =By.cssSelector(".validation-summary-errors");
    public LoginComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void inputEmail(String email){
        if(email != null && !email.equals(""))
        component.findElement(inputEmailSelector).sendKeys(email);
    }

    public void inputPassword(String password){
        if(password != null && !password.equals("")){
            component.findElement(inputPasswordSelector).sendKeys(password);
        }
    }

    public void selectLogin(){
        component.findElement(buttonLoginSelector).click();
    }

    public String getErrorMessage(){
        return component.findElement(loginErrorMessageSelector).getText();
    }
}
