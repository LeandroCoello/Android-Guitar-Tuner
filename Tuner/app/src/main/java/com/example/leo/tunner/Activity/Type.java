package com.example.leo.tunner.Activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.leo.tunner.R;

public class Type {



    public void initializeStrings(int type, Tuner t){

        setUp4Strings(t);

        if (type == 0) {
           setUp6Strings(t);
        }

        if(type == 1){
            setUp6Strings(t);
            setUp8Strings(t);
        }
    }

    private void setUp4Strings(Tuner t){
        t.setForthS((ImageView)t.findViewById(R.id.forthString));
        t.setThirdS((ImageView) t.findViewById(R.id.thirdString));
        t.setSecondS((ImageView) t.findViewById(R.id.secondString));
        t.setFirstS((ImageView) t.findViewById(R.id.firstString));

        t.setForthTV((TextView) t.findViewById(R.id.textView4));
        t.setThirdTV((TextView) t.findViewById(R.id.textView3));
        t.setSecondTV((TextView) t.findViewById(R.id.textView2));
        t.setFirstTV((TextView) t.findViewById(R.id.textView1));
    }

    private void setUp6Strings(Tuner t){
        t.setSixthS((ImageView)t.findViewById(R.id.sixthString));
        t.setFifthS((ImageView)t.findViewById(R.id.fifthString));

        t.setSixthTV((TextView) t.findViewById(R.id.textView6));
        t.setFifthTV((TextView) t.findViewById(R.id.textView5));
    }

    private void setUp8Strings(Tuner t){
        t.setEightS((ImageView)t.findViewById(R.id.eightString));
        t.setSeventhS((ImageView)t.findViewById(R.id.seventhString));

        t.setEightTV((TextView) t.findViewById(R.id.textView8));
        t.setSeventhTV((TextView) t.findViewById(R.id.textView7));
    }

    public void turnsLightsOff(int type, Tuner t){

        if(type == 0){
            t.getSixthS().clearColorFilter();
            t.getFifthS().clearColorFilter();
        }

        if(type == 1){
            t.getEightS().clearColorFilter();
            t.getSeventhS().clearColorFilter();
            t.getSixthS().clearColorFilter();
            t.getFifthS().clearColorFilter();
        }
    }

}
