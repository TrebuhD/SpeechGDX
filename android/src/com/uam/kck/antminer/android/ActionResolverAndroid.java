package com.uam.kck.antminer.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

import com.uam.kck.antminer.ActionResolver;


/**
 * Created by hubert on 19.10.14.
 */
public class ActionResolverAndroid implements ActionResolver {
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
        // TODO: Create the MyListActivity class
        appContext.startActivity(new Intent(this.appContext, MyListActivity.class));
    }
}
