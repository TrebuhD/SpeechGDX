package com.uam.kck.antminer.android;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.uam.kck.antminer.SceneTest;

import java.util.ArrayList;

public class AndroidLauncher extends AndroidApplication {
    public static final int REQUEST_SPEECH = 1;

    ActionResolverAndroid actionResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionResolver = new ActionResolverAndroid(this);

        initialize(new SceneTest(actionResolver));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SPEECH && resultCode == RESULT_OK) {
            ArrayList<String> thingsYouSaid =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            //TODO: do something with the text
            Gdx.app.log("you said: ", thingsYouSaid.get(0));
        }
    }

}
