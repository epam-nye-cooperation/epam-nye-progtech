package hu.nye.structural.proxy;

import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class BankAccountImpl implements BankAccount {
    private BigDecimal balance = BigDecimal.ZERO;
    private String name;

    @Override
    public void transact(BigDecimal amount) {
        if (amount.equals(BigDecimal.ZERO)) return;
        BigDecimal newBalance = balance.add(amount);
        boolean add = newBalance.compareTo(BigDecimal.ZERO) > 0;
        if (add) {
            balance = newBalance;
            System.out.println("Dear " + name + ", you " + (amount.compareTo(BigDecimal.ZERO) > 0 ? "received" : "sent") + " " + amount.abs() + "₿ your new balance is " + balance + "₿");
        } else {
            throw new IllegalStateException("Cannot go " + newBalance + "₿ on your balance " + name + ", sorry");
        }
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void setPII(String name) throws IllegalAccessException {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " has " + balance + "₿";
    }
}