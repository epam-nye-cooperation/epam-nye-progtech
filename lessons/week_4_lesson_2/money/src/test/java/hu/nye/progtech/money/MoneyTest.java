package hu.nye.progtech.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MoneyTest {

    private static final Currency HUF = Currency.getInstance("HUF");
    private static final Currency USD = Currency.getInstance("USD");

    @Mock
    private Bank bank;

    private Money underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Money(10, HUF);
    }

    @Test
    public void testAddShouldConvertAdditionalMoneyAddItToCurrentValueAndReturnNewMoneyObject() {
        // given
        Money addition = new Money(10, USD);
        given(bank.getExchangeRate(USD, HUF)).willReturn(new BigDecimal(2));

        // when
        Money result = underTest.add(addition, bank);

        // then
        Money expected = new Money(30, HUF);
        assertEquals(expected, result);
        assertNotSame(underTest, result);
    }

}
