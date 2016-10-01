package com.cswithandroid.unit6.codingame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Created by sahiljain112 on 01/10/16.
 */

public class TreeDictionary {

    TreeSet<String> t;
    LinkedHashSet<String> wordList;

    public TreeDictionary(InputStream inputStream) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
        }

        t = new TreeSet<>();
        wordList = new LinkedHashSet<>();
    }

    public void add( String word){
        t.add(word);
    }

    // Hash Set operations
    public boolean isGoodWord(String word){
        return !wordList.contains(word);
    }

    public boolean addToHashSet(String word){

        if(isGoodWord(word)){

        }
        return false;
    }
}
