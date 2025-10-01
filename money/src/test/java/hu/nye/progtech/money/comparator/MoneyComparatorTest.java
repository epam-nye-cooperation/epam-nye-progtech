package hu.nye.progtech.money.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.domain.Money;

@ExtendWith(MockitoExtension.class)
public class MoneyComparatorTest {

    private MoneyComparator underTest;

    @Mock
    private Bank bankMock;

    @BeforeEach
    public void setup() {
        underTest = new MoneyComparator(bankMock);
    }

    @Test
    public void compareShouldReturnZeroIfParametersSame() {
        // GIVEN
        int expected = 0;
        Currency huf = Currency.getInstance("HUF");
        Money money = new Money(BigDecimal.TEN, huf);
        given(bankMock.getExchangeRate(huf, huf)).willReturn(BigDecimal.ONE);

        // WHEN
        int actual = underTest.compare(money, money);

        // THEN
        assertEquals(expected, actual);
    }
}
