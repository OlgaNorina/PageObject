package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.DahsboardPage;
import ru.netology.web.page.TransferPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void shouldVerify() {
        val loginPage = new LoginPage();
        loginPage.openPage();
        val authInfo = DataHelper.getAuthInfoValid();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void shouldTransferMoneyFromSecondToFirst(int transfer) {

        DahsboardPage page = new DahsboardPage();
        int startBalanceOfFirstCard = page.getBalanceOfFirstCard();
        int startBalanceOfSecondCard = page.getBalanceOfSecondCard();
        System.out.println("Баланс карты 0001 до транзакции: " + startBalanceOfFirstCard);
        System.out.println("Баланс карты 0002 до транзакции: " + startBalanceOfSecondCard);

        val transferPage = new TransferPage();
        val transferFrom2To1Card = DataHelper.getSecondCardInfo();
        transferPage.transferFromSecondToFirst(transferFrom2To1Card, transfer);
        val balanceFirstCard = startBalanceOfFirstCard + transfer;
        val balanceSecondCard = startBalanceOfSecondCard - transfer;
        val currentBalanceOfFirstCard = page.getCurrentBalanceOfFirstCard();
        val currentBalanceOfSecondCard = page.getCurrentBalanceOfSecondCard();

        assertEquals(balanceFirstCard, currentBalanceOfFirstCard);
        assertEquals(balanceSecondCard, currentBalanceOfSecondCard);

        System.out.println("Баланс карты 0001 после транзакции: " + currentBalanceOfFirstCard);
        System.out.println("Баланс карты 0002 после транзакции: " + currentBalanceOfSecondCard);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void shouldTransferMoneyFromFirstToSecond(int transfer) {

        DahsboardPage page = new DahsboardPage();
        int startBalanceOfFirstCard = page.getBalanceOfFirstCard();
        int startBalanceOfSecondCard = page.getBalanceOfSecondCard();
        System.out.println("Баланс карты 0001 до транзакции: " + startBalanceOfFirstCard);
        System.out.println("Баланс карты 0002 до транзакции: " + startBalanceOfSecondCard);

        val transferPage = new TransferPage();
        val transferFrom1To2Card = DataHelper.getFirstCardInfo();
        transferPage.transferFromFirstToSecond(transferFrom1To2Card, transfer);
        val balanceFirstCardAfterTrans = startBalanceOfFirstCard - transfer;
        val balanceSecondCardAfterTrans = startBalanceOfSecondCard + transfer;
        val currentBalanceOfFirstCard = page.getCurrentBalanceOfFirstCard();
        val currentBalanceOfSecondCard = page.getCurrentBalanceOfSecondCard();

        assertEquals(balanceFirstCardAfterTrans, currentBalanceOfFirstCard);
        assertEquals(balanceSecondCardAfterTrans, currentBalanceOfSecondCard);

        System.out.println("Баланс карты 0001 после транзакции: " + currentBalanceOfFirstCard);
        System.out.println("Баланс карты 0002 после транзакции: " + currentBalanceOfSecondCard);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondMinus() {

        DahsboardPage page = new DahsboardPage();
        int startBalanceOfFirstCard = page.getBalanceOfFirstCard();
        int startBalanceOfSecondCard = page.getBalanceOfSecondCard();

        val transferPage = new TransferPage();
        val transferFrom1To2Card = DataHelper.getFirstCardInfo();
        int transfer = -1000;
        transferPage.transferFromFirstToSecond(transferFrom1To2Card, transfer);
        val balanceFirstCardAfterTrans = startBalanceOfFirstCard + transfer;
        val balanceSecondCardAfterTrans = startBalanceOfSecondCard - transfer;
        val currentBalanceOfFirstCard = page.getCurrentBalanceOfFirstCard();
        val currentBalanceOfSecondCard = page.getCurrentBalanceOfSecondCard();

        assertEquals(balanceFirstCardAfterTrans, currentBalanceOfFirstCard);
        assertEquals(balanceSecondCardAfterTrans, currentBalanceOfSecondCard);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondMoreThanExist() {

        DahsboardPage page = new DahsboardPage();
        int startBalanceOfFirstCard = page.getBalanceOfFirstCard();
        int startBalanceOfSecondCard = page.getBalanceOfSecondCard();

        System.out.println("Баланс карты 0001 до транзакции: " + startBalanceOfFirstCard);
        System.out.println("Баланс карты 0002 до транзакции: " + startBalanceOfSecondCard);

        val transferPage = new TransferPage();
        val transferFrom1To2Card = DataHelper.getFirstCardInfo();
        int transfer = 20000;
        transferPage.transferFromFirstToSecond(transferFrom1To2Card, transfer);
        val balanceFirstCardAfterTrans = startBalanceOfFirstCard - transfer;
        val balanceSecondCardAfterTrans = startBalanceOfSecondCard + transfer;
        val currentBalanceOfFirstCard = page.getCurrentBalanceOfFirstCard();
        val currentBalanceOfSecondCard = page.getCurrentBalanceOfSecondCard();

        assertEquals(balanceFirstCardAfterTrans, currentBalanceOfFirstCard);
        assertEquals(balanceSecondCardAfterTrans, currentBalanceOfSecondCard);

        System.out.println("Баланс карты 0001 после транзакции: " + currentBalanceOfFirstCard);
        System.out.println("Баланс карты 0002 после транзакции: " + currentBalanceOfSecondCard);
    }

    @Test
    void shouldInvalidVerification() {
        val loginPage = new LoginPage();
        loginPage.openPage();
        val authInfo = DataHelper.getAuthInfoInvalid();
        loginPage.invalidLogin(authInfo);
    }
}
