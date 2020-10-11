package com.example.nirvana.projectrmt15_1_62;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    TextView txtname;
    TextView txtlastname;
    TextView txttel;

    Button btnedt;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_profile );
        init ();
        findDataUser();
    }
    void init(){
        txtname =(TextView)findViewById ( R.id.txtname );
        txtlastname =(TextView)findViewById ( R.id.txtlastname );
        txttel =(TextView)findViewById ( R.id.txttel );

        btnedt = (Button)findViewById ( R.id.btnedt );
        btnback = (Button)findViewById ( R.id.btnback );

        btnedt.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity ( new Intent ( Profile.this, EditProfile.class ) );

            }
        } );

        btnback.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity ( new Intent ( Profile.this, MainActivity.class ) );

            }
        } );


    }
    void findDataUser(){

        try {
            Connection conn = ConnectDb.getConnection (); //Connection Object

            Statement mStmt1 = conn.createStatement ();

//            Login.idCus;

            String sql ="SELECT ct_id,ct_name,ct_lastname,ct_userid,ct_password,ct_phone \n" +
                    "FROM `ex_customer` WHERE ct_id = '"+Login.idCus+"'";
            ResultSet rs1 = mStmt1.executeQuery ( sql );

            List rowValues1 = new ArrayList ();

            if (rs1.next ()){
                txtname.setText (  rs1.getString ( "ct_name" ));
                txtlastname.setText ( rs1.getString ( "ct_lastname" ) );
                txttel.setText ( rs1.getString ( "ct_phone" ) );

            }

        }catch (Exception e){

        }
    }
}
