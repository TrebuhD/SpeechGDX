package com.uam.kck.antminer.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.uam.kck.antminer.AntMinerGame;
import com.uam.kck.antminer.SpeechTest;

public class AndroidLauncher extends AndroidApplication {
    ActionResolverAndroid actionResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionResolver = new ActionResolverAndroid(this);

        initialize(new SpeechTest(actionResolver));
    }
}
