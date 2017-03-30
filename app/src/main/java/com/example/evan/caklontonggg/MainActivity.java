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

    private Button mSubmit, mHelp, mLanjut;
    private TextView mSoal, mClue;
    private EditText mJawab;

    private String mAnswer;
    private Question[] mQuestionLibrary = new Question[]{
            new Question(R.string.soal, "kurus"),
            new Question(R.string.soal1, "karang")
    };
    private Clues[] mCluesCorrect = new Clues[]{
            new Clues(R.string.clues, "_ U _ _ _"),
            new Clues(R.string.clues1, "_ A _ _ _ _")
    };

    private int mQuestionNumber = 0; //index pertanyaan
    private int mCluesNumber = 0; // index clues (jawaban yang ditampilkan)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoal = (TextView)findViewById(R.id.soal);
        mClue = (TextView)findViewById(R.id.hasil);
        mSubmit = (Button)findViewById(R.id.submit);
        mHelp = (Button)findViewById(R.id.help);
        mLanjut = (Button) findViewById(R.id.lanjut);
        mJawab = (EditText)findViewById(R.id.jawab);

        int question = mQuestionLibrary[mQuestionNumber].getQuestion();
        mSoal.setText(question);
        String clue = mCluesCorrect[mCluesNumber].getFirstClue();
        mClue.setText(clue);

        mSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mAnswer = mJawab.getText().toString();
                checkAnswer(mAnswer);
                mJawab.getText().clear();
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
                    String indexClue = bantuan.getStringExtra("nextHelps");
                    intent.putExtra("helpsIndex", Integer.parseInt(indexClue));
                    String chances = bantuan.getStringExtra("chances");
                    intent.putExtra("chance", Integer.parseInt(chances));
                }
                startActivity(intent);
            }
        });

        mLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Lanjut!", Toast.LENGTH_LONG).show();
                updateQuestion();
                updateClues();
                updateFirstClue();
                mLanjut.setVisibility(View.INVISIBLE);

                Context context = MainActivity.this;
                Class destinationActivity = HelpsActivity.class;
                Intent intent = new Intent(context, destinationActivity);
                intent.putExtra("chance", 2);
            }
        });
    }

    private void updateQuestion() {
        int question = mQuestionLibrary[mQuestionNumber].getQuestion();
        mSoal.setText(question);
    }

    private void updateClues(){
        int clue = mCluesCorrect[mCluesNumber].getClues();
        mClue.setText(clue);
    }

    private void updateFirstClue(){
        String clue = mCluesCorrect[mCluesNumber].getFirstClue();
        mClue.setText(clue);
    }

    private void checkAnswer(String answerTrue){
        String answerIsTrue = mQuestionLibrary[mQuestionNumber].isCorrectAnswer();
        if (answerTrue.equalsIgnoreCase(answerIsTrue)){
            int clue = mCluesCorrect[mCluesNumber].getClues();
            mClue.setText(clue);
            Toast.makeText(MainActivity.this, "BENAR!"+" Jawabannya "+
                    answerIsTrue, Toast.LENGTH_SHORT).show();
            mLanjut.setVisibility(View.VISIBLE);
            mQuestionNumber = (mQuestionNumber + 1) % mQuestionLibrary.length;
            mCluesNumber = (mCluesNumber + 1) % mCluesCorrect.length;

            Intent intent = new Intent(MainActivity.this, HelpsActivity.class);
            intent.putExtra("chance", 2);
        }else {
            Toast.makeText(MainActivity.this, "SALAH", Toast.LENGTH_SHORT).show();
        }
    }
}