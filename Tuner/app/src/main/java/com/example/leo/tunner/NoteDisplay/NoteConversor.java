package com.example.leo.tunner.NoteDisplay;


import com.example.leo.tunner.MainActivity;

public class NoteConversor {

    // frequencies units: Hertz
    private Double e2 = 82.41;  //sixth string
    private Double a2 = 110.00; //fifth string
    private Double d3 = 146.83; //forth string
    private Double g3 = 196.00; //third string
    private Double b3 = 246.94; //second string
    private Double e4 = 329.63; //first string
    private Double acceptPerc = 0.1;
    MainActivity mAct;


    public NoteConversor(){

    }

    public String getNote(Float frequency, MainActivity mAct) {
        Double fr = frequency.doubleValue();
        String note;



        Double e2err = e2 * acceptPerc;
        if ((fr > e2 - e2err) && (fr < e2 + e2err)) {
            //mAct.turnLightOn(6);
            return note = "E";
        }

        Double a2err = a2 * acceptPerc;
        if ((fr > a2 - a2err) && (fr < a2 + a2err)){
            //mAct.turnLightOn(5);
            return note = "A";
        }

        Double d3err = e2 * acceptPerc;
        if ((fr > e2 - d3err) && (fr < e2 + d3err)){
            //mAct.turnLightOn(4);
            return note = "D";
        }

        Double g3err = g3 * acceptPerc;
        if ((fr > g3 - g3err) && (fr < g3 + g3err)){
            //mAct.turnLightOn(3);
            return note = "G";
        }

        Double b3err = b3 * acceptPerc;
        if ((fr > b3 - b3err) && (fr < b3 + b3err)){
            //mAct.turnLightOn(2);
            return note = "B";
        }

        Double e4err = e4 * acceptPerc;
        if ((fr > e4 - e4err) && (fr < e4 + e4err)) {
            //mAct.turnLightOn(1);
            return note = "E";
        } else
            return note = "Ninguna";
    }

}
