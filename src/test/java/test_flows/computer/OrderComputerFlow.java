package test_flows.computer;

import models.components.cart.TotalComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class OrderComputerFlow <T extends ComputerEssentialComponent>{
    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;



    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
    }
    public void buildComputerSpecAndAddToCard(String processor, String RAM){
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        T computerEssentialCompo = computerItemDetailPage.computerComponent(computerEssentialComponent);
        computerEssentialCompo.selectProcessorType(processor);
        computerEssentialCompo.selectRAMType(RAM);
        computerEssentialCompo.selectAddToCart();

        //waiting for added to cart successful

    }

    public void gotoShoppingCart(){
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        //computerItemDetailPage.headerMenuComponent().waitingUntilExistSucessfullMessage();
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
        //verify product info


        //verify qty

        //verify value

    }

}
