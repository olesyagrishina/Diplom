package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.DataHelper;
import ru.netology.PaymentPage;
import ru.netology.SQLHelper;

import static com.codeborne.selenide.Selenide.open;

public class CardPaymentTest {
    @BeforeEach
    public void setup() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldPayTravelByCardApproved() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generateApprovedPay();
        paymentPage.validDataInput(authInfo);
        paymentPage.successMessage();
        var payment_ID = SQLHelper.paymentID();
        var status = SQLHelper.paymentCardStatus(payment_ID);
        Assertions.assertEquals("APPROVED", status);
    }

    @Test
    public void shouldPayTravelByCardDeclined() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generateDeclinedPay();
        paymentPage.validDataInput(authInfo);
        paymentPage.errorMessage();
        var payment_ID = SQLHelper.paymentID();
        var status = SQLHelper.paymentCardStatus(payment_ID);
        Assertions.assertEquals("DECLINED", status);
    }

    @Test
    public void shouldPayTravelByNonExistentCard() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayNonExistentCard();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelByZeroCard() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayZeroCardNumber();
        paymentPage.validDataInput(authInfo);
        paymentPage.errorMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidMonthZero() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidMonthZero();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidMonth13() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidMonth13();
        paymentPage.validDataInput(authInfo);
        paymentPage.errorDateMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidMonth1() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidFormatMonth();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidYearZero() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidYearZero();
        paymentPage.validDataInput(authInfo);
        paymentPage.expireDateMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidYear1() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidYear1();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidCardholderNameRU() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidCardholderRU();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidCardholderNameNumber() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidCardholderNumber();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidCardholderNameSymbol() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidCardholderSymbol();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidCvcZero() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidCvcZero();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidCvc1() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidCvc1();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelIfInvalidCvc11() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generatePayInvalidCvc11();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelWithEmptyForm() {
        var paymentPage = new PaymentPage();
        paymentPage.cardPayment();
        var authInfo = DataHelper.generateEmptyPay();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }
}

