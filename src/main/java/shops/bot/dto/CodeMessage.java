package shops.bot.dto;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import shops.bot.enums.CodeMessageType;

public class CodeMessage {

    private SendMessage sendMessage;
    private EditMessageText editMessageText;
    public CodeMessageType type;

    public CodeMessageType getType() {
        return type;
    }

    public void setType(CodeMessageType type) {
        this.type=type;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage=sendMessage;
    }

    public EditMessageText getEditMessageText() {
        return editMessageText;
    }

    public void setEditMessageText(EditMessageText editMessageText) {
        this.editMessageText=editMessageText;
    }
}
