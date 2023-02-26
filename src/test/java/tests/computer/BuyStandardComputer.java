package tests.computer;

import models.components.order.CheapComputerComponent;
import models.components.order.StandardComputerComponent;
import org.testng.annotations.Test;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;
import url.Urls;

public class BuyStandardComputer extends BaseTest implements Urls {

    @Test
    public void testBuyStandardComputer(){
        String pageUrl = demoBaseUrl + "build-your-own-computer";
        driver.get(pageUrl);
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver, StandardComputerComponent.class);
        orderComputerFlow.buildComputerSpecAndAddToCard();
    }
}


