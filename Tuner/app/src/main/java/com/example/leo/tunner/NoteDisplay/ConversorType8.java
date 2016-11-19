package com.example.leo.tunner.NoteDisplay;


import com.example.leo.tunner.Activity.Tuner;

import java.util.ArrayList;
import java.util.Arrays;

public class ConversorType8 extends ConversorType {

    //Hertz
    private Double eighthString = 49.00;  // g1
    private Double seventhString = 61.74; //b1
    ArrayList<FreqRange> freqRangeList;

    public static final ArrayList<Double> STANDARD =  new ArrayList<Double>(Arrays.asList(49.00,61.74,82.41,110.00,146.83,196.00,246.94,329.63));
    public static final ArrayList<String> STANDARD_NOTES = new ArrayList<String>(Arrays.asList("G","B","E","A","D","G","B","E"));
    public static final ArrayList<String> INIT_STANDARD_NOTES = new ArrayList<String> (Arrays.asList("8G","7B","6E","5A","4D","3G","2B","1E"));

    public ConversorType8() {

        ConversorType ct = new ConversorType();
        ct.initialize();

        freqRangeList = ct.getFreqRangeList();

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
