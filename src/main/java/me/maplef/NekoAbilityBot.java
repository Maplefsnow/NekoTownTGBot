package me.maplef;

import org.telegram.telegrambots.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.abilitybots.api.objects.Locality;
import org.telegram.telegrambots.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class NekoAbilityBot extends AbilityBot{

    public NekoAbilityBot(TelegramClient telegramClient, String botUserName) {
        super(telegramClient, botUserName);
    }

    @Override
    public long creatorId() {
        return 1970225123;
    }

    @Override
    public void onRegister() {
        silent.send("NekoTown Bot Start!", -1002439035442L);
    }

    public Ability testBot() {
        return Ability
                .builder()
                .name("hello")
                .info("say hello qwq")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    silent.send("HelloWorld from Java TGBot API - Ability", ctx.chatId());
                })
                .build();
    }

    public Ability testCmd() {
        return Ability
                .builder()
                .name("test")
                .info("test commands")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action( ctx -> {
                    String str = ctx.toString();
                    System.out.println("receive: " + str);
                    silent.send("command recv!", ctx.chatId());
                })
                .build();
    }
}
