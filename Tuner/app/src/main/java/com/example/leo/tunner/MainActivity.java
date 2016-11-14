package com.example.leo.tunner;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.tunner.Task.ProcessingTask;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.TRANSPARENT;


public class MainActivity extends AppCompatActivity {

    private ProcessingTask ptask;
    private float fr = 0;
    private boolean isListening;


    private Button strtButton, stpButton;
    private ImageView sixthS, fifthS, forthS, thirdS, secondS, firstS, bemol, sharp, ok, f1, f2, f3, f4, f5, f6, fb, fs,fn;
    private TextView textFr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration conf = this.getResources().getConfiguration();

        if(conf.orientation ==Configuration.ORIENTATION_PORTRAIT){

            setContentView(R.layout.activity_main);
        }else {
            setContentView(R.layout.activity_landscape);

        }
        initialize();


    }

    private void initialize(){


        strtButton = (Button) findViewById(R.id.startButton);
        strtButton.setEnabled(true);
        stpButton = (Button) findViewById(R.id.stopButton);
        stpButton.setEnabled(false);

        sixthS = (ImageView) findViewById(R.id.sixtString);
        fifthS = (ImageView) findViewById(R.id.fifthString);
        forthS = (ImageView) findViewById(R.id.forthString);
        thirdS = (ImageView) findViewById(R.id.thirdString);
        secondS = (ImageView) findViewById(R.id.secondString);
        firstS = (ImageView) findViewById(R.id.firstString);
        bemol = (ImageView) findViewById(R.id.bemol);
        sharp = (ImageView) findViewById(R.id.sharp);
        ok = (ImageView)findViewById(R.id.ok);

        f6 = (ImageView) findViewById(R.id.frameSixS);
        f5 = (ImageView) findViewById(R.id.frameFifS);
        f4 = (ImageView) findViewById(R.id.frameForthS);
        f3 = (ImageView) findViewById(R.id.frameThirdS);
        f2 = (ImageView) findViewById(R.id.frameSecondS);
        f1 = (ImageView) findViewById(R.id.frameFirstS);
        fb = (ImageView) findViewById(R.id.frameBemol);
        fs = (ImageView) findViewById(R.id.frameSharp);
        fs = (ImageView) findViewById(R.id.frameNote);

        turnLightsOff();

        textFr = (TextView) findViewById(R.id.textFr);
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/jd_lcd_rounded.ttf");
        textFr.setTypeface(tf);
        textFr.setTextColor(GREEN);
    }

    public void startRecording(View v){

        strtButton.setEnabled(false);
        stpButton.setEnabled(true);
        isListening = true;

        ptask = new ProcessingTask(this);
        ptask.execute();


    }

    public void stopRecording(View v){
        isListening = false;
        strtButton.setEnabled(true);
        stpButton.setEnabled(false);
        textFr.setText(" ");
    }

    public void updateTxtFr(String text){
        textFr = (TextView) findViewById(R.id.textFr);
        textFr.setText(text);

    }

    public void turnLightOn(int index) {

        switch (index) {
            case 1:
                firstS.setColorFilter(RED);
                break;
            case 2:
                secondS.setColorFilter(RED);
                break;
            case 3:
                thirdS.setColorFilter(RED);
                break;
            case 4:
                forthS.setColorFilter(RED);
                break;
            case 5:
                fifthS.setColorFilter(RED);
                break;
            case 6:
                sixthS.setColorFilter(RED);
                break;
            case 7:
                bemol.setColorFilter(RED);
                break;
            case 8:
                sharp.setColorFilter(RED);
                break;
            case 9:
                ok.setColorFilter(GREEN);
                break;

        }
    }
     public void turnLightsOff(){

         sixthS.setColorFilter(GRAY);
         fifthS.setColorFilter(GRAY);
         forthS.setColorFilter(GRAY);
         thirdS.setColorFilter(GRAY);
         secondS.setColorFilter(GRAY);
         firstS.setColorFilter(GRAY);
         bemol.setColorFilter(GRAY);
         sharp.setColorFilter(GRAY);
         ok.setColorFilter(TRANSPARENT);

    }

    public boolean isListening(){
        return isListening;
    }

    public void displayToast(String text){
        Toast toast = Toast.makeText(this.getApplicationContext(), text,Toast.LENGTH_SHORT);
        toast.show();

    }


}
