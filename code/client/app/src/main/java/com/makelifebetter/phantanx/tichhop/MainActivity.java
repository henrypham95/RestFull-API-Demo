package com.makelifebetter.phantanx.tichhop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText username, password;
    EditText inputServerUrl;
    SharedPreferences sharedpreferences;
    ProgressDialog pd;
    TextView notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait");
        pd.setCancelable(true);
        inputServerUrl = (EditText) findViewById(R.id.input_server_url);
        notify = (TextView) findViewById(R.id.notify);
        button = (Button) findViewById(R.id.buttondangnhap);
        username = (EditText) findViewById(R.id.item_username);

        password = (EditText) findViewById(R.id.item_password);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                username.getText();
                password.getText();
                Const.BASE_URL="http://"+inputServerUrl.getText()+"";
                //

                //
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = Const.BASE_URL + ":8080/RESTfulExample/rest/tichhop/login?username=" + username.getText() + "&password=" + password.getText();
                System.out.println(url);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                username.setText("");
                                password.setText("");
                                notify.setText("");
                                pd.dismiss();
                                System.out.println("response " + response);
                                // Display the first 500 characters of the response string.
                                if (response.length() > 1) {
                                    sharedpreferences = getSharedPreferences(Const.MyPREFERENCES, Context.MODE_PRIVATE);
                                    String [] arrsecretkey = response.split("\\$");

                                    System.out.println(Arrays.toString(arrsecretkey));
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString(Const.MD_KEY, arrsecretkey[0]);
                                    editor.putString(Const.LD_KEY, arrsecretkey[1]);
                                    editor.putString(Const.ILA_KEY, arrsecretkey[2]);

                                    editor.commit();
                                    doOpenChildActivity();

                                } else {
                                    notify.setText("Tai khoan khong dung!");

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        System.out.println(error.toString());
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });
    }

    public void doOpenChildActivity() {
        Intent myIntent = new Intent(this, MainActivityChild.class);
        startActivity(myIntent);
    }
}
