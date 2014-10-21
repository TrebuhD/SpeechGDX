package com.uam.kck.SpeechGDX.android;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import com.uam.kck.SpeechGDX.ActionResolver;
import com.uam.kck.SpeechGDX.SpeechGDX;

/**
 * This class contains native android code. The methods may be called by libGDX.
 * Created by hubert on 19.10.14.
 */
public class ActionResolverAndroid extends Activity implements ActionResolver {
//    public static final int REQUEST_OK = 1;

    Handler uiThread;
    Context appContext;
    SpeechGDX gdx;

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

//    @Override
//    public void showSpeechPopup() {
//        // Use a separate thread:
//        uiThread.post(new Runnable() {
//            @Override
//            public void run() {
//                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
//                try {
//                    // We open the Speech dialog here using a request code
//                    // and retrieve the spoken text in AndroidLauncher's onActivityResult().
//                    ((Activity)appContext).startActivityForResult(i, REQUEST_OK);
//                } catch (Exception e) {
//                    showToast(e.toString(), 5000);
//                    Gdx.app.log(ActionResolverAndroid.class.getName(),
//                            "error initializing speech engine" + e);
//                }
//            }
//        });
//    }


    @Override
    public void showSpeechPopup() {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(appContext);
        speechRecognizer.setRecognitionListener(new MyListener(gdx)); // Passing gdx part 2
    }

    public void setGdx(SpeechGDX speechGDX) {
        this.gdx = speechGDX;
    }

}
