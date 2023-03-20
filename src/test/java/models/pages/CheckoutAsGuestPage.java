package models.pages;

import models.components.global.LoginComponent;
import org.openqa.selenium.WebDriver;

public class CheckoutAsGuestPage extends BasePage{
    public CheckoutAsGuestPage(WebDriver driver) {
        super(driver);
    }

    public LoginComponent loginComponent(){
        return findComponent(LoginComponent.class,driver);
    }
}
