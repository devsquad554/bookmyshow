package org.example.service;

import org.example.model.Payment;
import org.example.model.PaymentMethod;
import org.example.model.PaymentStatus;
import org.example.payment.PaymentObserver;
import org.example.payment.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private static PaymentService instance;

    private PaymentStrategy paymentStrategy;

    private List<PaymentObserver> observers = new ArrayList<>();

    private PaymentService(){

    }

    public  void addObservers(PaymentObserver observer){
        observers.add(observer);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }



    public static synchronized PaymentService getInstance(){
        if(instance == null){
            instance = new PaymentService();
        }
        return instance;
    }

    public Payment processServicePayment(double amount){
//        Payment payment = new Payment(amount, paymentMethod);
//        boolean success = mockPaymentProcess(amount, paymentMethod);
//        payment.setPaymentStatus(success ? PaymentStatus.SUCCESSFULL : PaymentStatus.FAILED);
//        return payment;
        Payment payment =  paymentStrategy.processPayment(amount);
        notifyUsers(payment);
        return  payment;
    }

    private void notifyUsers(Payment payment){
        // notification logic
        for(PaymentObserver observer: observers){
            observer.update(payment);
        }
    }

    private boolean mockPaymentProcess(double amount, PaymentMethod method){
        // Simulate success 90% of time
        return Math.random() > 0.1; // true
        // false when <= 0.1
    }

}
