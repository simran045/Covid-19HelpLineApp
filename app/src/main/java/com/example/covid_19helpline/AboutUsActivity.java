package com.example.covid_19helpline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {
    TextView copy;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
         copy=findViewById(R.id.copy);
         String res=getResources().getString(R.string.COPY);
         copy.setText(res);


        logo=(ImageView)findViewById(R.id.aboutuss);

        Animation myanim1= AnimationUtils.loadAnimation(this,R.anim.mysplashanim);
        logo.startAnimation(myanim1);

    }
}
