package hu.nye.progtech.money.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StaticBankTest {

    @Mock
    private ExchangeService exchangeServiceMock;

    private StaticBank underTest;

    @BeforeEach
    public void setUp() {
        underTest = new StaticBank(exchangeServiceMock);
    }

    @Test
    public void getExchangeRateShouldCallExchangeService() {
        // GIVEN
        Currency hunCurrency = Currency.getInstance("HUF");
        Currency usdCurrency = Currency.getInstance("USD");
        BigDecimal expected = BigDecimal.TEN;
        given(exchangeServiceMock.getExchangeRate(hunCurrency, usdCurrency)).willReturn(expected);

        // WHEN
        BigDecimal actual = underTest.getExchangeRate(hunCurrency, usdCurrency);

        // THEN
        assertEquals(expected, actual);
        verify(exchangeServiceMock).getExchangeRate(hunCurrency, usdCurrency);
    }
}
