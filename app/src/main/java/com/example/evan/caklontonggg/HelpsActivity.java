package com.example.evan.caklontonggg;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HelpsActivity extends AppCompatActivity {

    private Button mYa, mTidak;
    private Helps[] mHelpsCorrect = new Helps[]{
            new Helps(R.string.help1),
            new Helps(R.string.help2)
    };

    private int mHelpsNumber = 0;
    private int chances = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helps);

        mYa = (Button)findViewById(R.id.ya);
        mTidak = (Button)findViewById(R.id.tidak);

        mTidak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context = HelpsActivity.this;
                Class destinationActivity = MainActivity.class;
                Intent intent = new Intent(context, destinationActivity);
                startActivity(intent);
            }
        });

        mYa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent bantuan = getIntent();

                if (bantuan.hasExtra("chance")){
                    int chancesGet = bantuan.getIntExtra("chance", -1);
                    chances = chancesGet;
                }

                if (bantuan.hasExtra("helpsIndex")) {
                    int textBantuan = bantuan.getIntExtra("helpsIndex", -1);
                    mHelpsNumber = textBantuan;
                }

                Context context = HelpsActivity.this;
                Class destinationActivity = MainActivity.class;
                Intent intent = new Intent(context, destinationActivity);

                /*if (mHelpsNumber >= mHelpsCorrect.length){
                    mHelpsNumber = mHelpsNumber - 1;
                }*/

                if(chances <= 0){
                    chances = chances + 1;
                    mHelpsNumber = mHelpsNumber - 1;
                    Toast.makeText(HelpsActivity.this, "Bantuan sudah habis!", Toast.LENGTH_LONG).show();
                }
                int helps = mHelpsCorrect[mHelpsNumber].getHelps();

                mHelpsNumber = mHelpsNumber + 1;
                chances = chances - 1;


                intent.putExtra("nextHelps", String.valueOf(mHelpsNumber));
                intent.putExtra("helps",helps);
                intent.putExtra("chances", String.valueOf(chances));
                startActivity(intent);
            }
        });
    }
}
