package com.example.leo.tunner.Activity;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.leo.tunner.R;

public class TunerType8 extends TunerType6 {

    @Override
    public void initializeStrings(Tuner t){

        super.initializeStrings(t);
        setUp8Strings(t);

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
    }

    @Override
    public boolean onItemSelected(MenuItem selected, Tuner t) {
        return false;
    }


}
