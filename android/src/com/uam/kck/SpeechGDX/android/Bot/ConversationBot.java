package com.uam.kck.SpeechGDX.android.Bot;

import android.os.AsyncTask;

import com.badlogic.gdx.Gdx;
import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

/**
 * An implementation of the ChatterBot API.
 * Created by hubert on 22.10.14.
 */
public class ConversationBot {

    ChatterBotFactory factory;
    ChatterBot bot;
    static ChatterBotSession botSession;

    public ConversationBot() throws Exception {
        factory = new ChatterBotFactory();

        bot = factory.create(ChatterBotType.JABBERWACKY);
        botSession = bot.createSession();
    }

    public String ask(String what) throws Exception {
        RespondTask respondTask = new RespondTask();
        respondTask.execute(what);
        return respondTask.get();
    }

    private class RespondTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response = new String();
            try {
                response = botSession.think(strings[0]);
            } catch (Exception e) {
                Gdx.app.log("Exception while getting a response from bot", e.getMessage());
            }
            return response;
        }
    }

}
