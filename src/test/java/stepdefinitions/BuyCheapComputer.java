package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailPage;
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
    OrderComputerFlow orderComputerFlow = new OrderComputerFlow<>(driver, CheapComputerComponent.class);
    Double defaultPrice = 0.0;
    Double additionalProcessorPrice = 0.0;
    Double additionalRAMPrice = 0.0;
    Double additionalHDDPrice = 0.0;
    Double additionalSoftwarePrice = 0.0;

    @Given("User access to the computer detail page")
    public void gotoURL(){
        String pageUrl = demoBaseUrl + "build-your-cheap-own-computer";
        getDriver().get(pageUrl);
    }

    @When("User select processor {string} and RAM {string} and software {string}")
    public void selectProcessorAndRAM(String processor, String RAM, String software){
        orderComputerFlow.buildComputerSpecAndAddToCard(processor, RAM, software);
    }
    @Then("Verify item in cart correct data")
    public void verifyNumberItemInCart(){
        orderComputerFlow.gotoShoppingCart();
        orderComputerFlow.verifyShoppingCart();
    }

    @When("User check agree term and condition")
    public void agreeTermAndCondition(){
        orderComputerFlow.agreeTermAndCondition();
    }


    @When("User select Checkout")
    public void userSelectCheckout() {
        orderComputerFlow.selectCheckout();

    }

    @And("User select Check out as a guest")
    public void userSelectCheckOutAsAGuest() {
        orderComputerFlow.selectCheckoutAsGuest();
    }

    @And("User input data delivery address and confirm")
    public void userInputDataDeliveryAddressAndConfirm() {
        orderComputerFlow.inputDeliveryAddressAndConfirm();
        orderComputerFlow.selectConfirm();
    }
}
