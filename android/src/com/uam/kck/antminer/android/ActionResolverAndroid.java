package com.uam.kck.antminer.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.uam.kck.antminer.ActionResolver;


/**
 * Created by hubert on 19.10.14.
 */
public class ActionResolverAndroid implements ActionResolver {
    public static final int REQUEST_OK = 1;

    Handler uiThread;
    Context appContext;

    public ActionResolverAndroid(Context appContext) {
        uiThread = new Handler();
        this.appContext = appContext;
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
    public void showSpeechPopup() {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                try {
                    ((Activity)appContext).startActivityForResult(i, REQUEST_OK);
                } catch (Exception e) {
                    showToast(e.toString(), 5000);
                    Gdx.app.log(ActionResolverAndroid.class.getName(), "error initializing speech engine" + e);
                }
            }
        });
    }

    //protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //    super.onActivityResult(requestCode, resultCode, data);
    //    if (requestCode == REQUEST_OK && resultCode == RESULT_OK) {
    //        ArrayList<String> thingsYouSaid =
    //                data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
    //
    //        //TODO: do something with the text
    //        showToast(thingsYouSaid.get(0), 5000);
    //    }
    //}
}
