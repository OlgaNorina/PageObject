package ru.netology.web.page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DahsboardPage {

    public DahsboardPage() {
        $("[data-test-id=dashboard]").shouldBe(visible);
    }

    public static String getBalanceCard(){
        $$(".button").find(exactText("Обновить")).click();
        return $(".list").getText();
    }
}
