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

<<<<<<< HEAD:android/src/com/uam/kck/SpeechGDX/android/AndroidLauncher.java
    SpeechGDX speechGDX;
=======
    SceneTest sceneTest;
>>>>>>> 01b5fa2be574fe24616c264916453ac35f218932:android/src/com/uam/kck/antminer/android/AndroidLauncher.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionResolver = new ActionResolverAndroid(this);

<<<<<<< HEAD:android/src/com/uam/kck/SpeechGDX/android/AndroidLauncher.java
        speechGDX = new SpeechGDX(actionResolver);
        initialize(speechGDX);
=======
        sceneTest = new SceneTest(actionResolver);
        initialize(sceneTest);
>>>>>>> 01b5fa2be574fe24616c264916453ac35f218932:android/src/com/uam/kck/antminer/android/AndroidLauncher.java
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SPEECH && resultCode == RESULT_OK) {
            // Get the spoken sentence..
            ArrayList<String> thingsYouSaid =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            // ..and pass it to the textField:
<<<<<<< HEAD:android/src/com/uam/kck/SpeechGDX/android/AndroidLauncher.java
            speechGDX.setTextFieldText(thingsYouSaid.get(0));
=======
            sceneTest.setTextFieldText(thingsYouSaid.get(0));
>>>>>>> 01b5fa2be574fe24616c264916453ac35f218932:android/src/com/uam/kck/antminer/android/AndroidLauncher.java
            Gdx.app.log("you said: ", thingsYouSaid.get(0));
        }
    }

}
