package hu.nye.progtech.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MoneyComparatorTest {

    private static final Currency HUF = Currency.getInstance("HUF");
    private static final Currency USD = Currency.getInstance("USD");

    @Mock
    private Bank bank;

    private MoneyComparator underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MoneyComparator(bank);
    }

    @Test
    public void testCompareShouldReturnNegativeValueWhenFirstParameterIsLessThanTheSecondOne() {
        // given
        Money money1 = new Money(1, HUF);
        Money money2 = new Money(1, USD);
        given(bank.getExchangeRate(USD, HUF)).willReturn(BigDecimal.valueOf(2));

        // when
        int result = underTest.compare(money1, money2);

        // then
        assertTrue(result < 0);
    }

    @Test
    public void testCompareShouldReturnZeroWhenTheTwoParametersAreEqual() {
        // given
        Money money1 = new Money(1, HUF);
        Money money2 = new Money(1, HUF);
        given(bank.getExchangeRate(HUF, HUF)).willReturn(BigDecimal.ONE);

        // when
        int result = underTest.compare(money1, money2);

        // then
        assertEquals(0, result);
    }

    @Test
    public void testCompareShouldReturnPositiveValueWhenFirstParameterIsGreaterThanTheSecondOne() {
        // given
        Money money1 = new Money(3, HUF);
        Money money2 = new Money(1, USD);
        given(bank.getExchangeRate(USD, HUF)).willReturn(BigDecimal.valueOf(2));

        // when
        int result = underTest.compare(money1, money2);

        // then
        assertTrue(result > 0);
    }

}
