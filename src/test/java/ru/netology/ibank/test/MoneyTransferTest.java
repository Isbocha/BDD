package ru.netology.ibank.test;

import org.junit.jupiter.api.*;
import ru.netology.ibank.data.DataHelper;
import ru.netology.ibank.page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");

    }

    @Test
    @DisplayName("Should Successful Transfer From First Card To Second Of The Valid Amount")
    void shouldSuccessfulTransferFromFirstCardToSecondOfTheValidAmountTest() {

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getFirstCardNumber();
        var secondCard = DataHelper.getSecondCardNumber();
        var firstCardBalance = dashboardPage.getCardBalance(firstCard);
        var secondCardBalance = dashboardPage.getCardBalance(secondCard);
        var amount = DataHelper.validAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance + amount;
        var expectedBalanceSecondCard = secondCardBalance - amount;
        var transferPage = dashboardPage.transferPage1(firstCard);
        var transferMoney = transferPage.validTransfer(String.valueOf(amount), secondCard);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCard);
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    @DisplayName("Should Successful Transfer From Second Card To First Of The Valid Amount")
    void shouldSuccessfulTransferFromSecondCardToFirstOfTheValidAmountTest() {

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getFirstCardNumber();
        var secondCard = DataHelper.getSecondCardNumber();
        var firstCardBalance = dashboardPage.getCardBalance(firstCard);
        var secondCardBalance = dashboardPage.getCardBalance(secondCard);
        var amount = DataHelper.validAmount(secondCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;
        var transferPage = dashboardPage.transferPage2(secondCard);
        var transferMoney = transferPage.validTransfer(String.valueOf(amount), firstCard);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCard);
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    @DisplayName("Should Successful Transfer From Second Card To First Of The Invalid Amount")
    void shouldSuccessfulTransferFromFirstCardToSecondOfTheInvalidAmountTest() {

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getFirstCardNumber();
        var secondCard = DataHelper.getSecondCardNumber();
        var firstCardBalance = dashboardPage.getCardBalance(firstCard);
        var secondCardBalance = dashboardPage.getCardBalance(secondCard);
        var amount = DataHelper.invalidAmount(firstCardBalance);
        var transferPage = dashboardPage.transferPage1(firstCard);
        transferPage.transfer(String.valueOf(amount), firstCard);
        transferPage.invalidTransfer("Данная операция невозможна, т.к. сумма перевода превышает доступный лимит");
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCard);
        Assertions.assertEquals(firstCardBalance, actualBalanceFirstCard);
        Assertions.assertEquals(secondCardBalance, actualBalanceSecondCard);
    }

    @Test
    @DisplayName("Should Successful Transfer  From First Card To Second Of The Invalid Amount")
    void shouldSuccessfulTransferFromSecondCardToFirstOfTheInvalidAmountTest() {

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getFirstCardNumber();
        var secondCard = DataHelper.getSecondCardNumber();
        var firstCardBalance = dashboardPage.getCardBalance(firstCard);
        var secondCardBalance = dashboardPage.getCardBalance(secondCard);
        var amount = DataHelper.invalidAmount(secondCardBalance);
        var transferPage = dashboardPage.transferPage2(secondCard);
        transferPage.transfer(String.valueOf(amount), firstCard);
        transferPage.invalidTransfer("Данная операция невозможна, т.к. сумма перевода превышает доступный лимит");
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCard);
        Assertions.assertEquals(firstCardBalance, actualBalanceFirstCard);
        Assertions.assertEquals(secondCardBalance, actualBalanceSecondCard);
    }
}
