package com.example.marc.final_trab;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 27/07/2017.
 */

public class AddBurger extends AppCompatActivity {

    EditText id,name,price;
    Button btnAdd,btnDelete,btnEdit,btnViewAll;
    SQLiteDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_burger);

        id=(EditText)findViewById(R.id.burger_id);
        name=(EditText)findViewById(R.id.burger_name);
        price=(EditText)findViewById(R.id.burger_price);
        db=openOrCreateDatabase("Burgers_DB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS table_burgers(burger_id int,burger_name VARCHAR,price int);");
        addListenerOnButton();

    }

    private void addListenerOnButton() {
        final Context context = this;
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnViewAll = (Button) findViewById(R.id.btnViewAll);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO table_burgers VALUES('"+id.getText()+"','"+name.getText()+
                        "','"+price.getText()+"');");
                showMessage("Success", "Burger added");
            }

        });
        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.execSQL("UPDATE table_burgers SET burger_name='"+name.getText()+"',price='"+price.getText()+
                        "' WHERE burger_id='"+id.getText()+"'");
                showMessage("Success", "Burger Modified");
            }

        });
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.execSQL("DELETE FROM table_burgers WHERE burger_id='"+id.getText()+"'");
                showMessage("Success", "Burger Deleted");
            }

        });
        btnViewAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM table_burgers", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No Burger found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Id: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("Price: "+c.getString(2)+"\n\n");
                }
                showMessage("Burgers Details", buffer.toString());
            }

        });

    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}