package com.example.nirvana.projectrmt15_1_62;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewRubber extends AppCompatActivity {

    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_view_rubber );

        btnBack = (Button)findViewById ( R.id.btnback );
        btnBack.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity ( new Intent ( ViewRubber.this, MainActivity.class ) );
            }
        } );
    }
}
