package hu.nye.progtech;

import hu.nye.progtech.model.Money;
import hu.nye.progtech.model.MoneyCurrencies;
import hu.nye.progtech.service.impl.StaticBank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTest {

    private Money underTest;

    @Mock
    private StaticBank staticBank;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new Money(10, MoneyCurrencies.USD_CURRENCY);
    }

    @Test
    void testAddShouldSumTwoCurrenciesWhenGivenThem() {
        // given
        Money moneyToAdd = new Money(10, MoneyCurrencies.USD_CURRENCY);
        Money expected = new Money(20, MoneyCurrencies.USD_CURRENCY);
        BDDMockito.given(staticBank.convertTo(moneyToAdd, MoneyCurrencies.USD_CURRENCY))
                .willReturn(moneyToAdd);
        // when
        Money actual = underTest.add(moneyToAdd, staticBank);
        // then
        assertEquals(expected, actual);
        Mockito.verify(staticBank).convertTo(moneyToAdd, MoneyCurrencies.USD_CURRENCY);
    }

    @Test
    void testAddShouldSumTwoCurrenciesWhenGivenThemAndUseSpyObject() {
        // given
        /* A spy objektum ugyanúgy regisztrálja a metódus hívásokat amik történnek rajta,
          de egy igazi objektumot csomagol be és annak ellenőrízhető az állapota */
        underTest = Mockito.spy(new Money(10, MoneyCurrencies.USD_CURRENCY));
        Money moneyToAdd = new Money(10, MoneyCurrencies.USD_CURRENCY);
        /*
           BDDMockito#given ugyanazt csinálja mint a Mockito#when metódus,
           a mock objektum: staticBank
           hívott metódus: StaticBank#convertTo
           metódus paraméterek: pontosan azokat a paramétereket kell megadni amik hívásnál is ott lesznek
           return résznél vissza adja azt válasznak amit beállítottunk
        */
        // BDDMockito.given(staticBank.convertTo(moneyToAdd, MoneyCurrencies.USD_CURRENCY)).willReturn(moneyToAdd);
        Mockito.when(staticBank.convertTo(moneyToAdd, MoneyCurrencies.USD_CURRENCY)).thenReturn(moneyToAdd);
        // when
        Money actual = underTest.add(moneyToAdd, staticBank);
        // then
        // Ellenőrzi, hogy meghívásra került-e a addott paraméterrekell a mock objektumon a StaticBank#convertTo metódusa
        Mockito.verify(staticBank).convertTo(moneyToAdd, MoneyCurrencies.USD_CURRENCY);
        Mockito.verify(underTest, Mockito.times(1)).add(moneyToAdd, staticBank);
        assertEquals(20, actual.getValue());
        assertEquals(MoneyCurrencies.USD_CURRENCY, actual.getCurrency());
        // Ellenőrzi, hogy nincs-e több hívás a staticBank mock objektumon
        Mockito.verifyNoMoreInteractions(staticBank);
    }

}