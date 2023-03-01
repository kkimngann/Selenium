package test_flows.computer;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailPage;
import org.openqa.selenium.WebDriver;

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
        computerItemDetailPage.headerMenuComponent().waitingUntilExistSucessfullMessage();
        computerItemDetailPage.headerMenuComponent().clickShoppingCart();
    }

}
