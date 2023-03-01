package tests.computer;

import models.components.order.CheapComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyCheapComputer extends BaseTest implements Urls {

    public BuyCheapComputer(WebDriver driver) {
        super(driver);
    }

    @Test
    public void testBuyCheapComputer(){
        String pageUrl = demoBaseUrl + "build-your-cheap-own-computer";
        driver.get(pageUrl);
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver, CheapComputerComponent.class);
        orderComputerFlow.buildComputerSpecAndAddToCard("2.2GHz", "4GB");
    }
}
