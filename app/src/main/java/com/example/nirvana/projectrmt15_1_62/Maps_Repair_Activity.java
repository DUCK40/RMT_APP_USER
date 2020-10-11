package com.example.nirvana.projectrmt15_1_62;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps_Repair_Activity extends FragmentActivity implements OnMapReadyCallback ,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {


    private GoogleMap mMap;


    static  final  int REQUREST_LOCATION = 1;
    LocationManager locationManager;

    double Lati ;
    double Longti ;

    double getLati;
    double getLongti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_maps__repair_ );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager ()
                .findFragmentById ( R.id.map );
        mapFragment.getMapAsync ( this );

        locationManager  = (LocationManager)getSystemService ( Context.LOCATION_SERVICE );
        getLocation ();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng ( getLati, getLongti );
        mMap.addMarker ( new MarkerOptions ().position ( sydney ).title ( "Marker in Sydney" ) );
        mMap.moveCamera ( CameraUpdateFactory.newLatLng ( sydney ) );
        mMap.animateCamera ( CameraUpdateFactory.zoomTo ( 15.0f ) );
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult ( requestCode, permissions, grantResults );
        switch (requestCode){
            case REQUREST_LOCATION : getLocation ();
                break;
        }
    }


    void getLocation (){
        if(ActivityCompat.checkSelfPermission ( this, Manifest.permission.ACCESS_FINE_LOCATION )!=
                PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission ( this, Manifest.permission.ACCESS_COARSE_LOCATION )
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions ( this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUREST_LOCATION );
        }else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null){
                double latti = location.getLatitude ();
                double longti = location.getLongitude ();
                getLati = latti;
                getLongti = longti;

                System.out.println ("Show Lati"+latti);

                System.out.println ("Show Longti"+longti);
//                ((EditText)findViewById ( R.id.etLocationLat )).setText ( "Latijude"+latti );
//                ((EditText)findViewById ( R.id.etLocationLong )).setText ( "Longtijude"+longti );
            }else {
//                ((EditText)findViewById ( R.id.etLocationLat )).setText ( "No Location");
//                ((EditText)findViewById ( R.id.etLocationLong )).setText ( "No Location" );
            }
        }
    }

}
