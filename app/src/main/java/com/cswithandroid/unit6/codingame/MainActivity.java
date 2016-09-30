package com.cswithandroid.unit6.codingame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button bEnter, bDeclare;
    private TextView tvPlayer1,tvPlayer2, tvWord, tvWordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bEnter = (Button) findViewById( R.id.bEnter);
        bDeclare = (Button) findViewById( R.id.bDeclare);

        tvPlayer1 = (TextView) findViewById (R.id.tvPlayer1);
        tvPlayer2 = (TextView) findViewById (R.id.tvPlayer2);
        tvWord = (TextView) findViewById( R.id.Word);
        tvWordList = (TextView) findViewById( R.id.tvWordList);
    }
}
