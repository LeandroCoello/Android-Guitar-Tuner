package com.example.leo.tunner.NoteDisplay;


import com.example.leo.tunner.MainActivity;

public class NoteConversor {

    // [Hertz]
    private Double e2 = 82.41;  //sixth string
    private Double a2 = 110.00; //fifth string
    private Double d3 = 146.83; //forth string
    private Double g3 = 196.00; //third string
    private Double b3 = 246.94; //second string
    private Double e4 = 329.63; //first string
    private Double acceptPerc = 0.004;
    MainActivity mAct;


    public NoteConversor(){

    }

    public String getNote(Float frequency, MainActivity mAct) {
        Double fr = frequency.doubleValue();
        String note = "N/N";

        //No sound
        if (fr.equals(0.0)){
            return note = " ";
        }

        Double e2err = 0.8;//e2 * acceptPerc;

        //Low E2
        if(fr <= e2-e2err ){
            mAct.turnLightOn(6);
            mAct.turnLightOn(7);
            return note = "E";
        }
        //E2
        if ((fr > e2 - e2err) && (fr < e2 + e2err)) {
            mAct.turnLightOn(6);
            mAct.turnLightOn(9);
            return note = "E";
        }

        Double a2err = 0.8;//a2 * acceptPerc;
        Double limEA = (e2+e2err)+ ((a2-e2err)-(e2+e2err))/2;

        //High E2
        if((fr >= e2+e2err) && ((fr <= limEA))){
            mAct.turnLightOn(6);
            mAct.turnLightOn(8);
            return note = "E";
        }

        //Low A2
        if((fr > limEA)&&(fr <= a2-a2err)){
            mAct.turnLightOn(5);
            mAct.turnLightOn(7);
            return note = "A";
        }

        //A2
        if ((fr > a2 - a2err) && (fr < a2 + a2err)){
            mAct.turnLightOn(5);
            mAct.turnLightOn(9);
            return note = "A";
        }

        Double d3err = 0.8;//e2 * acceptPerc;
        Double limAD = (a2+a2err)+ ((d3-d3err)-(a2+a2err))/2;

        //High A2
        if((fr >= a2+a2err)&&(fr <= limAD)){
            mAct.turnLightOn(5);
            mAct.turnLightOn(8);
            return note = "A";
        }

        //Low D3
        if((fr > limAD)&&(fr <= d3-d3err)){
            mAct.turnLightOn(4);
            mAct.turnLightOn(7);
            return note = "D";
        }

        //D3
        if ((fr > d3 - d3err) && (fr <= d3 + d3err)){
            mAct.turnLightOn(4);
            mAct.turnLightOn(9);
            return note = "D";
        }

        Double g3err = g3 * acceptPerc;
        Double limDG = (d3+d3err)+ ((g3-g3err)-(d3+d3err))/2;

        //High D3
        if((fr > d3+d3err)&&(fr <= limDG)){
            mAct.turnLightOn(4);
            mAct.turnLightOn(8);
            return note = "D";
        }

        //Low G3
        if((fr > limDG)&&(fr <= g3-g3err)){
            mAct.turnLightOn(3);
            mAct.turnLightOn(7);
            return note = "G";
        }

        //G3
        if ((fr > g3 - g3err) && (fr <= g3 + g3err)){
            mAct.turnLightOn(3);
            mAct.turnLightOn(9);
            return note = "G";
        }

        Double b3err = b3 * acceptPerc;
        Double limGB = (g3+g3err)+ ((b3-b3err)-(g3+g3err))/2;

        //High G3
        if((fr > g3+g3err)&&(fr <= limGB)){
            mAct.turnLightOn(3);
            mAct.turnLightOn(8);
            return note = "G";
        }

        //Low B3
        if((fr > limGB)&&(fr <= b3-b3err )){
            mAct.turnLightOn(2);
            mAct.turnLightOn(7);
            return note = "B";
        }

        //B3
        if ((fr > b3 - b3err) && (fr <= b3 + b3err)){
            mAct.turnLightOn(2);
            mAct.turnLightOn(9);
            return note = "B";
        }

        Double e4err = e4 * acceptPerc;
        Double limBE = (b3+b3err)+ ((e4-e4err)-(b3+b3err))/2;

        //High B3
        if((fr > b3+b3err)&&(fr <= limBE)){
            mAct.turnLightOn(2);
            mAct.turnLightOn(8);
            return note = "B";
        }

        //Low E4
        if((fr > limBE)&&(fr <= e4-e4err)){
            mAct.turnLightOn(1);
            mAct.turnLightOn(7);
            return note = "E";
        }

        //E4
        if ((fr > e4 - e4err) && (fr <= e4 + e4err)) {
            mAct.turnLightOn(1);
            mAct.turnLightOn(9);
            return note = "E";
        }

        //High E4
        if(fr > e4+e4err) {
            mAct.turnLightOn(1);
            mAct.turnLightOn(8);
            return note = "E";
        }
        return note;
    }

}
