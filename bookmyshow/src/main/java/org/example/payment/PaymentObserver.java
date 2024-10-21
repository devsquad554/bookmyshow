package org.example.payment;

import org.example.model.Payment;

public interface PaymentObserver {
    void update(Payment payment);
}
