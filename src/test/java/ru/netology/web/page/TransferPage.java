package ru.netology.web.page;

import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    public DahsboardPage transferFromSecondToFirst(DataHelper.CardsInfo info, String value) {
        $$(withText("Пополнить")).first().click();
        $("[data-test-id=amount] input").setValue(value);
        $("[data-test-id=from] input").setValue(info.getNumberOfCard());
        $("[data-test-id=action-transfer]").click();
        return new DahsboardPage();
    }

    public DahsboardPage transferFromFirstToSecond(DataHelper.CardsInfo info, String value) {
        $$(withText("Пополнить")).last().click();
        $("[data-test-id=amount] input").setValue(value);
        $("[data-test-id=from] input").setValue(info.getNumberOfCard());
        $("[data-test-id=action-transfer]").click();
        return new DahsboardPage();
    }
}
