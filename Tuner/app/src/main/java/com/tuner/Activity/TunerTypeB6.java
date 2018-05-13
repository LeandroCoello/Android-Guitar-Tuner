package com.tuner.Activity;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tuner.NoteDisplay.ConversorType;
import com.tuner.NoteDisplay.NoteConversor;
import com.tuner.R;
import com.tuner.Activity.TunerType6;

import java.util.ArrayList;
import java.util.Arrays;

public class TunerTypeB6 extends TunerType6 {

    //STANDARD SIX-STRING BASS TUNING B0-E1-A1-D2-G2-C3
    public static final ArrayList<Double> STANDARD_BASS =  new ArrayList<Double>(Arrays.asList(30.87,41.204,55.00,73.416,97.99,130.80));
    public static final ArrayList<String> STANDARD_BASS_NOTES = new ArrayList<String>(Arrays.asList("B","E","A","D","G","C"));
    public static final ArrayList<String> INIT_STANDARD_BASS_NOTES = new ArrayList<String> (Arrays.asList("6B","5E","4A","3D","2G","1C"));

    //HALF DOWN SIX-STRING BASS TUNING Bb0-Eb1-Ab1-C#2-F#2-B2
    public static final ArrayList<Double> HALF_DOWN_6BASS =  new ArrayList<Double>(Arrays.asList(29.14,38.89,51.91,69.30,92.50,123.5));
    public static final ArrayList<String> HALF_DOWN_6BASS_NOTES = new ArrayList<String>(Arrays.asList("Bb","Eb","Ab","C#","F#","B"));
    public static final ArrayList<String> INIT_HALF_DOWN_6BASS_NOTES = new ArrayList<String>(Arrays.asList("Bb","Eb","Ab","C#","F#"," B "));


    //STANDARD FIVE-STRING BASS TUNING G0-B0-E1-A1-D2-G2
    public static final ArrayList<Double> STANDARD_FIVES_BASS =  new ArrayList<Double>(Arrays.asList(24.50,30.87,41.204,55.00,73.416,97.99));
    public static final ArrayList<String> STANDARD_FIVES_BASS_NOTES = new ArrayList<String>(Arrays.asList("G","B","E","A","D","G"));
    public static final ArrayList<String> INIT_STANDARD_FIVES_BASS_NOTES = new ArrayList<String> (Arrays.asList("6G","5B","4E","3A","2D","1G"));

    //HALF DOWN FIVE-STRING BASS TUNING F#0-Bb0-Eb1-Ab1-C#2-F#2
    public static final ArrayList<Double> HALF_DOWN_5BASS =  new ArrayList<Double>(Arrays.asList(23.12,29.14,38.89,51.91,69.30,92.50));
    public static final ArrayList<String> HALF_DOWN_5BASS_NOTES = new ArrayList<String>(Arrays.asList("Bb","Eb","Ab","C#","F#","B"));
    public static final ArrayList<String> INIT_HALF_DOWN_5BASS_NOTES = new ArrayList<String>(Arrays.asList("Bb","Eb","Ab","C#","F#"," B "));


    //HIGH FIVE-STRING BASS TUNING B0-E1-A1-D2-G2-C3
    public static final ArrayList<Double> TENOR_FIVES_BASS =  new ArrayList<Double>(Arrays.asList(24.50,41.204,55.00,73.416,97.99,130.80));
    public static final ArrayList<String> TENOR_FIVES_BASS_NOTES = new ArrayList<String>(Arrays.asList("B","E","A","D","G","C"));
    public static final ArrayList<String> INIT_TENOR_FIVES_BASS_NOTES = new ArrayList<String> (Arrays.asList("6B","5E","4A","3D","2G","1C"));



    @Override
    public void inflateMenu(MenuInflater mi, Menu menu){
        mi.inflate(R.menu.main_menu_bass6,menu);
    }

    @Override
    public void initializeStrings(Tuner t) {
        super.initializeStrings(t);


    }

    @Override
    public boolean onItemSelected(MenuItem selected, Tuner t) {

        switch (selected.getItemId()) {

            case R.id.standard6_tune:

            t.displayToast("Standard six strings selected!");
            updateTextViews("standard6",t);

            ConversorType ctype = new ConversorType();
            ctype.setTuning(STANDARD_BASS,STANDARD_BASS_NOTES);
            t.setNoteConversor(new NoteConversor(ctype));

            return true;

            case R.id.half_down6_tune:
                t.displayToast("Half down 6 strings selected!");
                updateTextViews("half_down6",t);

                ConversorType ctype1 = new ConversorType();
                ctype1.setTuning(HALF_DOWN_6BASS, HALF_DOWN_6BASS_NOTES);
                t.setNoteConversor(new NoteConversor(ctype1));

                return true;

            case R.id.standard5_tune:
                t.displayToast("Standard five strings selected!");
                updateTextViews("standard5",t);

                ConversorType ctype2 = new ConversorType();
                ctype2.setTuning(STANDARD_FIVES_BASS, STANDARD_FIVES_BASS_NOTES);
                t.setNoteConversor(new NoteConversor(ctype2));

                return true;

            case R.id.half_down5_tune:
                t.displayToast("Half down 5 strings selected!");
                updateTextViews("half_down5",t);

                ConversorType ctype3 = new ConversorType();
                ctype3.setTuning(HALF_DOWN_5BASS,HALF_DOWN_5BASS_NOTES);
                t.setNoteConversor(new NoteConversor(ctype3));

                return true;

            case R.id.tenor5_tune:
                t.displayToast("High five strings selected!");
                updateTextViews("tenor_five",t);

                ConversorType ctype4 = new ConversorType();
                ctype4.setTuning(TENOR_FIVES_BASS, TENOR_FIVES_BASS_NOTES);
                t.setNoteConversor(new NoteConversor(ctype4));

                return true;

            default:
                return false;
        }
    }


    public void updateTextViews(String tune, Tuner t){


        switch(tune) {

            case "standard6":

                t.setTextView(INIT_STANDARD_BASS_NOTES);

                smallDisplay(t);

                t.setTitle("Bass Tuner - Standard");

                break;

            case "standard5":

                t.setTextView(INIT_STANDARD_FIVES_BASS_NOTES);

                smallDisplay(t);

                t.setTitle("Bass Tuner - Standard");

                break;

            case "half_down6":

                t.setTextView(INIT_HALF_DOWN_6BASS_NOTES);

                bigDisplay(t);

                t.setTitle("Bass Tuner - Half Down Six");

                break;

            case "half_down5":

                t.setTextView(INIT_HALF_DOWN_5BASS_NOTES);

                bigDisplay(t);

                t.setTitle("Bass Tuner - Half Down Five");


                break;

            case "tenor_five":

                t.setTextView(INIT_TENOR_FIVES_BASS_NOTES);

                smallDisplay(t);

                t.setTitle("Bass Tuner - Tenor Five");


                break;
        }

    }
    public void setDefaultTextViews(Tuner t){
        t.setTextView(INIT_STANDARD_BASS_NOTES);
    }

    @Override
    public void updateTxtFr(String text,Tuner t) {

        t.setTextFr((TextView) t.findViewById(R.id.textFr));
        t.getTextFr().setText(text);

        if(text.length()==1 && t.getFn().getScaleX()==1.5f){
            RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp2.addRule(RelativeLayout.CENTER_VERTICAL);
            lp2.addRule(RelativeLayout.ALIGN_START,R.id.frameNote);
            lp2.setMargins(35,0,0,0);
            t.getTextFr().setLayoutParams(lp2);
        }
        if(text.length()==2 && t.getFn().getScaleX()==1.5f){

            bigDisplay(t);
        }

    }
}
