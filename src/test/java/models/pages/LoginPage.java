package models.pages;

import models.components.global.LoginComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public LoginComponent loginComponent(){
        return findComponent(LoginComponent.class, driver);
    }
}
