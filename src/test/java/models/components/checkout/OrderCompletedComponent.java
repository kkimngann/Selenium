package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector("div[class$=\"order-completed\"]")
public class OrderCompletedComponent extends Component {
    public OrderCompletedComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
}
