package ru.netology.ibank.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @Value
    public static class CardNumber {
        private String card;
        private String cardID;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOthersAutInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static CardNumber getFirstCardNumber() {
        return new CardNumber("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardNumber getSecondCardNumber() {
        return new CardNumber("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static CardNumber getCardNumber(String cardNumber) {
        if (cardNumber.equals(getFirstCardNumber())) {
            return getFirstCardNumber();
        } else
            return getSecondCardNumber();
    }
    public static int validAmount (int amount){
        return  new Random().nextInt(Math.abs(amount))+1;
    }
    public static int invalidAmount (int amount){
        return  Math.abs(amount)+ new Random().nextInt(10000);
    }
}
