package com.example.covid_19helpline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private  ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo=(ImageView)findViewById(R.id.logo1);
        //txt=(TextView)findViewById(R.id.covid);

        btn=findViewById(R.id.btnStart);

        //Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mysplashanim);
        //txt.startAnimation(myanim);

        Animation myanim1= AnimationUtils.loadAnimation(this,R.anim.mysplashanim);
        logo.startAnimation(myanim1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CovidHomePage.class));
                finish();
            }
        });

//   myanim1.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                startActivity(new Intent(".CovidHomePage"));
//                  finish();
//             }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });-->

    }
}
