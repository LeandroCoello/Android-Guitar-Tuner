package com.example.leo.tunner.NoteDisplay;

import java.util.ArrayList;
import java.util.Arrays;

public class ConversorType4 extends ConversorType{

    //Hertz
    private static Double BASS_FORTH_STRING = 41.204; //E1
    private static Double BASS_THIRD_STRING = 55.00; //A1
    private static Double BASS_SECOND_STRING = 73.416; //D2
    private static Double BASS_FIRST_STRING = 97.99;  //G2
    Double error = 0.25;

    //STANDARD FOUR-STRING BASS TUNING E1-A1-D2-G2
    public static final ArrayList<Double> STANDARD =  new ArrayList<Double>(Arrays.asList(41.204,55.00,73.416,97.99));
    public static final ArrayList<String> STANDARD_NOTES = new ArrayList<String>(Arrays.asList("E","A","D","G"));
    public static final ArrayList<String> INIT_STANDARD_NOTES = new ArrayList<String> (Arrays.asList("4E","3A","2D","1G"));

    //HALF_DOWN FOUR-STRING BASS TUNING Eb1-Ab1-C#2-F#2
    public static final ArrayList<Double> HALF_DOWN =  new ArrayList<Double>(Arrays.asList(38.89,51.91,69.30,92.50));
    public static final ArrayList<String> HALF_DOWN_NOTES = new ArrayList<String>(Arrays.asList("Eb","Ab","C#","F#"));

    //DROP D FOUR-STRING BASS TUNING D1-A1-D2-G2
    public static final ArrayList<Double> DROP_D =  new ArrayList<Double>(Arrays.asList(36.71,55.00,73.416,97.99));
    public static final ArrayList<String> DROP_D_NOTES = new ArrayList<String>(Arrays.asList("E","A","D","G"));
    public static final ArrayList<String> INIT_DROPD_NOTES = new ArrayList<String> (Arrays.asList("4D","3A","2D","1G"));


    @Override
    public void initialize() {

        freqRangeList = new ArrayList<FreqRange>();
        Double limEA = (forthString + error)+ ((thirdString - error)-(forthString + error))/2;
        Double limAD = (thirdString + error)+ ((secondString - error)-(thirdString + error))/2;
        Double limDG = (secondString + error)+ ((firstString - error)-(secondString + error))/2;


        FreqRange lowE = new FreqRange(0.0,forthString-error,"E",new ArrayList<Integer>(Arrays.asList(4,9)));
        freqRangeList.add(lowE);
        FreqRange e = new FreqRange(forthString-error,forthString+error,"E",new ArrayList<Integer>(Arrays.asList(4,11)));
        freqRangeList.add(e);
        FreqRange highE = new FreqRange(forthString+error,limEA,"E",new ArrayList<Integer>(Arrays.asList(4,10)));
        freqRangeList.add(highE);
        FreqRange lowA = new FreqRange(limEA, thirdString -error,"A",new ArrayList<Integer>(Arrays.asList(3,9)));
        freqRangeList.add(lowA);
        FreqRange a = new FreqRange(thirdString -error, thirdString +error,"A",new ArrayList<Integer>(Arrays.asList(3,11)));
        freqRangeList.add(a);
        FreqRange highA = new FreqRange(thirdString +error,limAD,"A",new ArrayList<Integer>(Arrays.asList(3,10)));
        freqRangeList.add(highA);
        FreqRange lowD = new FreqRange(limAD, secondString - error,"D",new ArrayList<Integer>(Arrays.asList(2,9)));
        freqRangeList.add(lowD);
        FreqRange d = new FreqRange(secondString - error, secondString + error,"D",new ArrayList<Integer>(Arrays.asList(2,11)));
        freqRangeList.add(d);
        FreqRange highD = new FreqRange(secondString + error,limDG,"D",new ArrayList<Integer>(Arrays.asList(2,10)));
        freqRangeList.add(highD);
        FreqRange lowG = new FreqRange(limDG, firstString - error,"G",new ArrayList<Integer>(Arrays.asList(1,9)));
        freqRangeList.add(lowG);
        FreqRange g = new FreqRange(firstString - error, firstString + error,"G",new ArrayList<Integer>(Arrays.asList(1,11)));
        freqRangeList.add(g);
        FreqRange highG = new FreqRange(firstString + error,null,"G",new ArrayList<Integer>(Arrays.asList(1,10)));
        freqRangeList.add(highG);
    }


    public void setTuning(ArrayList<Double> freqs,ArrayList<String> notes){
        setForthString(freqs.get(0));
        setThirdString(freqs.get(1));
        setSecondString(freqs.get(2));
        setFirstString(freqs.get(3));

        initialize();

        int index = 0;
        for(int i = 0; i<notes.size(); i++){

            for(int j=0; j<3;j++){
                freqRangeList.get(index).setNote(notes.get(i));
                index ++;
            }
        }

    }


}
