package com.example.nirvana.projectrmt15_1_62;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class History extends AppCompatActivity {

    private ArrayList<ClassListHistoryRepair> itemArrayList;  //List items Array
    private MyAppAdapter myAppAdapter; //Array Adapter
    private ListView listView; // ListView
    private boolean success = false; // boolean

    String name1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_history );

        listView = (ListView) findViewById(R.id.listView); //ListView Declaration
        itemArrayList = new ArrayList<ClassListHistoryRepair>(); // Arraylist Initialization

        // Calling Async Task
        SyncData orderData = new SyncData();
        orderData.execute("");
    }

    private class SyncData extends AsyncTask<String, String, String> {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(History.this, "Synchronising",
                    "ListView Loading! Please Wait...", true);
        }

        @Override
        protected String doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {
            try {
                Connection conn = ConnectDb.getConnection ();
                if (conn == null) {
                    success = false;
                } else {
                    // Change below query according to your own database.

//                    String sql1="SELECT th.tn_name,de.dep_name,ord.dateservice FROM `order`as ord,ex_customer as cus ,ex_department as de ,ex_technician as th\n" +
//                            " WHERE ord.order_cusid = cus.ct_id and de.dep_id = ord.order_depart and  th.tn_id=ord.order_user_id and order_cusid = '2'";
//
//
//
//                    String a= "";
//                    Statement stmt = conn.createStatement();
//                    ResultSet rs = stmt.executeQuery(sql1);
////                    ResultSet rs1 = stmt.executeQuery(sql1);
//                    List rowValues1 = new ArrayList ();
////ของ oeder detail
//                    if (rs != null) // if resultset not null, I add items to itemArraylist using class created
//                    {
//                        while (rs.next() ) {
//                            try {
//                                rowValues1.add ( rs.getString ( "tn_name" ) );
//
//                                itemArrayList.add(new ClassListHistoryRepair (rs.getString("tn_name"),rs.getString("dep_name"),rs.getString ( "dateservice" )));
////                                itemArrayList.add(new ClassListHistoryRepair (rs.getInt ("order_id"),rs.getString("ct_name"),rs.getString("ct_lastname"), rs.getString("ct_phone"), rs.getString("order_landmarks"), rs.getString("order_landmarks"),rs.getDouble ("order_lati"),rs.getDouble ("order_longti"),rs.getInt ("order_id")));
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                        System.out.println ("SHOWWWWWWW"+rowValues1.get (0));
//                        msg = "Found";
//                        success = true;
//
//                    } else {
//                        msg = "No Data found!";
//                        success = false;
//                    }


                    //////////////////////////////////////////////////////////////////
                    String sql="SELECT ord.order_id,th.tn_name,de.dep_name,st.name,ord.dateservice FROM `order`as ord,ex_customer as cus ,ex_department as de ,ex_technician as th,ex_status as st\n" +
                            " WHERE ord.order_cusid = cus.ct_id and de.dep_id = ord.order_depart and  th.tn_id=ord.order_user_id and st.id=ord.order_status and order_cusid = '"+Login.idCus+"'";

                    String sql1= "SELECT orderss.order_id,tech.tn_name,de.dep_name,st.name,orderss.dateservice,orderss.order_user_id \n" +
                            "FROM `order` AS orderss \n" +
                            "LEFT JOIN ex_technician AS tech\n" +
                            "ON orderss.order_user_id=tech.tn_id \n" +
                            "INNER JOIN  ex_department as de\n" +
                            "ON  de.dep_id = orderss.order_depart\n" +
                            "INNER JOIN  ex_status as st\n" +
                            "ON  st.id=orderss.order_status\n" +
                            "INNER JOIN  ex_customer as cus\n" +
                            "ON  orderss.order_cusid = cus.ct_id\n" +
                            "WHERE orderss.order_cusid = '"+Login.idCus+"' and (orderss.order_status = 3 OR orderss.order_status = 4)";

                    //ของorder



                    Statement stmt1 = conn.createStatement();
                    ResultSet rs1 = stmt1.executeQuery(sql1);

                    String name = "";
                    String deparment= "";
                    String rubberF = "";
                    String rubberB= "";
                    String priceF= "";
                    String priceB= "";
                    String dateService= "";
                    String statusRepair= "";
                    List rowRubber = new ArrayList ();
                    List rowPrice = new ArrayList ();
                    String idTech;

                    String idOrder;

                    if (rs1 != null) // if resultset not null, I add items to itemArraylist using class created
                    {
                        while (rs1.next() ) {
                            try {
                                idOrder = rs1.getString ( "order_id" );
                                System.out.println ("SHOOOOWSW3ID"+idOrder);
//                                rowValues1.add ( rs1.getString ( "tn_name" ) );
//                                name =rs1.getString ( "tn_name" );
//                                if (rs1.getString ( "tn_name" )== null){
//                                    name ="--";
//                                    name1="--";
//                                }else if(rs1.getString ( "tn_name" )!=null) {
//                                    name =rs1.getString ( "tn_name" );
////                                    name1=rs1.getString ( "tn_name" );
//                                }
//                                order_user_id
                                System.out.println ("1115sssaasdsa"+rs1.getString ( "order_user_id" ));

                                    deparment =rs1.getString ( "dep_name" );
                                    dateService = rs1.getString ( "dateservice" );
                                    statusRepair =rs1.getString ( "name" );
                                    System.out.println ("SHOOOOWSW3NAME ELSE ID"+name );

                                idTech=rs1.getString ( "order_user_id" );



                                    String sqld="SELECT dto.order_id,dto.detail_order_product,dto.detail_order_price,ord.dateservice FROM `detail_order` as dto ,`order` as ord , ex_department as de\n" +
                                            "WHERE dto.order_id = ord.order_id and ord.order_depart=de.dep_id and ord.order_id = '"+rs1.getString ("order_id")+"'\n" +
                                            "GROUP by dto.detail_order_id";

                                Statement stmt2 = conn.createStatement();
                                ResultSet rs2 = stmt2.executeQuery(sqld);

                                System.out.println ("SHOOOOWSW3NAME ID"+idTech );

                                String summitt="";

//                                if (rs1.getString ( "tn_name" )== null){
//                                    name ="--";
//                                    System.out.println ("SHOOOOWSW3F122 IDNULL"+name );
//
//                                }else  if (rs1.getString ( "tn_name" ) != null ){
//                                    name =rs1.getString ( "tn_name" );
//                                    System.out.println ("SHOOOOWSW3F122 hoyi IDNULL"+name );
//
//
//                                }
//                                System.out.println ("SHOOOOWSW3F122 lslID"+name );
                                int j =0;
                                while (rs2.next ()){
//                                rowRubber.add ( rs2.getString ( "detail_order_product" ) );
//                                rowPrice.add ( rs2.getString ( "detail_order_price" ) );


                                    System.out.println ("SHOOOOWSW3F122sls ID"+name );
                                    System.out.println ("SHOOOOWSW3F122 PRo"+rs2.getString ( "detail_order_product" ) );
                                    System.out.println ("SHOOOOWSW3F122 duc"+rs2.getString ( "detail_order_price" ) );
//                                    itemArrayList.add(new ClassListHistoryRepair (rs.getString("tn_name"),rs.getString("dep_name"),rs.getString ( "dateservice" )));


                                    System.out.println ("SHOOOOWSW3F122 IF IDNULL"+summitt );
                                    if (rs1.getString ( "tn_name" )== null){
                                        name ="--";
                                        System.out.println ("SHOOOOWSW3F122 IDNULL"+name );


                                    }else  if (rs1.getString ( "tn_name" ) != null ){
                                        name =rs1.getString ( "tn_name" );
                                        System.out.println ("SHOOOOWSW3F122 hoyi IDNULL"+name );


                                    }
                                    System.out.println ("SHOOOOWSW3F122665 hoyi IDNULL"+name );

                                        if (j==0){
//                                            if (name=="--"){
//                                                rubberF="--";
//                                                priceF = "--";
//                                                rubberB="--";
//                                                priceB = "--";
//                                                System.out.println ("SHOOOOWSW122"+rubberF+"PR2ICE"+priceF);
//                                            }else {
//                                                rubberF =rs2.getString ( "detail_order_product" );
//                                                priceF =rs2.getString ( "detail_order_price" );
//                                                System.out.println ("SHOOOOWSW1"+rubberF+"PRICE"+priceF);
//
//                                                j++;
//                                            }

                                            ////////////////////////////////////////////////////////
                                            if (rs1.getString ( "tn_name" )== null){
                                                name ="--";
                                                System.out.println ("SHOOOOWSW3F122 IDNULL"+name );
                                                rubberF="--";
                                                priceF = "--";
                                                rubberB="--";
                                                priceB = "--";

                                            }else  if (rs1.getString ( "tn_name" ) != null ){
                                                name =rs1.getString ( "tn_name" );
                                                System.out.println ("SHOOOOWSW3F122 hoyi IDNULL"+name );
                                                rubberF =rs2.getString ( "detail_order_product" );
                                                priceF =rs2.getString ( "detail_order_price" );
                                                System.out.println ("SHOOOOWSW1"+rubberF+"PRICE"+priceF);

                                                j++;

                                            }
                                            ////////////////////////////////////////////////////////

                                        }else if (j==1) {
//                                            if (name=="--"){
//                                                rubberF="-";
//                                                priceF = "-";
//                                                rubberB="-";
//                                                priceB = "-";
//                                            }else {
//                                                rubberB =rs2.getString ( "detail_order_product" );
//                                                priceB =rs2.getString ( "detail_order_price" );
//                                                System.out.println ("SHOOOOWSW2"+rubberB+"PRICE"+priceB);
//                                                j++;
//                                            }
                                            ////////////////////////////////////////////////////////
                                            if (rs1.getString ( "tn_name" )== null){
                                                name ="--";
//                                                System.out.println ("SHOOOOWSW3F122 IDNULL"+name );
                                                rubberF="--";
                                                priceF = "--";
                                                rubberB="--";
                                                priceB = "--";

                                            }else  if (rs1.getString ( "tn_name" ) != null ){
                                                name =rs1.getString ( "tn_name" );
//                                                System.out.println ("SHOOOOWSW3F122 hoyi IDNULL"+name );
                                                rubberB =rs2.getString ( "detail_order_product" );
                                                priceB =rs2.getString ( "detail_order_price" );
                                                System.out.println ("SHOOOOWSW1"+rubberF+"PRICE"+priceF);

                                                j++;

                                            }
                                            /////

                                        }



                                }

//                                itemArrayList.add(new ClassListHistoryRepair (rs.getString("tn_name"),rs.getString("dep_name"),rs.getString ( "dateservice" )));

                                    System.out.println ("SHOWSSSSS"+rubberF);
                                System.out.println ("SHOWSSSSS"+priceF);

//                                itemArrayList.add(new ClassListHistoryRepair (rs.getString("tn_name"),rs.getString("dep_name"),rs.getString ( "dateservice" )));
                                itemArrayList.add(new ClassListHistoryRepair (name,deparment,dateService, rubberF, priceF, rubberB,priceB,statusRepair));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        msg = "Found";
                        success = true;

                    } else {
                        msg = "No Data found!";
                        success = false;
                    }
                    /////////////////////////////////////////////////////////////////
                }
            } catch (Exception e) {
                e.printStackTrace();
                Writer writer = new StringWriter ();
                e.printStackTrace(new PrintWriter (writer));
                System.out.println ("SHOWROR"+writer.toString ());
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) // disimissing progress dialoge, showing error and setting up my ListView
        {
            progress.dismiss();
            Toast.makeText(History.this, msg + "", Toast.LENGTH_LONG).show();
            if (success == false) {
            } else {
                try {
                    myAppAdapter = new MyAppAdapter(itemArrayList, History.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter);
                    listView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String item = String.valueOf ( itemArrayList.get(position).getNameTechnician () );
                            String idc = String.valueOf ( itemArrayList.get(position).getDepartment () );
                            String landmark = String.valueOf ( itemArrayList.get(position).getDateRepair () );


//                            Toast.makeText ( getApplicationContext (), "ทำการบันทึกข้อมูลเรียบร้อย"+item+" "+idc, Toast.LENGTH_SHORT ).show ();

                        }
                    } );
                } catch (Exception ex) {

                }

            }
        }
    }


    public class MyAppAdapter extends BaseAdapter         //has a class viewholder which holds
    {
        public class ViewHolder {
            TextView txtNameTech;
           TextView txtDepartment;
           TextView txtRubberF;
           TextView txtRubberB;
           TextView txtPriceF;
            TextView txtPriceB;
            TextView txtDateOrder;
            TextView txtstatus;
        }

        public List<ClassListHistoryRepair> parkingList;

        public Context context;
        ArrayList<ClassListHistoryRepair> arraylist;

        private MyAppAdapter(List<ClassListHistoryRepair> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<ClassListHistoryRepair>();
            arraylist.addAll(parkingList);
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) // inflating the layout and initializing widgets
        {

            View rowView = convertView;
            ViewHolder viewHolder = null;
            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_content, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.txtNameTech = (TextView) rowView.findViewById(R.id.txtNameTech);
                viewHolder.txtDepartment = (TextView) rowView.findViewById(R.id.txtDepartment);
                viewHolder.txtRubberF = (TextView) rowView.findViewById(R.id.txtRubberF);
                viewHolder.txtRubberB = (TextView) rowView.findViewById(R.id.txtRubberB);
                viewHolder.txtPriceF = (TextView) rowView.findViewById(R.id.txtPriceF);
                viewHolder.txtPriceB = (TextView) rowView.findViewById(R.id.txtPriceB);
                viewHolder.txtDateOrder = (TextView) rowView.findViewById(R.id.txtDateOrder);
                viewHolder.txtstatus = (TextView) rowView.findViewById(R.id.txtstatus);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String statusText;
            // here setting up names and images
            viewHolder.txtNameTech.setText(parkingList.get(position).getNameTechnician () + "");
            viewHolder.txtDepartment.setText(parkingList.get(position).getDepartment () + "");
            viewHolder.txtRubberF.setText(parkingList.get(position).getRubberF () + "");
            viewHolder.txtRubberB.setText(parkingList.get(position).getRubberB () + "");
            viewHolder.txtPriceF.setText(parkingList.get(position).getPriceF () + "");
            viewHolder.txtPriceB.setText(parkingList.get(position).getPriceB () + "");
            viewHolder.txtDateOrder.setText(parkingList.get(position).getDateRepair () + "");
            statusText = parkingList.get(position).getstatusRepair () + "";

            if (statusText.equals ( "ยกเลิก" )){
                viewHolder.txtstatus.setText(parkingList.get(position).getstatusRepair () + "");
                viewHolder.txtstatus.setTextColor ( Color.RED );
            }else  if (statusText.equals ( "ซ่อมเสร็จ" )){
                viewHolder.txtstatus.setText(parkingList.get(position).getstatusRepair () + "");
//                viewHolder.txtstatus.setTextColor ( Color.RED );
            }

//                viewHolder.txtstatus.setText(parkingList.get(position).getstatusRepair () + "");
//                viewHolder.txtstatus.setTextColor ( Color.RED );

//
//
//            Picasso.with(context).load(parkingList.get(position).getImg()).into(viewHolder.imageView);

            return rowView;
        }
    }
}
