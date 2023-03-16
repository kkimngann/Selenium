package tests.computer;

import models.components.order.StandardComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.computer.OrderComputerFlow;
import tests.BaseCheck;
import url.Urls;

public class BuyStandardComputer extends BaseCheck implements Urls {

    public BuyStandardComputer(WebDriver driver) {
        super(driver);
    }

    @Test
    public void testBuyStandardComputer(){
        String pageUrl = demoBaseUrl + "build-your-own-computer";
        driver.get(pageUrl);
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver, StandardComputerComponent.class);
        orderComputerFlow.buildComputerSpecAndAddToCard("2GHz", "2GB");
    }
}


