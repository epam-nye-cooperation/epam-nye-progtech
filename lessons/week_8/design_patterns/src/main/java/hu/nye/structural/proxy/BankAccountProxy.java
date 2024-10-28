package hu.nye.structural.proxy;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class BankAccountProxy implements BankAccount {
    BankAccount realAccount;
    Role userRole;

    @Override
    public void transact(BigDecimal amount) throws IllegalAccessException {
        boolean reduce = amount.compareTo(BigDecimal.ZERO) < 0;
        if (!Role.OWNER.equals(userRole) && reduce) {
            throw new IllegalAccessException("You can only add to this balance");
        }
        System.out.print("As " + userRole + ": ");
        realAccount.transact(amount);
    }

    @Override
    public BigDecimal getBalance() throws IllegalAccessException {
        return realAccount.getBalance();
    }

    @Override
    public void setPII(String name) throws IllegalAccessException {
        if (Role.OWNER.equals(userRole)) {
            realAccount.setPII(name);
        } else {
            throw new IllegalAccessException("Cannot set PII data, ask the owner");
        }
    }

    public enum Role {
        READER, OWNER
    }
}