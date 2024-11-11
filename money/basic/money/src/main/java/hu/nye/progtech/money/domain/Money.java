package hu.nye.progtech.money.domain;

import java.util.Currency;

public record Money(double value, Currency currency) {
}
