package com.example.marc.final_trab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

/**
 * Created by user on 25/07/2017.
 */

public class TotalsActivity extends AppCompatActivity {

    MyApp mApp;
    EditText et_summary;
    TextView tv_total;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totals);
        mApp=((MyApp)getApplicationContext());
        et_summary = (EditText) findViewById(R.id.et_summary);
        tv_total = (TextView) findViewById(R.id.tv_total);
                et_summary.setText(mApp.getGlobalVarValue());
        String str = mApp.getGlobalVarValue();
        StringTokenizer st = new StringTokenizer(str,"$");
        String test="";
        float total=0;
        int count=0;

        while (st.hasMoreElements()) {

            String value[] = st.nextElement().toString().split("]");

            if(count > 0) {

                total+= Float.parseFloat(value[0].toString());
                //Log.i("TAG0", value[0].toString());
                //Log.i("TAG1", value[1].toString());
            }
            count++;
        }


        tv_total.setText("Total :" + total+"");

        Button buttonPay = (Button) findViewById(R.id.button4);
        buttonPay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("message/rfc222");
                intent.putExtra(Intent.EXTRA_EMAIL,
                        new String[] {"matheusprandini.96@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de compra");
                intent.putExtra(Intent.EXTRA_TEXT, "Pedido do Boleto para pagamento da Compra realizada.");
                intent.setData(Uri.parse("matheusprandini.96@gmail.com"));
                //startActivity(intent);
                startActivity(Intent.createChooser(intent, "Send mail..."));
                //startActivityForResult(intent);
            }

        });


    }
    public boolean isFloat( String input )
    {
        try
        {
            Float.parseFloat( input );
            return true;
        }
        catch( Exception e)
        {
            return false;
        }
    }

}
