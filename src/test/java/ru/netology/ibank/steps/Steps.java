//package ru.netology.ibank.steps;
//
//import com.codeborne.selenide.Selenide;
//import io.cucumber.java.en.When;
//import io.cucumber.java.ru.И;
//import io.cucumber.java.ru.Когда;
//import io.cucumber.java.ru.Пусть;
//import io.cucumber.java.ru.Тогда;
//import ru.netology.ibank.data.DataHelper;
//import ru.netology.ibank.page.DashboardPage;
//import ru.netology.ibank.page.LoginPageV1;
//import ru.netology.ibank.page.TransferPage;
//import ru.netology.ibank.page.VerificationPage;
//
//public class Steps {
//    private static LoginPageV1 loginPage;
//    private static DashboardPage dashboardPage;
//    private static VerificationPage verificationPage;
//    private static TransferPage transferPage;
//
//    @Пусть("пользователь залогинен с именем «vasya» и паролем «qwerty123»");
//    public void openAuthPage(String url) {
//        loginPage = Selenide.open(url, LoginPageV1.class);
//    }
//    public void loginAndPassword (String login, String password){
//        verificationPage = loginPage.validLogin(login, password);
//    }
//    public void setValidCode (String validCode){
//        dashboardPage =verificationPage.validVerify(validCode);
//    }
//
//    @Когда("когда пользователь переводит 5 000 рублей с карты с номером 5559 0000 0000 0002 на свою 1 карту с главной страницы,");
//    public void setValidCode (String amount, String cardNumber) {
//        transferPage = transferPage.transfer(amount, cardNumber);
//    }
//
//
//    @Тогда("баланс его 1 карты из списка на главной странице должен стать 15 000 рублей."){
//
//        }
//}
