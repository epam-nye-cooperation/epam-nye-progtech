package hu.nye.progtech.money.service;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.domain.Money;

public interface MoneyService {

    Money add(Money targetMoney, Money moneyToAdd, Bank bank);

    Integer compareTo(Money targetMoney, Money moneyToCompare, Bank bank);

}
