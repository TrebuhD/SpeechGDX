package com.uam.kck.SpeechGDX.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.uam.kck.SpeechGDX.SpeechGDX;


public class AndroidLauncher extends AndroidApplication {

    ActionResolverAndroid actionResolver;

    public static SpeechGDX speechGDX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionResolver = new ActionResolverAndroid(this);

        speechGDX = new SpeechGDX(actionResolver);
        initialize(speechGDX);
        actionResolver.setGdx(speechGDX);
        //TODO: Figure a way to stop clunkily passing this object into MyListener.
    }

}
