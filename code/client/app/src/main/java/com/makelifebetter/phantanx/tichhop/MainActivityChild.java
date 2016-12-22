package com.makelifebetter.phantanx.tichhop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mobibay@phantan on 12/19/2016.
 */

public class MainActivityChild extends AppCompatActivity {
    TextView textView;
    Button actionShowKey, actionCreateCourse;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tichhop);
        getSupportActionBar().setTitle("Admin Dashboard");
        sharedpreferences = getSharedPreferences(Const.MyPREFERENCES, Context.MODE_PRIVATE);
        actionShowKey = (Button) findViewById(R.id.action_show_key);
        actionShowKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityChild.this, ShowKeyActivity.class));
            }
        });
        actionCreateCourse = (Button) findViewById(R.id.action_create_course);
        actionCreateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityChild.this, CreateCourseActivity.class));
            }
        });
        sharedpreferences.getString("Token", "");
    }

}
