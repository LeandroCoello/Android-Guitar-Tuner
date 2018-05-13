package com.example.leo.tunner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.tunner.Activity.Tuner;
import com.example.leo.tunner.R;
import com.example.leo.tunner.Task.ProcessingTaskDemo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private Button sixStringsGuitar, eightStringsGuitar, fourStringsBass, sixStringsBass;

    // STARTS DEMO

    private boolean isListeningDemo;
    private ProcessingTaskDemo ptask;
    private Button strtButton, stpButton;
    private TextView textFr;


    // ENDS DEMO

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        //        Configuration conf = this.getResources().getConfiguration();
//
//        if(conf.orientation ==Configuration.ORIENTATION_PORTRAIT){
//
//            setContentView(R.layout.activity_main);
//        }else {
//            setContentView(R.layout.activity_main_landscape);
//
//        }

//        initialize();

//        STARTS DEMO

        setContentView(R.layout.demo);
        strtButton = (Button) findViewById(R.id.start);
        strtButton.setEnabled(true);
        stpButton = (Button) findViewById(R.id.stop);
        stpButton.setEnabled(false);
        textFr = (TextView) findViewById(R.id.freq);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    123);
        }

//        ENDS DEMO


    }

    private void initialize(){


        sixStringsGuitar = (Button) findViewById(R.id.sixSButton);
        eightStringsGuitar = (Button) findViewById(R.id.eigthSButton);
        fourStringsBass = (Button) findViewById(R.id.fourBassButton);
        sixStringsBass = (Button) findViewById(R.id.sixBassButton);

    }

    public void startSixSGuitarTuner(View v){


        Intent i = new Intent(this,Tuner.class);

        ArrayList<Integer> par = new ArrayList<Integer>();
        par.add(0,R.layout.activity_tuner);
        par.add(1,R.layout.activity_tuner_landscape);
        par.add(2,0);

        i.putExtra("params",par);
        startActivity(i);
    }

    public void startEightSGuitarTuner(View v){

        Intent i = new Intent(this,Tuner.class);

        ArrayList<Integer> par = new ArrayList<Integer>();
        par.add(0,R.layout.activity_tuner_8);
        par.add(1,R.layout.activity_tuner_landscape_8);
        par.add(2,1);

        i.putExtra("params",par);

        startActivity(i);
    }

    public void startFourSBassTuner(View v){


        Intent i = new Intent(this,Tuner.class);

        ArrayList<Integer> par = new ArrayList<Integer>();
        par.add(0,R.layout.activity_tuner_4);
        par.add(1,R.layout.activity_tuner_landscape_4);
        par.add(2,2);

        i.putExtra("params",par);
        startActivity(i);

    }

    public void startSixSBassTuner(View v){

        Intent i = new Intent(this,Tuner.class);

        ArrayList<Integer> par = new ArrayList<Integer>();
        par.add(0,R.layout.activity_tuner);
        par.add(1,R.layout.activity_tuner_landscape);
        par.add(2,3);

        i.putExtra("params",par);
        startActivity(i);

    }

    public void displayToast(String text){
        Toast toast = Toast.makeText(this.getApplicationContext(), text,Toast.LENGTH_SHORT);
        toast.show();

    }


    //STARTS DEMO

    public void startRecording(View v){

        strtButton.setEnabled(false);
        stpButton.setEnabled(true);
        isListeningDemo = true;

        ptask = new ProcessingTaskDemo(this);
        ptask.execute();


    }

    public void stopRecording(View v){
        isListeningDemo = false;
        strtButton.setEnabled(true);
        stpButton.setEnabled(false);
        textFr.setText(" ");
    }

    public boolean isListeningDemo() {
        return isListeningDemo;
    }

    public void setListeningDemo(boolean listeningDemo) {
        isListeningDemo = listeningDemo;
    }

    public void updateTxtView(String text) {

        textFr.setText(text);
    }

    //ENDS DEMO

}
