package org.practice.service;

import org.practice.model.BankAccount;

public class BankOperationService {
    public double getBalance(BankAccount account) {
        return account.getBalance();
    }

    public void deposit(BankAccount account, double amount) {
        account.deposit(amount);
    }

    public void withdraw(BankAccount account, double amount) {
        account.withdraw(amount);
    }
}