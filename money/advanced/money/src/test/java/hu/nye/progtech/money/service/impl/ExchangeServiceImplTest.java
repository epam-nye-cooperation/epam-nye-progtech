package hu.nye.progtech.money.service.impl;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.domain.CurrencyPair;
import hu.nye.progtech.money.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Currency;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExchangeServiceImplTest {

    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency HUF = Currency.getInstance("HUF");
    private static final Currency GBP = Currency.getInstance("GBP");

    private ExchangeServiceImpl underTest;

    @Mock
    private Bank bankService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ExchangeServiceImpl();
    }

    @Test
    public void testExchangeMoneyShouldBeSuccessWhenProvidedHandledCurrencyPair() {
        // GIVEN
        Money targetMoney = new Money(0, USD);
        Money moneyToExchange = new Money(100, HUF);
        CurrencyPair currencyPair = new CurrencyPair(HUF, USD);

        when(bankService.getExchangeRate(currencyPair)).thenReturn(Optional.of(1.1));

        // WHEN
        Money result = underTest.exchangeMoney(targetMoney, moneyToExchange, bankService);

        // THEN
        assertEquals(110, result.value(), 0.000001);
        assertEquals(USD, result.currency());
        verify(bankService).getExchangeRate(currencyPair);
    }

    @Test
    public void testExchangeMoneyShouldThrowUnsupportedOperationExceptionWhenProvidedNotHandledCurrencyPair() {
        // GIVEN
        Money targetMoney = new Money(0, USD);
        Money moneyToExchange = new Money(100, GBP);
        CurrencyPair currencyPair = new CurrencyPair(GBP, USD);

        when(bankService.getExchangeRate(currencyPair)).thenReturn(Optional.empty());

        // WHEN - THEN
        UnsupportedOperationException exception = assertThrows(
                UnsupportedOperationException.class,
                () -> underTest.exchangeMoney(targetMoney, moneyToExchange, bankService)
        );

        assertEquals("Can not exchange!", exception.getMessage());
        verify(bankService).getExchangeRate(currencyPair);
    }
}