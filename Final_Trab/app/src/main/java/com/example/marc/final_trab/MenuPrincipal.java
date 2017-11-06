package com.example.marc.final_trab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuPrincipal extends AppCompatActivity {

    int accessType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        accessType = Integer.parseInt(getIntent().getStringExtra("tipo"));

        ImageView cart = (ImageView) findViewById(R.id.imageView5);

        cart.setImageResource(R.drawable.cart);

        addListenerOnButton();
    }

    private void addListenerOnButton() {

        final Context context = this;
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodsMenu.class);
                intent.putExtra("tipo", String.valueOf(accessType));
                System.out.println(accessType);
                startActivity(intent);
            }

        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (accessType == 0) {
                    Intent intent = new Intent(context, DrinksActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, DrinksActivity.class);
                    startActivity(intent);
                }

            }

        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TotalsActivity.class);
                startActivity(intent);
            }

        });

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}
