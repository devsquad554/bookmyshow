package org.example.payment;

import org.example.model.Payment;
import org.example.model.PaymentMethod;
import org.example.model.PaymentStatus;

public class CreditCardPayment implements PaymentStrategy{
    @Override
    public Payment processPayment(double amount) {
        Payment payment = new Payment(amount, PaymentMethod.CREDIT_CARD);
        // calling sbi APIs by passing payment
        payment.setPaymentStatus(PaymentStatus.SUCCESSFULL);
        return payment;
    }
}
