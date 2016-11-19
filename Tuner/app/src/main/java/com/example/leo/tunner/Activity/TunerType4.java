package com.example.leo.tunner.Activity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leo.tunner.NoteDisplay.ConversorType;
import com.example.leo.tunner.NoteDisplay.ConversorType4;
import com.example.leo.tunner.R;

import java.util.ArrayList;

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


    public void inflateMenu(MenuInflater mi, Menu menu){
    }


    public void turnsLightsOff(Tuner t){

    }

    public boolean onItemSelected(MenuItem selected, Tuner t) {
        return false;
    }


}
