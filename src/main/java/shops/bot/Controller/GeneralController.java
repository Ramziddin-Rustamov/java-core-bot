package shops.bot.Controller;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import shops.bot.dto.CodeMessage;
import shops.bot.enums.CodeMessageType;
import shops.bot.util.InlineButtonUtil;

import java.util.LinkedList;
import java.util.List;

public class GeneralController {

    public CodeMessage handle(String text, Long chatId, Integer messageId) {
        CodeMessage codeMessage = new CodeMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        codeMessage.setSendMessage(sendMessage);

        if (text.equals("/boshlash")) {
            sendMessage.setText("Assalomu alaykum bizning nomer saqlaydigan botimizga xush kelibsiz!");
            sendMessage.setParseMode("Markdown");

            // Create buttons for the first row
            InlineKeyboardButton a = InlineButtonUtil.button("go to menu", "menu");

            // Create the first row and
            List<InlineKeyboardButton> row1 = InlineButtonUtil.row(a);

            // Create row collection
            List<List<InlineKeyboardButton>> rowCollection = InlineButtonUtil.rowCollection(row1);
            sendMessage.setReplyMarkup(InlineButtonUtil.keyboard(rowCollection));

            codeMessage.setType(CodeMessageType.MESSAGE);

        } else if (text.equals("/nomerlaringiz")) {
            sendMessage.setText("Nomerlaringizni saqlash uchun bosing.");
            sendMessage.setParseMode("Markdown");
            codeMessage.setSendMessage(sendMessage);
            codeMessage.setType(CodeMessageType.MESSAGE);

        } else if (text.equals("/info")) {
            String txt = "Bu bot nomerlarni saqlab odamlarimizga shu nomerlarni topishga yaqindan " +
                    "yordam beradi degan umidda ishlab chiqildi. " +
                    "Asosiy guruhimiz bu yerda [inline URL](https://www.youtube.com)";
            sendMessage.setText(txt);
            sendMessage.setParseMode("Markdown");
            codeMessage.setType(CodeMessageType.MESSAGE);

        } else if (text.equals("menu")) {
            EditMessageText editMessageText = new EditMessageText();

            editMessageText.setText("Asosiy menu");
            editMessageText.setChatId(chatId.toString());
            editMessageText.setMessageId(messageId);
            editMessageText.setParseMode("Markdown");

            editMessageText.setReplyMarkup(InlineButtonUtil.keyboard(
                    InlineButtonUtil.rowCollection(
                            InlineButtonUtil.row(InlineButtonUtil.button("To do list", "/todo/list")),
                            InlineButtonUtil.row(InlineButtonUtil.button("Create To do list", "/todo/create"))
                    )
            ));
            codeMessage.setType(CodeMessageType.EDIT);
            codeMessage.setEditMessageText(editMessageText);
        }
        return codeMessage;
    }
}
