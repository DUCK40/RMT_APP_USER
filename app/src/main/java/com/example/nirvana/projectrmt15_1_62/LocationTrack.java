package com.example.nirvana.projectrmt15_1_62;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationTrack extends Service implements LocationListener {
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    private final Context mContext;
    protected LocationManager locationManager;
    boolean checkGPS = false;
    boolean checkNetwork = false;
    boolean canGetLocation = false;
    Location retrunLoc;
    Location loc;
    Location loc1;
    double latitude;
    double longitude;
    String testt;
    String city1;
    String state1;

    public LocationTrack(Context mContext) {
        this.mContext = mContext;
        System.out.println ( "dasdsadsa" );
        getLocation ();
    }

    private Location getLocation() {
        testt = "KUYY";
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService ( LOCATION_SERVICE );

            // get GPS status
            checkGPS = locationManager
                    .isProviderEnabled ( LocationManager.GPS_PROVIDER );

            // get network provider status
            checkNetwork = locationManager
                    .isProviderEnabled ( LocationManager.NETWORK_PROVIDER );


            //////////////////////////////////////////////////////////////////////


            ////////////////////////////////////////////////////////////////////

            if (!checkGPS && !checkNetwork) {
                System.out.println ( "GPSSS" );
                Toast.makeText ( mContext, "กรุณาเปิด GPS ของเครื่องคุณ", Toast.LENGTH_SHORT ).show ();
            } else {
                this.canGetLocation = true;

                // if GPS Enabled get lat/long using GPS Services
                if (checkGPS) {

                    if (ActivityCompat.checkSelfPermission ( mContext, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission ( mContext, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.


                    }
                    ///////////////////////////////////////////////////////////////////////////

                    ///////////////////////////////////////////////////////////////////////////
                    locationManager.requestLocationUpdates (
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this );
                    if (locationManager != null) {
                        System.out.println ( "BRYRYRY1 GPS_PROVIDER" );
                        loc = locationManager
                                .getLastKnownLocation ( LocationManager.GPS_PROVIDER );
                        System.out.println ( "BRYRYRY2" );
                        if (loc != null) {

                            latitude = loc.getLatitude ();
                            longitude = loc.getLongitude ();

                            System.out.println ( "BRYRYRY3"+latitude );
                            //////////////////////////////////////////////
                            try {
                                Geocoder geocoder = new Geocoder ( mContext, Locale.getDefault () );
                                List<Address> addresses = geocoder.getFromLocation ( latitude, longitude, 1 );
                                if (addresses.size () > 0) {
                                    Address address = addresses.get ( 0 );
                                    String okcity = addresses.get ( 0 ).getLocality ();
                                    String okcity1 = addresses.get ( 0 ).getCountryName ();
                                    String admin = address.getAdminArea ();

                                    //getSubAdmin อำเภอ
                                    String subLocality = address.getSubAdminArea ();
                                    String locality = address.getLocality ();

                                    city1 = subLocality;
                                    state1 = admin;
                                    System.out.println ( "SHow admin1" + admin );
                                    System.out.println ( "SHow admin2" + locality );
                                    System.out.println ( "SHow admin3" + subLocality );
                                    System.out.println ( "SHow admin4" + okcity );
                                    System.out.println ( "SHow admin5" + okcity1 );
                                    System.out.println ( "SHow admin6" + addresses.size () );

                                }
                            } catch (IOException e) {
                                System.out.println ( "SHSISIPPPP" + e.getMessage () );
                                e.printStackTrace ();
                            }


                            //  getSimplifiedAddress(13.595157,100.811411);
                            ////////////////////////////////////////////////////////


                        }

                    }


                }


//                if (checkNetwork) {
//
//
//                    if (ActivityCompat.checkSelfPermission ( mContext, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission ( mContext, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                    }
//                    locationManager.requestLocationUpdates (
//                            LocationManager.NETWORK_PROVIDER,
//                            MIN_TIME_BW_UPDATES,
//                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this );
//
//                    if (locationManager != null) {
//                        System.out.println ( "BRYRYRY1 NETWORK_PROVIDER111" );
//                        loc = locationManager
//                                .getLastKnownLocation ( LocationManager.NETWORK_PROVIDER );
//
//                        loc1 = locationManager
//                                .getLastKnownLocation ( LocationManager.GPS_PROVIDER );
//                        System.out.println ( "popoop11" + loc1.getLatitude () );
//
//                    }
//
//                    if (loc1 != null) {
//                        latitude = loc1.getLatitude ();
//                        longitude = loc1.getLongitude ();
//                        System.out.println ( "popoop1checkNetwork" + loc.getLatitude () );
//                        ////////////////////////////////////////////////
//                        try {
//                            Geocoder geocoder = new Geocoder ( mContext, Locale.getDefault () );
//                            List<Address> addresses = geocoder.getFromLocation ( latitude, longitude, 1 );
//                            if (addresses.size () > 0) {
//                                Address address = addresses.get ( 0 );
//                                String okcity = addresses.get ( 0 ).getLocality ();
//                                String okcity1 = addresses.get ( 0 ).getCountryName ();
//                                String admin = address.getAdminArea ();
//
//                                //getSubAdmin อำเภอ
//                                String subLocality = address.getSubAdminArea ();
//                                String locality = address.getLocality ();
//
//                                city1 = subLocality;
//                                state1 = admin;
//                                System.out.println ( "SHow admin11" + admin );
//                                System.out.println ( "SHow admin12" + locality );
//                                System.out.println ( "SHow admin13" + subLocality );
//                                System.out.println ( "SHow admin14" + okcity );
//                                System.out.println ( "SHow admin15" + okcity1 );
//                                System.out.println ( "SHow admin16" + addresses.size () );
//
//                            }
//                        } catch (IOException e) {
//                            System.out.println ( "SHSISIPPPP" + e.getMessage () );
//                            e.printStackTrace ();
//                        }
//                        /////////////////////////////////////////////////
//                    }
//
//
//                }

            }

/////////////////////////////////////////////////////////////////////////////////////////////////
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println ( "SHOOOOOOWOWOWOWOW" + e.toString () );
        }



        return loc;
    }


    ////////////////////////////////////////////////////////////////////

    public String getState() {

        return state1;
    }

    public String getCity() {


        return city1;
    }

    public double getLongitude() {
        if (loc != null) {
            longitude = loc.getLongitude ();
        }
        return longitude;
    }

    public String getTestt() {

        return testt;
    }

    public double getLatitude() {
        if (loc != null) {
            latitude = loc.getLatitude ();
        }
        return latitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder ( mContext );


        alertDialog.setTitle ( "GPS is not Enabled!" );

        alertDialog.setMessage ( "Do you want to turn on GPS?" );


        alertDialog.setPositiveButton ( "Yes", new DialogInterface.OnClickListener () {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent ( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
                mContext.startActivity ( intent );
            }
        } );


        alertDialog.setNegativeButton ( "No", new DialogInterface.OnClickListener () {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel ();
            }
        } );


        alertDialog.show ();
    }


    public void stopListener() {
        if (locationManager != null) {

            if (ActivityCompat.checkSelfPermission ( mContext, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission ( mContext, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates ( LocationTrack.this );
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public String getSimplifiedAddress(double latitude11, double longitude11) {
        String location = "";
//        try {
//            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
//            List<Address> addresses = geocoder.getFromLocation(latitude11, longitude11, 1);
//            if (addresses.size() > 0) {
//                Address address = addresses.get(0);
//                String admin = address.getAdminArea();
//                String subLocality = address.getSubLocality();
//                String locality = address.getLocality();
//                System.out.println ("SHow admin1"+address);
//                System.out.println ("SHow admin1"+locality);
//                if (admin.length() > 10) {
//                    admin = admin.substring(0, 10) + "..";
//                    System.out.println ("SHow admin1"+admin);
//                }
//                if (locality != null && subLocality != null) {
//                    location = subLocality + "," + locality;
//                    System.out.println ("SHow admin2"+location);
//                } else if (subLocality != null) {
//                    location = subLocality + "," + admin;
//                    System.out.println ("SHow admin3"+location);
//                } else {
//                    location = locality + "," + admin;
//                    System.out.println ("SHow admin4"+location);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return location;
    }
}
