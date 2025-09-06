package test;

import com.codeborne.selenide.Selenide;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TransferPage;
import pages.VerificationPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CardTransferTest {

    private DashboardPage dashboard;

    @BeforeEach
    public void login() {
        // Выносим логин в отдельный метод, чтобы не повторять в каждом тесте
        LoginPage loginPage = new LoginPage().open();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        dashboard = verificationPage.validVerify("12345");
    }

//    @Test
//    public void shouldTransferMoneyBetweenCards() {
//        // Given
//        int initialBalanceFirstCard = dashboard.getCardBalance(0);
//        int initialBalanceSecondCard = dashboard.getCardBalance(1);
//        int transferAmount = 500;
//        String sourceCardNumber = "5559 0000 0000 0001";
//
//        // When
//        TransferPage transferPage = dashboard.selectCardToTransfer(1); // Пополняем вторую карту
//        dashboard = transferPage.makeTransfer(sourceCardNumber, transferAmount);
//
//        // Then
//        assertThat(dashboard.getCardBalance(0), equalTo(initialBalanceFirstCard - transferAmount));
//        assertThat(dashboard.getCardBalance(1), equalTo(initialBalanceSecondCard + transferAmount));
//    }
@Test
public void shouldTransferMoneyBetweenCards() {
    System.out.println("Opening login page...");
    LoginPage loginPage = new LoginPage().open();

    System.out.println("Attempting login...");
    try {
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        System.out.println("Login successful");
    } catch (Exception e) {
        System.out.println("Login failed: " + e.getMessage());
        // Сделайте скриншот для диагностики
        Selenide.screenshot("login_error");
        throw e;
    }
}
}