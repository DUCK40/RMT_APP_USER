package com.example.nirvana.projectrmt15_1_62;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    Button btnOk;
    Button btnCancel;

    EditText edtname;
    EditText edtlastname;
    EditText edtusername;
    EditText edtpassword;
    EditText edtphone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_register );
        init ();

    }
    void init(){

        edtname =(EditText)findViewById ( R.id.edtname );
        edtlastname =(EditText)findViewById ( R.id.edtlastname );
        edtusername =(EditText)findViewById ( R.id.edtusername );
        edtpassword =(EditText)findViewById ( R.id.edtpassword);
        edtphone =(EditText)findViewById ( R.id.edtphone );
        btnOk =(Button)findViewById ( R.id.btnok );
        btnCancel =(Button)findViewById ( R.id.btncancle );

        btnCancel.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity ( new Intent ( Register.this, Login.class ) );

            }
        } );
        btnOk.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if(edtname.getText ().toString ().isEmpty () || edtlastname.getText ().toString ().isEmpty () ||edtusername.getText ().toString ().isEmpty () || edtpassword.getText () .toString ().isEmpty () || edtphone.getText () .toString ().isEmpty () ){
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();

                }else {
                    checkInput();
                }


            }
        } );

    }

    void checkInput(){

        try {

            Connection conn = ConnectDb.getConnection (); //Connection Object
            Statement mStmt1 = conn.createStatement ();
            String usernameid = edtusername.getText ().toString ();
            System.out.println ("SJOWLAAA"+edtusername.getText ().toString ());

            String sql ="SELECT * FROM `ex_customer` WHERE ct_userid = '"+edtusername.getText ().toString ()+"'";


            ResultSet rs1 = mStmt1.executeQuery ( sql );

            List rowValues1 = new ArrayList ();


            while (rs1.next ()) {

                rowValues1.add ( rs1.getString ( "ct_userid" ) );

                System.out.println ("IKOW"+rs1.getString ( "ct_userid" ));

            }
            if (rowValues1.size () ==1){
                Toast.makeText(getApplicationContext(), "USERNAME นี้มีคนใช้ไปแล้ว ", Toast.LENGTH_SHORT).show();


            }else if (rowValues1.size () <=0){
                addUser ();
            }


        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println ("Shiw"+e.toString ());

        }




    }
    void addUser(){
        try {


            Connection conn = ConnectDb.getConnection (); //Connection Object
            Statement mStmt1 = conn.createStatement ();

            String sql ="INSERT INTO `ex_customer` (`ct_id`, `ct_name`, `ct_lastname`, `ct_userid`, `ct_password`, `ct_phone`, `ct_status`) \n " +
                    " VALUES ('null', '"+edtname.getText ().toString ()+"', '"+edtlastname.getText ().toString ()+"', '"+edtusername.getText ().toString ()+"', '"+edtpassword.getText ().toString ()+"', '"+edtphone.getText ().toString ()+"', '2')";



            mStmt1.executeUpdate (sql);

            ResultSet rs=mStmt1.getGeneratedKeys();
            List rowValues1 = new ArrayList ();
            if (rs.next ()){
                System.out.println ("AAAAAA"+rs.getInt(1));
                rowValues1.add ( rs.getInt(1) );
            }
            if (rowValues1.size () >=1){
                Toast.makeText(getApplicationContext(), "การสมัครเสร็จสิ้น", Toast.LENGTH_SHORT).show();
                startActivity ( new Intent ( Register.this, Login.class ) );


            }else if(rowValues1.size () <=0){
                Toast.makeText(getApplicationContext(), "กรุณาลองใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();

            }





        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println ("Shiw"+e.toString ());

        }

    }
}
