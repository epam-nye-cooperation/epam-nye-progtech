package com.nye.epam.strategy;

public class PayViaApplePay implements PaymentStrategy {

    @Override
    public boolean pay(int paymentAmount) {
        System.out.println("Trigger Face ID and Apple Pay flow on the device. Wait for response from Apple Pay on payment.");
        return true;
    }

    @Override
    public void collectPaymentDetails() {
        System.out.println("Collect Apple ID");
    }
}
