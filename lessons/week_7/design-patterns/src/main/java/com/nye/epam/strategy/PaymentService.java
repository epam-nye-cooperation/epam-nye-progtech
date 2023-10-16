package com.nye.epam.strategy;

public class PaymentService {

    public boolean triggerPayment(PaymentStrategy strategy, int amountToPay) {
        return strategy.pay(amountToPay);
    }

}
