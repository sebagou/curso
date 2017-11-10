package com.example.android7164.listaalunos2.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;


import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by android7164 on 10/11/17.
 */

public class Localizador {

    private Geocoder geo;

    public Localizador (Context ctx){

        geo = new Geocoder(ctx);
    }

    public LatLng getCoordenada(String endereco){

        List<Address> enderecos = null;
        try {
            enderecos = geo.getFromLocationName(endereco,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LatLng coordenada = new LatLng(-23,-46);

        Address end = enderecos.get(0);

        if(end != null) {
            coordenada = new LatLng(end.getLatitude(), end.getLongitude());
        }
        return coordenada;


    }
}
