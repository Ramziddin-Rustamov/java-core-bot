package shops.bot.Controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import shops.bot.Service.FileInfoService;
import shops.bot.dto.CodeMessage;

public class MainController extends TelegramLongPollingBot {

    private GeneralController generalController;
    private FileInfoService fileInfoService;

    public MainController(){
        this.generalController = new GeneralController();
        this.fileInfoService = new FileInfoService();
    }

    @Override
    public String getBotUsername() {
        return "RustamovRamziddinbot";
    }

    @Override
    public String getBotToken() {
        return "1902188650:AAEDnyJ-anM5fs4mBtbY7Vi2J-hostLob7I";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Handle callback query
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            Long chatId = callbackQuery.getMessage().getChatId();
            Integer messageId = callbackQuery.getMessage().getMessageId();

            // Handle the callback data
            if (data.equals("menu")) {
                CodeMessage codeMessage = generalController.handle(data, chatId, messageId);
                this.sendMsg(codeMessage);
            }

            // Handle message
        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            String text = message.getText();
            Long chatId = message.getChatId();
            Integer messageId = message.getMessageId();

            if (text != null) {
                if (text.equals("/boshlash") || text.equals("/nomerlaringiz") || text.equals("/info")) {
                    CodeMessage codeMessage = generalController.handle(text, chatId, messageId);
                    this.sendMsg(codeMessage);
                } else {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId.toString());
                    sendMessage.setText("Mavjud bo'lmagan buyruq!!");
                    sendMsg(sendMessage);
                }
            } else {
                // Handle files
                CodeMessage codeMessage = this.fileInfoService.getFileInfo(message);
                this.sendMsg(codeMessage);
            }
        }
    }

    public void sendMsg(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMsg(CodeMessage codeMessage) {
        try {
            switch (codeMessage.getType()) {
                case MESSAGE:
                    execute(codeMessage.getSendMessage());
                    break;
                case EDIT:
                    execute(codeMessage.getEditMessageText());
                    break;
                default:
                    break;
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
