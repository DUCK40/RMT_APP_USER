package com.example.nirvana.projectrmt15_1_62;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoRepair extends AppCompatActivity {

    int menu1 =1;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_no_repair );

        final Button button1 = (Button) findViewById(R.id.btnfrag1);
        final Button button2 = (Button) findViewById(R.id.btnfrag2);

        final FragmentMenu1 f1 = new FragmentMenu1();
        final Fragment1 MF1= new Fragment1 ();


        final FragmentMenu2 f2 = new FragmentMenu2();
        final Fragment2 MF2= new Fragment2 ();

        replaceFragment(f1);

//        replaceFragment1(MF1);
        button1.setBackgroundColor ( Color.BLACK );
        button2.setBackgroundColor ( Color.GRAY );
        button1.setTextColor ( Color.WHITE );
        button2.setTextColor ( Color.WHITE );

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                button1.setBackgroundColor ( Color.BLACK  );

                button2.setBackgroundColor ( Color.GRAY );
                button1.setTextColor ( Color.WHITE );
                button2.setTextColor ( Color.WHITE );
//                menu1=1;
                replaceFragment(f1);

//                replaceFragment1(MF1);
            }


        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                menu2=2;
                button1.setBackgroundColor ( Color.GRAY );
                button2.setBackgroundColor ( Color.BLACK  );
                button1.setTextColor ( Color.WHITE );
                button2.setTextColor ( Color.WHITE );
                replaceFragment(f2);
//                replaceFragment1(MF2);

            }



        });





    }
    //
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

//
//        private void replaceFragment1(Fragment fragment1) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.frameLayout1, fragment1)
//                    .commit();
//        }
}
