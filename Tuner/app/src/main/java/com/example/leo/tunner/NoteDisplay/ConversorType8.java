package com.example.leo.tunner.NoteDisplay;


import java.util.ArrayList;
import java.util.Arrays;

public class ConversorType8 extends ConversorType {

    //Hertz
    private Double eighthString = 49.00;  // g1
    private Double seventhString = 61.74; //b1
    ArrayList<FreqRange> freqRangeList;

    public ConversorType8() {
        freqRangeList = super.getFreqRangeList();

        Double error = 0.25;
        Double limGB = (eighthString + error) + ((seventhString - error) - (eighthString + error))/2;
        Double limBE = (seventhString + error) + ((sixthString - error) - (seventhString + error))/2;

        FreqRange lowG = new FreqRange(0.0,eighthString-error,"G",new ArrayList<Integer>(Arrays.asList(8,9)));
        FreqRange g1 = new FreqRange(eighthString - error, eighthString + error,"G",new ArrayList<Integer>(Arrays.asList(8,11)));
        FreqRange highG = new FreqRange(eighthString + error, limGB,"G",new ArrayList<Integer>(Arrays.asList(8,10)));

        FreqRange lowB = new FreqRange(limGB,seventhString - error,"B",new ArrayList<Integer>(Arrays.asList(7,9)));
        FreqRange b1 = new FreqRange(seventhString - error,seventhString + error,"B",new ArrayList<Integer>(Arrays.asList(8,11)));
        FreqRange highB = new FreqRange(seventhString + error, limBE,"B",new ArrayList<Integer>(Arrays.asList(7,10)));

        FreqRange lowE = new FreqRange(limBE,sixthString-error,"E",new ArrayList<Integer>(Arrays.asList(6,9)));

        freqRangeList.remove(0);
        freqRangeList.add(0,lowG);
        freqRangeList.add(1,g1);
        freqRangeList.add(2,highG);
        freqRangeList.add(3,lowB);
        freqRangeList.add(4,b1);
        freqRangeList.add(5,highB);
        freqRangeList.add(6,lowE);


    }

    @Override
    public ArrayList<FreqRange> getFreqRangeList(){

        return freqRangeList;
    }
}
