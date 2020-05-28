package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {
    public SelenideElement amountInput = $("[data-test-id=amount] input");
    public SelenideElement cardFromInput = $("[data-test-id=from] input");
    public SelenideElement buttonTransferAction = $("[data-test-id=action-transfer]");
    public SelenideElement buttonReplenishFirstCard = $$(withText("Пополнить")).first();
    public SelenideElement buttonReplenishSecondCard = $$(withText("Пополнить")).last();

    public DahsboardPage transferFromSecondToFirst(DataHelper.CardsInfo info, int transfer) {
        buttonReplenishFirstCard.click();
        amountInput.setValue(String.valueOf(transfer));
        cardFromInput.setValue(info.getNumberOfCard());
        buttonTransferAction.click();
        return new DahsboardPage();
    }

    public DahsboardPage transferFromFirstToSecond(DataHelper.CardsInfo info, int transfer) {
        buttonReplenishSecondCard.click();
        amountInput.setValue(String.valueOf(transfer));
        cardFromInput.setValue(info.getNumberOfCard());
        buttonTransferAction.click();
        return new DahsboardPage();
    }
}