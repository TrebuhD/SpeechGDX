package com.uam.kck.SpeechGDX.android;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.util.Log;

import com.uam.kck.SpeechGDX.SpeechGDX;

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
        Log.d(TAG, "onRmsChanged");
    }

    @Override
    public void onBufferReceived(byte[] bytes) {
        Log.d(TAG, "onBufferRecieved");
    }

    @Override
    public void onEndOfSpeech() {
        Log.d(TAG, "onEndOfSpeech");
    }

    @Override
    public void onError(int i) {
        Log.d(TAG, "onError");
        gdx.setTextFieldText("Error");
    }

    @Override
    public void onResults(Bundle bundle) {
        Log.d(TAG, "onResults");
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
