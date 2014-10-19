package com.uam.kck.antminer;

/**
 * Created by hubert on 19.10.14.
 */
public interface ActionResolver {
    public void showToast(CharSequence toastMessage, int toastDuration);
    public void showAlertBox(String alertBoxTitle, String alertBoxMessage,
                             String alertBoxButtonText);
    public void openUri(String uri);
    public void showMyList();

    public void showSpeechPopup();
}
