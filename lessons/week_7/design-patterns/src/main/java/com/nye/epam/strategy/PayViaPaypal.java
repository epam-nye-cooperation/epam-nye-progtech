package com.nye.epam.strategy;

public class PayViaPaypal implements PaymentStrategy {
    @Override
    public boolean pay(int paymentAmount) {
        System.out.println("Paypal payment strategy: username/password/get transaction id and consent");
        return true;
    }

    @Override
    public void collectPaymentDetails() {
        System.out.println("Collected Paypal email");
        System.out.println("Collected Paypal userID");
        System.out.println("Collected Paypal TransactionID");
    }
}
