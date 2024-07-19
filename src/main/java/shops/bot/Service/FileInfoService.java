package shops.bot.Service;

import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Video;
import shops.bot.dto.CodeMessage;
import shops.bot.enums.CodeMessageType;

import java.util.List;

public class FileInfoService {

    public CodeMessage getFileInfo(Message message){
        Long cId = message.getChatId();
        CodeMessage codeMessage = new CodeMessage();
        codeMessage.setType(CodeMessageType.MESSAGE);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(cId);

        if (message.getPhoto() != null){
            String s = this.show_photo_details(message.getPhoto());
            sendMessage.setText(s);
        }else if(message.getVideo() != null){
            String s = this.show_video_detail(message.getVideo());
            sendMessage.setText(s);
        }else{
            sendMessage.setText("Not found");
        }
        codeMessage.setSendMessage(sendMessage);
        return codeMessage;
    }

    private String show_photo_details(List<PhotoSize> photoSizeList){
        String s = "image info \n";
        for(PhotoSize photoSize : photoSizeList){
            s+= " Size = " + photoSize.getFileSize() + " , Id = " + photoSize.getFileSize() + " \n";
        }
        return s;
    }

    private String show_video_detail(Video video){
        String s = " video info \n";
        s+= video.toString();
        return s;
    }
}
