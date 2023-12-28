package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String name;
        private String cvc;
    }

    public static String getApprovedCardInfo() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardInfo() {
        return "4444 4444 4444 4442";
    }

    public static String getNonExistentCardInfo() {
        return "4444 4444 4444";
    }

    public static String getZeroCardInfo() {
        return "0000 0000 0000 0000";
    }

    public static String getEmptyField() {
        return " ";
    }

    public static String generateValidDateMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateInvalidFormatMonth(int minValue, int maxValue) {
        return Integer.toString(minValue + (int) (Math.random() * (maxValue - minValue + 1)));
    }

    public static String generateZeroDate() {
        return "00";
    }

    public static String generateValidDateYear(int shift) {
        return LocalDate.now().plusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateInvalidDateYear(int minValue, int maxValue) {
        return Integer.toString(minValue + (int) (Math.random() * (maxValue - minValue + 1)));
    }

    public static String generateCardholderName(String locale) {
        var faker = new Faker((new Locale(locale)));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateInvalidCardholderNameNumber() {
        return "123456";
    }

    public static String generateInvalidCardholderNameSymbol() {
        return "???????????";
    }

    public static String generateCvc(int minValue, int maxValue) {
        return Integer.toString(minValue + (int) (Math.random() * (maxValue - minValue + 1)));
    }

    public static String generateZeroCvc() {
        return Integer.toString(0);
    }

    public static CardInfo generateApprovedPay() {
        return new CardInfo(getApprovedCardInfo(), generateValidDateMonth(), generateValidDateYear(1), generateCardholderName("en"), generateCvc(100, 1000));
    }

    public static CardInfo generateDeclinedPay() {
        return new CardInfo(getDeclinedCardInfo(), generateValidDateMonth(), generateValidDateYear(1), generateCardholderName("en"), generateCvc(100, 1000));
    }

    public static CardInfo generatePayNonExistentCard() {
        return new CardInfo(getNonExistentCardInfo(), generateValidDateMonth(), generateValidDateYear(1), generateCardholderName("en"), generateCvc(100, 1000));
    }

    public static CardInfo generatePayZeroCardNumber() {
        return new CardInfo(getZeroCardInfo(), generateValidDateMonth(), generateValidDateYear(1), generateCardholderName("en"), generateCvc(100, 1000));
    }

    public static CardInfo generatePayInvalidMonth13() {
        return new CardInfo(getApprovedCardInfo(), generateInvalidFormatMonth(13, 14), generateValidDateYear(1), generateCardholderName("en"), generateCvc(100, 1000));
    }

    public static CardInfo generatePayInvalidFormatMonth() {
        return new CardInfo(getApprovedCardInfo(), generateInvalidFormatMonth(1, 2), generateValidDateYear(1), generateCardholderName("en"), generateCvc(100, 1000));
    }

    public static CardInfo generatePayInvalidMonthZero() {
        return new CardInfo(getApprovedCardInfo(), generateZeroDate(), generateValidDateYear(1), generateCardholderName("en"), generateCvc(100, 1000));
    }

    public static CardInfo generatePayInvalidYearZero() {
        return new CardInfo(getApprovedCardInfo(), generateValidDateMonth(), generateZeroDate(), generateCardholderName("en"), generateCvc(100, 1000));
    }

    public static CardInfo generatePayInvalidYear1() {
        return new CardInfo(getApprovedCardInfo(), generateValidDateMonth(), generateInvalidDateYear(1, 2), generateCardholderName("en"), generateCvc(100, 1000));
    }

    public static CardInfo generatePayInvalidCardholderRU() {
        return new CardInfo(getApprovedCardInfo(), generateValidDateMonth(), generateValidDateYear(1), generateCardholderName("ru"), generateCvc(100, 1000));
    }

    public static CardInfo generatePayInvalidCardholderNumber() {
        return new CardInfo(getApprovedCardInfo(), generateValidDateMonth(), generateValidDateYear(1), generateInvalidCardholderNameNumber(), generateCvc(100, 1000));
    }

    public static CardInfo generatePayInvalidCardholderSymbol() {
        return new CardInfo(getApprovedCardInfo(), generateValidDateMonth(), generateValidDateYear(1), generateInvalidCardholderNameSymbol(), generateCvc(100, 1000));
    }

    public static CardInfo generatePayInvalidCvcZero() {
        return new CardInfo(getApprovedCardInfo(), generateValidDateMonth(), generateValidDateYear(1), generateCardholderName("en"), generateZeroCvc());
    }

    public static CardInfo generatePayInvalidCvc1() {
        return new CardInfo(getApprovedCardInfo(), generateValidDateMonth(), generateValidDateYear(1), generateCardholderName("en"), generateCvc(0, 1));
    }

    public static CardInfo generatePayInvalidCvc11() {
        return new CardInfo(getApprovedCardInfo(), generateValidDateMonth(), generateValidDateYear(1), generateCardholderName("en"), generateCvc(0, 100));
    }

    public static CardInfo generateEmptyPay() {
        return new CardInfo(getEmptyField(), getEmptyField(), getEmptyField(), getEmptyField(), getEmptyField());
    }
}