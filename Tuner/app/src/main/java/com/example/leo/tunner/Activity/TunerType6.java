package com.example.leo.tunner.Activity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.leo.tunner.NoteDisplay.ConversorType;
import com.example.leo.tunner.NoteDisplay.NoteConversor;
import com.example.leo.tunner.R;

import java.util.ArrayList;


public class TunerType6 extends TunerType4 {

    @Override
    public void initializeStrings(Tuner t){

        super.initializeStrings(t);
        setUp6Strings(t);

    }
    private void setUp6Strings(Tuner t){
        t.setSixthS((ImageView)t.findViewById(R.id.sixthString));
        t.setFifthS((ImageView)t.findViewById(R.id.fifthString));

        t.setSixthTV((TextView) t.findViewById(R.id.textView6));
        t.setFifthTV((TextView) t.findViewById(R.id.textView5));
    }

    @Override
    public void turnsLightsOff(Tuner t) {

        t.getSixthS().clearColorFilter();
        t.getFifthS().clearColorFilter();
    }

    @Override
    public void inflateMenu(MenuInflater mi, Menu menu){
        mi.inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onItemSelected(MenuItem selected, Tuner t) {

        switch (selected.getItemId()) {
            case R.id.half_down_tune:
                t.displayToast("Half down tune selected!");
                updateTextViews("half_down",t);

                ConversorType ctype = new ConversorType();
                ctype.setTuning(ConversorType.HALF_DOWN, ConversorType.HALF_DOWN_NOTES);
                t.setNoteConversor(new NoteConversor(ctype));

                return true;

            case R.id.standard_tune:

                t.displayToast("Standard tune selected!");
                updateTextViews("standard",t);

                ConversorType ctype1 = new ConversorType();
                ctype1.initialize();
                t.setNoteConversor(new NoteConversor(ctype1));

                return true;

            case R.id.open_tune:
                t.displayToast("Open tune selected!");
                updateTextViews("standard",t);
                return true;

            case R.id.dropd_tune:
                t.displayToast("Drop D tune selected!");
                updateTextViews("drop_d",t);

                ConversorType ctype2 = new ConversorType();
                ctype2.setTuning(ConversorType.DROP_D, ConversorType.DROP_D_NOTES);
                t.setNoteConversor(new NoteConversor(ctype2));

                return true;

            default:
                return false;
        }
    }


    public void updateTextViews(String tune, Tuner t){


        switch(tune) {

            case "standard":

                t.setTextView(ConversorType.INIT_STANDARD_NOTES);

                t.getFn().setScaleX(1.0f);


                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) t.getTextFr().getLayoutParams();
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                lp.addRule(RelativeLayout.ALIGN_START,R.id.frameNote);
                lp.leftMargin = 44;
                t.getTextFr().setLayoutParams(lp);

                break;

            case "half_down":

                t.setTextView(ConversorType.HALF_DOWN_NOTES);

                t.getFn().setScaleX(1.5f);


                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp2.addRule(RelativeLayout.CENTER_VERTICAL);
                lp2.addRule(RelativeLayout.ALIGN_START,R.id.frameNote);
                lp2.setMargins(21,0,0,0);
                t.getTextFr().setLayoutParams(lp2);
                break;

            case "drop_d":

                t.setTextView(ConversorType.INIT_DROPD_NOTES);

                t.getFn().setScaleX(1.0f);


                RelativeLayout.LayoutParams lp3 = (RelativeLayout.LayoutParams) t.getTextFr().getLayoutParams();
                lp3.addRule(RelativeLayout.CENTER_VERTICAL);
                lp3.addRule(RelativeLayout.ALIGN_START,R.id.frameNote);
                lp3.leftMargin = 44;
                t.getTextFr().setLayoutParams(lp3);

                break;
        }
    }
}

