package shops.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import shops.bot.Controller.MainController;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        try {
            // Use DefaultBotSession for newer versions of the library
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new MainController());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
