package shops.bot.util;

import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InlineButtonUtil {

    public static InlineKeyboardButton button(String txt, String callbackData){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(txt);
        button.setCallbackData(callbackData);
        return button;
    }

    public static List<InlineKeyboardButton> row(InlineKeyboardButton... inlineKeyboardButtons){
        List<InlineKeyboardButton> row = new LinkedList<>();
        row.addAll(Arrays.asList(inlineKeyboardButtons));
        return row;
    }

    public static List<List<InlineKeyboardButton>> rowCollection (List<InlineKeyboardButton>... rows){
        List<List<InlineKeyboardButton>> collection = new LinkedList<>();
        collection.addAll(Arrays.asList(rows));
        return collection;
    }

    public static InlineKeyboardMarkup keyboard(List<List<InlineKeyboardButton>> rowCollection){
        InlineKeyboardMarkup keyboradMarkUp = new InlineKeyboardMarkup();
        keyboradMarkUp.setKeyboard(rowCollection);
        return keyboradMarkUp;

    }

}
