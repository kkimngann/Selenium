package models.components.global;

import models.components.Component;
import models.components.ComponentCSSSelector;
import models.components.ComponentXpathSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentXpathSelector("//form[contains(@action, \"/login\")]")
public class LoginComponent extends Component {

    private static By inputEmailSelector =By.cssSelector("#Email");
    private static By inputPasswordSelector =By.cssSelector("#Password");
    private static By buttonLoginSelector =By.cssSelector("[type=\"submit\"]");
    public LoginComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void inputEmail(String email){
        component.findElement(inputEmailSelector).sendKeys(email);
    }

    public void inputPassword(String password){
        component.findElement(inputPasswordSelector).sendKeys(password);
    }

    public void selectLogin(){
        component.findElement(buttonLoginSelector).click();
    }
}
