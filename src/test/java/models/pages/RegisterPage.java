package models.pages;

import models.components.registration.RegistrationComponent;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    public RegistrationComponent registrationComponent(){
        return findComponent(RegistrationComponent.class, driver);
    }
}

