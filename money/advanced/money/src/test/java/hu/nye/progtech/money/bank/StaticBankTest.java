package hu.nye.progtech.money.bank;


import hu.nye.progtech.money.domain.CurrencyPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Currency;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StaticBankTest {

    private StaticBank underTest;

    @BeforeEach
    public void setUp() {
        underTest = new StaticBank();
    }

    @Test
    public void testGetExchangeRateShouldReturnExchangeRatioWhenProvidedHandledCurrencyPair() {
        // GIVEN
        Double expectedRatio = 0.0034D;
        Currency hunCurr = Currency.getInstance("HUF");
        Currency usdCurr = Currency.getInstance("USD");

        // WHEN
        Optional<Double> actual = underTest.getExchangeRate(new CurrencyPair(hunCurr, usdCurr));

        // THEN
        assertTrue(actual.isPresent());
        assertEquals(expectedRatio, actual.get());
    }

    @Test
    public void testGetExchangeRateShouldReturnEmptyOptionalWhenProvidedUnHandledCurrencyPair() {
        // GIVEN
        Currency hunCurr = Currency.getInstance("HUF");
        Currency gbpCurr = Currency.getInstance("GBP");

        // WHEN
        Optional<Double> actual = underTest.getExchangeRate(new CurrencyPair(hunCurr, gbpCurr));

        // THEN
        assertFalse(actual.isPresent());
    }
    
}
