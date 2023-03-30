package models.pages;

import models.components.cart.TotalComponent;
import models.components.checkout.ConfirmOrderBillingInfoComponent;
import models.components.checkout.ConfirmOrderShippingInfoComponent;
import models.components.checkout.TotalComponentOrderDetail;
import org.openqa.selenium.WebDriver;

public class OrderDetailPage extends BasePage{
    public OrderDetailPage(WebDriver driver) {
        super(driver);
    }

    public ConfirmOrderBillingInfoComponent confirmOrderBillingInfoComponent(){
        return findComponent(ConfirmOrderBillingInfoComponent.class, driver);
    }
    public ConfirmOrderShippingInfoComponent confirmOrderShippingInfoComponent(){
        return findComponent(ConfirmOrderShippingInfoComponent.class, driver);
    }

    public TotalComponentOrderDetail totalComponent(){
        return findComponent(TotalComponentOrderDetail.class, driver);
    }


}
