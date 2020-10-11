package com.example.nirvana.projectrmt15_1_62;

import android.content.Intent;
import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static android.support.v4.content.ContextCompat.startActivity;

public class Insertdatacar extends AsyncTask<String, Void, String> {
    Boolean state = true;
    Boolean stateIndetail = false;

    static String  getSuccses ;

    OderRepair settingOrder;




   public static int idrecord;
    @Override
    protected void onPreExecute() {
        super.onPreExecute ();
    }

    @Override

    protected void onPostExecute(String s) {


        super.onPostExecute ( s );
    }
    @Override
    protected String doInBackground(String... strings) {


System.out.println (""+Login.IdDistrict);
        settingOrder = new OderRepair ();
       // settingOrder.setLandmark ("55555555555555555555");

        System.out.println ( "Show conn != nULL1Liati"+settingOrder.getLandmark () );
        String mParam1 = strings[0]; // รับParameter มาแล้วแปลงเป็นค่า Sring
        String mParam2 = strings[1];
        String mParam3 = strings[2];
        String mParam4 = strings[3];
        String mParam5 = strings[4];
        String mParam6 = strings[5];
        String mParam7 = strings[6];

        System.out.println ( "Show conn != nULL" );
        Statement stmt = null;
        int tt;
        try {

            Connection conn = ConnectDb.getConnection (); //Connection Object
            if (conn != null) {

                if (state == true){
                    String sql1 = "INSERT INTO `rmt_user_type` ( `usertype`) VALUES (?)";


                    String sql2 = "INSERT INTO oktsxyz_duck.`rmt_order_repair`\n " +
                            "( order_cusid,order_lati, order_longti,  order_state, order_landmarks)\n " +
                            " VALUES(?,?, ?, ?, ?);";

                    String sql21 = "INSERT INTO `order` (`order_id`, `order_cusid`, `order_lati`, `order_longti`, `order_user_id`, `order_ad_id`, `order_status`, `order_landmarks`, `order_rubberfont`, `order_rubberback`, `order_service_id`) \n" +
                            "VALUES (NULL, 1, 50, 60, null,null, 1,null, 1.75, 1.75, '1')";

                    String sql3 = "INSERT INTO `order` (`order_id`, `order_cusid`, `order_lati`, `order_longti`, \n" +
                                                        " `order_user_id`, `order_ad_id`, `order_status`, `order_landmarks`, \n" +
                                                         " `order_rubberfont`, `order_rubberback`, `order_service_id`,`order_depart`) \n" +
                                                                "VALUES (NULL, ?, ?, ?, \n" +
                                                                "99, NULL, ?, ?, \n" +
                                                                "?, ?, '1',"+Login.IdDistrict+");";



                    String sql = "INSERT INTO oktsxyz_duck.`rmt_order_repair`\n " +
                            "( order_cusid,order_lati, order_longti, order_tech, order_admin, order_state, order_landmarks)\n " +
                            " VALUES(?,?, ?, ?, ?, ?,?);";
                    PreparedStatement statement = (PreparedStatement) conn.prepareStatement ( sql3 );
                    statement.setString ( 1, mParam1 );
                    statement.setString ( 2, mParam2 );
                    statement.setString ( 3, mParam3 );
                    statement.setString ( 4, mParam4 );
                    statement.setString ( 5, mParam5 );
                statement.setString ( 6, mParam6 );
                statement.setString ( 7, mParam7 );


                   statement.executeUpdate ();
                    ResultSet rs=statement.getGeneratedKeys();

                    if(rs.next()){
                        idrecord = rs.getInt(1);
                        System.out.println ("AAAAAA"+rs.getInt(1));

//                        String SQL = "INSERT INTO `rmt_repair_details` \n" +
//                                " (`repair_deta_id`, `repair_deta_retype`, `repair_deta_price`, `repair_deta_idrepair`, `repair_deta_idpart`)\n" +
//                                "  VALUES (NULL, '2', '120', "+idrecord+", '1');";   // WHERE Name = '" + user + "'
//
//                         statement = conn.prepareStatement ( SQL );
//                        statement.executeUpdate ();
                       getSuccses = "1";




                    }



                    settingOrder.setRubberfont ( null );
                    settingOrder.setRubberback ( null );



                    conn.close ();


                    statement.close ();
                    stateIndetail =true;


                }
                if (stateIndetail){
                    System.out.println ("5555555556666"+idrecord);
                }




            } else {
                System.out.println ( "connect faild " );

            }
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println ("Shiw"+e.toString ());

        }


        return getSuccses;
    }


}
