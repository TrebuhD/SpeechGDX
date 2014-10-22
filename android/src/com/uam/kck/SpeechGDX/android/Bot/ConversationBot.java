package com.uam.kck.SpeechGDX.android.Bot;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotType;

/**
 * Created by hubert on 22.10.14.
 */
public class ConversationBot {

    ChatterBotFactory factory;
    ChatterBot bot;

    public ConversationBot() throws Exception {
        factory = new ChatterBotFactory();

        bot = factory.create(ChatterBotType.CLEVERBOT);
    }

}
