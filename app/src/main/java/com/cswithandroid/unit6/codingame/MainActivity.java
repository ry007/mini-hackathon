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

                if(USER1_TURN)
                  LOCK1 = true;
                else
                  LOCK2 = true;

                if(LOCK1 && LOCK2)
                    endGame();

            }
        });
    }
    private boolean checkValidWord(TextView tvWord){
        if( dictionary.getIndex()> dictionary.getCount()){

        }else{

        }
        return false;
    }

    public void changeTurn(){

      if(USER1_TURN == true){
        if(LOCK2 != true){
          USER1_TURN = false;
          USER2_TURN = true;
        }
      }
      else{
        if(LOCK1 != true){
          USER2_TURN = false;
          USER1_TURN = true;
        }
      }
    }

    public void endGame(){

    }
}
