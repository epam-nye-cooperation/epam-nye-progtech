package hu.nye.progtech.money.service;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.domain.Money;

public interface ExchangeService {

    Money exchangeMoney(Money targetMoney, Money moneyToAdd, Bank bank);

}
