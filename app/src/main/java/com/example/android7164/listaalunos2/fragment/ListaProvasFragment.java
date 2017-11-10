package com.example.android7164.listaalunos2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android7164.listaalunos2.R;
import com.example.android7164.listaalunos2.model.Prova;

import java.util.Arrays;
import java.util.List;

/**
 * Created by android7164 on 10/11/17.
 */

public class ListaProvasFragment extends Fragment {
    private ListView listViewProvas;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        View layoutProvas = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        this.listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas_listview);

        // Gerando as provas que irão popular nossa lista
        Prova prova1 = new Prova("20/06/2015", "Matemática");
        prova1.setTopicos(Arrays.asList("Álgebra linear", "Cálculo", "Estatística"));

        Prova prova2 = new Prova("25/07/2015", "Português");
        prova2.setTopicos(Arrays.asList("Complemento nominal", "Orações subordinadas", "Análise sintática"));

        List<Prova> provas = Arrays.asList(prova1, prova2);

        // Populando a lista com um ArrayAdapter que recebe a lista de provas
        this.listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, provas));

        this.listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {

                Prova selecionada = (Prova) adapter.getItemAtPosition(posicao);

                Toast.makeText(getActivity(),"Prova: "+ selecionada, Toast.LENGTH_SHORT).show();

            }
        });

        return layoutProvas;

    }





}

