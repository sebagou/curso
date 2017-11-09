package com.example.android7164.listaalunos2.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android7164.listaalunos2.R;
import com.example.android7164.listaalunos2.model.Aluno;

import java.util.List;

/**
 * Created by Marcio on 09/11/17.
 * adapter para preecher a listView
 */

public class ListaAlunosAdapter extends BaseAdapter{
    private final List<Aluno> alunos;
    private final Activity activity;
    private Context context;

    public ListaAlunosAdapter(Activity activity, List<Aluno> alunos){
        this.activity = activity;
        this.alunos = alunos;

    }


    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.item, parent, false);


        Aluno aluno = alunos.get(posicao);

        TextView nome = (TextView) view.findViewById(R.id.item_nome);
        nome.setText(aluno.getNome());

//        RatingBar nota = (RatingBar) view.findViewById(R.id.item_nota);
//        nota.setRating((float) aluno.getNota());

        Bitmap bitmap;
        ImageView foto = (ImageView) view.findViewById(R.id.item_foto);

        String caminho = aluno.getFoto();
        if(caminho !=null) {
           bitmap = BitmapFactory.decodeFile(caminho);
        }
        else {
           bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        }
        bitmap = Bitmap.createScaledBitmap(bitmap,100,100,true);
        foto.setImageBitmap(bitmap);
        foto.setTag(caminho);

        if(posicao % 2 == 0){
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par, activity.getTheme()));
        }
        else{
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar, activity.getTheme()));
        }
        return view;
    }
}
