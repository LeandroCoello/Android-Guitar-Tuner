package com.example.leo.tunner.NoteDisplay;

import java.util.ArrayList;
import java.util.Arrays;

public class ConversorType4 extends ConversorType{

    //Hertz
    private static Double BASS_FIFTH_STRING = 30.87; //B0
    private static Double BASS_FORTH_STRING = 41.204; //E1
    private static Double BASS_THIRD_STRING = 55.00; //A1
    private static Double BASS_SECOND_STRING = 73.416; //D2
    private static Double BASS_FIRST_STRING = 97.99;  //G2
    Double error = 0.25;

    public ConversorType4() {

        setFirstString(BASS_FIRST_STRING);
        setSecondString(BASS_SECOND_STRING);
        setThirdString(BASS_THIRD_STRING);
        setForthString(BASS_FORTH_STRING);

        ArrayList<FreqRange> freqRangeList = new ArrayList<FreqRange>();
        Double limEA = (forthString + error)+ ((thirdString - error)-(forthString + error))/2;
        Double limAD = (thirdString + error)+ ((secondString - error)-(thirdString + error))/2;
        Double limDG = (secondString + error)+ ((firstString - error)-(secondString + error))/2;


        FreqRange lowE = new FreqRange(0.0,forthString-error,"E",new ArrayList<Integer>(Arrays.asList(4,7)));
        freqRangeList.add(lowE);
        FreqRange e = new FreqRange(forthString-error,forthString+error,"E",new ArrayList<Integer>(Arrays.asList(4,9)));
        freqRangeList.add(e);
        FreqRange highE = new FreqRange(forthString+error,limEA,"E",new ArrayList<Integer>(Arrays.asList(4,8)));
        freqRangeList.add(highE);
        FreqRange lowA = new FreqRange(limEA, thirdString -error,"A",new ArrayList<Integer>(Arrays.asList(3,7)));
        freqRangeList.add(lowA);
        FreqRange a = new FreqRange(thirdString -error, thirdString +error,"A",new ArrayList<Integer>(Arrays.asList(3,9)));
        freqRangeList.add(a);
        FreqRange highA = new FreqRange(thirdString +error,limAD,"A",new ArrayList<Integer>(Arrays.asList(3,8)));
        freqRangeList.add(highA);
        FreqRange lowD = new FreqRange(limAD, secondString - error,"D",new ArrayList<Integer>(Arrays.asList(2,7)));
        freqRangeList.add(lowD);
        FreqRange d = new FreqRange(secondString - error, secondString + error,"D",new ArrayList<Integer>(Arrays.asList(2,9)));
        freqRangeList.add(d);
        FreqRange highD = new FreqRange(secondString + error,limDG,"D",new ArrayList<Integer>(Arrays.asList(2,8)));
        freqRangeList.add(highD);
        FreqRange lowG = new FreqRange(limDG, firstString - error,"G",new ArrayList<Integer>(Arrays.asList(1,7)));
        freqRangeList.add(lowG);
        FreqRange g = new FreqRange(firstString - error, firstString + error,"G",new ArrayList<Integer>(Arrays.asList(1,9)));
        freqRangeList.add(g);
        FreqRange highG = new FreqRange(firstString + error,null,"G",new ArrayList<Integer>(Arrays.asList(1,8)));
        freqRangeList.add(highG);
    }

    @Override
    public ArrayList<FreqRange> getFreqRangeList(){

       return freqRangeList;
    }
}
