package ru.netology.ibank.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.ibank.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField =  $("[data-test-id=code] input");
    private SelenideElement verifyBottom =$("[data-test-id=action-verify]");

    public VerificationPage (){
        codeField.shouldBe(visible);
    }
    public DashboardPage validVerify (DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyBottom.click();
        return new DashboardPage();
    }
}
