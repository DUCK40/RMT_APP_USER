package com.example.nirvana.projectrmt15_1_62;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Login extends AppCompatActivity {

    private final static int ALL_PERMISSIONS_RESULT = 101;
    public static String getAdress;
    public static String getCity;
    public static String District;
    public static int IdDistrict;
    public static String idCus = "1";
    public static String cityName;
    public static String getState;
    static Double lati;
    static Double longti;
    Boolean a;
    double latitude1;
    double longtitu11;
    LocationTrack locationTrack;
    OderRepair oderRepair;
    String sendcity;
    String sendsubLocality;
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<> ();
    private ArrayList<String> permissions = new ArrayList<> ();

    //    protected LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );


        locationTrack = new LocationTrack ( Login.this );

        if (locationTrack.canGetLocation ()) {
            sendsubLocality = locationTrack.getCity ();
            sendcity = locationTrack.getState ();

            System.out.println ( "SHOWWO" + sendcity );


        } else {
            a = true;
        }
        try {
            if (sendcity.contains ( "สมุทรปราการ" )) {
                //ถ้าอยู่ในสุมทรปราการ ให้ตัวแปร a เป็นtrue ทำเงื่อนไขต่อไป ตอนClick Login
                a = true;
                System.out.println ( "ABCDEFGHIG" );
            } else if (sendcity == null) {
                //ถ้าอยู่ในสุมทรปราการ ให้ตัวแปร a เป็นfalse ทำเงื่อนไขต่อไป ตอนClick Login

                a = false;
                System.out.println ( "ABCDEFGHIG11111" );
            }
        } catch (Exception e) {

        }
        findDeprament ();

/////////////////////////////////////////////////////////////////////////////////
        permissions.add ( ACCESS_FINE_LOCATION );
        permissions.add ( ACCESS_COARSE_LOCATION );

        permissionsToRequest = findUnAskedPermissions ( permissions );


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (permissionsToRequest.size () > 0)
                requestPermissions ( permissionsToRequest.toArray ( new String[permissionsToRequest.size ()] ), ALL_PERMISSIONS_RESULT );
        }

/////////////////////////////////////////////////////////////////////////////////////////
//        boolean aaa= true;
//        if (aaa =true){
//            a = true;
//            sendsubLocality = locationTrack.getCity ();
//            sendcity = locationTrack.getState ();
//            System.out.println ( "wwwwwsss M1111a"+sendcity );
//        }else if (aaa =false) {
//            if (locationTrack.canGetLocation ()) {
//                System.out.println ( "wwwwwsss M111122" );
//                double longitude = locationTrack.getLongitude ();
//                double latitude = locationTrack.getLatitude ();
//
//                sendsubLocality = locationTrack.getCity ();
//                sendcity = locationTrack.getState ();
//
//                System.out.println ( "wwwwwsss M " + sendcity ); //13.5951669
//                System.out.println ( "wwwwsss OP" + sendsubLocality ); //100.8113643
//                try {
//                    if (sendcity.contains ( "สมุทรปราการ" )) {
//                        //ถ้าอยู่ในสุมทรปราการ ให้ตัวแปร a เป็นtrue ทำเงื่อนไขต่อไป ตอนClick Login
//                        a = true;
//                        System.out.println ( "ABCDEFGHIG" );
//                    } else {
//                        //ถ้าอยู่ในสุมทรปราการ ให้ตัวแปร a เป็นfalse ทำเงื่อนไขต่อไป ตอนClick Login
//
//                        a = false;
//                        System.out.println ( "ABCDEFGHIG11111" );
//                    }
//                } catch (Exception e) {
//
//                }
//
//                findDeprament ();
//
//
//                /////////////////////////////////////////////////////
//
//                    if ( getState.equals ( "สมุทรปราการ" )){
//                        a=true;
//                    }else  {
//                        a=false;
//                    }
//
//
//                    Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
//            } else {
//
//                locationTrack.showSettingsAlert ();
//            }
//        }

        ////////////////////////////////////////////////////////////////////////////////////


//        if (locationTrack.canGetLocation ()) {
//            System.out.println ( "wwwwwsss M111122" );
//            double longitude = locationTrack.getLongitude ();
//            double latitude = locationTrack.getLatitude ();
//
//            sendsubLocality = locationTrack.getCity ();
//            sendcity = locationTrack.getState ();
//
//            System.out.println ( "wwwwwsss M " + sendcity ); //13.5951669
//            System.out.println ( "wwwwsss OP" + sendsubLocality ); //100.8113643
//
//            try {
////                if (sendcity.contains ( "สมุทรปราการ" )) {
////                    //ถ้าอยู่ในสุมทรปราการ ให้ตัวแปร a เป็นtrue ทำเงื่อนไขต่อไป ตอนClick Login
////                    a = true;
////                    System.out.println ( "ABCDEFGHIG" );
////                } else if (sendcity == null){
////                    //ถ้าอยู่ในสุมทรปราการ ให้ตัวแปร a เป็นfalse ทำเงื่อนไขต่อไป ตอนClick Login
////
////                    a = false;
////                    System.out.println ( "ABCDEFGHIG11111" );
////                }
////            } catch (Exception e) {
////
////            }
//
//            findDeprament ();
//
//
//            ///////////////////////////////////////////////////////
//
////                    if ( getState.equals ( "สมุทรปราการ" )){
////                        a=true;
////                    }else {
////                        a=false;
////                    }
//
//
////                    Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
//        } else {
//
//            locationTrack.showSettingsAlert ();
//        }

//        inti ();


        ///////////////////////////////////////////////////////////////////////////

        oderRepair = new OderRepair ();

        Repair obb = new Repair ();


        final EditText editLogin = (EditText) findViewById ( R.id.login );
        final EditText editPass = (EditText) findViewById ( R.id.password );


        //Button ในการสมัครรงาน
        LinearLayout s = (LinearLayout) findViewById ( R.id.register );

        //Butoon กดเมื่อ ทำการกรอก Userrname Password เรียบร้อย
        final Button button = (Button) findViewById ( R.id.button );

        s.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity ( new Intent ( Login.this, Register.class ) );
            }
        } );


//        getState.equals ( "สมุทรปราการ" )

        //if ตรงนี้ ต้อวเอา a มาใส่

        if (a) {
            button.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {


                    try {
                        Connection conn = ConnectDb.getConnection (); //Connection Object
                        PreparedStatement pre = null;
                        Statement stmt = null;

                        if (editLogin.getText ().length () == 0) {
                            Toast.makeText ( getBaseContext (), "กรุณากรอก Username", Toast.LENGTH_LONG ).show ();
                        } else if (editPass.getText ().length () == 0) {
                            Toast.makeText ( getBaseContext (), "กรุณากรอก Password", Toast.LENGTH_LONG ).show ();
                        } else {


                            stmt = conn.createStatement ();
                            String SQL = "SELECT * FROM `ex_customer`\n" +
                                    "WHERE ct_userid = '" + editLogin.getText ().toString () + "' AND  ct_password = '" + editPass.getText ().toString () + "' ";   // WHERE Name = '" + user + "'


                            Statement mStmt = conn.createStatement ();
                            ResultSet rs = mStmt.executeQuery ( SQL );


                            List rowValues1 = new ArrayList ();


                            while (rs.next ()) {

                                rowValues1.add ( rs.getString ( "ct_id" ) );
                                idCus = rs.getString ( "ct_id" );
                                oderRepair.setIdCus ( rs.getString ( "ct_id" ) );
                            }

                            if (rowValues1.size () < 1) {

                                Toast.makeText ( getApplicationContext (), "คุณยังไม่ได้สมัครการใช้งาน", Toast.LENGTH_SHORT ).show ();

                            } else if (rowValues1.size () == 1) {
                                System.out.println ( "ppppppp" + idCus );
                                startActivity ( new Intent ( Login.this, MainActivity.class ) );

                            }


                        }
                    } catch (Exception e) {

                    }


                }
            } );
        } else { //ในกรณีที่ไม่ได้อยู่สมุทรปราการ
            Toast.makeText ( getApplicationContext (), "คุณไม่ได้อยู่ในพื้นที่ ที่ให้บริการ", Toast.LENGTH_SHORT ).show ();

        }


//        init ();

    }


    //ค้นหาID สาขา เพื่อนำไปใช้ในการต้นหายางตอนจ้งซ่อม
    void findDeprament() {
        System.out.println ( "25s25s25" );

        try {
            Connection conn = ConnectDb.getConnection (); //Connection Object
//            String SQL = "SELECT dep_id,SUBSTRING(dep_name, 7)as dep_name FROM `ex_department`";

            String SQL = "SELECT * FROM `ex_department`";

//            SELECT dep_id,SUBSTRING(dep_name, 6) FROM `ex_department`


            Statement mStmt = conn.createStatement ();
            ResultSet rs = mStmt.executeQuery ( SQL );

            List rowValues1 = new ArrayList ();
            List rowValues2 = new ArrayList ();
            String aa = getAdress;


            while (rs.next ()) {
                rowValues1.add ( rs.getInt ( "dep_id" ) );
                rowValues2.add ( rs.getString ( "dep_name" ) );


            }

            for (int i = 0; i <= rowValues2.size (); i++) {
                System.out.println ( "asdasdassw1" + rowValues2.get ( i ).toString () );
//                System.out.println ( "asdasdassswsw112" +aacity );
                String oo = (String) rowValues2.get ( i );
                String nameCity =sendsubLocality;
                System.out.println ( "SLLLLLLLLL1" + oo );
                System.out.println ( "SLLLLLLLLL2" + nameCity );
                if (rowValues2.get ( i ).toString ().contains ("อำเภอ เมืองสมุทรปราการ")){
                    System.out.println ( "POPWOLOO"+nameCity );
                }
                if (nameCity.contains ( rowValues2.get ( i ).toString () )){
                    System.out.println ( "POPWOLOO1"+nameCity );
                }

//if (sendsubLocality.contains ( rowValues2.get ( i ).toString () )){}
                if (rowValues2.get ( i ).toString ().contains(nameCity) ) {
//                    System.out.println ( "asdasdassswsw2" + rowValues2.get ( i ).toString () );
                    IdDistrict = (int) rowValues1.get ( i );
                    System.out.println ( "TO be Number " + IdDistrict );

                } else {
                    System.out.println ( "SHOWWOASDASD" + oo );
                }
            }


        } catch (Exception e) {

        }

    }


    ///////////////////////////////////////////////////////////////////////////////
    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String> ();

        for (String perm : wanted) {
            if (!hasPermission ( perm )) {
                result.add ( perm );
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores ()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission ( permission ) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission ( perms )) {
                        permissionsRejected.add ( perms );
                    }
                }

                if (permissionsRejected.size () > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale ( permissionsRejected.get ( 0 ) )) {
                            showMessageOKCancel ( "These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener () {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions ( permissionsRejected.toArray ( new String[permissionsRejected.size ()] ), ALL_PERMISSIONS_RESULT );
                                            }
                                        }
                                    } );
                            return;
                        }
                    }

                }

                break;
        }

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder ( Login.this )
                .setMessage ( message )
                .setPositiveButton ( "OK", okListener )
                .setNegativeButton ( "Cancel", null )
                .create ()
                .show ();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        locationTrack.stopListener ();


    }


}
