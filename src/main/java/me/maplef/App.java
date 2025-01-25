package me.maplef;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class App 
{
    public static void main( String[] args ) {
        if (System.getenv("BOT_TOKEN") == null) {
            System.err.println("No BOT_TOKEN set!");
            return;
        }

        String BOT_TOKEN = System.getenv("BOT_TOKEN");

        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            NekoAbilityBot nekoAbilityBot = new NekoAbilityBot(new OkHttpTelegramClient(BOT_TOKEN), BOT_TOKEN);
            nekoAbilityBot.onRegister();

            botsApplication.registerBot(BOT_TOKEN, nekoAbilityBot);
            System.out.println("NekoAbilityBot Hello World!");

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        System.out.println("NekoTownBot GoodBye!");
                        nekoAbilityBot.getSilent().send("NekoTownBot GoodBye!", -1002439035442L);
                        botsApplication.unregisterBot(BOT_TOKEN);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            });

            // Ensure this prcess wait forever
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
