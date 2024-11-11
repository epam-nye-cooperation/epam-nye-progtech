package hu.nye.progtech.money.service.impl;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.domain.CurrencyPair;
import hu.nye.progtech.money.domain.Money;
import hu.nye.progtech.money.service.ExchangeService;

public class ExchangeServiceImpl implements ExchangeService {

    @Override
    public Money exchangeMoney(Money targetMoney, Money moneyToExchange, Bank bank) {
        Double exchangeRate = bank.getExchangeRate(
                        new CurrencyPair(moneyToExchange.currency(), targetMoney.currency()))
                .orElseThrow(() -> new UnsupportedOperationException("Can not exchange!"));
        return new Money(moneyToExchange.value() * exchangeRate, targetMoney.currency());
    }
}
