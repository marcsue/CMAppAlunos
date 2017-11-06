package com.example.marc.final_trab;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by user on 25/07/2017.
 */

public class DrinksClientActivity extends AppCompatActivity {

    MyApp mApp;
    private HashMap<String, Location> locations;
    ListView listView1 ;
    SQLiteDatabase db;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        db=openOrCreateDatabase("Drinks_DB", Context.MODE_PRIVATE, null);

        setContentView(R.layout.drinks_client);
        locations = loadLocationData();
        addListenerOnButton();
        initializeUI();
    }

    private void addListenerOnButton() {
        final Context context = this;
        listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {

                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {
                        Object o = listView1.getItemAtPosition(position);
                        String pen = o.toString();
                        mApp=((MyApp)getApplicationContext());
                        mApp.setGlobalVarValue(pen);
                        Toast.makeText(getApplicationContext(), "You have chosen the" + " " + pen, Toast.LENGTH_LONG).show();

                        //showMessage("test","test");


                    }


                }
        );

    }

    private void initializeUI()
    {
        String[] drinks = getDrinksNames();
        listView1 = (ListView) findViewById(R.id.listView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,drinks);
        listView1.setAdapter(adapter);
    }



    private String[] getDrinksNames()
    {
        String[] drinks = new String[locations.size()];
        drinks = locations.keySet().toArray(drinks);
        return drinks;
    }

    public void onListItemClick(ListView l, View v, int position, long id)
    {

    }

    private HashMap<String, Location> loadLocationData()
    {
        HashMap<String, Location> locations = new HashMap<String, Location>();
        Cursor c=db.rawQuery("SELECT * FROM table_drinks order by drink_id asc", null);
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            locations.put("- "+c.getString(1).toString()+" [$"+c.getString(2).toString()+"]", new Location(Integer.parseInt(c.getString(0)),c.getString(1).toString(),Double.parseDouble(c.getString(2))));
        }

        return locations;
    }

}
