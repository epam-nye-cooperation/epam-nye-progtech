package hu.nye.progtech.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StaticBankTest {

    private static final Currency HUF = Currency.getInstance("HUF");
    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency EUR = Currency.getInstance("EUR");

    private StaticBank underTest;

    @BeforeEach
    public void setUp() {
        underTest = new StaticBank();
    }

    @Test
    public void testGetExchangeRateShouldReturnOneWhenTheCurrenciesAreTheSame() {
        // given in setup

        // when
        BigDecimal result = underTest.getExchangeRate(HUF, HUF);

        // then
        assertEquals(BigDecimal.ONE, result);
    }

    @Test
    public void testGetExchangeRateShouldReturnCorrectExchangeRateWhenConvertingFromHufToUsd() {
        // given in setup

        // when
        BigDecimal result = underTest.getExchangeRate(HUF, USD);

        // then
        assertEquals(BigDecimal.valueOf(1.0 / 249.3), result);
    }

    @Test
    public void testGetExchangeRateShouldThrowRuntimeExceptionWhenExchangeRateIsUnknown() {
        // given in setup

        // when - then
        assertThrows(RuntimeException.class, () -> {
            underTest.getExchangeRate(EUR, HUF);
        });
    }

    @ParameterizedTest
    @MethodSource("getExchangeRateDataProvider")
    public void testGetExchangeRateShouldReturnCorrectValue(Currency fromCurrency, Currency toCurrency, BigDecimal expected) {
        // given in setup

        // when
        BigDecimal result = underTest.getExchangeRate(fromCurrency, toCurrency);

        // then
        assertEquals(expected, result);
    }

    private static Stream<Arguments> getExchangeRateDataProvider() {
        return Stream.of(
            Arguments.of(HUF, HUF, BigDecimal.ONE),
            Arguments.of(USD, USD, BigDecimal.ONE),
            Arguments.of(HUF, USD, BigDecimal.valueOf(1.0 / 249.3)),
            Arguments.of(USD, HUF, BigDecimal.valueOf(249.3))
        );
    }

}
