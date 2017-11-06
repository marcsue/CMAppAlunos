package com.example.marc.final_trab;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    int accessType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            final Context context = this;

            final EditText user = (EditText) findViewById(R.id.name);
            final EditText password = (EditText) findViewById(R.id.pass);

            Button logar = (Button) findViewById(R.id.logar);
            Button registrar = (Button) findViewById(R.id.adicionar);

            logar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                     boolean verifica =   buscaLogin(user.getText().toString(),password.getText().toString());
                        if(verifica){
                            Intent intent = new Intent(context,MenuPrincipal.class);
                            intent.putExtra("tipo",String.valueOf(accessType));
                            startActivity(intent);
                        }
                        else{
                            System.out.println("NAO DEU CARAI");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, RegisterActivity.class);
                    startActivity(intent);

                }
            });

    }

    private boolean buscaLogin(String user,String password) throws IOException, JSONException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String query = "http://10.15.241.198:8080/buscaUser";
        //String query = "http://127.0.0.1:8080/addUser";

        String json = "{\"username\":\""+user+"\",\"password\":\""+password+"\"}";

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
        JSONObject jsonObject = new JSONObject(result);

        // adicona a dependencia org.apache.commons.io

        in.close();
        conn.disconnect();

        if (jsonObject.getString("username") != "null"){
            int i = jsonObject.getInt("acessType");
            accessType = i;
            System.out.println(accessType);
            return true;
        }


        else
            return false;




        //return jsonObject;
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
