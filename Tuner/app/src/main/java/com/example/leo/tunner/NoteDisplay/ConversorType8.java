package com.example.leo.tunner.NoteDisplay;


import java.util.ArrayList;
import java.util.Arrays;

public class ConversorType8 extends ConversorType {

    //Hertz
    private Double eighthString = 49.00;  // g1
    private Double seventhString = 61.74; //b1

    public ConversorType8() {
        ArrayList<FreqRange> freqRangeList = super.getFreqRangeList();

        Double error = 0.25;
        Double limGB = (eighthString + error) + ((seventhString - error) - (eighthString + error))/2;
        Double limBE = (seventhString + error) + ((sixthString - error) - (seventhString + error))/2;

        FreqRange lowE = new FreqRange(0.0,eighthString-error,"G",new ArrayList<Integer>(Arrays.asList(8,7)));
    }

    @Override
    public ArrayList<FreqRange> getFreqRangeList(){

        return freqRangeList;
    }
}
