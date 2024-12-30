package me.maplef;

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
            botsApplication.registerBot(BOT_TOKEN, new NekoTownBot());
            System.out.println("NekoTownBot Hello World!");

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        System.out.println("NekoTownBot GoodBye!");
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
