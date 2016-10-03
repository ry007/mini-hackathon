package com.cswithandroid.unit6.codingame;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity {

    private Button bEnter, bDeclare; // ,bInst;
    private TextView tvPlayer1,tvPlayer2, tvWordList;
    private EditText tvWord;
    private TreeDictionary dictionary;
    private boolean USER1_TURN = true, USER2_TURN = false, LOCK1 = false, LOCK2 = false;
    private int player1Score = 0,player2Score = 0;
    private int defaultTextColor;

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
       //bInst = (Button) findViewById( R.id.bInst);

        tvPlayer1 = (TextView) findViewById (R.id.tvPlayer1);
        tvPlayer2 = (TextView) findViewById (R.id.tvPlayer2);
        tvWord = (EditText) findViewById( R.id.tvWord);
        tvWordList = (TextView) findViewById( R.id.tvWordList);
        defaultTextColor = tvPlayer2.getTextColors().getDefaultColor();

        tvPlayer1.setTextColor(Color.rgb(200,0,0));

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.instructions);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });

//        tvWord.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//              InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//              imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
//            }
//        });

//        bInst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//            }
//        });
    }

//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//
//        Log.d("keycode", "keycode entered is "+keyCode);
//
////        if(keyCode < 29 || keyCode > 54) {
////            Toast.makeText(this,"Enter valid character",Toast.LENGTH_SHORT).show();
////            return super.onKeyUp(keyCode, event);
////        }
//        char unicodeChar = (char)event.getUnicodeChar();
//        String word = tvWord.getText().toString();
//        word = word + unicodeChar;
//        Log.d("newWord","the new word is "+word);
//
//        // Check if the word is a valid one or not!
//        tvWord.setText(word);
//
//        return true;
//    }

    private boolean declare(){
        if(USER1_TURN)
            LOCK1 = true;
        else
            LOCK2 = true;

        return (LOCK1 && LOCK2);        // returns true if both locks are set!
    }

    private void checkValidWord(TextView tvWord){
        String word=tvWord.getText().toString();
        if( dictionary.getIndex() >= dictionary.getCount()){
            if(dictionary.addNewWord(word)){
                makeToast("New word added");
                int score= dictionary.computeScore(word);
                updateViews(word);
                changeTurn(score);
            }else{
                makeToast("The word does not exist in the Atlas");
            }
        }else{
            //makeToast("Sequence branch");
            if(dictionary.checkWordExistence(word)){
                updateViews(word);
                makeToast("Correct sequence word. Go on!");
            }
            else {
                makeToast("Incorrect sequence. You can't play anymore");
                boolean endGameCondition = declare();

                if(endGameCondition)
                    endGame();
                else
                    changeTurn(0);
            }
        }

    }

    public void changeTurn(int score){

      setTextViews("","Enter Sequence!");
      dictionary.resetCurrentIndex();

      if(USER1_TURN){
          player1Score += score;

        if(!LOCK2){
            tvPlayer2.setTextColor(Color.rgb(200,0,0));
            tvPlayer1.setTextColor(defaultTextColor);
            USER1_TURN = false;
            USER2_TURN = true;
        }
      }
      else{
          player2Score += score;
        if(!LOCK1){

            tvPlayer1.setTextColor(Color.rgb(200,0,0));
            tvPlayer2.setTextColor(defaultTextColor);
            USER2_TURN = false;
            USER1_TURN = true;
        }
      }
        setScores();    // Sets both players updated scores
    }

    public void updateViews (String word) {

      String wordList = tvWordList.getText().toString();
      wordList = wordList + " \n" + word;

      if(dictionary.getIndex() >= dictionary.getCount()){
        setTextViews(wordList,"Enter new word");
      }
      else
        setTextViews(wordList, "Enter sequence!");
    }

    public void makeToast(String message){
      Toast newToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
      newToast.show();
    }

    public void setTextViews(String wordListString, String wordHint) {
          tvWordList.setText(wordListString);
          tvWord.getText().clear();
          tvWord.setHint(wordHint);
    }

    public void endGame(){
          makeToast("End of game!");
          player1Score = player2Score = 0;
          setScores();
          dictionary.resetValues();
          tvWord.getText().clear();
          tvWord.setHint("Enter new word!");
    }

    public void setScores() {
        tvPlayer1.setText("PLayer 1: "+player1Score);
        tvPlayer2.setText("Player 2: "+player2Score);
    }
}
