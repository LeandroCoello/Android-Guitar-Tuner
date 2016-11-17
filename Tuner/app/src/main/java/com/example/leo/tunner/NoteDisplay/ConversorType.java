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

    public ConversorType() {

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


        FreqRange lowE = new FreqRange(0.0,sixthString-e2err,"E",new ArrayList<Integer>(Arrays.asList(6,7)));
        freqRangeList.add(lowE);
        FreqRange e = new FreqRange(sixthString-e2err,sixthString+e2err,"E",new ArrayList<Integer>(Arrays.asList(6,9)));
        freqRangeList.add(e);
        FreqRange highE = new FreqRange(sixthString+e2err,limEA,"E",new ArrayList<Integer>(Arrays.asList(6,8)));
        freqRangeList.add(highE);
        FreqRange lowA = new FreqRange(limEA, fifthString -a2err,"A",new ArrayList<Integer>(Arrays.asList(5,7)));
        freqRangeList.add(lowA);
        FreqRange a = new FreqRange(fifthString -a2err, fifthString +a2err,"A",new ArrayList<Integer>(Arrays.asList(5,9)));
        freqRangeList.add(a);
        FreqRange highA = new FreqRange(fifthString +a2err,limAD,"A",new ArrayList<Integer>(Arrays.asList(5,8)));
        freqRangeList.add(highA);
        FreqRange lowD = new FreqRange(limAD, forthString -d3err,"D",new ArrayList<Integer>(Arrays.asList(4,7)));
        freqRangeList.add(lowD);
        FreqRange d = new FreqRange(forthString -d3err, forthString +d3err,"D",new ArrayList<Integer>(Arrays.asList(4,9)));
        freqRangeList.add(d);
        FreqRange highD = new FreqRange(forthString +d3err,limDG,"D",new ArrayList<Integer>(Arrays.asList(4,8)));
        freqRangeList.add(highD);
        FreqRange lowG = new FreqRange(limDG, thirdString -g3err,"G",new ArrayList<Integer>(Arrays.asList(3,7)));
        freqRangeList.add(lowG);
        FreqRange g = new FreqRange(thirdString -g3err, thirdString +g3err,"G",new ArrayList<Integer>(Arrays.asList(3,9)));
        freqRangeList.add(g);
        FreqRange highG = new FreqRange(thirdString +g3err,limGB,"G",new ArrayList<Integer>(Arrays.asList(3,8)));
        freqRangeList.add(highG);
        FreqRange lowB = new FreqRange(limGB, secondString -b3err,"B",new ArrayList<Integer>(Arrays.asList(2,7)));
        freqRangeList.add(lowB);
        FreqRange b = new FreqRange(secondString -b3err, secondString +b3err,"B",new ArrayList<Integer>(Arrays.asList(2,9)));
        freqRangeList.add(b);
        FreqRange highB = new FreqRange(secondString +b3err,limBE,"B",new ArrayList<Integer>(Arrays.asList(2,8)));
        freqRangeList.add(highB);
        FreqRange lowE4 = new FreqRange(limBE, firstString -e4err,"E",new ArrayList<Integer>(Arrays.asList(1,7)));
        freqRangeList.add(lowE4);
        FreqRange efour = new FreqRange(firstString -e4err, firstString +e4err,"E",new ArrayList<Integer>(Arrays.asList(1,9)));
        freqRangeList.add(efour);
        FreqRange highE4 = new FreqRange(firstString +e4err,null,"E",new ArrayList<Integer>(Arrays.asList(1,8)));
        freqRangeList.add(highE4);
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

    public void setEbTuning(){

        setSixthString(77.78); //Eb
        setFifthString(103.8); //Ab
        setForthString(138.6); //C#
        setThirdString(185.0); //F#
        setSecondString(233.1); //Bb
        setFirstString(311.1); //Eb

        for(int i =0; i < freqRangeList.size(); i++){

            switch (freqRangeList.get(i).getNote()){
                case "E":
                    freqRangeList.get(i).setNote("Eb");
                    break;

                case "A":
                    freqRangeList.get(i).setNote("Ab");
                    break;

                case "D":
                    freqRangeList.get(i).setNote("C#");
                    break;

                case "G":
                    freqRangeList.get(i).setNote("F#");
                    break;

                case "B":
                    freqRangeList.get(i).setNote("Bb");
                    break;
            }

        }

    }

}
