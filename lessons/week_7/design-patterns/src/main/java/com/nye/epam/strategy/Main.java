package com.nye.epam.strategy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();

        PayViaApplePay payViaApplePay = new PayViaApplePay();
        PayViaBank payViaBank = new PayViaBank();
        PayViaPaypal payViaPaypal = new PayViaPaypal();

        paymentService.triggerPayment(payViaApplePay, 100);
        paymentService.triggerPayment(payViaBank, 1000);
        paymentService.triggerPayment(payViaPaypal, 500);


        // Lambdas & Strategy design pattern

        System.out.println(
            Stream.of(1, 2, -6, -1900, 0, 5, 1243)
                .map(number -> number * 2)
                .filter(number -> number >= 0)
                .collect(Collectors.toList())
        );

    }

}
