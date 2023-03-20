package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow <T extends ComputerEssentialComponent>{
    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    public Double noOptionPrice = 0.0 ;
    public Double additionalProcessorPrice = 0.0 ;
    public Double additionalRAMPrice = 0.0 ;
    public Double additionalSoftwarePrice = 0.0 ;
    public Double additionalHDDPrice = 0.0;
    public Double additionalOSPrice = 0.0 ;
    public Double pricePerUnit = 0.0;




    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
    }
    public void buildComputerSpecAndAddToCard(String processor, String RAM, String software){
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        T computerEssentialCompo = computerItemDetailPage.computerComponent(computerEssentialComponent);
        noOptionPrice = computerEssentialCompo.getDefaultPrice();
        additionalProcessorPrice = getAdditionalPrice(computerEssentialCompo.selectProcessorType(processor));
        additionalRAMPrice = getAdditionalPrice(computerEssentialCompo.selectRAMType(RAM));
        additionalSoftwarePrice = getAdditionalPrice(computerEssentialCompo.selectSoftware(software));
        pricePerUnit = noOptionPrice+ additionalProcessorPrice + additionalRAMPrice + additionalSoftwarePrice + additionalHDDPrice + additionalOSPrice;

        computerEssentialCompo.selectAddToCart();


        //waiting for added to cart successful

    }

    public void gotoShoppingCart(){
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        computerItemDetailPage.headerMenuComponent().waitingUntilExistSucessfullMessage();
        computerItemDetailPage.headerMenuComponent().clickShoppingCart();
    }

    public void verifyShoppingCart(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComponent = shoppingCartPage.totalComponent();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        for (String priceType : priceCategories.keySet()){
            System.out.printf(priceType);
            System.out.println(priceCategories.get(priceType));
        }
        //verify qty
        CartItemRowComponent cartItemRowComponent = shoppingCartPage.cartItemRowComponents().get(0);
        Double actualQty = cartItemRowComponent.qtyInput();
        Double expectedQty = 1.0;
        Assert.assertEquals(actualQty, expectedQty, "Data do not match:\nActual: " + actualQty + "\nExpected: " + expectedQty);
        //verify value
        Double actualValue = cartItemRowComponent.subTotal();
        Double expectedValue = pricePerUnit;
        Assert.assertEquals(actualValue, expectedValue, "Data do not match:\nActual: " + actualValue + "\nExpected: " + expectedValue);

    }

    public double getAdditionalPrice(String fullTextOption){
        /*if(!fullTextOption.contains("[") || (!fullTextOption.contains("]"))){
            return 0;
        }
        String price = fullTextOption.substring(fullTextOption.indexOf("[")+1,fullTextOption.indexOf("]"));
        return Double.parseDouble(price);*/
        String pattern = ".*\\[\\+(.*)\\]";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(fullTextOption);
        if(matcher.find()){
            return Double.parseDouble(matcher.group(1));
        }
        return 0;

    }

}
