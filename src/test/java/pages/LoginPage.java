package pages;

//Метод loginWith(String login, String password, String code): заполняет поля и нажимает кнопку "Продолжить", а затем "Войти".
//Возвращает следующий Page Object — DashboardPage.

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import data.AuthInfo;
import data.VerificationCode;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    // Элемент для списка карт (Selenide пример)
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $(".notification");

    public LoginPage open() {
        Selenide.open("http://127.0.0.1:9999");
        return this;
    }

    public LoginPage waitUntilLoaded() {
        loginField.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    public VerificationPage validLogin(AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return page(VerificationPage.class); // Ждем загрузки следующей страницы
    }

    public LoginPage invalidLogin(AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        errorNotification.shouldBe(visible);
        return this;
    }
    }

