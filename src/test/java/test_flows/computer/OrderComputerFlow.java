package test_flows.computer;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.TotalComponentOrderDetail;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckoutAsGuestPage;
import models.pages.ComputerItemDetailPage;
import models.pages.OrderDetailPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.core.*;
import support.verfication.Verifier;
import testdata.GuestInfo;

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
    public Double additionPaymentMethodPrice = 0.0;
    public Double additionShippingMethodPrice = 0.0;
    public Double taxAmount = 0.0;
    public Double orderQty = 1.0;





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
        Double expectedQty = orderQty;
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

    public void agreeTermAndCondition() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComponent().agreeTermOfService();

    }

    public void selectCheckout() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComponent().selectCheckout();
    }

    public void selectCheckoutAsGuest() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.customerBlocksComponent().selectCheckoutAsGuest();
    }

    public void inputDeliveryAddressAndConfirm() {
        CheckoutAsGuestPage checkoutAsGuestPage = new CheckoutAsGuestPage(driver);
        ObjectMapper om = new ObjectMapper();
        GuestInfo guestInfo;
        String currentProjectLocation = System.getProperty("user.dir");
        try{
            guestInfo = om.readValue(Paths.get(currentProjectLocation + "/src/test/java/testdata/GuestInfo.json").toFile(), GuestInfo.class);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Can not get guest data for test");
        }

        //Input billing info and continue
        checkoutAsGuestPage.checkoutStepBillingComponent().inputFirstName(guestInfo.billingInfo.firstName);
        checkoutAsGuestPage.checkoutStepBillingComponent().inputLastName(guestInfo.billingInfo.lastName);
        checkoutAsGuestPage.checkoutStepBillingComponent().inputEmail(guestInfo.billingInfo.email);
        checkoutAsGuestPage.checkoutStepBillingComponent().inputCompany(guestInfo.billingInfo.company);
        checkoutAsGuestPage.checkoutStepBillingComponent().selectCountry(guestInfo.billingInfo.country);
        checkoutAsGuestPage.checkoutStepBillingComponent().selectStateProvide(guestInfo.billingInfo.state);
        checkoutAsGuestPage.checkoutStepBillingComponent().inputCity(guestInfo.billingInfo.city);
        checkoutAsGuestPage.checkoutStepBillingComponent().inputAddress1(guestInfo.billingInfo.add1);
        checkoutAsGuestPage.checkoutStepBillingComponent().inputAddress2(guestInfo.billingInfo.add2);
        checkoutAsGuestPage.checkoutStepBillingComponent().inputZipPostalCode(guestInfo.billingInfo.zipCode);
        checkoutAsGuestPage.checkoutStepBillingComponent().inputPhoneNumber(guestInfo.billingInfo.phoneNum);
        checkoutAsGuestPage.checkoutStepBillingComponent().inputFaxNumber(guestInfo.billingInfo.faxNum);
        checkoutAsGuestPage.checkoutStepBillingComponent().selectBtnContinue();

        //Select continue at the shipping
        checkoutAsGuestPage.checkoutStepShippingComponent().selectBtnContinue();

        //Select Shipping method and continue
        checkoutAsGuestPage.checkoutStepShippingMethodComponent().selectShippingMethod(guestInfo.shippingMethod);
        additionShippingMethodPrice = getAdditionalPaymentMethodPrice(checkoutAsGuestPage.checkoutStepShippingMethodComponent().getShippingMethod());
        checkoutAsGuestPage.checkoutStepShippingMethodComponent().selectBtnContinue();

        //Select Payment method and continue
        checkoutAsGuestPage.checkoutStepPaymentMethodComponent().selectPaymentMethod(guestInfo.paymentMethod);
        additionPaymentMethodPrice = getAdditionalPaymentMethodPrice(checkoutAsGuestPage.checkoutStepPaymentMethodComponent().getPaymentMethod());
        checkoutAsGuestPage.checkoutStepPaymentMethodComponent().selectBtnContinue();

        //Input payment Info and continue
        checkoutAsGuestPage.checkoutStepPaymentInfoComponent().selectCardType(guestInfo.creditCardInfo.cardType);
        checkoutAsGuestPage.checkoutStepPaymentInfoComponent().inputCardholderName(guestInfo.creditCardInfo.cardHolderName);
        checkoutAsGuestPage.checkoutStepPaymentInfoComponent().selectMonth(guestInfo.creditCardInfo.cardExpireMonth);
        checkoutAsGuestPage.checkoutStepPaymentInfoComponent().selectYear(guestInfo.creditCardInfo.cardExpireYear);
        checkoutAsGuestPage.checkoutStepPaymentInfoComponent().inputCardNumber(guestInfo.creditCardInfo.cardNumber);
        checkoutAsGuestPage.checkoutStepPaymentInfoComponent().inputCardCode(guestInfo.creditCardInfo.cardCode);
        checkoutAsGuestPage.checkoutStepPaymentInfoComponent().selectBtnContinue();

    }

    public void verifyConfirmOrder() {
        CheckoutAsGuestPage checkoutAsGuestPage = new CheckoutAsGuestPage(driver);
        ObjectMapper om = new ObjectMapper();
        GuestInfo guestInfo;
        String currentProjectLocation = System.getProperty("user.dir");
        try{
            guestInfo = om.readValue(Paths.get(currentProjectLocation + "/src/test/java/testdata/GuestInfo.json").toFile(), GuestInfo.class);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Can not get guest data for test");
        }

        // Verify billing info
        String actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderBillingInfoComponent().getName();
        String expected = guestInfo.billingInfo.firstName + " " + guestInfo.billingInfo.lastName;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderBillingInfoComponent().getEmail();
        expected = guestInfo.billingInfo.email;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderBillingInfoComponent().getPhone();
        expected = guestInfo.billingInfo.phoneNum;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderBillingInfoComponent().getFax();
        expected = guestInfo.billingInfo.phoneNum;
        Verifier.verifyActualContainsExpected(actual, expected);

        if(guestInfo.billingInfo.company!= null && !guestInfo.billingInfo.company.equals("")) {
            actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderBillingInfoComponent().getCompany();
            expected = guestInfo.billingInfo.company;
            Verifier.verifyActualContainsExpected(actual, expected);
        }

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderBillingInfoComponent().getAddress1();
        expected = guestInfo.billingInfo.add1;
        Verifier.verifyActualContainsExpected(actual, expected);

        if(guestInfo.billingInfo.add2!= null && !guestInfo.billingInfo.add2.equals("")) {
            actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderBillingInfoComponent().getAddress2();
            expected = guestInfo.billingInfo.add2;
            Verifier.verifyActualContainsExpected(actual, expected);
        }

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderBillingInfoComponent().getCityStateZip();
        expected = guestInfo.billingInfo.city + " , " + guestInfo.billingInfo.state + " " + guestInfo.billingInfo.zipCode;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderBillingInfoComponent().getCountry();
        expected = guestInfo.billingInfo.country;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderBillingInfoComponent().getPaymentMethod();
        expected = guestInfo.paymentMethod;
        Verifier.verifyExpectedContainsActual(actual, expected);


        // Verify shipping info
        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderShippingInfoComponent().getName();
        expected = guestInfo.shippingInfo.firstName + " " + guestInfo.shippingInfo.lastName;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderShippingInfoComponent().getEmail();
        expected = guestInfo.shippingInfo.email;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderShippingInfoComponent().getPhone();
        expected = guestInfo.shippingInfo.phoneNum;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderShippingInfoComponent().getFax();
        expected = guestInfo.shippingInfo.phoneNum;
        Verifier.verifyActualContainsExpected(actual, expected);

        if(guestInfo.shippingInfo.company!= null && !guestInfo.shippingInfo.company.equals("")) {
            actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderShippingInfoComponent().getCompany();
            expected = guestInfo.shippingInfo.company;
            Verifier.verifyActualContainsExpected(actual, expected);
        }

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderShippingInfoComponent().getAddress1();
        expected = guestInfo.shippingInfo.add1;
        Verifier.verifyActualContainsExpected(actual, expected);

        if(guestInfo.shippingInfo.add2!= null && !guestInfo.shippingInfo.add2.equals("")) {
            actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderShippingInfoComponent().getAddress2();
            expected = guestInfo.shippingInfo.add2;
            Verifier.verifyActualContainsExpected(actual, expected);
        }

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderShippingInfoComponent().getCityStateZip();
        expected = guestInfo.shippingInfo.city + " , " + guestInfo.shippingInfo.state + " " + guestInfo.billingInfo.zipCode;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderShippingInfoComponent().getCountry();
        expected = guestInfo.shippingInfo.country;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().confirmOrderShippingInfoComponent().getShippingMethod();
        expected = guestInfo.shippingMethod;
        Verifier.verifyExpectedContainsActual(actual, expected);

        //Verify total:
        TotalComponent totalComponent = checkoutAsGuestPage.checkoutStepConfirmOrderComponent().totalComponent();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        for (String priceType : priceCategories.keySet()){
            System.out.printf(priceType);
            System.out.println(priceCategories.get(priceType));
        }
        List<String> priceLabels = new ArrayList<>(priceCategories.keySet());
        double actualTotal = priceCategories.get(priceLabels.get(priceLabels.size()-1));
        double expectedTotal = pricePerUnit * orderQty + additionShippingMethodPrice + additionPaymentMethodPrice + taxAmount;
        Assert.assertEquals(actualTotal, expectedTotal, "Data do not match:\nActual: " + actualTotal + "\nExpected: " + expectedTotal);
    }

    public void selectContinueConfirmOrder() {
        CheckoutAsGuestPage checkoutAsGuestPage = new CheckoutAsGuestPage(driver);
        checkoutAsGuestPage.checkoutStepConfirmOrderComponent().selectBtnContinue();
    }


    public double getAdditionalPaymentMethodPrice(String fullTextOption){
        String pattern = ".*\\(([0-9]+\\.[0-9]+)\\)";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(fullTextOption);
        if(matcher.find()){
            return Double.parseDouble(matcher.group(1));
        }
        return 0;
    }

    public void verifyOrderCompleted() {
        CheckoutAsGuestPage checkoutAsGuestPage = new CheckoutAsGuestPage(driver);
        String orderNumber =  checkoutAsGuestPage.orderCompletedComponent().getOrderNumber();
        Verifier.verifyNotEmpty(orderNumber);
    }

    public void selectViewOrderDetail() {
        CheckoutAsGuestPage checkoutAsGuestPage = new CheckoutAsGuestPage(driver);
        checkoutAsGuestPage.orderCompletedComponent().selectViewOrderDetailAndSwitchTab();
    }


    public void verifyOtherDetailPage() {
        OrderDetailPage orderDetailPage = new OrderDetailPage(driver);
        ObjectMapper om = new ObjectMapper();
        GuestInfo guestInfo;
        String currentProjectLocation = System.getProperty("user.dir");
        try{
            guestInfo = om.readValue(Paths.get(currentProjectLocation + "/src/test/java/testdata/GuestInfo.json").toFile(), GuestInfo.class);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Can not get guest data for test");
        }

        // Verify billing info
        String actual = orderDetailPage.confirmOrderBillingInfoComponent().getName();
        String expected = guestInfo.billingInfo.firstName + " " + guestInfo.billingInfo.lastName;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = orderDetailPage.confirmOrderBillingInfoComponent().getEmail();
        expected = guestInfo.billingInfo.email;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = orderDetailPage.confirmOrderBillingInfoComponent().getPhone();
        expected = guestInfo.billingInfo.phoneNum;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = orderDetailPage.confirmOrderBillingInfoComponent().getFax();
        expected = guestInfo.billingInfo.phoneNum;
        Verifier.verifyActualContainsExpected(actual, expected);

        if(guestInfo.billingInfo.company!= null && !guestInfo.billingInfo.company.equals("")) {
            actual = orderDetailPage.confirmOrderBillingInfoComponent().getCompany();
            expected = guestInfo.billingInfo.company;
            Verifier.verifyActualContainsExpected(actual, expected);
        }

        actual = orderDetailPage.confirmOrderBillingInfoComponent().getAddress1();
        expected = guestInfo.billingInfo.add1;
        Verifier.verifyActualContainsExpected(actual, expected);

        if(guestInfo.billingInfo.add2!= null && !guestInfo.billingInfo.add2.equals("")) {
            actual = orderDetailPage.confirmOrderBillingInfoComponent().getAddress2();
            expected = guestInfo.billingInfo.add2;
            Verifier.verifyActualContainsExpected(actual, expected);
        }

        actual = orderDetailPage.confirmOrderBillingInfoComponent().getCityStateZip();
        expected = guestInfo.billingInfo.city + " , " + guestInfo.billingInfo.state + " " + guestInfo.billingInfo.zipCode;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = orderDetailPage.confirmOrderBillingInfoComponent().getCountry();
        expected = guestInfo.billingInfo.country;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = orderDetailPage.confirmOrderBillingInfoComponent().getPaymentMethod();
        expected = guestInfo.paymentMethod;
        Verifier.verifyExpectedContainsActual(actual, expected);


        // Verify shipping info
        actual = orderDetailPage.confirmOrderShippingInfoComponent().getName();
        expected = guestInfo.shippingInfo.firstName + " " + guestInfo.shippingInfo.lastName;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = orderDetailPage.confirmOrderShippingInfoComponent().getEmail();
        expected = guestInfo.shippingInfo.email;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = orderDetailPage.confirmOrderShippingInfoComponent().getPhone();
        expected = guestInfo.shippingInfo.phoneNum;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = orderDetailPage.confirmOrderShippingInfoComponent().getFax();
        expected = guestInfo.shippingInfo.phoneNum;
        Verifier.verifyActualContainsExpected(actual, expected);

        if(guestInfo.shippingInfo.company!= null && !guestInfo.shippingInfo.company.equals("")) {
            actual = orderDetailPage.confirmOrderShippingInfoComponent().getCompany();
            expected = guestInfo.shippingInfo.company;
            Verifier.verifyActualContainsExpected(actual, expected);
        }

        actual = orderDetailPage.confirmOrderShippingInfoComponent().getAddress1();
        expected = guestInfo.shippingInfo.add1;
        Verifier.verifyActualContainsExpected(actual, expected);

        if(guestInfo.shippingInfo.add2!= null && !guestInfo.shippingInfo.add2.equals("")) {
            actual = orderDetailPage.confirmOrderShippingInfoComponent().getAddress2();
            expected = guestInfo.shippingInfo.add2;
            Verifier.verifyActualContainsExpected(actual, expected);
        }

        actual = orderDetailPage.confirmOrderShippingInfoComponent().getCityStateZip();
        expected = guestInfo.shippingInfo.city + " , " + guestInfo.shippingInfo.state + " " + guestInfo.billingInfo.zipCode;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = orderDetailPage.confirmOrderShippingInfoComponent().getCountry();
        expected = guestInfo.shippingInfo.country;
        Verifier.verifyActualContainsExpected(actual, expected);

        actual = orderDetailPage.confirmOrderShippingInfoComponent().getShippingMethod();
        expected = guestInfo.shippingMethod;
        Verifier.verifyExpectedContainsActual(actual, expected);

        //Verify total:
        TotalComponentOrderDetail totalComponent = orderDetailPage.totalComponent();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        for (String priceType : priceCategories.keySet()){
            System.out.printf(priceType);
            System.out.println(priceCategories.get(priceType));
        }
        List<String> priceLabels = new ArrayList<>(priceCategories.keySet());
        double actualTotal = priceCategories.get("Order Total:");
        double expectedTotal = pricePerUnit * orderQty + additionShippingMethodPrice + additionPaymentMethodPrice + taxAmount;
        Assert.assertEquals(actualTotal, expectedTotal, "Data do not match:\nActual: " + actualTotal + "\nExpected: " + expectedTotal);
    }
}