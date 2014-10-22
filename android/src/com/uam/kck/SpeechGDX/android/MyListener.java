package com.uam.kck.SpeechGDX.android;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.uam.kck.SpeechGDX.SpeechGDX;
import com.uam.kck.SpeechGDX.android.Bot.ConversationBot;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Custom RecognitionListener.
 * Created by hubert on 21.10.14.
 */
public class MyListener implements RecognitionListener{
    public static final String TAG = "Listener";
    SpeechGDX gdx;
    Context context;
    ConversationBot bot;
    TextToSpeech tts;

    public MyListener(final SpeechGDX gdx, Context context, ConversationBot bot) { // passing gdx part 3
        this.gdx = gdx;
        this.context = context;
        this.bot = bot;
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.FRENCH);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Gdx.app.log("Error", " this language is not supported.");
                    }
                }
                else
                    Gdx.app.log("Error", " error initializing text-to-speech engine.");
            }
        });
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        Log.d(TAG, "onReadyForSpeech");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.d(TAG, "onBeginningOfSpeech");
    }

    @Override
    public void onRmsChanged(float volumeDB) {
//        float volumeNo = (volumeDB+120)/1.8f; // Normalize to 0-100 scale.
    }

    @Override
    public void onBufferReceived(byte[] bytes) {
        Log.d(TAG, "onBufferReceived");
    }

    @Override
    public void onEndOfSpeech() {
        Log.d(TAG, "onEndOfSpeech");
        gdx.stopMicButtonPulse();
        Vibrator v = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50);
    }

    @Override
    public void onError(int i) {
        Log.d(TAG, "onError");
        switch (i) {
            case SpeechRecognizer.ERROR_AUDIO:
                gdx.showToast("Audio error");
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                gdx.showToast("Client error");
                break;
            case SpeechRecognizer.ERROR_SERVER:
                gdx.showToast("Server error");
            case SpeechRecognizer.ERROR_NETWORK:
                gdx.showToast("There was a problem with your connection.");
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                gdx.showToast("Connection timed out");
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                gdx.showToast("No matches found");
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                gdx.showToast("I'm still listening");
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                gdx.showToast("Insufficient permissions");
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                gdx.showToast("Try again");
            default:
                gdx.showToast("Error #" + i);
        }
    }

    @Override
    public void onResults(Bundle results) {
        Log.d(TAG, "onResults" + results);
        ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        for (String element : data) {
            Log.d(TAG, "result " + element);
        }

        Gdx.app.log("results", String.valueOf(data.size()));
        gdx.setInputTextFieldText(data.get(0));

        try {
            handleResult(data.get(0));
        } catch (Exception e) {
            gdx.showToast("Exception: " + e);
        }
    }

    private void handleResult(String sentence) throws Exception {
        String response = bot.ask(sentence);
        gdx.setBotResponseTextFieldText("\n " + response);
        speakOutLoud(response);
    }

    private void speakOutLoud(String sentence) {
        if(sentence == null || "".equals(sentence)) { return; }
        else tts.speak(sentence, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onPartialResults(Bundle bundle) {
        Log.d(TAG, "onPartialResults");
    }

    @Override
    public void onEvent(int i, Bundle bundle) {
        Log.d(TAG, "onEvent");
    }
}
