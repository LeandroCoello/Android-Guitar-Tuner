package com.example.leo.tunner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.leo.tunner.Task.ProcessingTask;


public class MainActivity extends AppCompatActivity {

    private ProcessingTask ptask;
    private float fr = 0;
    private boolean isListening;


    private Button startButton, stopButton;
    private TextView textFr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.button);
        startButton.setEnabled(true);
        stopButton = (Button) findViewById(R.id.button2);
        stopButton.setEnabled(false);

        textFr = (TextView) findViewById(R.id.textFr);
        textFr.setText(Float.toString(fr));




    }

    public void startRecording(View v){

        isListening = true;
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        ptask = new ProcessingTask(this);
        textFr.setText("148");
        ptask.execute();
        textFr.setText("151");


    }

    public void stopRecording(View v){
        isListening = false;
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        fr = 0;
        //ptask.cancel(true);
        textFr.setText(Float.toString(fr));
    }

    public void updateTxtView(String text){
        textFr = (TextView) findViewById(R.id.textFr);
        textFr.setText(text);

    }

    public boolean isListening(){
        return isListening;
    }


}
