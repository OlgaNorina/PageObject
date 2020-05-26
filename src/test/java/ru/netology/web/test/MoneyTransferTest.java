package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.DahsboardPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    /*@BeforeEach
    void shouldVerification() {
        val loginPage = new LoginPage();
        loginPage.openPage();
        val authInfo = DataHelper.getAuthInfoValid();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
        System.out.println(DahsboardPage.getBalanceOfFirstCard());
        System.out.println(DahsboardPage.getBalanceOfSecondCard());
    }*/

    @ParameterizedTest
    @CsvFileSource(resources = "/dataFromSecondToFirst.csv")
    void shouldTransferMoneyFromSecondToFirst(int transfer, int balanceOfFirstCard, int balanceOfSecondCard) {
        val loginPage = new LoginPage();
        loginPage.openPage();
        val authInfo = DataHelper.getAuthInfoValid();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
        val dahsboardPage = new DahsboardPage();
        val transferToFirst = dahsboardPage.transferToFirstCard();
        //System.out.println($(".list"));
        //System.out.println("Баланс карты 0001: " + DahsboardPage.getBalanceOfFirstCard());
        //System.out.println(DahsboardPage.getBalanceOfSecondCard());
        val transferFrom2To1Card = DataHelper.getSecondCardInfo();
        transferToFirst.transferInfo(transferFrom2To1Card, transfer);
        assertEquals(balanceOfFirstCard, DahsboardPage.getBalanceOfFirstCard());
        assertEquals(balanceOfSecondCard, DahsboardPage.getBalanceOfSecondCard());
    }

    /*@Test
    void shouldTransferMoneyFromFirstToSecond(int transfer) {
        val transferPage = new TransferPage();
        val transferFrom1To2Card = DataHelper.getFirstCardInfo();
        transferPage.transferInfo(transferFrom1To2Card, 11000);
        assertEquals(0, DahsboardPage.getBalanceOfFirstCard());
        assertEquals(20000, DahsboardPage.getBalanceOfSecondCard());
    }

    //-1000, 1, 1,9, 0, больше, чем есть

    @Test
    void shouldInvalidVerification() {
        val loginPage = new LoginPage();
        loginPage.openPage();
        val authInfo = DataHelper.getAuthInfoInvalid();
        loginPage.invalidLogin(authInfo);
    }*/
}
