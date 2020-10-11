package com.example.nirvana.projectrmt15_1_62;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Confrim_Repair extends AppCompatActivity {

    Repair obRepair;
    TextView txtRubberfont;
    TextView txtRubberback;
    TextView txtService;
    TextView txtTravel;
    TextView sumprice;
    Button btnOk;
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
        setContentView ( R.layout.activity_confrim__repair );
        obRepair = new Repair ();

        init ();
        btnOk.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                //                //เงื่อนไข ตรวจสอบว่าเลือกยางหน้าไหม ถ้าไม่ใช่ให้ txtRubberfont
                if (Repair.selectRubberFont >= 1) {
                    try {
                        Connection conn = ConnectDb.getConnection (); //Connection Object


                        Statement mStmt1 = conn.createStatement ();


                        String SqIn = "INSERT INTO `detail_order` (`detail_order_id`, `detail_order_product`, `detail_order_price`, `order_id`)" +
                                " VALUES (NULL, '" + Repair.nameRubberfont + "', '" + getTxtRubFont + "', '" + Insertdatacar.idrecord + "');";




                        System.out.println ("Login.IdDistrict1"+Login.IdDistrict);
                        System.out.println ("Login.IdDistrict2"+ Repair.selectRubberFont);

                        String sql ="SELECT swd.part_d_id , swd.part_id, swd.part_d_sec_use,de.dep_name , swd.part_d_create   FROM `ex_software_datail` as swd,ex_department as de,ex_user as userr \n" +
                                "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id   and de.dep_id = "+Login.IdDistrict+" and swd.part_id ="+Repair.selectRubberFont+" and \n" +
                                "part_d_create = (SELECT MIN(part_d_create) FROM  `ex_software_datail` as swd,ex_department as de,ex_user as userr \n" +
                                "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id and de.dep_id = "+Login.IdDistrict+" and swd.part_id =" + Repair.selectRubberFont+ " and swd.part_d_sec_use >0)";


                        ResultSet rs1 = mStmt1.executeQuery ( sql );

                        List rowValues1 = new ArrayList ();
                        int resultsum=0;



                        while (rs1.next ()) {


                            rowValues1.add ( rs1.getString ( "part_d_sec_use" ) );
                            resultsum= Integer.parseInt ( rs1.getString ( "part_d_sec_use" ) );


                        }
                        int sum =resultsum-1;
                        System.out.println ("SHOWWW USERs"+sum);

                        String sqlUp = "UPDATE `ex_software_datail`INNER JOIN ( ex_department,ex_user) ON ex_user.dep_id = ex_department.dep_id and ex_software_datail.part_d_userid = ex_user.user_id set ex_software_datail.part_d_sec_use = "+sum+" \n" +
                                "WHERE  ex_department.dep_id = "+Login.IdDistrict+" and ex_software_datail.part_id ="+Repair.selectRubberFont+"";


                        mStmt1.executeUpdate ( SqIn );
                        mStmt1.executeUpdate ( sqlUp );


                        mStmt1.close ();


                    } catch (Exception e) {

                    }
                }


                if (Repair.selectRubberBack >= 1) {
                    try {
                        Connection conn = ConnectDb.getConnection (); //Connection Object


                        Statement mStmt1 = conn.createStatement ();
//


                        String SqIn = "INSERT INTO `detail_order` (`detail_order_id`, `detail_order_product`, `detail_order_price`, `order_id`)\n " +
                                "VALUES (NULL, '" + Repair.nameRubberback + "', '" + getTxtRubBack + "', '" + Insertdatacar.idrecord + "');";

                        String sql ="SELECT swd.part_d_id , swd.part_id, swd.part_d_sec_use,de.dep_name , swd.part_d_create   FROM `ex_software_datail` as swd,ex_department as de,ex_user as userr \n" +
                                "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id   and de.dep_id = "+Login.IdDistrict+" and swd.part_id ="+Repair.selectRubberBack+" and \n" +
                                "part_d_create = (SELECT MIN(part_d_create) FROM  `ex_software_datail` as swd,ex_department as de,ex_user as userr \n" +
                                "WHERE swd.part_d_userid = userr.user_id and  userr.dep_id=de.dep_id and de.dep_id = "+Login.IdDistrict+" and swd.part_id =" + Repair.selectRubberBack+ " and swd.part_d_sec_use >0 )";


                        ResultSet rs1 = mStmt1.executeQuery ( sql );

                        List rowValues1 = new ArrayList ();
                        int resultsum=0;



                        while (rs1.next ()) {


                            rowValues1.add ( rs1.getString ( "part_d_sec_use" ) );
                            resultsum= Integer.parseInt ( rs1.getString ( "part_d_sec_use" ) );


                        }
                        int sum =resultsum-1;
                        System.out.println ("SHOWWW USERs"+sum);

                        String sqlUp = "UPDATE `ex_software_datail`INNER JOIN ( ex_department,ex_user) ON ex_user.dep_id = ex_department.dep_id and ex_software_datail.part_d_userid = ex_user.user_id set ex_software_datail.part_d_sec_use = "+sum+" \n" +
                                "WHERE  ex_department.dep_id = "+Login.IdDistrict+" and ex_software_datail.part_id ="+Repair.selectRubberBack+"";




                        mStmt1.executeUpdate ( SqIn );
                        mStmt1.executeUpdate ( sqlUp );



                        mStmt1.close ();

                    } catch (Exception e) {

                    }
                }

                //ใส่Toast ว่ายืนยันการแจ้งซ่อม
                    Toast.makeText(getApplicationContext(), "ทำการยืนยันการซ่อมเรียบร้อย", Toast.LENGTH_SHORT).show();
                    startActivity ( new Intent ( Confrim_Repair.this, MainActivity.class ) );

            }
        } );


    }

    void init() {
        txtRubberfont = (TextView) findViewById ( R.id.priceRubberfont );
        txtRubberback = (TextView) findViewById ( R.id.priceRubberback );
        txtService = (TextView) findViewById ( R.id.priceService );
        txtTravel = (TextView) findViewById ( R.id.priceTravel );
        sumprice = (TextView) findViewById ( R.id.sumprice );

        btnOk = (Button) findViewById ( R.id.button1 );
        obMainActivity = new MainActivity ();
//        find ();
        rubberSelectFont ();
        System.out.println ( "SHOWOWOWO" + obMainActivity.idOrder );
//        sumPrice ();



    }

    void rubberSelectFont() {
        //  selectRubberFont ค่าที่เลือกมาจากspinner เพื่อทำการqury


        //เงื่อนไข ตรวจสอบว่าเลือกยางหน้าไหม ถ้าไม่ใช่ให้ txtRubberfont
        if (Repair.selectRubberFont >= 1) {
            try {
                Connection conn = ConnectDb.getConnection (); //Connection Object

                String SQL1 = "SELECT depart.dep_name,sfw.sw_name,SUM(sfwd.part_d_sec),sfwd.part_d_sec_use,sfwd.part_d_price\n" +
                        " FROM ex_software AS sfw,ex_software_datail AS sfwd,ex_user AS users,ex_department AS depart\n" +
                        " WHERE sfwd.part_id = '" + Repair.selectRubberFont + "' AND sfwd.part_d_userid=users.user_id\n" +
                        " AND users.dep_id=depart.dep_id and depart.dep_id ='" + Login.IdDistrict + "'\n" +
                        " GROUP BY depart.dep_id ,sfwd.part_id";


                Statement mStmt1 = conn.createStatement ();
//                mStmt1.execute ( SqIn );

                ResultSet rs1 = mStmt1.executeQuery ( SQL1 );

                List rowValues11 = new ArrayList ();


                while (rs1.next ()) {
                    txtRubberfont.setText ( rs1.getString ( "part_d_price" ) );
                    getTxtRubFont = rs1.getString ( "part_d_price" );


                }
                String SqIn = "INSERT INTO `detail_order` (`detail_order_id`, `detail_order_product`, `detail_order_price`, `order_id`)" +
                        " VALUES (NULL, '" + Repair.nameRubberfont + "', '" + getTxtRubFont + "', '" + Insertdatacar.idrecord + "');";
                mStmt1.executeUpdate ( SqIn );

                rs1.close ();
                mStmt1.close ();


            } catch (Exception e) {

            }
        } else if (Repair.selectRubberFont == 0) {
            txtRubberfont.setText ( "0" );
        }

        if (Repair.selectRubberBack >= 1) {
            try {
                Connection conn = ConnectDb.getConnection (); //Connection Object

                String SQL1 = "SELECT depart.dep_name,sfw.sw_name,SUM(sfwd.part_d_sec),sfwd.part_d_sec_use,sfwd.part_d_price \n" +
                        " FROM ex_software AS sfw,ex_software_datail AS sfwd,ex_user AS users,ex_department AS depart\n" +
                        " WHERE sfwd.part_id='" + Repair.selectRubberBack + "' AND sfwd.part_d_userid=users.user_id\n" +
                        " AND users.dep_id=depart.dep_id and depart.dep_id ='" + Login.IdDistrict + "'  and sfwd.part_d_sec_use >1 \n" +
                        " GROUP BY depart.dep_id ,sfwd.part_id";


                Statement mStmt1 = conn.createStatement ();
//                mStmt1.execute ( SqIn );

                ResultSet rs1 = mStmt1.executeQuery ( SQL1 );

                List rowValues11 = new ArrayList ();


                while (rs1.next ()) {
                    txtRubberback.setText ( rs1.getString ( "part_d_price" ) );
                    getTxtRubBack = rs1.getString ( "part_d_price" );

                }
                String SqIn = "INSERT INTO `detail_order` (`detail_order_id`, `detail_order_product`, `detail_order_price`, `order_id`)\n " +
                        "VALUES (NULL, '" + Repair.nameRubberback + "', '" + getTxtRubBack + "', '" + Insertdatacar.idrecord + "');";

//                mStmt1.executeUpdate ( SqIn );
                rs1.close ();
                mStmt1.close ();

            } catch (Exception e) {

            }
        } else if (Repair.selectRubberBack == 0) {
            txtRubberback.setText ( "0" );

        }
        txtService.setText ( "50" );
        txtTravel.setText ( "50" );


    }








//    void sumPrice() {
//        int font;
//        int back1;
//        int service;
//        int travel;
//
//        int sum;
//        font = Integer.parseInt ( txtRubberfont.getText ().toString () );
//        back1 = Integer.parseInt ( txtRubberback.getText ().toString () );
//        service = Integer.parseInt ( txtService.getText ().toString () );
//        travel = Integer.parseInt ( txtTravel.getText ().toString () );
//
//        sum = font + back1 + service + travel;
//        String sum1 = String.valueOf ( sum );
//        sumprice.setText ( sum1 );
//
//
//    }

    void find() {
        Boolean stater = null;


        try {
            Connection conn = ConnectDb.getConnection (); //Connection Object
            if (conn != null) {
                String SQL = "SELECT * FROM `service`\n" +
                        "WHERE service_id= (SELECT order_service_id\n" +
                        "FROM `order` WHERE order_id  = " + obMainActivity.idOrder + " )";


                Statement mStmt = conn.createStatement ();
                ResultSet rs = mStmt.executeQuery ( SQL );

                List rowValues1 = new ArrayList ();
                while (rs.next ()) {

                    rowValues1.add ( rs.getString ( "service_name" ) );
                }
                txtService.setText ( rowValues1.get ( 0 ).toString () );
                txtTravel.setText ( "50" );
//                rowValues1.clear ();
            }
//conn.close ();

        } catch (Exception e) {

        }


        try {

            System.out.println ( "llllllllooo" + obRepair.selectRubberFont );
            int rFont = obRepair.selectRubberFont;
            int rBack = obRepair.selectRubberBack;

            Connection conn = ConnectDb.getConnection (); //Connection Object
            if (conn != null) {


                if (rBack == rFont) {
                    int za = 0;
                    System.out.println ( "HELOOOOOOOOO" + rBack );
                    System.out.println ( "HELOOOOOOOOO" + rFont );

                    String SQL = "SELECT part_d_id, part_d_sec,part_d_sec_use,part_d_price, MIN(part_d_inproduct) \n" +
                            " FROM part_detail\n" +
                            " WHERE part_id =" + rFont + " AND part_d_sec_use = 1 and part_d_branchid = 1";


                    Statement mStmt = conn.createStatement ();
                    ResultSet rs = mStmt.executeQuery ( SQL );

                    List rowValues1 = new ArrayList ();


                    int saa = 0;


                    while (rs.next ()) {


                        rowValues1.add ( rs.getInt ( "part_d_sec_use" ) );
                        saa = rs.getInt ( "part_d_sec_use" );
                        za = rs.getInt ( "part_d_sec_use" );
                        System.out.println ( "HAHA!1za" + za );


                    }
                    System.out.println ( "HAHA!1za" + za );
                    if (za > 1) {
                        stater = true;
                        System.out.println ( "HAHA" + stater );
                    } else {
                        stater = false;
                        System.out.println ( "HAHA!" + stater );
                    }


///////////////////////////////////////////////////////////////////
                    if (stater == false) {
                        System.out.println ( "Create Show1" );


                        if (obRepair.stateRubberFont == true) {
                            String SQL1 = "SELECT part_d_id, part_d_sec,part_d_sec_use,part_d_price, MIN(part_d_inproduct) \n" +
                                    " FROM part_detail\n" +
                                    " WHERE part_id =" + rFont + " AND part_d_sec_use = 1 and part_d_branchid = 1";


                            Statement mStmt1 = conn.createStatement ();
                            ResultSet rs1 = mStmt1.executeQuery ( SQL1 );

                            List rowValues11 = new ArrayList ();
                            List rowValues12 = new ArrayList ();

                            int sa;


                            while (rs1.next ()) {

                                rowValues12.add ( rs1.getInt ( "part_d_id" ) );

                                rowValues11.add ( rs1.getString ( "part_d_price" ) );
                                sa = rs1.getInt ( "part_d_id" );
                                useProduct = rs1.getString ( "part_d_sec_use" );
                                idpartfont = rs1.getInt ( "part_d_id" );
                                if (sa <= 0) {
                                    txtRubberfont.setText ( "0" );
                                } else if (sa >= 1) {
                                    txtRubberfont.setText ( rs1.getString ( "part_d_price" ) );

                                }


                            }
                        } else if (obRepair.stateRubberFont == false) {
                            txtRubberfont.setText ( "0" );
                        }

                        //ในกณรีล้อทเา้กัน
                        ////////////////////////////////////////////////////////////////////////
                        if (obRepair.stateRubberBack == true) {
                            String SQL1 = "SELECT part_d_id, part_d_sec,part_d_sec_use,part_d_price, MIN(part_d_inproduct) \n" +
                                    " FROM part_detail\n" +
                                    " WHERE part_id =" + rFont + " AND part_d_sec_use > 1 and part_d_branchid = 1";


                            Statement mStmt1 = conn.createStatement ();
                            ResultSet rs1 = mStmt.executeQuery ( SQL1 );

                            List rowValues11 = new ArrayList ();
                            List rowValues12 = new ArrayList ();

                            int sa;


                            while (rs1.next ()) {

                                rowValues12.add ( rs1.getInt ( "part_d_id" ) );

                                rowValues11.add ( rs1.getString ( "part_d_price" ) );
                                sa = rs1.getInt ( "part_d_id" );
                                useProduct1 = rs1.getString ( "part_d_sec_use" );
                                idpartback = rs1.getInt ( "part_d_id" );
                                if (sa <= 0) {
                                    txtRubberback.setText ( "0" );
                                } else if (sa >= 1) {

                                    txtRubberback.setText ( rs1.getString ( "part_d_price" ) );
                                }


                            }
                        } else if (obRepair.stateRubberBack == false) {
                            txtRubberback.setText ( "0" );
                        }

                        ///////////////////////////////////////////////////////////////////////

                    }


                    /////////////////////////////////////////////////////


                } else if (rBack != rFont) {

                    if (true) {
                        System.out.println ( "Create Show" );
                        if (obRepair.stateRubberFont == true) {
                            String SQL1 = "SELECT part_d_id, part_d_sec,part_d_sec_use,part_d_price, MIN(part_d_create) \n" +
                                    " FROM part_detail \n" +
                                    " WHERE   part_id  = " + rFont + " AND part_d_sec_use >0  and part_d_branchid = 1";


                            Statement mStmt1 = conn.createStatement ();
                            ResultSet rs1 = mStmt1.executeQuery ( SQL1 );

                            List rowValues11 = new ArrayList ();
                            List rowValues12 = new ArrayList ();

                            int sa;


                            while (rs1.next ()) {

                                rowValues12.add ( rs1.getInt ( "part_d_id" ) );

                                rowValues11.add ( rs1.getString ( "part_d_price" ) );
                                sa = rs1.getInt ( "part_d_id" );
                                useProduct = rs1.getString ( "part_d_sec_use" );
                                idpartfont = rs1.getInt ( "part_d_id" );
                                if (sa <= 0) {
                                    txtRubberfont.setText ( "0" );
                                } else if (sa >= 1) {

                                    txtRubberfont.setText ( rs1.getString ( "part_d_price" ) );
                                }


                            }
                        } else if (obRepair.stateRubberFont == false) {
                            txtRubberfont.setText ( "0" );
                        }


/////////////////////////////////////////////////////////////////////////////
                        if (obRepair.stateRubberBack == true) {
                            String SQL1 = "SELECT part_d_id, part_d_sec,part_d_sec_use,part_d_price, MIN(part_d_create) \n" +
                                    " FROM part_detail \n" +
                                    " WHERE   part_id  = " + rBack + " AND part_d_sec_use >0  and part_d_branchid = 1";


                            Statement mStmt1 = conn.createStatement ();
                            ResultSet rs1 = mStmt1.executeQuery ( SQL1 );

                            List rowValues11 = new ArrayList ();
                            List rowValues12 = new ArrayList ();

                            int sa;


                            while (rs1.next ()) {

                                rowValues12.add ( rs1.getInt ( "part_d_id" ) );

                                rowValues11.add ( rs1.getString ( "part_d_price" ) );
                                sa = rs1.getInt ( "part_d_id" );
                                useProduct1 = rs1.getString ( "part_d_sec_use" );
                                idpartback = rs1.getInt ( "part_d_id" );
                                System.out.println ( "xoxoxoxo" + useProduct1 );
                                if (sa <= 0) {
                                    txtRubberback.setText ( "0" );
                                } else if (sa >= 1) {

                                    txtRubberback.setText ( rs1.getString ( "part_d_price" ) );
                                }


                            }
                        } else if (obRepair.stateRubberBack == false) {
                            txtRubberback.setText ( "0" );
                        }
                    }

                }


                if (stater = true) {
                    System.out.println ( "Create Show1" );
                    /////////////////////////////////////////////////////////

//                    if (obRepair.stateRubberFont == true){
//                        String SQL = "SELECT part_d_id, part_d_sec,part_d_sec_use,part_d_price, MIN(part_d_create) \n" +
//                                " FROM part_detail \n" +
//                                " WHERE   part_id  = "+rFont+" AND part_d_sec_use >0  and part_d_branchid = 1";
//
//
//                        Statement mStmt = conn.createStatement ();
//                        ResultSet rs = mStmt.executeQuery ( SQL );
//
//                        List rowValues1 = new ArrayList ();
//                        List rowValues2 = new ArrayList ();
//
//                        int sa ;
//
//
//                        while (rs.next ()) {
//
//                            rowValues2.add ( rs.getInt ( "part_d_id" ) );
//
//                            rowValues1.add ( rs.getString ( "part_d_price" ) );
//                            sa = rs.getInt ( "part_d_id" );
//
//                            if (sa <=0){
//                                txtRubberfont.setText ( "0" );
//                            }else if(sa >= 1){
//                                txtRubberfont.setText (  rs.getString ( "part_d_price" ) );
//                            }
//
//
//
//                        }
//                    }else if (obRepair.stateRubberFont  ==false){
//                        txtRubberfont.setText ( "0" );
//                    }
//
//
///////////////////////////////////////////////////////////////////////////////
//                    if (obRepair.stateRubberBack == true){
//                        String SQL = "SELECT part_d_id, part_d_sec,part_d_sec_use,part_d_price, MIN(part_d_create) \n" +
//                                " FROM part_detail \n" +
//                                " WHERE   part_id  = "+rBack+" AND part_d_sec_use >0  and part_d_branchid = 1";
//
//
//                        Statement mStmt = conn.createStatement ();
//                        ResultSet rs = mStmt.executeQuery ( SQL );
//
//                        List rowValues1 = new ArrayList ();
//                        List rowValues2 = new ArrayList ();
//
//                        int sa ;
//
//
//                        while (rs.next ()) {
//
//                            rowValues2.add ( rs.getInt ( "part_d_id" ) );
//
//                            rowValues1.add ( rs.getString ( "part_d_price" ) );
//                            sa = rs.getInt ( "part_d_id" );
//
//                            if (sa <=0){
//                                txtRubberback.setText ( "0" );
//                            }else if(sa >= 1){
//                                txtRubberback.setText (  rs.getString ( "part_d_price" ) );
//                            }
//
//
//
//                        }
//                    }else if (obRepair.stateRubberBack ==false){
//                        txtRubberback.setText ( "0" );
//                    }

///////////////////////////////////////////////////////////////////////////////////////////

                }


                conn.close ();
            } else {
                System.out.println ( "connect faild " );

            }
        } catch (Exception e) {
            e.printStackTrace ();

        }
    }

    void updateData() {
        int i = 1;
        int sum1;
        int Product = Integer.parseInt ( useProduct );
        sum1 = Product - i;
        System.out.println ( "dasdasd+Product" + Product );
        System.out.println ( "dasdasd+i" + i );
        System.out.println ( "dasdasd" + sum1 );


        if (txtRubberfont.length () > 1) {

            Connection conn = ConnectDb.getConnection (); //Connection Object
            try {
                if (conn != null) {


                    String SQL = "UPDATE `part_detail` SET `part_d_sec_use`= " + sum1 + " WHERE  \n" +
                            " part_id  = " + obRepair.selectRubberFont + " AND part_d_sec_use >0  and part_d_branchid = 1 AND part_d_id = " + idpartfont + "";
                    Statement mStmt = conn.createStatement ();
                    mStmt.executeUpdate ( SQL );

                    mStmt.close ();
                    conn.close ();

                }
            } catch (Exception e) {

            }


            int useProduct11 = Integer.parseInt ( useProduct1 );

            System.out.println ( "xoxoxo1+useProduct11" + useProduct11 );


        }
        if (txtRubberback.length () > 1) {
            Connection conn = ConnectDb.getConnection (); //Connection Object
            try {
                if (conn != null) {
                    int sum2;
                    int useProduct11 = Integer.parseInt ( useProduct1 );
                    sum2 = useProduct11 - i;
                    System.out.println ( "xoxoxo1" + sum2 );

                    String SQL = "UPDATE `part_detail` SET `part_d_sec_use`= " + sum2 + " WHERE  \n" +
                            " part_id  = " + obRepair.selectRubberBack + " AND part_d_sec_use >0  and part_d_branchid = 1 AND part_d_id = " + idpartback + " ";
                    Statement mStmt = conn.createStatement ();
                    mStmt.executeUpdate ( SQL );

                    List rowValues1 = new ArrayList ();
                    mStmt.close ();
                    conn.close ();
                }
            } catch (Exception e) {

            }
        }

    }

    public class ShowDetailService extends AsyncTask<String, Void, String> {


        @Override

        protected void onPostExecute(String s) {
//            txt.setText ( s);
            System.out.println ( "Show conn != nULL12" + s.toString () );
            super.onPostExecute ( s );
        }

        @Override
        protected String doInBackground(String... strings) {
            System.out.println ( "Show conn != nULL1234" );
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

                    String SQL = "SELECT part_d_id, part_d_sec,part_d_sec_use,part_d_price, MIN(part_d_create) \n" +
                            " FROM part_detail \n" +
                            " WHERE  part_d_branchid = 1";


                    Statement mStmt = conn.createStatement ();
                    ResultSet rs = mStmt.executeQuery ( SQL );

                    List rowValues1 = new ArrayList ();


                    while (rs.next ()) {


                        rowValues1.add ( rs.getString ( "part_d_price" ) );

//                        txtRubberfont.setText (  rs.getString ( "part_d_price" ) );


                    }


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


