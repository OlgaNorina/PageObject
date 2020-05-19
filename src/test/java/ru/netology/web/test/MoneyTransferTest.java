package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.DahsboardPage;
import ru.netology.web.page.TransferPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MoneyTransferTest {

    @BeforeEach
    void shouldVerification() {
        val loginPage = new LoginPage();
        loginPage.openPage();
        val authInfo = DataHelper.getAuthInfoValid();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
        System.out.println(DahsboardPage.getBalanceCard());
    }

    @Order(1)
    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        val transferPage = new TransferPage();
        val transferFrom2To1Card = DataHelper.getsecondCardInfo();
        transferPage.transferFromSecondToFirst(transferFrom2To1Card, "1000");
        assertEquals("**** **** **** 0001, баланс: 11000 р.\n" +
                        "Пополнить\n" + "**** **** **** 0002, баланс: 9000 р.\n" + "Пополнить",
                DahsboardPage.getBalanceCard());
    }

    @Order(2)
    @Test
    void shouldTransferMoneyFromFirstToSecond() {
        val transferPage = new TransferPage();
        val transferFrom1To2Card = DataHelper.getfirstCardInfo();
        transferPage.transferFromFirstToSecond(transferFrom1To2Card, "11000");
        assertEquals("**** **** **** 0001, баланс: 0 р.\n" +
                        "Пополнить\n" + "**** **** **** 0002, баланс: 20000 р.\n" + "Пополнить",
                DahsboardPage.getBalanceCard());
    }

    @Order(3)
    @Test
    void shouldTransferMoneyFromSecondToFirstMinus() {
        val transferPage = new TransferPage();
        val transferFrom2To1Card = DataHelper.getsecondCardInfo();
        transferPage.transferFromSecondToFirst(transferFrom2To1Card, "-1000");
        assertEquals("**** **** **** 0001, баланс: 1000 р.\n" +
                        "Пополнить\n" + "**** **** **** 0002, баланс: 19000 р.\n" + "Пополнить",
                DahsboardPage.getBalanceCard());
    }

    @Order(4)
    @Test
    void shouldTransferMoneyFromSecondToFirstUnit() {
        val transferPage = new TransferPage();
        val transferFrom2To1Card = DataHelper.getsecondCardInfo();
        transferPage.transferFromSecondToFirst(transferFrom2To1Card, "1");
        assertEquals("**** **** **** 0001, баланс: 1001 р.\n" +
                        "Пополнить\n" + "**** **** **** 0002, баланс: 18999 р.\n" + "Пополнить",
                DahsboardPage.getBalanceCard());
    }

    @Order(5)
    @Test
    void shouldTransferMoneyFromFirstToSecondWintPenny() {
        val transferPage = new TransferPage();
        val transferFrom1To2Card = DataHelper.getsecondCardInfo();
        transferPage.transferFromFirstToSecond(transferFrom1To2Card, "1.9");
        assertEquals("**** **** **** 0001, баланс: 1001 р.\n" +
                        "Пополнить\n" + "**** **** **** 0002, баланс: 18999 р.\n" + "Пополнить",
                DahsboardPage.getBalanceCard());
    }

    @Order(6)
    @Test
    void shouldTransferMoneyFromFirstToSecondWintEmpty() {
        val transferPage = new TransferPage();
        val transferFrom1To2Card = DataHelper.getsecondCardInfo();
        transferPage.transferFromFirstToSecond(transferFrom1To2Card, " ");
        assertEquals("**** **** **** 0001, баланс: 1001 р.\n" +
                        "Пополнить\n" + "**** **** **** 0002, баланс: 18999 р.\n" + "Пополнить",
                DahsboardPage.getBalanceCard());
    }

     @Order(7)
    @Test
    void shouldTransferMoneyFromSecondToFirstWintSumbol() {
        val transferPage = new TransferPage();
        val transferFrom2To1Card = DataHelper.getsecondCardInfo();
        transferPage.transferFromSecondToFirst(transferFrom2To1Card, "fKТ%&/.");
        assertEquals("**** **** **** 0001, баланс: 1001 р.\n" +
                        "Пополнить\n" + "**** **** **** 0002, баланс: 18999 р.\n" + "Пополнить",
                DahsboardPage.getBalanceCard());
    }

    @Order(8)
    @Test
    void shouldInvalidVerification() {
        val loginPage = new LoginPage();
        loginPage.openPage();
        val authInfo = DataHelper.getAuthInfoInvalid();
        loginPage.invalidLogin(authInfo);
    }

    @Order(9)
    @Test
    void shouldTransferMoneyFromFirstToSecondMoreThanIs() {
        val transferPage = new TransferPage();
        val transferFrom1To2Card = DataHelper.getfirstCardInfo();
        transferPage.transferFromFirstToSecond(transferFrom1To2Card, "2000");
        assertEquals("**** **** **** 0001, баланс: 0 р.\n" +
                        "Пополнить\n" + "**** **** **** 0002, баланс: 20000 р.\n" + "Пополнить",
                DahsboardPage.getBalanceCard());
    }
}
