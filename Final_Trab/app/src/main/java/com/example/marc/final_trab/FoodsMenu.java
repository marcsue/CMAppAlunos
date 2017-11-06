package com.example.marc.final_trab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by user on 27/07/2017.
 */

public class FoodsMenu extends AppCompatActivity {

    int accessType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foods_menu);

        System.out.println(accessType);
        accessType = Integer.parseInt(getIntent().getStringExtra("tipo"));

        ImageView imagem = (ImageView) findViewById(R.id.imageView);
        ImageView imagem2 = (ImageView) findViewById(R.id.imageView2);

        imagem.setImageResource(R.drawable.pizza);
        imagem2.setImageResource(R.drawable.burger);

        addListenerOnButton();
    }

    private void addListenerOnButton() {

        final Context context = this;
        Button button1 = (Button) findViewById(R.id.buttonP);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (accessType == 0) {
                    Intent intent = new Intent(context, PizzasActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, PizzasClientActivity.class);
                    startActivity(intent);
                }
            }

        });

        Button button2 = (Button) findViewById(R.id.buttonB);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (accessType == 0) {
                    Intent intent = new Intent(context, BurgersActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, BurgersClientActivity.class);
                    startActivity(intent);
                }
            }

        });

    }

}
