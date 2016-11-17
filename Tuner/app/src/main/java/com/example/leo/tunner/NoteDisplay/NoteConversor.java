package com.example.leo.tunner.NoteDisplay;


import com.example.leo.tunner.MainActivity;

import java.util.ArrayList;


public class NoteConversor {


    MainActivity mAct;
    ArrayList<FreqRange> freqRangeList = new ArrayList<FreqRange>();
    ConversorType type;

    public NoteConversor(ConversorType tp){

        type =tp;
        freqRangeList = tp.getFreqRangeList();
    }


    public String getNote(Float frequency, MainActivity mAct){
        Double fr = frequency.doubleValue();
        String note = " ";
        FreqRange noteF = null;

        if(fr.equals(0.0)){
            return note;
        }

        for(int i = 0; i < freqRangeList.size(); i++){

            if (freqRangeList.get(i).inRange(fr)){

                noteF = freqRangeList.get(i);
                break;
            }
        }
        if(noteF !=null) {
            note = noteF.getNote();
            noteF.turnOnLeds(mAct);
        }
        return note;
    }


    public ConversorType getType() {
        return type;
    }



}
