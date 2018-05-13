package com.tuner.NoteDisplay;


import java.util.ArrayList;
import java.util.Arrays;

public class ConversorType8 extends ConversorType {

    //Hertz
    private Double eighthString = 49.00;  // g1
    private Double seventhString = 61.74; //b1
    ArrayList<FreqRange> freqRangeList;


    //STANDARD SEVEN/EIGHT-STRING GUITAR TUNING  F#1-B1-E2-A2-D3-G3-B3-E4
    public static final ArrayList<Double> STANDARD =  new ArrayList<Double>(Arrays.asList(49.00,61.74,82.41,110.00,146.83,196.00,246.94,329.63));
    public static final ArrayList<String> STANDARD_NOTES = new ArrayList<String>(Arrays.asList("F#","B","E","A","D","G","B","E"));
    public static final ArrayList<String> INIT_STANDARD_NOTES = new ArrayList<String>(Arrays.asList("F#"," B "," E "," A "," D "," G "," B "," E "));


    //HALF DOWN SEVEN/EIGHT-STRING GUITAR TUNING  F1-Bb1-Eb2-Ab2-C#3-F#3-Bb3-Eb4
    public static final ArrayList<Double> HALF_DOWN =  new ArrayList<Double>(Arrays.asList(43.65,58.27,77.78,103.8,138.6,185.0,233.1,311.1));
    public static final ArrayList<String> HALF_DOWN_NOTES = new ArrayList<String>(Arrays.asList("F","Bb","Eb","Ab","C#","F#","Bb","Eb"));
    public static final ArrayList<String> INIT_HALF_DOWN_NOTES = new ArrayList<String>(Arrays.asList(" F ","Bb","Eb","Ab","C#","F#","Bb","Eb"));

    //DROP E EIGHT-STRING GUITAR TUNING  E1-B1-E2-A2-D3-G3-B3-E4
    public static final ArrayList<Double> DROP_E =  new ArrayList<Double>(Arrays.asList(41.20,61.74,82.41,110.00,146.83,196.00,246.94,329.63));
    public static final ArrayList<String> DROP_E_NOTES = new ArrayList<String>(Arrays.asList("E","B","E","A","D","G","B","E"));
    public static final ArrayList<String> INIT_DROPE_NOTES = new ArrayList<String> (Arrays.asList("8E","7B","6E","5A","4D","3G","2B","1E"));

    //DROP A SEVEN-STRING GUITAR TUNING  F#1-A1-E2-A2-D3-G3-B3-E4
    public static final ArrayList<Double> DROP_A =  new ArrayList<Double>(Arrays.asList(49.00,55.00,82.41,110.00,146.83,196.00,246.94,329.63));
    public static final ArrayList<String> DROPA_NOTES = new ArrayList<String>(Arrays.asList("F#","A","E","A","D","G","B","E"));
    public static final ArrayList<String> INIT_DROPA_NOTES = new ArrayList<String>(Arrays.asList("F#"," A "," E "," A "," D "," G "," B "," E "));



    @Override
    public void initialize() {

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

    @Override
    public void setTuning(ArrayList<Double> freqs,ArrayList<String> notes){
        eighthString = freqs.get(0);
        seventhString = freqs.get(1);
        setSixthString(freqs.get(2));
        setFifthString(freqs.get(3));
        setForthString(freqs.get(4));
        setThirdString(freqs.get(5));
        setSecondString(freqs.get(6));
        setFirstString(freqs.get(7));

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
