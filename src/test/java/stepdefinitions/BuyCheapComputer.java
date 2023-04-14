package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import runner.CucumberRunnerTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Feature;
import models.components.order.CheapComputerComponent;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

@Feature("src/test/resources/features/BuyCheapComputer.feature")
public class BuyCheapComputer extends BaseSteps implements Urls {
    WebDriver driver = getDriver();
    OrderComputerFlow orderComputerFlow = new OrderComputerFlow<>(driver, CheapComputerComponent.class);
    Double defaultPrice = 0.0;
    Double additionalProcessorPrice = 0.0;
    Double additionalRAMPrice = 0.0;
    Double additionalHDDPrice = 0.0;
    Double additionalSoftwarePrice = 0.0;

    @Given("User access to the computer detail page")
    public void gotoURL(){
        Allure.step("User access to the computer detail page");
        String pageUrl = demoBaseUrl + "build-your-cheap-own-computer";
        WebDriver driver = getDriver();
        driver.get(pageUrl);
    }

    @When("User select processor {string} and RAM {string} and software {string}")
    public void selectProcessorAndRAM(String processor, String RAM, String software){
        Allure.step("User select processor "+processor+" and RAM "+RAM+" and software " + software);
        orderComputerFlow.buildComputerSpecAndAddToCard(processor, RAM, software);
    }
    @Then("Verify item in cart correct data")
    public void verifyNumberItemInCart(){
        Allure.step("Verify item in cart correct data");
        orderComputerFlow.gotoShoppingCart();
        orderComputerFlow.verifyShoppingCart();
    }

    @When("User check agree term and condition")
    public void agreeTermAndCondition(){
        Allure.step("User check agree term and condition");
        orderComputerFlow.agreeTermAndCondition();
    }


    @When("User select Checkout")
    public void userSelectCheckout() {
        Allure.step("User select Checkout");
        orderComputerFlow.selectCheckout();

    }

    @And("User select Check out as a guest")
    public void userSelectCheckOutAsAGuest() {
        Allure.step("User select Check out as a guest");
        orderComputerFlow.selectCheckoutAsGuest();
    }

    @And("User input data delivery address and confirm")
    public void userInputDataDeliveryAddressAndConfirm() {
        Allure.step("User input data delivery address and confirm");
        orderComputerFlow.inputDeliveryAddressAndConfirm();
    }

    @Then("Show correct screen confirm Order")
    public void showCorrectScreenConfirmOrder() {
        Allure.step("Show correct screen confirm Order");
        orderComputerFlow.verifyConfirmOrder();
    }

    @When("User select continue confirm order")
    public void userSelectContinueConfirmOrder() {
        Allure.step("User select continue confirm order");
        orderComputerFlow.selectContinueConfirmOrder();

    }

    @Then("Show screen Order completed correct")
    public void showScreenOrderCompletedCorrect() {
        Allure.step("Show screen Order completed correct");
        orderComputerFlow.verifyOrderCompleted();
    }

    @When("User select to view order detail")
    public void userSelectToViewOrderDetail() {
        Allure.step("User select to view order detail");
        orderComputerFlow.selectViewOrderDetail();
    }

    @Then("Show screen other detail correct")
    public void showScreenOtherDetailCorrect() {
        Allure.step("Show screen other detail correct");
        orderComputerFlow.verifyOtherDetailPage();
    }
}
