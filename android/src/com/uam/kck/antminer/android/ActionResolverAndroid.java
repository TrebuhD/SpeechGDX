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

import java.util.ArrayList;


/**
 * Created by hubert on 19.10.14.
 */
public class ActionResolverAndroid extends Activity implements ActionResolver {
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
    public void showAlertBox(final String alertBoxTitle, final String alertBoxMessage, final String alertBoxButtonText) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(appContext)
                        .setTitle(alertBoxTitle)
                        .setMessage(alertBoxMessage)
                        .setNeutralButton(alertBoxButtonText,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).create().show();
            }
        });
    }

    @Override
    public void openUri(String uri) {
        Uri myUri = Uri.parse(uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, myUri);
        appContext.startActivity(intent);
    }

    @Override
    public void showMyList() {
        appContext.startActivity(new Intent(this.appContext, MyListActivity.class));
    }

    @Override
    public void showSpeechPopup() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            startActivityForResult(i, REQUEST_OK);
        } catch (Exception e) {
            showToast(e.toString(), 5000);
            Gdx.app.log(ActionResolverAndroid.class.getName(), "error initializing speech engine" + e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OK && resultCode == RESULT_OK) {
            ArrayList<String> thingsYouSaid =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            //TODO: do something with the text
        }
    }
}
