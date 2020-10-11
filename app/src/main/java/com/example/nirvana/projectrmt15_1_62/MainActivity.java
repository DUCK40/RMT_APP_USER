package com.example.nirvana.projectrmt15_1_62;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int REQUREST_LOCATION = 1;
    private static final String TAG = "LocationDemo";
    public static int idOrder;
    static Double lati;
    static Double longti;
    LocationManager locationManager;
    String cityName;
    Login obLogin;
    OderRepair oderRepair;
    LinearLayout btnRepair;
    LinearLayout btnDetailRepair;
    LinearLayout btnLinearHistoty;
    LinearLayout btnLinearSeeProfile;

    LinearLayout viewRubber;
    Maps_Repair_Activity obMaps_repair_activity;
    int iz = 1;
    Boolean statecity = true;
    private GoogleApiClient mGoogleApiClient;

    public static int statusRepair ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        System.out.println ( "To Be Continute" + Login.IdDistrict );
//        findDeparment ();

        System.out.println ( "STATE CITY BOOLEAN" + statecity );
        System.out.println ( "SHOWW CITY111" + Login.getState );
        System.out.println ( "SHOWW CITY1112" + Login.cityName );


        init ();


    }


//    void findDeparment() {
//        try {
//            Connection conn = ConnectDb.getConnection (); //Connection Object
//
//            String SQL1 = "SELECT * FROM `ex_department` WHERE dep_status = '1' ;";
//
//
//            System.out.println ( "SPSP211111za1" );
//            Statement mStmt = conn.createStatement ();
//            System.out.println ( "SPSP211111za2" );
//            ResultSet rs = mStmt.executeQuery ( SQL1 );
//            System.out.println ( "SPSP211111za3" );
//
//            List rowValues1 = new ArrayList ();
//
//            System.out.println ( "SPSP211111za4" );
//
//
//            while (rs.next ()) {
//
//
//                rowValues1.add ( rs.getString ( "dep_name" ) );
//
////                System.out.println ("qsdqwq1"+rowValues1.get ( 1 ));
//            }
//            for (int i = 0; i <= rowValues1.size (); i++) {
//                if (Login.getAdress.contains ( String.valueOf ( rowValues1.get ( i ) ) )) {
//
//                    statecity = true;
//                } else {
//                    statecity = false;
//                }
//                System.out.println ( "qsdqwq2" + rowValues1.get ( i ) );
//            }
//            System.out.println ( "qsdqwq3" + rowValues1.get ( 1 ) );
//
//
//            conn.close ();
//            mStmt.close ();
//            rowValues1.clear ();
//
//            rs.close ();
//
//        } catch (Exception e) {
//
//        }
//    }


    void init() {


        obLogin = new Login ();
        oderRepair = new OderRepair ();


        btnRepair = (LinearLayout) findViewById ( R.id.tests );
        btnDetailRepair = (LinearLayout) findViewById ( R.id.testt);
//        btnLinearHistoty = (LinearLayout)findViewById ( R.id.btnLinearHistoty );
        btnLinearSeeProfile = (LinearLayout)findViewById ( R.id.btnLinearSeeProfile );
        viewRubber = (LinearLayout)findViewById ( R.id.viewRubber );


        btnRepair.setOnClickListener ( this );
        btnDetailRepair.setOnClickListener ( this );
//        btnLinearHistoty.setOnClickListener ( this );
        btnLinearSeeProfile.setOnClickListener ( this );
        viewRubber.setOnClickListener ( this );

        System.out.println ( "1245666B" + oderRepair.getLati () );


    }


    @Override
    public void onClick(View view) {


        if (statecity) {
            switch (view.getId ()) {
                    //แจ้งซ่อม
                case R.id.tests:


                    try {
                        Connection conn = ConnectDb.getConnection (); //Connection Object
                        int getStatus = 0;

                        String SQL = "SELECT cus_id \n" +
                                "FROM oktsxyz_duck.customer \n" +
                                "INNER JOIN oktsxyz_duck.order ON order_cusid \n" +
                                "WHERE order_cusid = " + obLogin.idCus + " and order_status BETWEEN 1 AND 2 \n" +
                                "GROUP BY cus_id;";

                        String SQL1 = "SELECT * FROM`order`\n" +
                                "WHERE order_id= (SELECT MAX(order_id)\n" +
                                "FROM `order` WHERE order_cusid =" + obLogin.idCus + ");";


                        System.out.println ( "SPSP2" );
                        Statement mStmt = conn.createStatement ();
                        ResultSet rs = mStmt.executeQuery ( SQL1 );

                        List rowValues1 = new ArrayList ();
                        List rowValues2 = new ArrayList ();

                        System.out.println ( "SPSP3" );

                        while (rs.next ()) {


                            rowValues2.add ( rs.getInt ( "order_service_id" ) );
                            rowValues1.add ( rs.getInt ( "order_status" ) );


                            getStatus = rs.getInt ( "order_status" );
                            statusRepair=getStatus;


                            idOrder = rs.getInt ( "order_service_id" );
                        }
                        System.out.println ( "qsdqwq" + idOrder );


                        if (getStatus == 0 || getStatus == 4 || getStatus == 3) {

                            startActivity ( new Intent ( MainActivity.this, Repair.class ) );

                        } else if (getStatus == 1 || getStatus == 2 ) {

                            Toast.makeText ( getApplicationContext (), "คุณได้ทำการแจ้งซ่อมไปแล้ว", Toast.LENGTH_SHORT ).show ();

                        }
                        conn.close ();
                        mStmt.close ();
                        rowValues1.clear ();
                        rowValues2.clear ();
                        rs.close ();

                    } catch (Exception e) {

                    }


                    break;
                    // ดูประวัติการแจ้งซ่อม
                case R.id.testt:
                    System.out.println ( "fsafsaf4sa6f46sf46as" );

                    try {
                        Connection conn = ConnectDb.getConnection (); //Connection Object
                        System.out.println ( "SPSP1" );

                        int getStatus = 0;
                        String SQL = "SELECT cus_id \n" +
                                "FROM oktsxyz_duck.customer \n" +
                                "INNER JOIN oktsxyz_duck.order ON order_cusid \n" +
                                "WHERE order_cusid = " + obLogin.idCus + " and order_status BETWEEN 1 AND 2 \n" +
                                "GROUP BY cus_id;";


                        String SQL1 = "SELECT * FROM`order`\n" +
                                "WHERE order_id= (SELECT MAX(order_id)\n" +
                                "FROM `order` WHERE order_cusid =" + obLogin.idCus + ");";

                        System.out.println ( "SPSP2" + obLogin.idCus );

                        Statement mStmt = conn.createStatement ();
                        System.out.println ( "SPSP211" );
                        ResultSet rs1 = mStmt.executeQuery ( SQL1 );

                        List rowValues1 = new ArrayList ();


                        System.out.println ( "SPSP3" );

                        while (rs1.next ()) {


                            rowValues1.add ( rs1.getInt ( "order_status" ) );

                            idOrder = rs1.getInt ( "order_service_id" );
                            getStatus = rs1.getInt ( "order_status" );

                        }
                        System.out.println ( "SPSP12 :1:" + obLogin.idCus );
                        System.out.println ( "SPSP123 :1:" + getStatus );
                        /////////////////////////////////////////////////////////////////////////
                            startActivity ( new Intent ( MainActivity.this, NoRepair.class ) );

////                    กรณีมีคำสั่งซ่อมรอทำ-
//                        if (getStatus == 0 || getStatus == 4 || getStatus == 3) {
//                            //สถานะ0 หรือ 4 แสดง หน้า ไม่มีการแจ้งซ่อม *เพราะ4คือสถานะซ่อมเสร็จ *เพราะ5คือสถานะยกเลิกซ่อม
//
//                            startActivity ( new Intent ( MainActivity.this, NoRepair.class ) );
//
//
//                            //กรณีซ่อมเสร็จแล้ว
//                        } else if (getStatus == 1) {
//                            //สถานะ1 แสดง หน้า WaitRepair
//
//                            startActivity ( new Intent ( MainActivity.this, WaitRepair.class ) );
//
//                        } else if (getStatus == 2) {
//                            //สถานะ2 แสดง หน้า confrim_Order
//
////                            Intent intent = new Intent(MainActivity.this, Traveling.class);
////                            intent.putExtra("MyValue", "จ่ายงานเรียบร้อย\n  กรุณารอสักครู่");
////                            startActivity(intent);
//                            startActivity ( new Intent ( MainActivity.this, Traveling.class ) );
//
//                        }
//                        else if (getStatus == 3) {
//                            //สถานะ2 แสดง หน้า ยืนยันการซ่อม เมื่อเสร็จแล้ว
//
//
//                            startActivity ( new Intent ( MainActivity.this, WaitRepair.class ) );
//
//                        }
                        ///////////////////////////////////////////////////////////////


                    } catch (Exception e) {

                    }


//                startActivity(new Intent (MainActivity.this, Confrim_Repair.class));
//
//                Toast.makeText ( getApplicationContext (),"ดูสถานะการซ่อม", Toast.LENGTH_SHORT).show () ;
//                if (iz == 1){
//                    startActivity(new Intent (MainActivity.this, WaitRepair.class));
//
//                }else if (iz == 2){
//                    startActivity(new Intent (MainActivity.this, Confrim_Repair.class));
//
//                }
                    break;
                    // ดูข้อมูลส่วนตัว
                case R.id.btnLinearSeeProfile:
                    startActivity(new Intent (MainActivity.this, Profile.class));

                    break;
                    // วิธีดูเบอร์ยาง
                case R.id.viewRubber:
                    startActivity(new Intent (MainActivity.this, ViewRubber.class));

                    break;
            }


        } else {
            Toast.makeText ( getApplicationContext (), "อยู่ข้างนอกนะ", Toast.LENGTH_SHORT ).show ();

        }


    }
}
