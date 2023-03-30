package models.components.product;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@ComponentCSSSelector(value=".product-grid")
public class ProductGridComponent extends Component {
    public ProductGridComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<ProductItemComponent> productItemComponentList(){
        return findComponents(ProductItemComponent.class, driver);
    }
}
