package com.example.thiago.app_aluno2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosActivity extends Activity {

    private final String TAG = "CADASTRO_ALUNO";
    private final String  ALUNOS_KEY = "LISTA";

    private EditText edNome;
    private Button botao;
    private ListView lvListagem;

    private List<String> listaAlunos;

    private ArrayAdapter<String> adapter;

    private int adapterLayout = android.R.layout.simple_list_item_1;


    protected void  onSaveInstnceState(Bundle outState){

        outState.putStringArrayList(ALUNOS_KEY, (ArrayList<String>) listaAlunos);

        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState(): " + listaAlunos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

        super.onRestoreInstanceState(savedInstanceState);

        listaAlunos =  savedInstanceState.getStringArrayList(ALUNOS_KEY);

        Log.i(TAG, "onSaveRestoreState(): " + listaAlunos);
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listaalunoslayout);

        edNome = (EditText) findViewById(R.id.edNomeListagem);
        botao = (Button) findViewById(R.id.btAddListagem);
        lvListagem = (ListView) findViewById(R.id.lvListagem);

        listaAlunos = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, adapterLayout, listaAlunos);

        lvListagem.setAdapter(adapter);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaAlunos.add(edNome.getText().toString());
                edNome.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        lvListagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaAlunosActivity.this, "Aluno: " + listaAlunos.get(position), Toast.LENGTH_LONG).show();
            }
        });

        lvListagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(ListaAlunosActivity.this, "Aluno: " + listaAlunos.get(position) + "[click longo]", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}
