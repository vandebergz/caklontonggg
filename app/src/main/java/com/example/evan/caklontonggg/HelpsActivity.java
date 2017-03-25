package com.example.evan.caklontonggg;

import android.content.Context;
import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helps);

        final Helps disHelps = new Helps();
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

                if (bantuan.hasExtra("clueIndex")) {
                    int textBantuan = bantuan.getIntExtra("clueIndex", -1);
                    mHelpsNumber = textBantuan;
                }

                Context context = HelpsActivity.this;
                Class destinationActivity = MainActivity.class;
                Intent intent = new Intent(context, destinationActivity);
                if (mHelpsNumber >= mHelpsCorrect.length){
                    mHelpsNumber = mHelpsNumber - 1;
                    Toast.makeText(HelpsActivity.this, "Bantuan sudah habis!", Toast.LENGTH_LONG).show();
                }
                int helps = mHelpsCorrect[mHelpsNumber].getHelps();

                intent.putExtra("debug", String.valueOf(mHelpsNumber));
                mHelpsNumber = mHelpsNumber+1;
                disHelps.setHelps(mHelpsNumber);
                intent.putExtra("indexNumber", String.valueOf(mHelpsNumber));
                intent.putExtra("helps",helps);
                startActivity(intent);
            }
        });
    }
}
