package org.example.payment;

import org.example.model.Payment;
import org.example.model.PaymentStatus;

public class SmsNotificationObserver implements PaymentObserver{
    @Override
    public void update(Payment payment) {
        if(payment.getPaymentStatus() == PaymentStatus.SUCCESSFULL){
            // calling those 3rd party APIs
            System.out.println("SMS sent for payment id: "+ payment.getId());
        }
    }
}
