package br.com.contatosapi.contatosapi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ContatoAdapter contatoAdapter;
    ListView list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating button --- tela de cadastro
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Adicione um novo Contato", Snackbar.LENGTH_LONG)
//                       .setAction("Action", null).show();

                startActivity(new Intent(MainActivity.this, InserirActivity.class));
            }
        });

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        list_view = (ListView) findViewById(R.id.list_contatos);
        //cria o adapter
        contatoAdapter = new ContatoAdapter(this, new ArrayList<Contato>());

        //definir adapter na lista
        list_view.setAdapter(contatoAdapter);


        //preencher lista

        //Executa a tarefa assincrona
        new AsyncTask<Void,Void,Void>(){
            //Inicia a lista que vai receber os itens
            ArrayList<Contato> lstContatos = new ArrayList<Contato>();

            @Override
            //Tudo que estiver dentro do metodo vai executar em segundo plano
            protected Void doInBackground(Void... voids) {

                //------Usando ip da maquina -- quando for usar outra maquina tem que ficar trocando de maquina
                //String retornoJson = Http.get("http://10.107.134.17/inf3m20172/selecionar.php"); ==== OUTRA SALA
                String retornoJson = Http.get("http://10.107.144.29/API/selecionar.php");

                //Usando ip local do celular -- Não funciona quando for usar em servidores de hospedagem
                //String retornoJson = Http.get("http://10.0.0.2/API/selecionar.php");
                Log.d("CHEGOU", retornoJson);

                //Pega objetos do array json
                try {
                    JSONArray jsonArray = new JSONArray(retornoJson);

                    for (int i = 0; i < jsonArray.length(); i++ ){
                        JSONObject item = jsonArray.getJSONObject(i);
                        Contato c = Contato.create(
                         item.getString("nome"), item.getString("telefone"), item.getString("imagem")
                        );
                        lstContatos.add(c);
                    }

                } catch (JSONException e) {
                    Log.e("ERRO", e.getMessage());
                }
                Log.d("LISTA", lstContatos.size()+"");
                return null;
            }

            @Override
            //Executa ações para a tela após executar ações em segundo plano
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                contatoAdapter.addAll(lstContatos);
            }

        }.execute();//Executa a tarefa



        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        /*preencher uns dados fake
        contatoAdapter.add(Contato.create("Winderson Nunes", "456978123"));
        contatoAdapter.add(Contato.create("Julio Cocielo", "98754646"));
        contatoAdapter.add(Contato.create("SAM", "465419184"));*/

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        //logs (é um print )
        //debug
        //log.d("TAG",retornojason);
        //erro log.e ...
    }


}
