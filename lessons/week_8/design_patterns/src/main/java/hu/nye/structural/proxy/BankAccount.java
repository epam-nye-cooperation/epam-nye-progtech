package hu.nye.structural.proxy;

import java.math.BigDecimal;

public interface BankAccount {
    void transact(BigDecimal amount) throws IllegalAccessException;

    BigDecimal getBalance() throws IllegalAccessException;

    void setPII(String name) throws IllegalAccessException;
}