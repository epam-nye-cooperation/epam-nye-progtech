package hu.nye.progtech.money.service.impl;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.domain.CurrencyPair;
import hu.nye.progtech.money.domain.Money;
import hu.nye.progtech.money.service.ExchangeService;
import hu.nye.progtech.money.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {

//    @Autowired
//    @Lazy // Lusta inicalizálás stratégiához, alapesetben EAGER (mohót) hazsnálja a Spring.
//    private MoneyService moneyService;

    @Override
    public Money exchangeMoney(Money targetMoney, Money moneyToExchange, Bank bank) {
        Double exchangeRate = bank.getExchangeRate(
                        new CurrencyPair(moneyToExchange.currency(), targetMoney.currency()))
                .orElseThrow(() -> new UnsupportedOperationException("Can not exchange!"));
        return new Money(moneyToExchange.value() * exchangeRate, targetMoney.currency());
    }
}
