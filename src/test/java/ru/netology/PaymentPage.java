package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.checkerframework.checker.units.qual.C;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    private final SelenideElement pay = $(byText("Купить"));
    private final SelenideElement cardPayment = $(byText("Оплата по карте"));
    private final SelenideElement payInCredit = $(byText("Купить в кредит"));
    private final SelenideElement creditPayment = $(byText("Кредит по данным карты"));
    private final SelenideElement cardNumberInput = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthInput = $("[placeholder='08']");
    private final SelenideElement yearInput = $("[placeholder='22']");
    private final SelenideElement userInput = $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input");
    private final SelenideElement cvcInput = $("[placeholder='999']");
    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement successMessage = $(byText("Успешно"));
    private final SelenideElement errorMessage = $(byText("Ошибка"));
    private final SelenideElement invalidFormatMessage = $(byText("Неверный формат"));
    private final SelenideElement expiryDateMessage = $(byText("Истёк срок действия карты"));
    private final SelenideElement errorDateMessage = $(byText("Неверно указан срок действия карты"));

    public void cardPayment() {
        pay.click();
        cardPayment.shouldBe(visible);
    }

    public void creditPayment() {
        payInCredit.click();
        creditPayment.shouldBe(visible);
    }

    public void validDataInput(DataHelper.CardInfo info) {
        cardNumberInput.setValue(info.getCardNumber());
        monthInput.setValue(info.getMonth());
        yearInput.setValue(info.getYear());
        userInput.setValue(info.getName());
        cvcInput.setValue(String.valueOf(info.getCvc()));
        continueButton.click();
    }

    public void successMessage() {
        successMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorMessage() {
        errorMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void invalidFormatMessage() {
        invalidFormatMessage.shouldBe(visible);
    }

    public void expireDateMessage() {
        expiryDateMessage.shouldBe(visible);
    }

    public void errorDateMessage() {
        errorDateMessage.shouldBe(visible);
    }
}
