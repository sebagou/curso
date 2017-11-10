package com.example.android7164.listaalunos2.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.android7164.listaalunos2.R;
import com.example.android7164.listaalunos2.fragment.MapFragment;

/**
 * Created by android7164 on 10/11/17.
 */

public class MostraAlunosActivity extends AppCompatActivity{

    protected void onCreate(Bundle bundle){

        super.onCreate(bundle);
        setContentView(R.layout.activity_mostra_alunos);

        MapFragment mapaFragment = new MapFragment();

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.mostra_alunos_mapa, mapaFragment);
        tx.commit();
    }
}
