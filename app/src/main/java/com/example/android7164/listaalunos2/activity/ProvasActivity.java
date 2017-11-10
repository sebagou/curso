package com.example.android7164.listaalunos2.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.android7164.listaalunos2.R;
import com.example.android7164.listaalunos2.fragment.ListaProvasFragment;

/**
 * Created by android7164 on 10/11/17.
 */

public class ProvasActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.provas_view, new ListaProvasFragment());

        transaction.commit();

    }


}
