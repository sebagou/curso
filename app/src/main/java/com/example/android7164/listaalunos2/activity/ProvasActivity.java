package com.example.android7164.listaalunos2.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.android7164.listaalunos2.R;
import com.example.android7164.listaalunos2.fragment.DetalheProvaFragment;
import com.example.android7164.listaalunos2.fragment.ListaProvasFragment;
import com.example.android7164.listaalunos2.model.Prova;

/**
 * Created by android7164 on 10/11/17.
 */

public class ProvasActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(isLand()) {
            transaction
                    .replace(R.id.provas_lista, new ListaProvasFragment())
                    .replace(R.id.provas_detalhes, new DetalheProvaFragment());
        }
         else{
            transaction.replace(R.id.provas_view, new ListaProvasFragment());
        }

        transaction.commit();

    }

    private boolean isLand(){
        return getResources().getBoolean(R.bool.isLand);
    }

    public void selecionaProva(Prova prova){

        FragmentManager manager = getSupportFragmentManager();

        if(isLand()) {
            DetalheProvaFragment detalhesProva = (DetalheProvaFragment) manager.findFragmentById(R.id.provas_detalhes);
            detalhesProva.populaCamposComDados(prova);
        }
        else{
            Bundle argument = new Bundle();
            argument.putSerializable("prova", prova);

            DetalheProvaFragment detalhesProva = new DetalheProvaFragment();
            detalhesProva.setArguments(argument);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.provas_view, detalhesProva);

            transaction.addToBackStack(null);

            transaction.commit();
        }
    }

}
