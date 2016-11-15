package com.example.leo.tunner.NoteDisplay;


import com.example.leo.tunner.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class NoteConversor {

    // [Hertz]
    private Double e2 = 82.41;  //sixth string
    private Double a2 = 110.00; //fifth string
    private Double d3 = 146.83; //forth string
    private Double g3 = 196.00; //third string
    private Double b3 = 246.94; //second string
    private Double e4 = 329.63; //first string
    MainActivity mAct;
    ArrayList<FreqRange> freqRangeList = new ArrayList<FreqRange>();


    public NoteConversor(){

        initialize();
    }

    private void initialize(){
        Double e2err = 0.45;
        Double a2err = 0.45;
        Double limEA = (e2+e2err)+ ((a2-e2err)-(e2+e2err))/2;
        Double d3err = 0.55;
        Double limAD = (a2+a2err)+ ((d3-d3err)-(a2+a2err))/2;
        Double g3err = 0.55;
        Double limDG = (d3+d3err)+ ((g3-g3err)-(d3+d3err))/2;
        Double b3err = 0.35;
        Double limGB = (g3+g3err)+ ((b3-b3err)-(g3+g3err))/2;
        Double e4err = 0.45;
        Double limBE = (b3+b3err)+ ((e4-e4err)-(b3+b3err))/2;


        FreqRange lowE = new FreqRange(0.0,e2-e2err,"E",new ArrayList<Integer>(Arrays.asList(6,7)));
        freqRangeList.add(lowE);
        FreqRange e = new FreqRange(e2-e2err,e2+e2err,"E",new ArrayList<Integer>(Arrays.asList(6,9)));
        freqRangeList.add(e);
        FreqRange highE = new FreqRange(e2+e2err,limEA,"E",new ArrayList<Integer>(Arrays.asList(6,8)));
        freqRangeList.add(highE);
        FreqRange lowA = new FreqRange(limEA,a2-a2err,"A",new ArrayList<Integer>(Arrays.asList(5,7)));
        freqRangeList.add(lowA);
        FreqRange a = new FreqRange(a2-a2err,a2+a2err,"A",new ArrayList<Integer>(Arrays.asList(5,9)));
        freqRangeList.add(a);
        FreqRange highA = new FreqRange(a2+a2err,limAD,"A",new ArrayList<Integer>(Arrays.asList(5,8)));
        freqRangeList.add(highA);
        FreqRange lowD = new FreqRange(limAD,d3-d3err,"D",new ArrayList<Integer>(Arrays.asList(4,7)));
        freqRangeList.add(lowD);
        FreqRange d = new FreqRange(d3-d3err,d3+d3err,"D",new ArrayList<Integer>(Arrays.asList(4,9)));
        freqRangeList.add(d);
        FreqRange highD = new FreqRange(d3+d3err,limDG,"D",new ArrayList<Integer>(Arrays.asList(4,8)));
        freqRangeList.add(highD);
        FreqRange lowG = new FreqRange(limDG,g3-g3err,"G",new ArrayList<Integer>(Arrays.asList(3,7)));
        freqRangeList.add(lowG);
        FreqRange g = new FreqRange(g3-g3err,g3+g3err,"G",new ArrayList<Integer>(Arrays.asList(3,9)));
        freqRangeList.add(g);
        FreqRange highG = new FreqRange(g3+g3err,limGB,"G",new ArrayList<Integer>(Arrays.asList(3,8)));
        freqRangeList.add(highG);
        FreqRange lowB = new FreqRange(limGB,b3-b3err,"B",new ArrayList<Integer>(Arrays.asList(2,7)));
        freqRangeList.add(lowB);
        FreqRange b = new FreqRange(b3-b3err,b3+b3err,"B",new ArrayList<Integer>(Arrays.asList(2,9)));
        freqRangeList.add(b);
        FreqRange highB = new FreqRange(b3+b3err,limBE,"B",new ArrayList<Integer>(Arrays.asList(2,8)));
        freqRangeList.add(highB);
        FreqRange lowE4 = new FreqRange(limBE,e4-e4err,"E",new ArrayList<Integer>(Arrays.asList(1,7)));
        freqRangeList.add(lowE4);
        FreqRange efour = new FreqRange(e4-e4err,e4+e4err,"E",new ArrayList<Integer>(Arrays.asList(1,9)));
        freqRangeList.add(efour);
        FreqRange highE4 = new FreqRange(e4+e4err,null,"E",new ArrayList<Integer>(Arrays.asList(1,8)));
        freqRangeList.add(highE4);
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




}
