package com.example.android7164.listaalunos2.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created by android7164 on 10/11/17.
 */

public class AtualizadorDeLocalizacao implements LocationListener {

    private GoogleApiClient client;

    public AtualizadorDeLocalizacao(Context context){

        Configurador config = new Configurador(this);

        this.client = new GoogleApiClient().Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(config)
                .build();

        this.client.connect();


        }
    }

    @Override
    public void onLocationChanged(Location location){

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng local = new LatLng(latitude, longitude);
    }

    public void incia (LocationRequest request) {
        lO

    }

}
