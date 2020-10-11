package com.example.nirvana.projectrmt15_1_62;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Repair extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    static final int REQUREST_LOCATION = 1;
    public static int selectRubberFont;
    public static int selectRubberBack;
    public static Boolean stateRubberFont = false;
    public static Boolean stateRubberBack = false;
    public static String nameRubberfont;
    public static String nameRubberback;
    final List<String> spinnerArray = new ArrayList<> ();
    final List<String> spinnerArray1 = new ArrayList<> ();
    final List<String> spinnerArrayId = new ArrayList<> ();
    final List<String> spinnerArray1Id = new ArrayList<> ();
    LocationTrack locationTrack;
    List rowValues11 = new ArrayList ();
    List rowValues22 = new ArrayList ();

    String mam = "kssaาหหฟ";
    OderRepair settingOrder;
    Marker marker;
    Login login;
    Spinner spinner;
    Spinner spinner1;
    EditText edtMark;
    Button btn1;
    LocationManager locationManager;
    double Lati;
    double Longti;
    double getLati;
    double getLongti;
    double inputLati;
    double inputLongti;
    //    EditText edtlati;
//    EditText edtlongti;
//    Button btngetLocation;
    String conLati;
    String conLongti;
    Boolean ter = true;
    Insertdatacar insertorders;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_repair );

        System.out.println ( "To Be Continute" + Login.IdDistrict );

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder ().permitAll ().build ();
        StrictMode.setThreadPolicy ( policy );

        settingOrder = new OderRepair ();
        init ();
        selectPart ();


        locationManager = (LocationManager) getSystemService ( Context.LOCATION_SERVICE );

        getLocation ();


        changLocation ();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager ()
                .findFragmentById ( R.id.map );
        mapFragment.getMapAsync ( this );


        final InsertOder obInsert = new InsertOder ();

        final Insertdatacar insertorder = new Insertdatacar ();
//        insertorders = new Insertdatacar ();

//btn1.setOnClickListener ( new View.OnClickListener () {

//    @Override
//    public void onClick(View view) {
//        try {
//            if (ter == true){
//                insertorder.execute (  "1",settingOrder.getRubberback (),"11.033","3","1" );
//
//            }
////            obInsert.execute (  "1","10.03","11.033","3","1");
//        }catch (Exception e){
//
//        }


//     insertorders.execute ( "1","10.03","11.033","3","1" );
//        obInsert.execute ( "5555555" );

//    }

//} );


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        System.out.println ( "Helloll onConnected" );
    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.println ( "Helloll onConnectionSuspended" );
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.out.println ( "Helloll onConnectionFailed" );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        String country =null;

        System.out.println ( "Helloll" );

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng ( getLati, getLongti );
        mMap.addMarker ( new MarkerOptions ().position ( sydney ).title ( "Marker in Sydney" ) );
        marker = mMap.addMarker ( new MarkerOptions ().position ( sydney ).title ( "Marker in Sydney" ).snippet ( "KUYYYYYYYYYYY" ) );

        mMap.moveCamera ( CameraUpdateFactory.newLatLng ( sydney ) );
        mMap.animateCamera ( CameraUpdateFactory.zoomTo ( 15.0f ) );

        /////////////////////////////////////////////////////////////////////////////////////

        System.out.println ( "SHOWLATILONTTI" + getLati + getLongti );
        ////////////////////////////////////////////////////////////////////////////////
        //เพิ่มMarker
        mMap.setOnMapClickListener ( new GoogleMap.OnMapClickListener () {

            public void onMapClick(LatLng arg0) {
                String city1 = "";
                if (marker == null) {

                    try {
                        Geocoder geocoder;
                        List<Address> addresses;
                        geocoder = new Geocoder ( getBaseContext (), Locale.getDefault () );

                        addresses = geocoder.getFromLocation ( arg0.latitude, arg0.longitude, 1 ); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                        String address = addresses.get ( 0 ).getAddressLine ( 0 ); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String city = addresses.get ( 0 ).getLocality ();
                        String state = addresses.get ( 0 ).getAdminArea ();
                        String country = addresses.get ( 0 ).getCountryName ();
                        String postalCode = addresses.get ( 0 ).getPostalCode ();
                        String knownName = addresses.get ( 0 ).getFeatureName (); // Only if available else return NULL

                        /////////////////////////////////////////////////////////////
                        city1 = state;
                        System.out.println ( "address22" + address );
                        System.out.println ( "city22" + city );
                        System.out.println ( "state122: " + state );
                        System.out.println ( "country22" + country );
                        System.out.println ( "postalCode22" + postalCode );
                        System.out.println ( "knownName22" + knownName );
                        addresses.clear ();
                    } catch (Exception e) {

                    }
                    if (city1.equals ( "สมุทรปราการ" )) {
                        marker = mMap.addMarker ( new MarkerOptions ().position ( arg0 ).title ( "Marker in Sydney" ).snippet ( "KUYYYYYYYYYYY" ) );

                    } else {
                        Toast.makeText ( getApplicationContext (), "คุณเลือกตำแหน่งไม่ถูกต้อง", Toast.LENGTH_SHORT ).show ();
                    }
                    //ใช้
//                    marker = mMap.addMarker ( new MarkerOptions ().position ( arg0).title ( "Marker in Sydney" ).snippet ( "KUYYYYYYYYYYY" )  );

                }

                System.out.println ( "Show latijude start :" + arg0.latitude + "Show Longtijude start " + arg0.longitude );
                System.out.println ( "Show latijude Select :" + Lati + "Show Longtijude Select " + Longti );
//                System.out.println ( "zzzzzz :"+circle.getCenter().latitude+"zzzz"+circle.getCenter().longitude );


            }


        } );

        //////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////
        //ลบ Marker
        mMap.setOnMarkerClickListener ( new GoogleMap.OnMarkerClickListener () {
            public boolean onMarkerClick(Marker arg0) {


                arg0.remove ();

                Toast.makeText ( getApplicationContext ()
                        , "Remove Marker " + String.valueOf ( arg0.getId () )
                        , Toast.LENGTH_SHORT ).show ();
                marker.remove ();
                marker = null;

//                marker = mMap.addMarker ( new MarkerOptions ().position ( null).title ( "Marker in Sydney" ).snippet ( "KUYYYYYYYYYYY" )  );


                return true;
            }

        } );
        ////////////////////////////////////////////////////////////////////////////
    }

    ////////////////////////////////////////////////////////////////////
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult ( requestCode, permissions, grantResults );
        System.out.println ( "00000000000000000" );
        switch (requestCode) {
            case REQUREST_LOCATION:
                getLocation ();
                break;
        }
    }


    void getLocation() {
        if (ActivityCompat.checkSelfPermission ( this, Manifest.permission.ACCESS_FINE_LOCATION ) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission ( this, Manifest.permission.ACCESS_COARSE_LOCATION )
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions ( this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUREST_LOCATION );
        } else {
            Location location = locationManager.getLastKnownLocation ( LocationManager.NETWORK_PROVIDER );

            if (location != null) {
                double latti = location.getLatitude ();
                double longti = location.getLongitude ();

                //////////////////////////////////////////////////////////////////////
                try {
                    Geocoder geocoder;
                    List<Address> addresses;

                    geocoder = new Geocoder ( this, Locale.getDefault () );

                    addresses = geocoder.getFromLocation ( location.getLatitude (), location.getLongitude (), 1 ); // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                    String address = addresses.get ( 0 ).getAddressLine ( 0 ); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get ( 0 ).getLocality ();
                    String state = addresses.get ( 0 ).getAdminArea ();
                    String country = addresses.get ( 0 ).getCountryName ();
                    String postalCode = addresses.get ( 0 ).getPostalCode ();
                    String knownName = addresses.get ( 0 ).getFeatureName (); // Only if available else return NULL

                    /////////////////////////////////////////////////////////////
//            city1 = state;
                    System.out.println ( "address" + address );
                    System.out.println ( "city" + city );
                    System.out.println ( "state12: " + state );
                    System.out.println ( "country" + country );
                    System.out.println ( "postalCode" + postalCode );
                    System.out.println ( "knownName" + knownName );


                } catch (Exception e) {

                }

                ///////////////////////////////////////////////////////////////////////

                String originalati;
                String originalongti;

                getLati = latti;
                getLongti = longti;


                conLati = new Double ( latti ).toString ();
                conLongti = new Double ( longti ).toString ();
                settingOrder.setLati ( conLati );
                settingOrder.setLongti ( conLongti );
//                edtlongti.setText ( conLongti );
//                if (edtlati.getText ().toString ().equals ( "" ) || edtlongti.getText ().toString ().equals ( "" )) {
//                    edtlati.setText ( conLati );
//                    edtlongti.setText ( conLongti );
//                }


                originalati = new String ( String.valueOf ( latti ) );
                originalongti = new String ( String.valueOf ( latti ) );

//                System.out.println ( "originalati" + inputLati );
//                System.out.println ( "originalati" + edtlati.getText ().toString () );
//                System.out.println (" originalongti");

//                tete = new String ( String.valueOf ( latti ) );
//                conLati = new Double(latti).toString();


                if (inputLati == latti) {
                    System.out.println ( "2222222" );
                    getLati = 10.000;
                    getLongti = 20.00;
                    System.out.println ( "2222222" + inputLati );
//                    edtlati.setText ( conLati );
//                    edtlongti.setText ( new Double(getLongti).toString() );

                } else if (inputLati != latti) {
                    System.out.println ( "2222222" + inputLati );
                }
//                else if (inputLati != latti){
////                    edtlati.setText ( conLati );
//                    edtlongti.setText ( new Double(inputLongti).toString() );
////                    System.out.println ("11111111"+originalati);
//
//                }


//                edtlongti.setText ( new Double(getLongti).toString() );

//                if (edtlati.getText ().toString () .equals ( tete )){
//                    System.out.println ("OKa");
//                    edtlongti.setText ( new Double(getLongti).toString() );
//                }else if(edtlati.getText ().toString () != tete.toString ()){
//                    edtlongti.setText ( "aaaaaaaaaaaaa" );
//                }


                System.out.println ( "Show Lati" + latti );

                System.out.println ( "Show Longti" + longti );


//                ((EditText)findViewById ( R.id.etLocationLat )).setText ( "Latijude"+latti );
//                ((EditText)findViewById ( R.id.etLocationLong )).setText ( "Longtijude"+longti );
            } else {
//                ((EditText)findViewById ( R.id.etLocationLat )).setText ( "No Location");
//                ((EditText)findViewById ( R.id.etLocationLong )).setText ( "No Location" );
            }

        }

    }


    void init() {
        btn1 = (Button) findViewById ( R.id.button1 );

        edtMark = (EditText) findViewById ( R.id.edtmark );

        spinner = (Spinner) findViewById ( R.id.spinner );
        spinner1 = (Spinner) findViewById ( R.id.spinner1 );

//        edtlati = (EditText) findViewById ( R.id.lati );
//        edtlongti = (EditText) findViewById ( R.id.longti );
//        btngetLocation = (Button) findViewById ( R.id.btngetLocation );

        login = new Login ();
        btn1.setOnClickListener ( this );

        edtMark.addTextChangedListener ( new TextWatcher () {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.equals ( "" )) {
                    settingOrder.setLandmark ( null );
                }
                if (s.length () >= 1) {
                    settingOrder.setLandmark ( edtMark.getText ().toString () );

                }
            }


            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        } );


//            Insertdatacar insertorder = new Insertdatacar ();
//            InsertOder obInsert = new InsertOder ();


//settingOrder.setLandmark ( edtMark.getText ().toString () );
        insertorders = new Insertdatacar ();


        findPart ();


    }

    void changLocation() {


//        btngetLocation.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText ( Repair.this,
////                        "Click" ,Toast.LENGTH_SHORT ).show ();
//
////                inputLati = Double.parseDouble ( edtlati.getText ().toString () );
////                inputLongti = Double.parseDouble ( edtlongti.getText ().toString () );
//
////                tete= 1150;
////                tete = Double.parseDouble(edtlati.getText().toString());
////                System.out.println ("SHOW DATA A "+tete);
//                getLocation ();
//            }
//        } );
    }


    void selectPart() {


        ArrayAdapter<String> adapterThai = new ArrayAdapter<String> ( this,
                android.R.layout.simple_dropdown_item_1line, spinnerArray );


        ArrayAdapter<String> adapterThai1 = new ArrayAdapter<String> ( this,
                android.R.layout.simple_dropdown_item_1line, spinnerArray1 );


        spinner.setAdapter ( adapterThai );
        spinner1.setAdapter ( adapterThai1 );


        spinner1.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i) {
                    case (0):
                        //Case selection redirecting user to 'Training Table'
                        settingOrder.setRubberback ( null );
                        selectRubberBack = 0;
                        nameRubberback = "0";
                        stateRubberBack = true;
                        break;
                    case (1):
                        //Case selection redirecting user to 'Race Table'
                        Toast.makeText ( Repair.this,
                                "Select ล้อหลัง: " + spinnerArray1.get ( i ),
                                Toast.LENGTH_SHORT ).show ();
                        settingOrder.setRubberback ( spinnerArray1.get ( i ) );
//                        selectRubberBack = (int) rowValues11.get ( i );//jj
                        nameRubberback = spinnerArray1.get ( i );
                        stateRubberBack = true;
                        break;

                    case (2):
                        //Case selection redirecting user to 'Workshops page'
                        Toast.makeText ( Repair.this,
                                "Select ล้อหลัง: " + spinnerArray1.get ( i ),
                                Toast.LENGTH_SHORT ).show ();
                        settingOrder.setRubberback ( spinnerArray1.get ( i ) );
                        nameRubberback = spinnerArray1.get ( i );
//                        selectRubberBack = (int) rowValues11.get ( i );//jj

                        stateRubberBack = true;

                        break;

                    case (3):
                        //Case selection redirecting user to 'Workshops page'
                        Toast.makeText ( Repair.this,
                                "Select ล้อหลัง: " + spinnerArray1.get ( i ),
                                Toast.LENGTH_SHORT ).show ();
                        settingOrder.setRubberback ( spinnerArray1.get ( i ) );
                        nameRubberback = spinnerArray1.get ( i );
//                        selectRubberBack = (int) rowValues11.get ( i );//jj

                        stateRubberBack = true;
                        break;
                    case (4):
                        //Case selection redirecting user to 'Workshops page'
                        Toast.makeText ( Repair.this,
                                "Select ล้อหลัง: " + spinnerArray1.get ( i ),
                                Toast.LENGTH_SHORT ).show ();
                        settingOrder.setRubberback ( spinnerArray1.get ( i ) );
                        nameRubberback = spinnerArray1.get ( i );
//                        selectRubberBack = (int) rowValues11.get ( i );//jj

                        stateRubberBack = true;
                        break;
                    case (5):
                        //Case selection redirecting user to 'Workshops page'
                        Toast.makeText ( Repair.this,
                                "Select ล้อหลัง: " + spinnerArray1.get ( i ),
                                Toast.LENGTH_SHORT ).show ();
                        settingOrder.setRubberback ( spinnerArray1.get ( i ) );
                        nameRubberback = spinnerArray1.get ( i );
//                        selectRubberBack = (int) rowValues11.get ( i );//jj

                        stateRubberBack = true;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                settingOrder.setRubberback ( null );
                nameRubberback = "0";
                selectRubberBack = 0;
                stateRubberBack = false;
            }
        } );


        spinner.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case (0):
                        //Case selection redirecting user to 'Training Table'
                        settingOrder.setRubberfont ( null );
                        nameRubberfont = "0";
                        selectRubberFont = 0;
                        stateRubberFont = true;

                        break;
                    case (1):
                        //Case selection redirecting user to 'Race Table'
                        Toast.makeText ( Repair.this,
                                "Select ล้อหน้า: " + spinnerArray.get ( i ),
                                Toast.LENGTH_SHORT ).show ();
                        settingOrder.setRubberfont ( spinnerArray.get ( i ) );

                        nameRubberfont = spinnerArray.get ( i );
//                        selectRubberFont = (int) rowValues22.get ( 1 );//jx

                        stateRubberFont = true;
                        break;

                    case (2):
                        //Case selection redirecting user to 'Workshops page'
                        Toast.makeText ( Repair.this,
                                "Select ล้อหน้า: " + spinnerArray.get ( i ),
                                Toast.LENGTH_SHORT ).show ();
                        settingOrder.setRubberfont ( spinnerArray.get ( i ) );
                        nameRubberfont = spinnerArray.get ( i );
//                        selectRubberFont = (int) rowValues22.get ( 2 );//jx

                        stateRubberFont = true;
                        break;
                    case (3):
                        //Case selection redirecting user to 'Workshops page'
                        Toast.makeText ( Repair.this,
                                "Select ล้อหน้า: " + spinnerArray.get ( i ),
                                Toast.LENGTH_SHORT ).show ();
                        settingOrder.setRubberfont ( spinnerArray.get ( i ) );
                        nameRubberfont = spinnerArray.get ( i );
//                        selectRubberFont = (int) rowValues22.get ( 3 );//jx

                        stateRubberFont = true;
                        break;
                    case (4):
                        //Case selection redirecting user to 'Workshops page'
                        Toast.makeText ( Repair.this,
                                "Select ล้อหน้า: " + spinnerArray.get ( i ),
                                Toast.LENGTH_SHORT ).show ();
                        settingOrder.setRubberfont ( spinnerArray.get ( i ) );
                        nameRubberfont = spinnerArray.get ( i );
//                        selectRubberFont = (int) rowValues22.get ( 4 );//jx

                        stateRubberFont = true;
                        break;
                    case (5):
                        //Case selection redirecting user to 'Workshops page'
                        Toast.makeText ( Repair.this,
                                "Select ล้อหน้า: " + spinnerArray.get ( i ),
                                Toast.LENGTH_SHORT ).show ();
                        settingOrder.setRubberfont ( spinnerArray.get ( i ) );
                        nameRubberfont = spinnerArray.get ( i );
//                        selectRubberFont = (int) rowValues22.get ( 5);//jx

                        stateRubberFont = true;
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                settingOrder.setRubberfont ( null );
                nameRubberfont = "0";
                selectRubberFont = 0;
                stateRubberFont = false;
            }


        } );


    }

    void findPart() {
        locationTrack = new LocationTrack ( Repair.this );
        String sendsubLocality = locationTrack.getCity ();
        System.out.println ( "SHOW DEPARTRRR" + Login.IdDistrict );
        PreparedStatement pre = null;
        Statement stmt = null;
        try {
            Connection conn = ConnectDb.getConnection (); //Connection Object
            String SQL = "SELECT * FROM `ex_software`";

            //เอาไปใส่spinner แรก
            String sql1 = "SELECT sfw.sw_id,depart.dep_name,sfw.sw_name,SUM(sfwd.part_d_sec),SUM(sfwd.part_d_sec_use)\n" +
                    "FROM ex_software AS sfw,ex_software_datail AS sfwd,ex_user AS users,ex_department AS depart\n" +
                    " WHERE sfw.sw_id=sfwd.part_id AND sfwd.part_d_userid=users.user_id\n" +
                    " AND users.dep_id=depart.dep_id and depart.dep_id ='" + Login.IdDistrict + "' and sfwd.part_d_sec_use >=1\n" +
                    " GROUP BY depart.dep_id ,sfwd.part_id";
            //เอาไปใส่spinner สอง
            String sql2 = "SELECT sfw.sw_id,depart.dep_name,sfw.sw_name,SUM(sfwd.part_d_sec),SUM(sfwd.part_d_sec_use)\n" +
                    " FROM ex_software AS sfw,ex_software_datail AS sfwd,ex_user AS users,ex_department AS depart\n" +
                    " WHERE sfw.sw_id=sfwd.part_id AND sfwd.part_d_userid=users.user_id\n" +
                    " AND users.dep_id=depart.dep_id and depart.dep_id ='" + Login.IdDistrict + "'  and sfwd.part_d_sec_use >1 \n" +
                    " GROUP BY depart.dep_id ,sfwd.part_id";

            Statement mStmt1 = conn.createStatement ();
            ResultSet rs1 = mStmt1.executeQuery ( sql1 );

            Statement mStmt2 = conn.createStatement ();
            ResultSet rs2 = mStmt2.executeQuery ( sql2 );

            List rowValues = new ArrayList ();

            List rowValues2 = new ArrayList ();


            while (rs1.next ()) {

                rowValues11.add ( rs1.getString ( "sw_id" ) );
                rowValues.add ( rs1.getString ( "sw_name" ) );
                System.out.println ( "psssss" + rs1.getString ( "sw_name" ) );


            }
            while (rs2.next ()) {

                rowValues22.add ( rs2.getString ( "sw_id" ) );
                rowValues2.add ( rs2.getString ( "sw_name" ) );


            }


            for (int i = 0; i < rowValues.size (); i++) {
                int s = 1;
                if (i == 0) {
//                    spinnerArray.add ( "--" );
////                    spinnerArray1.add ( "--" );
//                    spinnerArray1.add ( 0, "--" );
////                    spinnerArray.add ( 0, "--" );


                    spinnerArray1.add ( 0, "เลือกขนาดยาง" );
                    spinnerArray.add ( 0, "เลือกขนาดยาง" );
                    spinnerArray.add ( (String) rowValues.get ( i ) );
                    spinnerArray1.add ( (String) rowValues2.get ( i ) );
                } else {

                    spinnerArray.add ( (String) rowValues.get ( i ) );
                    spinnerArray1.add ( (String) rowValues2.get ( i ) );
                }
                s = s + 1;

            }
            mStmt1.close ();
            rs1.close ();
            mStmt2.close ();
            rs2.close ();
            rowValues.clear ();
            rowValues2.clear ();

        } catch (Exception e) {

        }

    }


    @Override
    public void onClick(View view) {
        Insertdatacar insertorder = new Insertdatacar ();
        InsertOder ob = new InsertOder ();
        switch (view.getId ()) {

            case R.id.button1:
                if (nameRubberback == "0" && nameRubberfont == "0") {
                    Toast.makeText ( getApplicationContext (), "กรุณาเลือกขนาดยาง", Toast.LENGTH_SHORT ).show ();
//                    startActivity ( new Intent ( Repair.this, Repair.class ) );

                } else {
                    insertorder.execute ( login.idCus, settingOrder.getLati (), settingOrder.getLongti (), "1", settingOrder.getLandmark (), settingOrder.getRubberfont (), settingOrder.getRubberback () );

                    startActivity ( new Intent ( Repair.this, Confrim_Repair1.class ) );
                    Toast.makeText ( getApplicationContext (), "ทำการแจ้งซ่อมเรียบร้อย", Toast.LENGTH_SHORT ).show ();

                }
//                System.out.println ("00200202");

//                    ob.execute ( );


                //อย่าลืมเปิดคอมเมนนะจ๊ะ
//                insertorder.execute ( login.idCus, settingOrder.getLati (), settingOrder.getLongti (), "1", settingOrder.getLandmark (), settingOrder.getRubberfont (), settingOrder.getRubberback () );
//
//
//               settingOrder.setLandmark (edtMark.getText ().toString ());
//                insertorder.execute ( login.idCus, settingOrder.getLati (), settingOrder.getLongti (), "1", settingOrder.getLandmark (), settingOrder.getRubberfont (), settingOrder.getRubberback () );
//
//                startActivity ( new Intent ( Repair.this, Confrim_Repair1.class ) );
//                Toast.makeText ( getApplicationContext (), "ทำการแจ้งซ่อมเรียบร้อย", Toast.LENGTH_SHORT ).show ();

                break;
        }
//

    }


    public class InsertOder extends AsyncTask<String, Void, String> {


        @Override

        protected void onPostExecute(String s) {
//            txt.setText ( s);
            System.out.println ( "Show conn != nULL12" + s.toString () );
            super.onPostExecute ( s );
        }

        @Override
        protected String doInBackground(String... strings) {
            System.out.println ( "Show conn != nULL" );
//        String mParam1 = strings[0]; // รับParameter มาแล้วแปลงเป็นค่า Sring
//        String mParam2 = strings[1];
//        String mParam3 = strings[2];
//        String mParam4 = strings[3];
//        String mParam5 = strings[4];
//        String mParam6 = strings[5];
//        String mParam7 = strings[6];
//        String mParam8 = strings[7];


            Statement stmt = null;
            try {

                Connection conn = ConnectDb.getConnection (); //Connection Object
                if (conn != null) {

                    String sql1 = "INSERT INTO `test` (`name`) VALUES ('1สสสllll')";
                    String sql11 = "INSERT INTO `test` (`name`) VALUES ('11111123')";


                    String sql2 = "INSERT INTO oktsxyz_duck.`rmt_order_repair`\n " +
                            "( order_cusid,order_lati, order_longti,  order_state, order_landmarks)\n " +
                            " VALUES(?,?, ?, ?, ?);";

                    String sql = "INSERT INTO `order` (`order_id`, `order_cusid`, `order_lati`, `order_longti`, `order_user_id`, `order_ad_id`, `order_status`, `order_landmarks`, `order_rubberfont`, `order_rubberback`, `order_service_id`) \n" +
                            "VALUES (NULL, ?, ?, ?, NULL, NULL, ?, ?, ?, ?, '1');";


                    PreparedStatement statement = (PreparedStatement) conn.prepareStatement ( sql1 );
                    PreparedStatement statement1 = (PreparedStatement) conn.prepareStatement ( sql11 );


//                statement.setString ( 1, mParam1 );
//                statement.setString ( 2, mParam2 );
//                statement.setString ( 3, mParam3 );
//                statement.setString ( 4, mParam4 );
//                statement.setString ( 5, mParam5 );
//                statement.setString ( 6, mParam6 );
//                statement.setString ( 7, mParam7 );
//                statement.setString ( 8, mParam8 );
                    statement1.executeUpdate ();
                    statement.executeUpdate ();
                    statement.close ();
                    statement1.close ();


                    conn.close ();
                } else {
                    System.out.println ( "connect faild " );

                }
            } catch (Exception e) {
                e.printStackTrace ();

            }

            return null;
        }
    }


}



