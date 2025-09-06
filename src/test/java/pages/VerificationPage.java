package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import data.VerificationCode;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public DashboardPage validVerify(VerificationCode code) {
        codeField.setValue(code.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
