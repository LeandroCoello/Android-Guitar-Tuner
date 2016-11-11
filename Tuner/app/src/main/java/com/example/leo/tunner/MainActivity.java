package com.example.leo.tunner;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leo.tunner.Task.ProcessingTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


public class MainActivity extends AppCompatActivity {

    private ProcessingTask ptask;
    private float fr = 0;
    private boolean isListening;


    private Button startButton, stopButton;
    private ImageView sixthS, fifthS, forthS, thirdS, secondS, firstS, bemol, sharp, ok;
    private TextView textFr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();


    }

    private void initialize(){

        startButton = (Button) findViewById(R.id.button);
        startButton.setEnabled(true);
        stopButton = (Button) findViewById(R.id.button2);
        stopButton.setEnabled(false);

        sixthS = (ImageView) findViewById(R.id.sixtString);
        fifthS = (ImageView) findViewById(R.id.fifthString);
        forthS = (ImageView) findViewById(R.id.forthString);
        thirdS = (ImageView) findViewById(R.id.thirdString);
        secondS = (ImageView) findViewById(R.id.secondString);
        firstS = (ImageView) findViewById(R.id.firstString);
        bemol = (ImageView) findViewById(R.id.bemol);
        sharp = (ImageView) findViewById(R.id.sharp);
        ok = (ImageView)findViewById(R.id.ok);

        turnLightsOff();

        textFr = (TextView) findViewById(R.id.textFr);
        textFr.setText(Float.toString(fr));
    }

    public void startRecording(View v){

        isListening = true;
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        ptask = new ProcessingTask(this);
        ptask.execute();


    }

    public void stopRecording(View v){
        isListening = false;
        startButton.setEnabled(true);
        stopButton.setEnabled(false);

        //ptask.cancel(true);
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
         ok.setColorFilter(GRAY);

    }

    public boolean isListening(){
        return isListening;
    }



}
