package com.cswithandroid.unit6.codingame;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity {

    private Button bEnter, bDeclare;
    private TextView tvPlayer1,tvPlayer2, tvWord, tvWordList;
    private TreeDictionary dictionary;
    private boolean USER1_TURN = true, USER2_TURN = false, LOCK1 = false, LOCK2 = false;
    private int player1Score=0,player2Score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new TreeDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }

        bEnter = (Button) findViewById( R.id.bEnter);
        bDeclare = (Button) findViewById( R.id.bDeclare);

        tvPlayer1 = (TextView) findViewById (R.id.tvPlayer1);
        tvPlayer2 = (TextView) findViewById (R.id.tvPlayer2);
        tvWord = (TextView) findViewById( R.id.tvWord);
        tvWordList = (TextView) findViewById( R.id.tvWordList);

        bEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidWord(tvWord);
            }
        });
        bDeclare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                declare();
            }
        });
    }

    private void declare(){
        if(USER1_TURN)
            LOCK1 = true;
        else
            LOCK2 = true;

        if(LOCK1 && LOCK2)
            endGame();
    }

    private boolean checkValidWord(TextView tvWord){
        if( dictionary.getIndex()> dictionary.getCount()){
            String word=tvWord.getText().toString();
            if(dictionary.addNewWord(tvWord.getText().toString())){
               int score= dictionary.computeScore(word);
                updateViews();
                changeTurn(score);
            }else{
                makeToast("The word does not exist in the Atlas");
            }
        }else{
            if(!dictionary.checkWordExistence(tvWord.getText().toString())){
                updateViews();
            }else{
                makeToast("Incorrect sequence. You can't play anymore");
                declare();
                changeTurn(0);
            }
        }
        return false;
    }

    public void changeTurn(int score){
        resetTextViews();
      if(USER1_TURN == true){
          player1Score+=score;
          tvPlayer1.setText("PLayer 1: "+player1Score);
        if(LOCK2 != true){
          USER1_TURN = false;
          USER2_TURN = true;
        }
      }
      else{
          player2Score+=score;
          tvPlayer2.setText("PLayer 2: "+player2Score);
        if(LOCK1 != true){
          USER2_TURN = false;
          USER1_TURN = true;
        }
      }
    }

    public void updateViews (word) {

      String wordList = tvWordList.getText().toString();
      wordList = wordList + " \n" + word;
      tvWordList.setText(wordList);

      if(dictionary.getIndex() > dictionary.getCount()){
        tvWord.setText("Enter new word");
      }
      else
        tvWord.setText("Enter sequence!");

    }

    public void makeToast(String message){
      Toast newToast = Toast.makeToast(this, message, TOAST.LENGTH_LONG);
      newToast.show();
    }

    public void resetTextViews() {
      tvWordList.setText("");
      tvWord.setText("Enter sequence");
    }

    public void endGame(){
      dictionary.resetValues();
      tvWordList.setText("Enter new word!");
    }
}
