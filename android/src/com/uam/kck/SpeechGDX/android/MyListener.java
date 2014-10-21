package com.uam.kck.SpeechGDX.android;

import android.os.Bundle;
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

    public MyListener(SpeechGDX gdx) { // passing gdx part 3
        this.gdx = gdx;
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
    }

    @Override
    public void onError(int i) {
        Log.d(TAG, "onError");
        switch (i) {
            case RecognizerIntent.RESULT_AUDIO_ERROR:
                gdx.showToast("Audio error");
                break;
            case RecognizerIntent.RESULT_CLIENT_ERROR:
                gdx.showToast("Client error");
                break;
            case RecognizerIntent.RESULT_NETWORK_ERROR:
                gdx.showToast("There was a problem with your connection.");
                break;
            case RecognizerIntent.RESULT_NO_MATCH:
                gdx.showToast("No matches found");
                break;
            case 8:
                gdx.showToast("I'm still listening");
                break;
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
