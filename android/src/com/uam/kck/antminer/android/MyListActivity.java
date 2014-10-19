package com.uam.kck.antminer.android;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.uam.kck.antminer.ToastTest;

/**
 * Created by hubert on 19.10.14.
 */
public class MyListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] items = new String[] {
                "First",
                "Second",
                "Third"};

        this.setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items));
        }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        long miliseconds = 100;
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(miliseconds);

        super.onListItemClick(l, v, position, id);

        Intent toastTest = new Intent(this, ToastTestAndroidActivity.class);
        ToastTest.myListVar = position;

        startActivity(toastTest);
    }
}
