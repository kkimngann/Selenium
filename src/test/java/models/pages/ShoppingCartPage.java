package models.pages;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.CustomerBlocksComponent;
import models.components.global.LoginComponent;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartItemRowComponent> cartItemRowComponents() {
        return findComponents(CartItemRowComponent.class, driver);
    }

    public TotalComponent totalComponent() {
        return findComponent(TotalComponent.class, driver);
    }

    public CustomerBlocksComponent customerBlocksComponent(){
        return findComponent(CustomerBlocksComponent.class, driver);
    }
    public LoginComponent loginComponent(){
        return findComponent(LoginComponent.class,driver);
    }
}

