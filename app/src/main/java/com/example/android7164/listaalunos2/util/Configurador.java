package com.example.android7164.listaalunos2.util;

import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by android7164 on 10/11/17.
 */

public class Configurador implements GoogleApiClient.ConnectionCallbacks {

    private AtualizadorDeLocalizacao atualizador;

    public Configurador(AtualizadorDeLocalizacao atualizador){
        this.atualizador = atualizador;

    }

    @Override
    public void onConnected(Bundle bundle){

        LocationRequest request = LocationRequest.create();

        request.setInterval(2000);

        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        request.setSmallestDisplacement(50);

        AtualizadorDeLocalizacao.inicia(request);
    }
}
