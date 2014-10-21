package com.uam.kck.SpeechGDX;

/**
 * Here we list all the native methods that we wish to use.
 * Created by hubert on 19.10.14.
 */
public interface ActionResolver {
    public void showToast(CharSequence toastMessage, int toastDuration);
    public void recognizeSpeech();
}
