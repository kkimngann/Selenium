package models.components.cart;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(".cart-item-row")
public class CartItemRowComponent extends Component {

    private static final By unitPriceSel = By.cssSelector(".product-unit-price");
    private static final By qtyInputSel = By.cssSelector(".qty-input");
    private static final By subTotalSel = By.cssSelector(".product-subtotal");

    public CartItemRowComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double unitPrice(){
        return Double.parseDouble(component.findElement(unitPriceSel).getText().trim());
    }

    public double qtyInput(){
        return Double.parseDouble(component.findElement(qtyInputSel).getAttribute("value").trim());
    }

    public double subTotal(){
        return Double.parseDouble(component.findElement(subTotalSel).getText().trim());
    }






}
