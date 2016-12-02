package com.example.leo.tunner.Activity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.leo.tunner.NoteDisplay.ConversorType;
import com.example.leo.tunner.NoteDisplay.ConversorType4;
import com.example.leo.tunner.NoteDisplay.NoteConversor;
import com.example.leo.tunner.R;

public class TunerType4 {



    public void initializeStrings(Tuner t){

        setUp4Strings(t);

    }

    void setUp4Strings(Tuner t){
        t.setForthS((ImageView)t.findViewById(R.id.forthString));
        t.setThirdS((ImageView) t.findViewById(R.id.thirdString));
        t.setSecondS((ImageView) t.findViewById(R.id.secondString));
        t.setFirstS((ImageView) t.findViewById(R.id.firstString));

        t.setForthTV((TextView) t.findViewById(R.id.textView4));
        t.setThirdTV((TextView) t.findViewById(R.id.textView3));
        t.setSecondTV((TextView) t.findViewById(R.id.textView2));
        t.setFirstTV((TextView) t.findViewById(R.id.textView1));
    }



    public void turnsLightsOff(Tuner t){

    }

    public void inflateMenu(MenuInflater mi, Menu menu){
        mi.inflate(R.menu.main_menu_bass4,menu);

    }

    public boolean onItemSelected(MenuItem selected, Tuner t) {

        switch (selected.getItemId()) {
            case R.id.half_down_tune:
                t.displayToast("Half down tune selected!");
                updateTextViews("half_down",t);

                ConversorType ctype = new ConversorType4();
                ctype.setTuning(ConversorType4.HALF_DOWN, ConversorType4.HALF_DOWN_NOTES);
                t.setNoteConversor(new NoteConversor(ctype));

                return true;

            case R.id.standard_tune:

                t.displayToast("Standard tune selected!");
                updateTextViews("standard",t);

                ConversorType ctype1 = new ConversorType4();
                ctype1.setTuning(ConversorType4.STANDARD, ConversorType4.STANDARD_NOTES);
                t.setNoteConversor(new NoteConversor(ctype1));

                return true;

            case R.id.dropd_tune:
                t.displayToast("Drop D tune selected!");
                updateTextViews("drop_d",t);

                ConversorType ctype2 = new ConversorType4();
                ctype2.setTuning(ConversorType4.DROP_D, ConversorType4.DROP_D_NOTES);
                t.setNoteConversor(new NoteConversor(ctype2));

                return true;

            default:
                return false;
        }
    }


    public void updateTextViews(String tune, Tuner t){


        switch(tune) {

            case "standard":

                t.setTextView(ConversorType4.INIT_STANDARD_NOTES);

                smallDisplay(t);

                t.setTitle("Bass Tuner - Standard");

                break;

            case "half_down":

                t.setTextView(ConversorType4.HALF_DOWN_NOTES);

                bigDisplay(t);

                t.setTitle("Bass Tuner - Half Down");


                break;

            case "drop_d":

                t.setTextView(ConversorType4.INIT_DROPD_NOTES);

                smallDisplay(t);

                t.setTitle("Bass Tuner - Drop D");

                break;
        }
    }

    public void setDefaultTextViews(Tuner t){
        t.setTextView(ConversorType4.INIT_STANDARD_NOTES);
    }

    public void bigDisplay(Tuner t){

        t.getFn().setScaleX(1.5f);
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.CENTER_VERTICAL);
        lp2.addRule(RelativeLayout.ALIGN_START,R.id.frameNote);
        lp2.setMargins(19,0,0,0);
        t.getTextFr().setLayoutParams(lp2);
    }

    public void smallDisplay(Tuner t){
        t.getFn().setScaleX(1.0f);
        RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) t.getTextFr().getLayoutParams();
        lp3.addRule(RelativeLayout.CENTER_VERTICAL);
        lp3.addRule(RelativeLayout.ALIGN_START,R.id.frameNote);
        lp3.leftMargin = 34;
        t.getTextFr().setLayoutParams(lp3);
    }

    public void updateTxtFr(String text,Tuner t) {
        t.setTextFr((TextView) t.findViewById(R.id.textFr));
        t.getTextFr().setText(text);
    }
}
