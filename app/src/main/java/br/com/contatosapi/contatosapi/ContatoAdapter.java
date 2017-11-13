package br.com.contatosapi.contatosapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 16254861 on 13/11/2017.
 */

public class ContatoAdapter extends ArrayAdapter<Contato> {

    public ContatoAdapter(Context context, ArrayList<Contato> contatos){
        super(context, 0, contatos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, null);
        }

        Contato item = getItem(position);

        TextView txt_nome = v.findViewById(R.id.txt_contato);
        TextView txt_telefone = v.findViewById(R.id.txt_telefone);
        ImageView img_foto = v.findViewById(R.id.img_contato);

        Picasso.with(getContext())
                .load("http://10.0.2.2/inf3m20172/"+item.getFoto())
                .into(img_foto);

        txt_nome.setText(item.getNome());
        txt_telefone.setText(item.getTelefone());

     return v;
    }
}
