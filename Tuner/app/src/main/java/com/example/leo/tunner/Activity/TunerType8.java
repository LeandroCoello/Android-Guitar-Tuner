package com.example.leo.tunner.Activity;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.leo.tunner.NoteDisplay.ConversorType;
import com.example.leo.tunner.NoteDisplay.ConversorType8;
import com.example.leo.tunner.NoteDisplay.NoteConversor;
import com.example.leo.tunner.R;

public class TunerType8 extends TunerType6 {

    @Override
    public void initializeStrings(Tuner t){

        super.initializeStrings(t);
        setUp8Strings(t);
        bigDisplay(t);

    }
    private void setUp8Strings(Tuner t){
        t.setEightS((ImageView)t.findViewById(R.id.eightString));
        t.setSeventhS((ImageView)t.findViewById(R.id.seventhString));

        t.setEightTV((TextView) t.findViewById(R.id.textView8));
        t.setSeventhTV((TextView) t.findViewById(R.id.textView7));
    }

    @Override
    public void turnsLightsOff(Tuner t) {

        super.turnsLightsOff(t);
        t.getEightS().clearColorFilter();
        t.getSeventhS().clearColorFilter();
    }

    @Override
    public void inflateMenu(MenuInflater mi, Menu menu){
        mi.inflate(R.menu.main_menu_guitar8,menu);
    }

    @Override
    public boolean onItemSelected(MenuItem selected, Tuner t) {

        switch (selected.getItemId()) {
            case R.id.standard_tune:

                t.displayToast("Standard tune selected!");
                updateTextViews("standard", t);

               ConversorType ctype1 = new ConversorType8();
               ctype1.setTuning(ConversorType8.STANDARD,ConversorType8.STANDARD_NOTES);
               t.setNoteConversor(new NoteConversor(ctype1));

                return true;

            case R.id.half_down_tune:
                t.displayToast("Half down tune selected!");
                updateTextViews("half_down", t);

                ConversorType ctype = new ConversorType8();
                ctype.setTuning(ConversorType8.HALF_DOWN, ConversorType8.HALF_DOWN_NOTES);
                t.setNoteConversor(new NoteConversor(ctype));

                return true;

            case R.id.dropE_tune:
                t.displayToast("Drop E tune selected!");
                updateTextViews("dropE", t);

                ConversorType ctype3 = new ConversorType8();
                ctype3.setTuning(ConversorType8.DROP_E, ConversorType8.DROP_E_NOTES);
                t.setNoteConversor(new NoteConversor(ctype3));

                return true;

            case R.id.dropA_tune:
                t.displayToast("Drop A tune selected!");
                updateTextViews("dropA", t);

                ConversorType ctype2 = new ConversorType8();
                ctype2.setTuning(ConversorType8.DROP_A, ConversorType8.DROPA_NOTES);
                t.setNoteConversor(new NoteConversor(ctype2));

                return true;

            default:
                return false;
        }
    }

    public void updateTextViews(String tune, Tuner t){


        switch(tune) {

            case "standard":

                t.setTextView(ConversorType8.INIT_STANDARD_NOTES);

                bigDisplay(t);

                break;

            case "half_down":

                t.setTextView(ConversorType8.INIT_HALF_DOWN_NOTES);

                bigDisplay(t);

                break;

            case "dropE":

                t.setTextView(ConversorType8.INIT_DROPE_NOTES);

                smallDisplay(t);


                break;

            case "dropA":

                t.setTextView(ConversorType8.INIT_DROPA_NOTES);

                bigDisplay(t);


                break;
        }
    }


    public void setDefaultTextViews(Tuner t){
        t.setTextView(ConversorType8.INIT_STANDARD_NOTES);
    }

    @Override
    public void updateTxtFr(String text,Tuner t) {

        t.setTextFr((TextView) t.findViewById(R.id.textFr));
        t.getTextFr().setText(text);

        if(text.length()==1 && t.getFn().getScaleX()==1.5f){
            RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp2.addRule(RelativeLayout.CENTER_VERTICAL);
            lp2.addRule(RelativeLayout.ALIGN_START,R.id.frameNote);
            lp2.setMargins(44,0,0,0);
            t.getTextFr().setLayoutParams(lp2);
        }
        if(text.length()==2 && t.getFn().getScaleX()==1.5f){

            bigDisplay(t);
        }

    }


}
