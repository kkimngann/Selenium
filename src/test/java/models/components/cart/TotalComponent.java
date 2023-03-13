package models.components.cart;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentCSSSelector(".cart-footer .totals")
public class TotalComponent extends Component {
    private static final By priceTableSel = By.cssSelector("");
    private static final By priceTypeSel = By.cssSelector("");
    private static final By priceValueSel = By.cssSelector("");
    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Map<String, Double> priceCategories(){
        Map<String, Double> priceCategories = new HashMap<>();
        List<WebElement> priceTableRowElement = component.findElements(priceTableSel);
        for (WebElement webElement : priceTableRowElement) {
            WebElement priceTypeElement = webElement.findElement(priceTypeSel);
            WebElement priceValueElement = webElement.findElement(priceValueSel);
            String priceType = priceTypeElement.getText().trim();
            double priceValue = Double.parseDouble(priceValueElement.getText().trim());
            priceCategories.put(priceType, priceValue);
        }
        return priceCategories;
    }
}
