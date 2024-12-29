package me.maplef;


import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class NekoTownBot implements LongPollingSingleThreadUpdateConsumer {
    private final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    private TelegramClient tgClient = new OkHttpTelegramClient(BOT_TOKEN);
    
    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage message = SendMessage.builder()
                                            .chatId(chat_id)
                                            .text(message_text)
                                            .build();
            
            try {
                tgClient.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
