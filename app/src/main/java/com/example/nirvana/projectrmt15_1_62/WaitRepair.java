package com.example.nirvana.projectrmt15_1_62;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WaitRepair extends AppCompatActivity {


    TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_wait_repair );
        Button btn =(Button)findViewById ( R.id.btnback );
         txtShow = (TextView)findViewById ( R.id.txtShow );

//        Bundle bundle = getIntent().getExtras();
//        String text = bundle.getString("MyValue");
        txtShow.setText ( "กรุณารอสักครู่");

        btn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (WaitRepair.this, MainActivity.class));

            }
        } );
    }
}
