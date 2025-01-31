package org.practice.service;

import org.practice.datasource.CSVDataSource;
import org.practice.model.BankAccount;
import org.practice.model.User;

public class UserService {
    private CSVDataSource csvData;

    public UserService(CSVDataSource csvData) {
        this.csvData = csvData;
    }

    public BankAccount login(User user) {
        if (csvData.authenticate(user.getUsername(), user.getPassword())) {
            return new BankAccount(user);
        }
        return null;
    }

    public void createAccount(User user) {
        csvData.addUser(user);
    }
}