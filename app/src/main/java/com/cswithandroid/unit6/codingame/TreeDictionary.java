package com.cswithandroid.unit6.codingame;

import android.content.Intent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Created by sahiljain112 on 01/10/16.
 */

public class TreeDictionary {

    TreeSet<String> t;
    TreeSet<String> wordSet;
    ArrayList<String> wordList;
    HashMap<Character,Integer> scores;
    int count;
    int currentIndex;

    public TreeDictionary(InputStream inputStream) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        t = new TreeSet<>();
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            word = word.toLowerCase();
            t.add(word);
        }

        wordSet = new TreeSet<>();
        wordList = new ArrayList<>();
        scores = new HashMap<>();
        count = 0;
        currentIndex = 0;
        initializeScores();
    }

    public void initializeScores(){

        // Score allotment: Based on popular game Wordament!
        scores.put('a',3);
        scores.put('b',4);
        scores.put('c',5);
        scores.put('d',3);
        scores.put('e',3);
        scores.put('f',4);
        scores.put('g',3);
        scores.put('h',3);
        scores.put('i',3);
        scores.put('j',3);
        scores.put('k',3);
        scores.put('l',3);
        scores.put('m',3);
        scores.put('n',3);
        scores.put('o',3);
        scores.put('p',3);
        scores.put('q',3);
        scores.put('r',3);
        scores.put('s',3);
        scores.put('t',3);
        scores.put('u',3);
        scores.put('v',3);
        scores.put('w',3);
        scores.put('x',3);
        scores.put('y',3);
        scores.put('z',3);

    }
    // Set operations
    public boolean isGoodWord(String word){

        char firstChar = word.charAt(0);
        if(wordList.size() > 0){
          String lastWord = wordList.get(wordList.size() - 1);
          char lastChar = lastWord.charAt( lastWord.length() - 1);
          if(firstChar != lastChar)
            return false;
        }
         return !wordSet.contains(word); // && t.contains(word);
    }

    public boolean addNewWord(String word){

        word = word.toLowerCase();
        if(isGoodWord(word)){
            wordSet.add(word);
            wordList.add(word);
            count = count + 1;
            return true;
        }
        return false;
    }

    public void resetValues(){
        wordList = new ArrayList<>();
        wordSet = new TreeSet<>();
    }

    public boolean checkWordExistence(String word){

        word = word.toLowerCase();
        if(wordList.isEmpty())
            return false;

        if(wordList.get(currentIndex).equals(word)){
            currentIndex = currentIndex + 1;
            return true;
        }
        else {
            currentIndex = 0;
            return false;
        }
    }

    public int computeScore(String word){

        int scoreValue = 0;
        word = word.toLowerCase();
        for(int i = 0 ; i < word.length(); i++){
            char c = word.charAt(i);
            scoreValue += scores.get(c);
        }

        return scoreValue;
    }

    public void resetCurrentIndex(){
        currentIndex = 0;
    }

    public int getCount(){
        return count;
    }

    public int getIndex(){
        return currentIndex;
    }

}
