package hu.nye.progtech.service.impl;

import hu.nye.progtech.model.Money;
import hu.nye.progtech.model.MoneyCurrencies;
import hu.nye.progtech.model.exception.NotHandledCurrencyException;
import hu.nye.progtech.service.MonateryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Currency;
import java.util.stream.Stream;

class StaticBankTest {

    private static final Money HUF_MONEY = new Money(1, MoneyCurrencies.HUF_CURRENCY);
    private static final Money USD_MONEY = new Money(1, MoneyCurrencies.USD_CURRENCY);
    private MonateryService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StaticBank();
    }

    @Test
    void testConvertToShouldNotConvertWhenGivenIdenticalCurrencies() {
        // given
        Money usd10 = new Money(10, MoneyCurrencies.USD_CURRENCY);
        // when
        final Money actual = underTest.convertTo(usd10, MoneyCurrencies.USD_CURRENCY);
        // then
        Assertions.assertEquals(usd10, actual);
    }

    @Test
    void testConvertToShouldConvertWhenGivenDifferentCurrencies() {
        // Given
        Money usd10 = new Money(10, MoneyCurrencies.USD_CURRENCY);
        Money expectedMoney = new Money(2493.0, MoneyCurrencies.HUF_CURRENCY);
        // When
        final Money actual = underTest.convertTo(usd10, MoneyCurrencies.HUF_CURRENCY);
        // Then
        Assertions.assertEquals(expectedMoney, actual);
    }

    @Test
    void testConvertToShouldThrowExceptionWhenGivenNotHandledCurrency() {
        // Given
        Money usd10 = new Money(10, MoneyCurrencies.USD_CURRENCY);
        // When - Then
        final NotHandledCurrencyException actual = Assertions.assertThrows(NotHandledCurrencyException.class, () -> {
            underTest.convertTo(usd10, Currency.getInstance("GBP"));
        });
        Assertions.assertEquals("Could not convert currencies from USD to GBP", actual.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getExchangeRateDataProvider")
    void testConvertToShouldReturnExchangedMoney(Money money, Currency toCurrency, Money expected) {
        // given in setup
        // when
        final Money actual = underTest.convertTo(money, toCurrency);
        // then
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> getExchangeRateDataProvider() {
        return Stream.of(
                Arguments.of(HUF_MONEY, MoneyCurrencies.HUF_CURRENCY, HUF_MONEY),
                Arguments.of(USD_MONEY, MoneyCurrencies.USD_CURRENCY, USD_MONEY),
                Arguments.of(HUF_MONEY, MoneyCurrencies.USD_CURRENCY, new Money(0.0034, MoneyCurrencies.USD_CURRENCY)),
                Arguments.of(USD_MONEY, MoneyCurrencies.HUF_CURRENCY, new Money(249.3, MoneyCurrencies.HUF_CURRENCY))
        );
    }
}
