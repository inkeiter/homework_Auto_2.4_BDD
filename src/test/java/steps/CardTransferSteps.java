package steps;

import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import data.DataHelper;
import data.AuthInfo;
import data.VerificationCode;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TransferPage;
import pages.VerificationPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CardTransferSteps {

    private DashboardPage dashboard;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void userLoggedIn(String login, String password) {
        LoginPage loginPage = new LoginPage().open();
        AuthInfo authInfo = new AuthInfo(login, password);
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        VerificationCode code = DataHelper.getVerificationCodeFor(authInfo);
        dashboard = verificationPage.validVerify(code);
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void transferMoney(int amount, String sourceCard, int targetCardIndex) {
        TransferPage transferPage = dashboard.selectCardToTransfer(targetCardIndex - 1);
        dashboard = transferPage.makeTransfer(sourceCard, amount);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void verifyBalance(int cardIndex, int expectedBalance) {
        int actualBalance = dashboard.getCardBalance(cardIndex - 1);
        assertThat(actualBalance, equalTo(expectedBalance));
    }
}