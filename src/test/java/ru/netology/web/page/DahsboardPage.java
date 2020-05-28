package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DahsboardPage {
    public SelenideElement dashboard = $("[data-test-id=dashboard]");
    public String valueFirst = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]").getText();
    public String valueSecond = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]").getText();

    public DahsboardPage() {
        dashboard.shouldBe(visible);
    }

    public int getBalanceOfFirstCard() {
        String balanceOfFirstCard = valueFirst.substring(29, valueFirst.indexOf(" ", 29));
        return Integer.parseInt(balanceOfFirstCard);
    }

    public int getBalanceOfSecondCard() {
        String balanceOfSecondCard = valueSecond.substring(29, valueSecond.indexOf(" ", 29));
        return Integer.parseInt(balanceOfSecondCard);
    }

    public int getCurrentBalanceOfFirstCard() {
        SelenideElement value = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
        String value1 = value.getText();
        String balanceOfFirstCard = value1.substring(29, value1.indexOf(" ", 29));
        return Integer.parseInt(balanceOfFirstCard);
    }

    public int getCurrentBalanceOfSecondCard() {
        SelenideElement value = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");
        String value1 = value.getText();
        String balanceOfSecondCard = value1.substring(29, value1.indexOf(" ", 29));
        return Integer.parseInt(balanceOfSecondCard);
    }
}
