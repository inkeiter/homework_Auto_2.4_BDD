package test;

import com.codeborne.selenide.Selenide;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TransferPage;
import pages.VerificationPage;
import data.AuthInfo;
import data.VerificationCode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CardTransferTest {

    private DashboardPage dashboard;

    @BeforeEach
    public void login() {
        // 1. Открываем страницу логина
        LoginPage loginPage = new LoginPage().open();

        // 2. Получаем данные для авторизации
        AuthInfo authInfo = DataHelper.getAuthInfo();

        // 3. Логинимся и получаем страницу верификации
        VerificationPage verificationPage = loginPage.validLogin(authInfo);

        // 4. Получаем код верификации
        VerificationCode code = DataHelper.getVerificationCodeFor(authInfo);

        // 5. Вводим код и получаем dashboard
        dashboard = verificationPage.validVerify(code);
    }

    @Test
    public void shouldTransferMoneyBetweenCards() {
        // Given
        int initialBalanceFirstCard = dashboard.getCardBalance(0);
        int initialBalanceSecondCard = dashboard.getCardBalance(1);
        int transferAmount = 500;
        String sourceCardNumber = "5559 0000 0000 0001";

        // When
        TransferPage transferPage = dashboard.selectCardToTransfer(1); // Пополняем вторую карту
        dashboard = transferPage.makeTransfer(sourceCardNumber, transferAmount);

        // Then
        assertThat(dashboard.getCardBalance(0), equalTo(initialBalanceFirstCard - transferAmount));
        assertThat(dashboard.getCardBalance(1), equalTo(initialBalanceSecondCard + transferAmount));
    }
}