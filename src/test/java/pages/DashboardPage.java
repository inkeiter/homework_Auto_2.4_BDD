package pages;

//Метод getCardBalance(String cardNumber) или getCardBalance(int cardIndex):
//получает текст элемента карты и извлекает из него числовое значение баланса (как в подсказке).
//Это доменный метод — он решает конкретную бизнес-задачу.
//
//Метод selectCardToTransfer(String cardNumber) или selectCardToTransfer(int cardIndex):
//нажимает кнопку "Пополнить" у конкретной карты. Возвращает следующий Page Object — TransferPage.

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    // Элемент для списка карт (Selenide пример)
    private ElementsCollection cards = $$(".list__item div"); // Локатор для всех карточек
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    // Доменный метод для получения баланса
    public int getCardBalance(int cardIndex) {
        String text = cards.get(cardIndex).text(); // Получаем текст элемента
        return extractBalance(text);                // Извлекаем число из текста
    }

    // Вспомогательный private метод для парсинга баланса
    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish).trim();
        return Integer.parseInt(value);
    }

    // Доменный метод для перехода на страницу перевода
    public TransferPage selectCardToTransfer(int cardIndex) {
        cards.get(cardIndex).$("button").click(); // Ищем кнопку внутри элемента карты и кликаем
        return new TransferPage(); // Возвращаем объект следующей страницы
    }
}