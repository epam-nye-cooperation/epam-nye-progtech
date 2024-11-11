package hu.nye.progtech.money.service.impl;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.domain.Money;
import hu.nye.progtech.money.service.ExchangeService;
import hu.nye.progtech.money.service.MoneyService;

public class MoneyServiceImpl implements MoneyService {

    private final ExchangeService exchangeServiceImpl = new ExchangeServiceImpl();

    @Override
    public Money add(Money targetMoney, Money moneyToAdd, Bank bank) {
        if (!targetMoney.currency().equals(moneyToAdd.currency())) {
            moneyToAdd = exchangeServiceImpl.exchangeMoney(targetMoney, moneyToAdd, bank);
        }
        return new Money(targetMoney.value() + moneyToAdd.value(), targetMoney.currency());
    }

    @Override
    public Integer compareTo(Money targetMoney, Money moneyToCompare, Bank bank) {
        if (!targetMoney.currency().equals(moneyToCompare.currency())) {
            moneyToCompare = exchangeServiceImpl.exchangeMoney(targetMoney, moneyToCompare, bank);
        }
        return Double.compare(targetMoney.value(), moneyToCompare.value());
    }

}
