package models.components.order;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector("table.cart")
public class ProductListInCartComponent extends Component {
    public ProductListInCartComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

}
