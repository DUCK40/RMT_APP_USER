package com.example.nirvana.projectrmt15_1_62;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class FragmentMenu1 extends Fragment {

    TextView txtNamefood;

    MainActivity obMain;
    Login obLogin;
    NoRepair ob;


    int statusOrder;



    String StatusName;
    String TechName;
    String TechTel;
    String Deparment;
    int priceB;
    int priceF;
    List rowValues1 = new ArrayList ();
    List rowValues2 = new ArrayList ();
    int priceAll;


    TextView txtStatusOrder;
    TextView txtRubberF;
    TextView txtRubberB;
    TextView txtPriceF;
    TextView txtPriceB;
    TextView txtPriceAll;
    TextView txtNameTech;
    TextView txtTalTech;
    TextView txtDeparment;
    Button btnBack;

    DecimalFormat df = new DecimalFormat("0.00");

    public FragmentMenu1() {
        ob = new NoRepair ();
        obMain = new MainActivity ();
        System.out.println ( "sdadas" );



        try {
            Connection conn = ConnectDb.getConnection (); //Connection Object


            String SQL1 = "SELECT * FROM`order`\n" +
                    "WHERE order_id= (SELECT MAX(order_id)\n" +
                    "FROM `order` WHERE order_cusid =" + obLogin.idCus + ");";

            Statement mStmt = conn.createStatement ();
            ResultSet rs = mStmt.executeQuery ( SQL1 );

            while (rs.next ()) {
                statusOrder = rs.getInt ( "order_status" );

            }

            System.out.println ( "sdadas1"+statusOrder );

        }catch (Exception e){

        }

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int a = obMain.statusRepair;
        View view1 = null;
        System.out.println ("SHOWWOWOWOWO"+statusOrder);
        if (statusOrder == 0 || statusOrder == 4 || statusOrder == 3){
            //กรณีที่ยังไม่มีการแจ้งซ่อม
            View rootView = inflater.inflate(R.layout.activity_wait_repair, container,false);
            txtNamefood = (Button) rootView.findViewById ( R.id.btnback );
            txtNamefood.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent (getActivity (), MainActivity.class));

                }
            } );
            view1 = rootView;
            return rootView;
        }else if (statusOrder == 1 || statusOrder == 2){

System.out.println ( "HOHWHWH" );
//            NumberFormat numberFormat = NumberFormat.getInstance ( );
//
            View rootView = inflater.inflate(R.layout.detail_repair, container,false);
            txtStatusOrder = (TextView) rootView.findViewById ( R.id.txtstatus );
            txtRubberF = (TextView) rootView.findViewById ( R.id.txtRubberF );
            txtRubberB = (TextView) rootView.findViewById ( R.id.txtRubberB );
            txtPriceF = (TextView) rootView.findViewById ( R.id.txtPriceF );
            txtPriceB = (TextView) rootView.findViewById ( R.id.txtPriceB );
            txtPriceAll = (TextView) rootView.findViewById ( R.id.txtPriceAll );
            txtNameTech = (TextView) rootView.findViewById ( R.id.txtNameTech );
            txtTalTech = (TextView) rootView.findViewById ( R.id.txtTelTech );
            txtDeparment = (TextView) rootView.findViewById ( R.id.txtDepartment );
            btnBack = (Button) rootView.findViewById ( R.id.btnBack );

            btnBack.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent (getActivity (), MainActivity.class));

                }
            } );




//            DecimalFormat df = new DecimalFormat("0.00");
//           String oo = df.format ( data1 );


//            txtStatusOrder.setText ( new DecimalFormat("0.00").format(6.1115) );
//            txtRubberF.setText (df.format ( 615.5632 ));
//            txtRubberB.setText (data );
//            txtPriceF.setText (data );
//            txtPriceB.setText ( data );
//            txtPriceAll.setText (data );
//            txtNameTech.setText ( data );
//            txtTalTech.setText ( data );
//            txtDeparment.setText ( data );

            opop();

            view1 = rootView;
            return rootView;
        }
//        View rootView = inflater.inflate(R.layout.fragment_fragment_menu1, container,false);



        //        txtNamefood = (TextView) rootView.findViewById ( R.id.txtNamefood );


//        txtNamefood.setText ( nameFood );
        return view1;
        
    }

    void opop() {


        try {



            String orderId = null;
//            DecimalFormat df = new DecimalFormat ( "0.00" );
            DecimalFormat df1 = new DecimalFormat ( "0.00" );
            Connection conn = ConnectDb.getConnection (); //Connection Object
            System.out.println ( "shoiwn"+obLogin.idCus );

            String SQL = "SELECT orderss.order_id,tech.tn_name,de.dep_name,st.name,orderss.dateservice,orderss.order_user_id,tech.tn_name,tech.tn_phone \n" +
                    "FROM `order` AS orderss \n" +
                    "LEFT JOIN ex_technician AS tech\n" +
                    "ON orderss.order_user_id=tech.tn_id \n" +
                    "INNER JOIN  ex_department as de\n" +
                    "ON  de.dep_id = orderss.order_depart\n" +
                    "INNER JOIN  ex_status as st\n" +
                    "ON  st.id=orderss.order_status\n" +
                    "INNER JOIN  ex_customer as cus\n" +
                    "ON  orderss.order_cusid = cus.ct_id\n" +
                    "WHERE orderss.order_cusid = '" + obLogin.idCus + "' and  orderss.order_status = 2";

            Statement stmt1 = conn.createStatement ();
            ResultSet rs1 = stmt1.executeQuery ( SQL );
            while (rs1.next ()) {
                orderId = rs1.getString ( "order_id" );
                StatusName = rs1.getString ( "name" );
                TechName = rs1.getString ( "tn_name" );
                TechTel = rs1.getString ( "tn_phone" );
                Deparment = rs1.getString ( "dep_name" );

            }
            System.out.println ( "ppopoooooo"+orderId );
            rs1.close ();


            String SQL1 = "SELECT dto.order_id,dto.detail_order_product,dto.detail_order_price,ord.dateservice\n" +
                    "FROM `detail_order` as dto ,`order` as ord , ex_department as de\n" +
                    "WHERE dto.order_id = ord.order_id and ord.order_depart=de.dep_id and ord.order_id = '53'\n" +
                    "GROUP by dto.detail_order_id";

            Statement stmt2 = conn.createStatement ();
            ResultSet rs2 = stmt2.executeQuery ( SQL1 );
//            List rowValues1 = new ArrayList ();
//            List rowValues2 = new ArrayList ();
            int i =0;

            while (rs2.next ()) {
                rowValues1.add ( rs2.getString ( "detail_order_product" ) );
                rowValues2.add ( rs2.getString ( "detail_order_price" ) );


            }
            System.out.println ( "shhshshhw1"+rowValues1.get ( 0 ).toString () );
            System.out.println ( "shhshshhw2"+rowValues2.get ( 0 ).toString () );

            rs2.close ();
            conn.close ();
//            priceB = (int) rowValues2.get ( 1 );
//            priceF = (int) rowValues2.get ( 0 );
//            priceB = (int) rowValues2.get ( 1 );
//            priceB = 5555;
//            System.out.println ("sadpppa21"+priceB);

//            txtRubberF.setText ( (String) rowValues1.get ( 0 ) );
//            txtRubberB.setText ( (String) rowValues1.get ( 1 ) );
//            txtPriceF.setText ( (String) rowValues2.get ( 0 ) );
////            txtPriceF.setText ( strRubPriceF );
//
//            txtPriceB.setText ( (String) rowValues2.get ( 1 ) );
            /////////////////////////////////////////////////////////////////////////////////
//            String a1;
//            String a2;
//
//
//            a1 = txtPriceF.getText ().toString ();
//            a2 = txtPriceB.getText ().toString ();
//
//            System.out.println ( "ppppppppppppppppppppppppppppppp111:" );
//
////            String Sum1 = df1.format ( aaa );
//
////            System.out.println ("ppppppopopopo"+Sum1);
//
//            int aa;
//            int bb;
//            int sum;
//
//
//            aa = Integer.parseInt ( a1 );
//            bb = Integer.parseInt ( a2 );
//
//            sum = aa + bb;
//
//            System.out.println ( "ppppppppppppppppppppppppppppppp11:" + sum );
//            String oo = df.format ( sum );
//            System.out.println ( "ppppppppppppppppppppppppppppppp" + oo );
//
//
//            txtPriceAll.setText ( oo );

            ////////////////////////////////////////////////////////////////////////////////


        } catch (Exception e) {

        }

        int Pf = Integer.parseInt ( (rowValues2.get ( 0 ).toString () ) );
        int Pb = Integer.parseInt ( (rowValues2.get ( 1 ).toString () ) );

//        int Pf=0 ;
//        int Pb =0;

        int sum = Pb+Pf;

//        int fff = (int) rowValues2.get ( 0 );
//        df.format ( fff );
        String sucessPriA = String.valueOf ( df.format ( sum ) );
        String sucessPriF = String.valueOf ( df.format ( Pf ) );
        String sucessPriB = String.valueOf ( df.format ( Pb ) );
        System.out.println ( "SUMF"+ Pf );
        System.out.println ( "SUMB"+Pb );
        System.out.println ( "SUMF"+sum );



//        System.out.println ("sadpppa21"+rowValues2.get ( 0 ));
        txtStatusOrder.setText ( StatusName );
        txtPriceAll.setText ( sucessPriA );
        txtNameTech.setText ( TechName );
        txtTalTech.setText ( TechTel );
        txtDeparment.setText ( Deparment );
        txtPriceF.setText (sucessPriF);
        txtPriceB.setText (sucessPriB);

        txtRubberF.setText ( (String) rowValues1.get ( 0 ).toString () );
        txtRubberB.setText ( (String) rowValues1.get ( 1 ).toString () );


    }


}
