package com.makelifebetter.phantanx.tichhop;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowKeyActivity extends AppCompatActivity {
    TextView notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_key);
        notify= (TextView) findViewById(R.id.notify);
        SharedPreferences sharedpreferences = getSharedPreferences(Const.MyPREFERENCES, Context.MODE_PRIVATE);

        getSupportActionBar().setTitle("Secret key");
        String mdk = sharedpreferences.getString(Const.MD_KEY,"");
        String ldk = sharedpreferences.getString(Const.LD_KEY,"");
        String ilak = sharedpreferences.getString(Const.ILA_KEY,"");
        notify.setText("Moodle key\n"+mdk+"\n"+
                "LogicDoc key\n"+ldk+"\n"+
                "Ilias key\n"+ilak+"\n");
    }
}
