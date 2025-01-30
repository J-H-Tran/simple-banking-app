package org.practice.datasource;

import org.practice.model.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVDataSource {
    private static final String CSV_FILE = "sba-users.csv";
    private Map<String, String> users = new HashMap<>();

    public CSVDataSource() {
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length == 2) {
                    users.put(values[0], values[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); //TODO: replace with proper logging
        }
    }

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user.getPassword());
        saveUsers();
    }

    private void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}