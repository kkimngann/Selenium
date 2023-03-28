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
    private static final By priceTableSel = By.cssSelector(".cart-total tr");
    private static final By priceTypeSel = By.cssSelector(".cart-total-left");
    private static final By priceValueSel = By.cssSelector(".cart-total-right");
    private static final By termOfServiceSel = By.cssSelector("#termsofservice");
    private static final By buttonCheckoutSel = By.cssSelector(".checkout-buttons");
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

    public void agreeTermOfService(){
        WebElement agreeTermOfServiceElement = component.findElement(termOfServiceSel);
        if(!agreeTermOfServiceElement.isSelected()){
            agreeTermOfServiceElement.click();
        }
    }

    public void selectCheckout(){
        WebElement btnCheckoutElement = component.findElement(buttonCheckoutSel);
        btnCheckoutElement.click();
    }
}
