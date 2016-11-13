package com.example.leo.tunner.NoteDisplay;


import com.example.leo.tunner.MainActivity;

import java.util.ArrayList;

public class FreqRange {

    Double lowerLim,upperLim;
    ArrayList<Integer> leds;
    String note;

    public FreqRange(Double lower, Double upper, String notePar, ArrayList<Integer> ledsPar){

        lowerLim = lower;
        upperLim = upper;
        note = notePar;
        leds = ledsPar;


    }

    public boolean inRange(Double freq){
        boolean in = false;

        if (upperLim == null){
            in = in2(freq);
        } else {
            in = in1(freq);
        }

        return  in;
    }

    public boolean in1(Double freq){
        boolean in = false;

       if((freq > lowerLim) && (freq <= upperLim)){
           in = true;
       }

        return in;
    }

    public  boolean in2(Double freq){
       boolean in = false;

        if(freq > lowerLim) in = true;

        return in;
    }

    public String getNote() {
        return note;
    }


    public void turnOnLeds(MainActivity mact){

        for(int i = 0; i< leds.size(); i++) mact.turnLightOn(leds.get(i));
    }


}