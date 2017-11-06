package com.example.marc.final_trab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by marc on 03/08/17.
 */

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final Context context = this;

        final EditText user = (EditText) findViewById(R.id.name);
        final EditText pass = (EditText) findViewById(R.id.password);
        final EditText email = (EditText) findViewById(R.id.email);

        Button registrar = (Button) findViewById(R.id.registrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addUser(user.getText().toString(), pass.getText().toString(),email.getText().toString());

                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });




    }

    private void addUser(String user,String password,String email) throws IOException, JSONException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String query = "http://10.15.241.198:8080/addUser";
        //String query = "http://127.0.0.1:8080/addUser";

        String json = "{\"username\":\""+user+"\",\"password\":\""+password+"\",\"acessType\":1,\"email\":\""+email+"\"}";

        URL url = new URL(query);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes("UTF-8"));
        os.close();

        // read the response
        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
        /*JSONObject jsonObject = new JSONObject(result);*/

        // adicona a dependencia org.apache.commons.io



        in.close();
        conn.disconnect();

        System.out.println(result);

        //return jsonObject;
    }


}
