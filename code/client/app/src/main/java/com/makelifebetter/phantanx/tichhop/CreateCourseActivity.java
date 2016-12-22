package com.makelifebetter.phantanx.tichhop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CreateCourseActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    EditText inputFullname, inputShortname;
    Button actionCreateCourse;
    ProgressDialog pd;
    TextView nofify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_course);
        getSupportActionBar().setTitle("Create Course");
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait");
        pd.setCancelable(true);
        nofify= (TextView) findViewById(R.id.notify);
        sharedpreferences = getSharedPreferences(Const.MyPREFERENCES, Context.MODE_PRIVATE);
        inputFullname = (EditText) findViewById(R.id.input_fullname);
        inputShortname = (EditText) findViewById(R.id.input_shortname);
        actionCreateCourse = (Button) findViewById(R.id.action_create_course);
        final RequestQueue queue = Volley.newRequestQueue(CreateCourseActivity.this);
        actionCreateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                nofify.setText("");
                String moodleToken = sharedpreferences.getString(Const.MD_KEY, "");
                String iliassid = sharedpreferences.getString(Const.ILA_KEY, "");
                String fullname = inputFullname.getText() + "";
                String shortname = inputShortname.getText() + "";
                String url = Const.BASE_URL + ":8080/RESTfulExample/rest/tichhop/create-course?moodletoken=" + moodleToken + "&iliassid=" + iliassid + "&fullname=" + fullname + "&shortname=" + shortname;
                System.out.println(url);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        System.out.println("response "+response);
                        String[] rs = response.split("\\$");
                        nofify.setText(rs[0]+"\n"+rs[1]);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                });
                queue.add(stringRequest);
            }
        });


    }
}
