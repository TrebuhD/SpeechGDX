package com.uam.kck.SpeechGDX.android;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.uam.kck.SpeechGDX.SpeechGDX;

import java.util.ArrayList;

public class AndroidLauncher extends AndroidApplication {
    public static final int REQUEST_SPEECH = 1;

    ActionResolverAndroid actionResolver;

    public static SpeechGDX speechGDX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionResolver = new ActionResolverAndroid(this);

        speechGDX = new SpeechGDX(actionResolver);
        initialize(speechGDX);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SPEECH && resultCode == RESULT_OK) {
            // Get the spoken sentence..
            ArrayList<String> thingsYouSaid =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            // ..and pass it to the textField:
            //speechGDX.setTextFieldText(thingsYouSaid.get(0));
            Gdx.app.log("you said: ", thingsYouSaid.get(0));
        }
    }

}
