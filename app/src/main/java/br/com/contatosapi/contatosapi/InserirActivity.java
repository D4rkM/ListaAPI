package br.com.contatosapi.contatosapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class InserirActivity extends AppCompatActivity {

    Context ctx;

    EditText txtnome, txtfoto, txttelefone;
    FloatingActionButton inserir;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtnome = (EditText) findViewById(R.id.txtNome);
        txttelefone = (EditText) findViewById(R.id.txtTelefone);
        txtfoto = (EditText) findViewById(R.id.txtFoto);
        inserir = (FloatingActionButton) findViewById(R.id.fab);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        ctx = this;



        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inserirContato();

            }
        });
    }
    //Função para pegar valores e inerir no banco
    public void inserirContato(){
        //define a url da pagina que irá receber os valores
        final String url = "http://10.0.2.2/API/inserir.php";

        //Insere os valores no tipo hashMap para enviar ao mapa
        final HashMap<String, String> valores = new HashMap<>();
        valores.put("nome", txtnome.getText().toString());
        valores.put("telefone", txttelefone.getText().toString());
        valores.put("foto", txtfoto.getText().toString());

        new AsyncTask<Void,Void,Void>(){

            Boolean sucesso =false;
            String mensagem = "";
            //ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //Cria a barra de progresso
                //progressDialog = ProgressDialog.show(ctx, "Carregando", "Aguarde", true/*Indeterminado*/, false/*Não pode cancelar*/);
                inserir.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }

            //inicia a conexão em background(segundo plano)
            @Override
            protected Void doInBackground(Void... voids) {
                //Simula lentidao de envio (5s)
                SystemClock.sleep(5000);
                //Executa o envio dos dados
                String resultado = Http.post(url, valores);

                try {
                    JSONObject jsonObject = new JSONObject(resultado);
                    sucesso = jsonObject.getBoolean("sucesso");
                    mensagem = jsonObject.getString("mensagem");
                } catch (JSONException e) {
                    e.printStackTrace();
                    sucesso = false;
                    mensagem = "Erro ao inserir..";
                }

                return null;
            }

            //Vai ser executado após o termino da função de background
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                inserir.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                //fecha a barra de progresso
                //progressDialog.dismiss();
                //Exibe a mensagem de erro ou de sucesso apos a conexao
                Toast.makeText(ctx, mensagem, Toast.LENGTH_SHORT).show();
                //caso teve sucesso volta para a activity principal
                if (sucesso){
                    startActivity(new Intent(ctx, MainActivity.class));
                }
            }
        }.execute();


    }

}
