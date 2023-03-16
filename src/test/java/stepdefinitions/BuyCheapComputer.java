package stepdefinitions;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import runner.CucumberRunnerTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Feature;
import models.components.order.CheapComputerComponent;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

@Feature("src/test/resources/features/BuyCheapComputer.feature")
public class BuyCheapComputer extends CucumberRunnerTest implements Urls {
    OrderComputerFlow<CheapComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver, CheapComputerComponent.class);
    @Given("User access to the computer detail page")
    public void gotoURL(){
        String pageUrl = demoBaseUrl + "build-your-cheap-own-computer";
        driver.get(pageUrl);
    }

    @When("User select processor {string} and RAM {string}")
    public void selectProcessorAndRAM(String processor, String RAM){
        orderComputerFlow.buildComputerSpecAndAddToCard(processor, RAM);
    }
    @Then("Verify item in cart correct data")
    public void verifyNumberItemInCart(){
        orderComputerFlow.gotoShoppingCart();
        orderComputerFlow.verifyShoppingCart();

    }

}
