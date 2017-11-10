package com.example.android7164.listaalunos2.fragment;

import android.util.Log;

import com.example.android7164.listaalunos2.dao.AlunoDAO;
import com.example.android7164.listaalunos2.model.Aluno;
import com.example.android7164.listaalunos2.util.Localizador;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by android7164 on 10/11/17.
 */

public class MapFragment extends SupportMapFragment {

    @Override
    public void onResume(){
        super.onResume();

        Localizador localizador = new Localizador(getActivity());

        final LatLng local = localizador.getCoordenada("Rua Vergueiro 3185 São Paulo");



        Log.i("MAPA", "Coordenadas da Caelum: " + local);

        AlunoDAO dao = new AlunoDAO(getContext());
        final List<Aluno> alunos = dao.getLista();
        dao.close();

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                centralizaNo(local,googleMap);

                for(Aluno aluno : alunos){

                    MarkerOptions marcador = new MarkerOptions()
                            .title(aluno.getNome())
                            .position(new Localizador(getContext()).getCoordenada(aluno.getEndereco()));

                    googleMap.addMarker(marcador);
                }
            }
        });





    }

    private void centralizaNo(LatLng local, GoogleMap mapa){

        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 11));

    }

}
