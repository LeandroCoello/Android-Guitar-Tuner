package com.example.leo.tunner.NoteDisplay;

import java.util.ArrayList;
import java.util.Arrays;


public class ConversorType  {

    //Hertz
    Double sixthString = 82.41;
    Double fifthString = 110.00;
    Double forthString = 146.83;
    Double thirdString = 196.00;
    Double secondString = 246.94;
    Double firstString = 329.63;
    ArrayList<FreqRange> freqRangeList = new ArrayList<FreqRange>();

    public static final ArrayList<Double> STANDARD =  new ArrayList<Double>(Arrays.asList(82.41,110.00,146.83,196.00,246.94,329.63));
    public static final ArrayList<String> STANDARD_NOTES = new ArrayList<String>(Arrays.asList("E","A","D","G","B","E"));
    public static final ArrayList<String> INIT_STANDARD_NOTES = new ArrayList<String> (Arrays.asList("6E","5A","4D","3G","2B","1E"));


    public static final ArrayList<Double> HALF_DOWN =  new ArrayList<Double> (Arrays.asList(77.78,103.8,138.6,185.0,233.1,311.1));
    public static final ArrayList<String> HALF_DOWN_NOTES = new ArrayList<String> (Arrays.asList("Eb","Ab","C#","F#","Bb","Eb"));

    public static final ArrayList<Double> DROP_D =  new ArrayList<Double>(Arrays.asList(73.42,110.00,146.83,196.00,246.94,329.63));
    public static final ArrayList<String> DROP_D_NOTES = new ArrayList<String>(Arrays.asList("D","A","D","G","B","E"));
    public static final ArrayList<String> INIT_DROPD_NOTES = new ArrayList<String> (Arrays.asList("6D","5A","4D","3G","2B","1E"));



    public void initialize(){
        Double e2err = 0.25;
        Double a2err = 0.25;
        Double limEA = (sixthString+e2err)+ ((fifthString -e2err)-(sixthString+e2err))/2;
        Double d3err = 0.35;
        Double limAD = (fifthString +a2err)+ ((forthString -d3err)-(fifthString +a2err))/2;
        Double g3err = 0.45;
        Double limDG = (forthString +d3err)+ ((thirdString -g3err)-(forthString +d3err))/2;
        Double b3err = 0.35;
        Double limGB = (thirdString +g3err)+ ((secondString -b3err)-(thirdString +g3err))/2;
        Double e4err = 0.35;
        Double limBE = (secondString +b3err)+ ((firstString -e4err)-(secondString +b3err))/2;


        FreqRange lowE = new FreqRange(0.0,sixthString-e2err,"E",new ArrayList<Integer>(Arrays.asList(6,9)));
        freqRangeList.add(lowE);
        FreqRange e = new FreqRange(sixthString-e2err,sixthString+e2err,"E",new ArrayList<Integer>(Arrays.asList(6,11)));
        freqRangeList.add(e);
        FreqRange highE = new FreqRange(sixthString+e2err,limEA,"E",new ArrayList<Integer>(Arrays.asList(6,10)));
        freqRangeList.add(highE);
        FreqRange lowA = new FreqRange(limEA, fifthString -a2err,"A",new ArrayList<Integer>(Arrays.asList(5,9)));
        freqRangeList.add(lowA);
        FreqRange a = new FreqRange(fifthString -a2err, fifthString +a2err,"A",new ArrayList<Integer>(Arrays.asList(5,11)));
        freqRangeList.add(a);
        FreqRange highA = new FreqRange(fifthString +a2err,limAD,"A",new ArrayList<Integer>(Arrays.asList(5,10)));
        freqRangeList.add(highA);
        FreqRange lowD = new FreqRange(limAD, forthString -d3err,"D",new ArrayList<Integer>(Arrays.asList(4,9)));
        freqRangeList.add(lowD);
        FreqRange d = new FreqRange(forthString -d3err, forthString +d3err,"D",new ArrayList<Integer>(Arrays.asList(4,11)));
        freqRangeList.add(d);
        FreqRange highD = new FreqRange(forthString +d3err,limDG,"D",new ArrayList<Integer>(Arrays.asList(4,10)));
        freqRangeList.add(highD);
        FreqRange lowG = new FreqRange(limDG, thirdString -g3err,"G",new ArrayList<Integer>(Arrays.asList(3,9)));
        freqRangeList.add(lowG);
        FreqRange g = new FreqRange(thirdString -g3err, thirdString +g3err,"G",new ArrayList<Integer>(Arrays.asList(3,11)));
        freqRangeList.add(g);
        FreqRange highG = new FreqRange(thirdString +g3err,limGB,"G",new ArrayList<Integer>(Arrays.asList(3,10)));
        freqRangeList.add(highG);
        FreqRange lowB = new FreqRange(limGB, secondString -b3err,"B",new ArrayList<Integer>(Arrays.asList(2,9)));
        freqRangeList.add(lowB);
        FreqRange b = new FreqRange(secondString -b3err, secondString +b3err,"B",new ArrayList<Integer>(Arrays.asList(2,11)));
        freqRangeList.add(b);
        FreqRange highB = new FreqRange(secondString +b3err,limBE,"B",new ArrayList<Integer>(Arrays.asList(2,10)));
        freqRangeList.add(highB);
        FreqRange lowE4 = new FreqRange(limBE, firstString -e4err,"E",new ArrayList<Integer>(Arrays.asList(1,9)));
        freqRangeList.add(lowE4);
        FreqRange efour = new FreqRange(firstString -e4err, firstString +e4err,"E",new ArrayList<Integer>(Arrays.asList(1,11)));
        freqRangeList.add(efour);
        FreqRange highE4 = new FreqRange(firstString +e4err,null,"E",new ArrayList<Integer>(Arrays.asList(1,10)));
        freqRangeList.add(highE4);
    }


    public void setTuning(ArrayList<Double> freqs,ArrayList<String> notes){
        setSixthString(freqs.get(0));
        setFifthString(freqs.get(1));
        setForthString(freqs.get(2));
        setThirdString(freqs.get(3));
        setSecondString(freqs.get(4));
        setFirstString(freqs.get(5));

        initialize();

        int index = 0;
        for(int i = 0; i<notes.size(); i++){

            for(int j=0; j<3;j++){
                freqRangeList.get(index).setNote(notes.get(i));
                index ++;
            }
        }

    }


    public ArrayList<FreqRange> getFreqRangeList(){

        return freqRangeList;
    }


    public void setSixthString(Double sixthString) {
        this.sixthString = sixthString;
    }

    public void setFifthString(Double fifthString) {
        this.fifthString = fifthString;
    }

    public void setForthString(Double forthString) {
        this.forthString = forthString;
    }

    public void setThirdString(Double thirdString) {
        this.thirdString = thirdString;
    }

    public void setSecondString(Double secondString) {
        this.secondString = secondString;
    }

    public void setFirstString(Double firstString) {
        this.firstString = firstString;
    }


}
