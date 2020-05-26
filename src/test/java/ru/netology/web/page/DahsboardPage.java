package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DahsboardPage {
    public SelenideElement dashboard = $("[data-test-id=dashboard]");
    public SelenideElement buttonNewDashboardPage = $$(".button").find(exactText("Обновить"));
    public static String valueFirst = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]").getText();
    public static String valueSecond = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]").getText();
    public SelenideElement buttonReplenishFirstCard = $$(withText("Пополнить")).first();
    public SelenideElement buttonReplenishSecondCard = $$(withText("Пополнить")).last();

    public DahsboardPage() {
        dashboard.shouldBe(visible);
    }

    public TransferPage transferToFirstCard(){
        buttonReplenishFirstCard.click();
        return new TransferPage();
    }

    public TransferPage transferToSecondCard(){
        buttonReplenishSecondCard.click();
        return new TransferPage();
    }

    public static int getBalanceOfFirstCard(){
        String balanceOfFirstCard = valueFirst.substring(29, valueFirst.indexOf(" ", 29));
        return Integer.parseInt(balanceOfFirstCard);
    }

    public static int getBalanceOfSecondCard(){
        String balanceOfSecondCard = valueSecond.substring(29, valueSecond.indexOf(" ", 29));
        return Integer.parseInt(balanceOfSecondCard);
    }
}
