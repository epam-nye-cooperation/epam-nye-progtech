package hu.nye.progtech.money.service.impl;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.domain.Money;
import hu.nye.progtech.money.service.ExchangeService;
import hu.nye.progtech.money.service.MoneyService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Set;

//@Component
@Service
//@Controller
//@Repository
public class MoneyServiceImpl implements MoneyService {

    // #1 DI Stratégia - Konstruktor
    private final ExchangeService exchangeService;
    private final Bank bank;

    private final Set<Bank> bankServices;

    //    public MoneyServiceImpl(ExchangeService exchangeService, Bank bank,
    //                            Set<Bank> bankServices) {
    //        this.exchangeService = exchangeService;
    //        this.bank = bank;
    //        this.bankServices = bankServices;
    //    }

    // A  @Qualifier annotációval tudjuk megnevezni azt a Spring Bean-t amit szeretnénk DI (függőség injektálni).
    public MoneyServiceImpl(ExchangeService exchangeService, @Qualifier("staticBank") Bank bank,
                            Set<Bank> bankServices) {
        this.exchangeService = exchangeService;
        this.bank = bank;
        this.bankServices = bankServices;
    }

    // #2 DI stratégia - Setter metódus
    //    private ExchangeService exchangeService;
    //    private Bank bank;
    //
    //
    //    @Autowired
    //    public void setExchangeService(ExchangeService exchangeService) {
    //        this.exchangeService = exchangeService;
    //    }
    //
    //    @Autowired
    //    public void setBank(@Qualifier("staticBank") Bank bank) {
    //        this.bank = bank;
    //    }

    // #3 DI stratégia - Mező Autowired
    //
    //    @Autowired
    //    private ExchangeService exchangeService;
    //    @Autowired
    //    @Qualifier("staticBank")
    //    private Bank bank;


    // A Spring Bean létrehozása után fut le
    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct");
    }

    // A Spring Bean megsemmístése előtt fut le
    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy");
    }

    @Override
    public Money add(Money targetMoney, Money moneyToAdd) {
        if (!targetMoney.currency().equals(moneyToAdd.currency())) {
            moneyToAdd = exchangeService.exchangeMoney(targetMoney, moneyToAdd, bank);
        }
        return new Money(targetMoney.value() + moneyToAdd.value(), targetMoney.currency());
    }

    @Override
    public Integer compareTo(Money targetMoney, Money moneyToCompare) {
        if (!targetMoney.currency().equals(moneyToCompare.currency())) {
            moneyToCompare = exchangeService.exchangeMoney(targetMoney, moneyToCompare, bank);
        }
        return Double.compare(targetMoney.value(), moneyToCompare.value());
    }

}
