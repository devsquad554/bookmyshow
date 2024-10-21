package org.example.payment;

import org.example.model.Payment;

public interface PaymentStrategy {
    Payment processPayment(double amount);
}
