package testdata;

public class GuestInfo{
    public ShippingInfo shippingInfo;
    public BillingInfo billingInfo;
    public String shippingMethod;
    public String paymentMethod;
    public CreditCardInfo creditCardInfo;
    public String purchaseOther;
    public static class ShippingInfo{
        public String firstName;
        public String lastName;
        public String email;
        public String company;
        public String country;
        public String state;
        public String city;
        public String add1;
        public String add2;
        public String zipCode;
        public String phoneNum;
        public String faxNum;
    }
    public static class CreditCardInfo{
        public String cardType;
        public String cardHolderName;
        public String cardExpireMonth;
        public String cardExpireYear;
        public String cardNumber;
        public String cardCode;
    }
    public static class BillingInfo{
        public String firstName;
        public String lastName;
        public String email;
        public String company;
        public String country;
        public String state;
        public String city;
        public String add1;
        public String add2;
        public String zipCode;
        public String phoneNum;
        public String faxNum;
    }



}