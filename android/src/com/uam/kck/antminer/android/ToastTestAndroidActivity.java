package com.uam.kck.antminer.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.uam.kck.antminer.ToastTest;

/**
 * Created by hubert on 19.10.14.
 */
public class ToastTestAndroidActivity extends AndroidApplication {
    ActionResolverAndroid actionResolver;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionResolver = new ActionResolverAndroid(this);

        initialize(new ToastTest(actionResolver));
    }

}
