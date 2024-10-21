package org.example.payment;

import org.example.model.PaymentMethod;

public class PaymentStrategyFactory {
    public static PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod){
        switch (paymentMethod){
            case CREDIT_CARD:
                return new CreditCardPayment();
            case PAYPAL:
                return new PaypalPayment();
            default:
                throw new IllegalArgumentException("Invalid payment method");
        }
    }
}
