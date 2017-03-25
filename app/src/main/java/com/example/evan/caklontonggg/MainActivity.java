package com.example.evan.caklontonggg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mSubmit, mHelp;
    private TextView mSoal, mClue, mJawab;

    private String mAnswer;
    private Question[] mQuestionLibrary = new Question[]{
            new Question(R.string.soal, "kurus")
    };
    private Clues[] mCluesCorrect = new Clues[]{
            new Clues(R.string.clues)
    };

    private int mQuestionNumber = 0;
    private int mCluesNumber = 0;

    private void checkAnswer(String answerTrue){
        String answerIsTrue = mQuestionLibrary[mQuestionNumber].isCorrectAnswer();
        if (answerTrue.equalsIgnoreCase(answerIsTrue)){
            int clue = mCluesCorrect[mCluesNumber].getClues();
            mClue.setText(clue);
            Toast.makeText(MainActivity.this, "BENAR", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "SALAH", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoal = (TextView)findViewById(R.id.soal);
        mClue = (TextView)findViewById(R.id.hasil);
        mSubmit = (Button)findViewById(R.id.submit);
        mHelp = (Button)findViewById(R.id.help);
        mJawab = (EditText)findViewById(R.id.jawab);

        int question = mQuestionLibrary[mQuestionNumber].getQuestion();
        mSoal.setText(question);

        mSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mAnswer = mJawab.getText().toString();
                checkAnswer(mAnswer);
                mJawab.setText("");
            }
        });


        final Intent bantuan = getIntent();
        if (bantuan.hasExtra("helps")){
            int textBantuan = bantuan.getIntExtra("helps", -1);
            mClue.setText(textBantuan);
        }

        mHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context = MainActivity.this;
                Class destinationActivity = HelpsActivity.class;
                Intent intent = new Intent(context, destinationActivity);
                if (bantuan.hasExtra("helps")) {
                    String indexClue = bantuan.getStringExtra("indexNumber");
                    intent.putExtra("clueIndex", Integer.parseInt(indexClue));
                }
                startActivity(intent);
            }
        });
    }
}
