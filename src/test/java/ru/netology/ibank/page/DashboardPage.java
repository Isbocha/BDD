package ru.netology.ibank.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.ibank.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement header = $("[data-test-id=dashboard]");
    private SelenideElement firstTransfer = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] [data-test-id=action-deposit]");
    private SelenideElement secondTransfer = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] [data-test-id=action-deposit]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement update = $("[data-test-id=action-reload]");

    public DashboardPage() {
        header.shouldBe(visible);
    }

    public TransferPage transferPage1(DataHelper.CardNumber cardNumber) {
        firstTransfer.click();
//        cards.first().click();
        return new TransferPage();
    }
    public TransferPage transferPage2(DataHelper.CardNumber cardNumber) {
        secondTransfer.click();
//        cards.last().click();
        return new TransferPage();
    }
    public int getCardBalance(DataHelper.CardNumber cardNumber) {
        var text = cards.findBy(attribute("data-test-id", cardNumber.getCardID())).text();
        return extractBalance(text);
    }

    public int getCardBalanceForIndex(int index) {
        var text = cards.get(index).getText();
        return extractBalance(text);
    }
    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


}
