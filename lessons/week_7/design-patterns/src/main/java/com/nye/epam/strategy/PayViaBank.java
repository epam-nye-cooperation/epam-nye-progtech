package com.nye.epam.strategy;

public class PayViaBank implements PaymentStrategy {
    @Override
    public boolean pay(int paymentAmount) {
        System.out.println("Paypal payment strategy: redirect to bank site, wait for money to arrive to account");
        return true;
    }

    @Override
    public void collectPaymentDetails() {
        System.out.println("Collect bank account details");
    }
}
