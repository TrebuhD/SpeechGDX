package com.uam.kck.SpeechGDX.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import com.uam.kck.SpeechGDX.ActionResolver;
import com.uam.kck.SpeechGDX.SpeechGDX;
import com.uam.kck.SpeechGDX.android.Bot.ConversationBot;

/**
 * This class contains native android code. The methods may be called by libGDX.
 * Created by hubert on 19.10.14.
 */
public class ActionResolverAndroid extends Activity implements ActionResolver {

    Handler uiThread;

    Context appContext;
    SpeechGDX gdx;
    SpeechRecognizer speechRecognizer;

    private ConversationBot bot;

    public ActionResolverAndroid(Context appContext) {
        uiThread = new Handler();
        this.appContext = appContext;
        try {
            bot = new ConversationBot();
        } catch (Exception e) {
            showToast("Error while initializing bot: " + e, 5000);
        }
    }

    @Override
    public void showToast(final CharSequence toastMessage, final int toastDuration) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, toastMessage, toastDuration).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void recognizeSpeech() {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                speechRecognizer = SpeechRecognizer.createSpeechRecognizer(appContext);
                speechRecognizer.setRecognitionListener(new MyListener(gdx, appContext, bot));

                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
                i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
                gdx.actionPulseMicButton();
                speechRecognizer.startListening(i);
            }
        });
    }

    public void setGdx(SpeechGDX speechGDX) {
        this.gdx = speechGDX;
    }

}
