package com.example.nirvana.projectrmt15_1_62;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Confrim_Repair1 extends AppCompatActivity {

    Repair obRepair;
    TextView txtRubberfont;
    TextView txtRubberback;
    TextView txtService;
    TextView txtTravel;
    TextView sumprice;
    Button btnOk;
    Button btnCancle;

    int productUse =0;
    String pricefont = "";
    String priceback = "";


    MainActivity obMainActivity;


    String useProduct = "";
    String useProduct1 = "";

    int idpartfont = 0;
    int idpartback = 0;

    String getTxtRubFont;
    String getTxtRubBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_confrim__repair1 );
        init ();
        findDataPutText();


    }

    void init (){
        txtRubberfont = (TextView) findViewById ( R.id.priceRubberfont );
        txtRubberback = (TextView) findViewById ( R.id.priceRubberback );
        txtService = (TextView) findViewById ( R.id.priceService );
        txtTravel = (TextView) findViewById ( R.id.priceTravel );
        sumprice = (TextView) findViewById ( R.id.sumprice );

        btnOk = (Button) findViewById ( R.id.button1 );
        btnCancle = (Button)findViewById ( R.id.btnCancle );

        btnCancle.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                try {

                    Connection conn = ConnectDb.getConnection (); //Connection Object
                    Statement mStmt1 = conn.createStatement ();


                    String sql = "UPDATE `order` SET `order_status` = '4' WHERE `order`.`order_id` = '"+Insertdatacar.idrecord+"'";

                    mStmt1.executeUpdate ( sql );


                    Toast.makeText(getApplicationContext(), "ยกเลิกการแจ้งซ่อมเรียบร้อย", Toast.LENGTH_SHORT).show();
                    startActivity ( new Intent ( Confrim_Repair1.this, MainActivity.class ) );



                    mStmt1.close ();

                }catch (Exception e){

                }
            }
        } );

        btnOk.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (txtRubberfont.getText ().length () > 1) {
                    try {
                        System.out.println ("SHOWOWOWALS");
                        Connection conn = ConnectDb.getConnection (); //Connection Object


                        Statement mStmt1 = conn.createStatement ();
//                        Statement mStmt2 = conn.createStatement ();
                        Statement mStmt3 = conn.createStatement ();



                        String SqIn = "INSERT INTO `detail_order` (`detail_order_id`, `detail_order_product`, `detail_order_price`, `order_id`)" +
                                " VALUES (NULL, '" + Repair.nameRubberfont + "', '" + txtRubberfont.getText ().toString () + "', '" + Insertdatacar.idrecord + "');";



                        String sqlUp = "UPDATE ex_software_datail AS exsfw  SET exsfw.part_d_sec_use = exsfw.part_d_sec_use -1\n" +
                                "WHERE exsfw.part_d_id =\n" +
                                "(SELECT sfwd.part_d_id \n" +
                                "FROM (SELECT * FROM ex_software_datail) AS sfwd\n" +
                                "INNER JOIN ex_software AS sfw\n" +
                                "ON sfwd.part_id = sfw.sw_id\n" +
                                "WHERE sfwd.part_d_sec_use > 0\n" +
                                "AND  sfw.sw_name = "+Repair.nameRubberfont+"\n" +
                                "AND sfwd.part_d_depname = "+Login.IdDistrict+" \n" +
                                "ORDER BY \n" +
                                "sfwd.part_d_create ASC\n" +
                                "limit 1)";
                        System.out.println ("opopopopasdwa");

                        mStmt3.executeUpdate ( sqlUp );

                        mStmt1.executeUpdate ( SqIn );
                        ResultSet rs=mStmt1.getGeneratedKeys();

                        List rowValues1 = new ArrayList ();
                        if (rs.next ()){

                            rowValues1.add ( rs.getInt(1) );
                        }

                        if (rowValues1.size () >=1){
                            Toast.makeText(getApplicationContext(), "การแจ้งซ่อมเสร็จสิ้น", Toast.LENGTH_SHORT).show();
                            startActivity ( new Intent ( Confrim_Repair1.this, MainActivity.class ) );


                        }else if(rowValues1.size () <=0){
                            Toast.makeText(getApplicationContext(), "มีขอผิดพลาดกรุณาติดต่อ 0836600711", Toast.LENGTH_SHORT).show();

                        }




                        mStmt1.close ();
//                        mStmt2.close ();
                        mStmt3.close ();


                    } catch (Exception e) {

                    }
                }


                if (txtRubberback.getText ().length () > 1) {
                    System.out.println ("SHOWOWOWALS1");
                    try {
                        System.out.println ("SHOWOWOWALS");
                        Connection conn = ConnectDb.getConnection (); //Connection Object


                        Statement mStmt1 = conn.createStatement ();
//                        Statement mStmt2 = conn.createStatement ();
                        Statement mStmt3 = conn.createStatement ();



                        String SqIn = "INSERT INTO `detail_order` (`detail_order_id`, `detail_order_product`, `detail_order_price`, `order_id`)" +
                                " VALUES (NULL, '" + Repair.nameRubberback + "', '" + txtRubberfont.getText ().toString () + "', '" + Insertdatacar.idrecord + "');";



                        String sqlUp = "UPDATE ex_software_datail AS exsfw  SET exsfw.part_d_sec_use = exsfw.part_d_sec_use -1\n" +
                                "WHERE exsfw.part_d_id =\n" +
                                "(SELECT sfwd.part_d_id \n" +
                                "FROM (SELECT * FROM ex_software_datail) AS sfwd\n" +
                                "INNER JOIN ex_software AS sfw\n" +
                                "ON sfwd.part_id = sfw.sw_id\n" +
                                "WHERE sfwd.part_d_sec_use > 0\n" +
                                "AND  sfw.sw_name = "+Repair.nameRubberback+"\n" +
                                "AND sfwd.part_d_depname = "+Login.IdDistrict+" \n" +
                                "ORDER BY \n" +
                                "sfwd.part_d_create ASC\n" +
                                "limit 1)";
                        System.out.println ("opopopopasdwa");

                        mStmt3.executeUpdate ( sqlUp );
                        mStmt1.executeUpdate ( SqIn );
                        ResultSet rs=mStmt1.getGeneratedKeys();

                        List rowValues1 = new ArrayList ();
                        if (rs.next ()){

                            rowValues1.add ( rs.getInt(1) );
                        }

//                        if (rowValues1.size () >=1){
//                            Toast.makeText(getApplicationContext(), "การแจ้งซ่อมเสร็จสิ้น", Toast.LENGTH_SHORT).show();
//                            startActivity ( new Intent ( Confrim_Repair1.this, WaitRepair.class ) );
//
//
//                        }else if(rowValues1.size () <=0){
//                            Toast.makeText(getApplicationContext(), "มีขอผิดพลาดกรุณาติดต่อ 0836600711", Toast.LENGTH_SHORT).show();
//
//                        }

                        mStmt1.close ();
//                        mStmt2.close ();
                        mStmt3.close ();



                    } catch (Exception e) {

                    }
                }
            }
        } );

    }


    //เอาข้อมูลมาใส่TextView มาหน้า Confrim
    void findDataPutText(){
        System.out.println ("onnnnnnnnnnn"+obRepair.selectRubberFont+"A123:A"+obRepair.selectRubberBack);
        //ในกรณีล้อตรงกัน
        if (Repair.nameRubberfont.equals ( Repair.nameRubberback )){
            System.out.println ("RUBBER ==");
            //qury สินค้ามาดูก่อนว่ามีกี่อัน
            try{
                Connection conn = ConnectDb.getConnection (); //Connection Object


                    Statement mStmt1 = conn.createStatement ();

                    String sql ="SELECT swd.part_d_id , swd.part_id, swd.part_d_sec_use,de.dep_name , swd.part_d_create   FROM `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                            " WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id   and de.dep_id ="+Login.IdDistrict+" and  swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberfont+" and\n" +
                            "part_d_create = (SELECT MIN(part_d_create) FROM  `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                            "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id and de.dep_id ="+Login.IdDistrict+" and swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberfont+" and swd.part_d_sec_use >0 )";

                    ResultSet rs1 = mStmt1.executeQuery ( sql );

                    List rowValues1 = new ArrayList ();


                    while (rs1.next ()) {

                        rowValues1.add ( rs1.getString ( "part_d_sec_use" ) );
                        productUse= Integer.parseInt ( rs1.getString ( "part_d_sec_use" ) );

                    }
                    rowValues1.clear ();
                    rs1.close ();
                    mStmt1.close ();
                System.out.println ("onnnnnnnnnnnss"+productUse);

            }catch (Exception e){

            }



            //
            if (productUse == 1){
                System.out.println ("SHOWINNNNNNNN");

                try{
                    Connection conn = ConnectDb.getConnection (); //Connection Object



                    Statement mStmt1 = conn.createStatement ();
                    Statement mStmt2 = conn.createStatement ();


                    //=1  swd.part_d_sec_use,swd.part_d_price

                    String sql ="SELECT swd.part_d_id , swd.part_id, swd.part_d_sec_use,swd.part_d_price,de.dep_name , swd.part_d_create   FROM `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                            " WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id   and de.dep_id ="+Login.IdDistrict+" and  swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberfont+" and\n" +
                            "part_d_create = (SELECT MIN(part_d_create) FROM  `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                            "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id and de.dep_id ="+Login.IdDistrict+" and swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberfont+" and swd.part_d_sec_use = 1 )";


                    //>1

                    String sql1 ="SELECT swd.part_d_id , swd.part_id, swd.part_d_sec_use,swd.part_d_price,swd.part_d_price,de.dep_name , swd.part_d_create   FROM `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                            " WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id   and de.dep_id ="+Login.IdDistrict+" and  swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberback+" and\n" +
                            "part_d_create = (SELECT MIN(part_d_create) FROM  `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                            "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id and de.dep_id ="+Login.IdDistrict+" and swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberback+" and swd.part_d_sec_use > 1 )";


                    ResultSet rs1 = mStmt1.executeQuery ( sql );
                    ResultSet rs2 = mStmt2.executeQuery ( sql1 );

                    List rowValues1 = new ArrayList ();





                    while (rs1.next ()) {
                        pricefont=rs1.getString ( "part_d_price" );
//                        rowValues1.add ( rs1.getString ( "part_d_price" ) );
                        System.out.println ("SHOWDD1"+rowValues1.add ( rs1.getString ( "part_d_price" ) ));
                        System.out.println ("SHOWDD1"+pricefont);


                    }
                    while (rs2.next ()) {
                        priceback=rs2.getString ( "part_d_price" );

//                        rowValues1.add ( rs1.getString ( "part_d_price" ) );
                        System.out.println ("SHOWDD2"+rowValues1.add ( rs2.getString ( "part_d_price" ) ));
                        System.out.println ("SHOWDD2"+priceback);


                    }


                    txtRubberfont.setText ( pricefont);
                    txtRubberback.setText ( priceback);
                    txtService.setText ( "50" );
                    txtTravel.setText ( "50");
                    sumPrice();


//                     int a = Integer.parseInt ( txtRubberfont.toString () );
//                    int b= Integer.parseInt ( txtRubberback.toString () );
//                    int c= Integer.parseInt ( txtService.toString () );
//                    int d= Integer.parseInt ( txtTravel.toString () );
//
//                    int sum =a+b+c+d;
//                    String sum1 = String.valueOf ( sum );
//                    sumprice.setText ( sum1);

                    rowValues1.clear ();
                    rs1.close ();
                    rs2.close ();
                    mStmt1.close ();
                    mStmt2.close ();

                }catch (Exception e){
System.out.println ("SHOWWOWO"+e.getMessage ());
                }

            }else if(productUse > 1){
                System.out.println ("product >1"+productUse);
                try{
                    Connection conn = ConnectDb.getConnection (); //Connection Object


                    Statement mStmt1 = conn.createStatement ();
                    Statement mStmt2 = conn.createStatement ();

                    String sql ="SELECT swd.part_d_id , swd.part_id, swd.part_d_sec_use,swd.part_d_price,de.dep_name , swd.part_d_create   FROM `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                            " WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id   and de.dep_id ="+Login.IdDistrict+" and  swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberfont+" and\n" +
                            "part_d_create = (SELECT MIN(part_d_create) FROM  `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                            "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id and de.dep_id ="+Login.IdDistrict+" and swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberfont+" and swd.part_d_sec_use > 1 )";


                    //>1

                    String sql1 ="SELECT swd.part_d_id , swd.part_id, swd.part_d_sec_use,swd.part_d_price,swd.part_d_price,de.dep_name , swd.part_d_create   FROM `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                            " WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id   and de.dep_id ="+Login.IdDistrict+" and  swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberback+" and\n" +
                            "part_d_create = (SELECT MIN(part_d_create) FROM  `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                            "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id and de.dep_id ="+Login.IdDistrict+" and swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberback+" and swd.part_d_sec_use > 1 )";


                    ResultSet rs1 = mStmt1.executeQuery ( sql );
                    ResultSet rs2 = mStmt2.executeQuery ( sql1 );

                    List rowValues1 = new ArrayList ();


                    while (rs1.next ()) {
                        pricefont=rs1.getString ( "part_d_price" );


                    }
                    while (rs2.next ()) {
                        priceback=rs2.getString ( "part_d_price" );



                    }
                    txtRubberfont.setText ( pricefont);
                    txtRubberback.setText ( priceback);
                    txtService.setText ( "50" );
                    txtTravel.setText ( "50" );
                    sumPrice();


//                    int a = Integer.parseInt ( txtRubberfont.toString () );
//                    int b= Integer.parseInt ( txtRubberback.toString () );
//                    int c= Integer.parseInt ( txtService.toString () );
//                    int d= Integer.parseInt ( txtTravel.toString () );
//
//                    int sum =a+b+c+d;
//                    String sum1 = String.valueOf ( sum );
//                    sumprice.setText ( sum1 );
                    sumPrice();

                    rowValues1.clear ();
                    rs1.close ();
                    rs2.close ();
                    mStmt1.close ();
                    mStmt2.close ();


                }catch (Exception e){

                }
            }



            //Else if นี้ ในเงื่อนไขล้อหน้าและล้อหลังไม่ตรงกัน

        }else if(Repair.nameRubberfont != Repair.nameRubberback || Repair.nameRubberback != Repair.nameRubberfont ||Repair.nameRubberfont==null || Repair.nameRubberback == null ){
            try{
                Connection conn = ConnectDb.getConnection (); //Connection Object

                System.out.println ("kokokooooo"+Repair.nameRubberback);


                Statement mStmt1 = conn.createStatement ();
                Statement mStmt2 = conn.createStatement ();

                String sql ="SELECT swd.part_d_id , swd.part_id, swd.part_d_sec_use,swd.part_d_price,de.dep_name , swd.part_d_create   FROM `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                        " WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id   and de.dep_id ="+Login.IdDistrict+" and  swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberfont+" and\n" +
                        "part_d_create = (SELECT MIN(part_d_create) FROM  `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                        "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id and de.dep_id ="+Login.IdDistrict+" and swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberfont+" and swd.part_d_sec_use >= 1 )";


                //>1

                String sql1 ="SELECT swd.part_d_id , swd.part_id, swd.part_d_sec_use,swd.part_d_price,swd.part_d_price,de.dep_name , swd.part_d_create   FROM `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                        " WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id   and de.dep_id ="+Login.IdDistrict+" and  swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberback+" and\n" +
                        "part_d_create = (SELECT MIN(part_d_create) FROM  `ex_software_datail` as swd,ex_department as de,ex_user as userr ,ex_software as sw\n" +
                        "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id and de.dep_id ="+Login.IdDistrict+" and swd.part_id = sw.sw_id and sw.sw_name ="+Repair.nameRubberback+" and swd.part_d_sec_use >= 1 )";


                ResultSet rs1 = mStmt1.executeQuery ( sql );
                ResultSet rs2 = mStmt2.executeQuery ( sql1 );

                List rowValues1 = new ArrayList ();


                while (rs1.next ()) {
                    pricefont=rs1.getString ( "part_d_price" );


                }
                while (rs2.next ()) {
                    priceback=rs2.getString ( "part_d_price" );



                }
                txtRubberfont.setText ( pricefont);
                txtRubberback.setText ( priceback);
                System.out.println ("kokokooooo"+priceback);
                txtService.setText ( "50" );
                txtTravel.setText ( "50");
                sumPrice();

//                int a = Integer.parseInt ( txtRubberfont.toString () );
//                int b= Integer.parseInt ( txtRubberback.toString () );
//                int c= Integer.parseInt ( txtService.toString () );
//                int d= Integer.parseInt ( txtTravel.toString () );
//
//                int sum =a+b+c+d;
//                  String sum1 = String.valueOf ( sum );
//
//                sumprice.setText ( sum1 );

                rowValues1.clear ();
                rs1.close ();
                rs2.close ();
                mStmt1.close ();
                mStmt2.close ();


            }catch (Exception e){

            }
        }

    }
        void sumPrice() {

        System.out.println ("dasvvvzzzzzz");
        int font;
        int back1;
        int service;
        int travel;

        int sum;
        if (txtRubberfont.getText ().toString () == ""){
            font=0;
        }else {
            font = Integer.parseInt ( txtRubberfont.getText ().toString () );
        }
        if (txtRubberback.getText ().toString () == ""){
            back1=0;
        }else {
            back1 = Integer.parseInt ( txtRubberback.getText ().toString () );
        }


        service = Integer.parseInt ( txtService.getText ().toString () );
        travel = Integer.parseInt ( txtTravel.getText ().toString () );
            System.out.println ("dasvvvzzzzzz"+font);

        sum = font + back1 + service + travel;

        String sum1 = String.valueOf ( sum );
            System.out.println ("1255SSSS"+sum1);
        sumprice.setText ( sum1 );


    }
}
