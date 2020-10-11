package com.example.nirvana.projectrmt15_1_62;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Traveling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_traveling );
        Button btn =(Button)findViewById ( R.id.btnback );

        btn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (Traveling.this, MainActivity.class));

            }
        } );
    }
}
