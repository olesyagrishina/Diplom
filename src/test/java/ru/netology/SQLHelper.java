package ru.netology;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    @SneakyThrows
    private static Connection getConn() {
        String db = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        return DriverManager.getConnection(db, login, password);
    }

    @SneakyThrows
    public static String paymentID() {
        var cardSQL = "SELECT payment_id FROM order_entity order by created DESC;";
        String payment_ID = null;
        try (var cardsStmt = getConn().prepareStatement(cardSQL);
        ) {
            try (var rs = cardsStmt.executeQuery()) {
                if (rs.next()) {
                    payment_ID = rs.getString("payment_id");
                }
            }
        }
        return payment_ID;
    }

    @SneakyThrows
    public static String paymentCardStatus(String paymentID) {
        var cardSQL = "SELECT status FROM payment_entity WHERE transaction_id =?; ";
        String paymentCardStatus = null;
        try (var cardsStmt = getConn().prepareStatement(cardSQL);) {
            cardsStmt.setString(1, paymentID);
            try (var rs = cardsStmt.executeQuery()) {
                if (rs.next()) {
                    paymentCardStatus = rs.getString("status");
                }
            }
        }
        return paymentCardStatus;
    }

    @SneakyThrows
    public static String paymentCreditStatus(String paymentID) {
        var cardSQL = "SELECT status FROM credit_request_entity WHERE bank_id =?; ";
        String paymentCreditStatus = null;
        try (var cardsStmt = getConn().prepareStatement(cardSQL);) {
            cardsStmt.setString(1, paymentID);
            try (var rs = cardsStmt.executeQuery()) {
                if (rs.next()) {
                    paymentCreditStatus = rs.getString("status");
                }
            }
        }
        return paymentCreditStatus;
    }
}


