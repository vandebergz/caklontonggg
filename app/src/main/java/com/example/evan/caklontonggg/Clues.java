package com.example.evan.caklontonggg;

/**
 * Created by evan on 3/25/17.
 */

public class Clues {


    private String mFirstClue, mSecondClue, mThirdClue;
    private int mClues;

    public Clues(int clues, String firstClue){

        mClues = clues;
        mFirstClue = firstClue;

    }

    public int getClues(){
        return mClues;
    }

    public void setClues(int clues){
        mClues = clues;
    }

    public String getFirstClue() {
        return mFirstClue;
    }

    public void setFirstClue(String mFirstClue) {
        this.mFirstClue = mFirstClue;
    }
}