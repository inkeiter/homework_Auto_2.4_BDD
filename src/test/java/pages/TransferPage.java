package pages;

//Метод makeTransfer(String fromCard, String amount) или makeTransfer(int amount, String toCard):
// заполняет поля "Сумма" и выбирает карту-отправитель из выпадающего списка (если он есть), нажимает кнопку "Пополнить".
//Возвращает предыдущий Page Object — DashboardPage, так как после перевода мы на него попадаем.

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement fromInput = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    // Этот метод занимается только своим делом — переводом денег.
    // Он не парсит баланс!
    public DashboardPage makeTransfer(String cardNumber, int amount) {
        amountInput.setValue(String.valueOf(amount));
        fromInput.setValue(cardNumber);
        transferButton.click();
        return new DashboardPage(); // Возвращаемся на дашборд
    }
}