package tests.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.Test;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyCheapComputer extends BaseTest implements Urls {

    @Test
    public void testBuyCheapComputer(){
        String pageUrl = demoBaseUrl + "build-your-cheap-own-computer";
        driver.get(pageUrl);
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver, CheapComputerComponent.class);
        orderComputerFlow.buildComputerSpecAndAddToCard();
    }
}
