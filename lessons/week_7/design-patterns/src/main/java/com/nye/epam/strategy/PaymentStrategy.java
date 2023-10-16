package com.nye.epam.strategy;

public interface PaymentStrategy {
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}
