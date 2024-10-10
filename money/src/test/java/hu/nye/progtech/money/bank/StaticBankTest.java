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
    public void testGetExchangeRateShouldCallExchangeServiceWhenCalled() {
        // GIVEN
        BigDecimal expectedRatio = BigDecimal.TEN;
        Currency hunCurr = Currency.getInstance("HUF");
        Currency usdCurr = Currency.getInstance("USD");
        given(exchangeServiceMock.getExchangeRate(hunCurr, usdCurr)).willReturn(expectedRatio);

        // WHEN
        BigDecimal actualRatio = underTest.getExchangeRate(hunCurr, usdCurr);

        // THEN
        assertEquals(expectedRatio, actualRatio);
        verify(exchangeServiceMock).getExchangeRate(hunCurr, usdCurr);
    }
}
