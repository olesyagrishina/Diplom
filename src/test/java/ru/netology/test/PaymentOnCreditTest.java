package ru.netology.test;

import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import ru.netology.DataHelper;
import ru.netology.PaymentPage;
import ru.netology.SQLHelper;

public class PaymentOnCreditTest {
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
    public void shouldPayTravelOnCreditApproved() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generateApprovedPay();
        paymentPage.validDataInput(authInfo);
        paymentPage.successMessage();
        var payment_ID = SQLHelper.paymentID();
        var status = SQLHelper.paymentCreditStatus(payment_ID);
        Assertions.assertEquals("APPROVED", status);
    }

    @Test
    public void shouldPayTravelOnCreditDeclined() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generateDeclinedPay();
        paymentPage.validDataInput(authInfo);
        paymentPage.errorMessage();
        var payment_ID = SQLHelper.paymentID();
        var status = SQLHelper.paymentCreditStatus(payment_ID);
        Assertions.assertEquals("DECLINED", status);
    }

    @Test
    public void shouldPayTravelByNonExistentCardOnCredit() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayNonExistentCard();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelByZeroCardOnCredit() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayZeroCardNumber();
        paymentPage.validDataInput(authInfo);
        paymentPage.errorMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidMonthZero() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidMonthZero();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidMonth13() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidMonth13();
        paymentPage.validDataInput(authInfo);
        paymentPage.errorDateMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidMonth1() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidFormatMonth();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidYearZero() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidYearZero();
        paymentPage.validDataInput(authInfo);
        paymentPage.expireDateMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidYear1() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidYear1();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidCardholderNameRU() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidCardholderRU();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidCardholderNameNumber() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidCardholderNumber();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidCardholderNameSymbol() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidCardholderSymbol();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidCvcZero() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidCvcZero();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidCvc1() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidCvc1();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelOnCreditIfInvalidCvc11() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generatePayInvalidCvc11();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }

    @Test
    public void shouldPayTravelOnCreditWithEmptyForm() {
        var paymentPage = new PaymentPage();
        paymentPage.creditPayment();
        var authInfo = DataHelper.generateEmptyPay();
        paymentPage.validDataInput(authInfo);
        paymentPage.invalidFormatMessage();
    }
}
