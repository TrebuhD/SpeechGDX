package com.uam.kck.SpeechGDX.android;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.uam.kck.SpeechGDX.SpeechGDX;

import java.util.ArrayList;

/**
 * Custom RecognitionListener.
 * Created by hubert on 21.10.14.
 */
public class MyListener implements RecognitionListener{
    public static final String TAG = "Listener";
    SpeechGDX gdx;
    Context context;

    public MyListener(SpeechGDX gdx, Context context) { // passing gdx part 3
        this.gdx = gdx;
        this.context = context;
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
    public void onRmsChanged(float v) {
        // Detects change in volume. This gets called too often and spams logcat.
    }

    @Override
    public void onBufferReceived(byte[] bytes) {
        Log.d(TAG, "onBufferReceived");
    }

    @Override
    public void onEndOfSpeech() {
        Log.d(TAG, "onEndOfSpeech");
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
        gdx.setTextFieldText(data.get(0));
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
