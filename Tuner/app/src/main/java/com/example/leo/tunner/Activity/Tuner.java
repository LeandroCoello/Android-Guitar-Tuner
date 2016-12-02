package com.example.leo.tunner.Activity;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.tunner.NoteDisplay.ConversorType;
import com.example.leo.tunner.NoteDisplay.ConversorType4;
import com.example.leo.tunner.NoteDisplay.ConversorType8;
import com.example.leo.tunner.NoteDisplay.NoteConversor;
import com.example.leo.tunner.R;
import com.example.leo.tunner.Task.ProcessingTask;

import java.util.ArrayList;
import java.util.Arrays;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class Tuner extends AppCompatActivity {

    private ProcessingTask ptask;
    private NoteConversor noteConversor;
    private float fr = 0;
    private boolean isListening;
    private int layout_portrait, layout_landscape, typeCode;
    private Button strtButton, stpButton;
    private ImageView eightS,seventhS,sixthS, fifthS, forthS, thirdS, secondS, firstS, bemol, sharp, ok, fn;
    private TextView textFr, firstTV,secondTV, thirdTV, forthTV, fifthTV, sixthTV, seventhTV, eightTV;
    private TunerType4 type;
    private ArrayList<TextView> textViewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        Configuration conf = this.getResources().getConfiguration();


        ArrayList<Integer> params = getIntent().getExtras().getIntegerArrayList("params");
        layout_portrait = params.get(0);
        layout_landscape = params.get(1);
        typeCode = params.get(2);

        seUpTypes(typeCode);

        if(conf.orientation == Configuration.ORIENTATION_PORTRAIT){

            setContentView(layout_portrait);
        }

        if(conf.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(layout_landscape);

        }


       initialize();


    }

    private void initialize(){

        strtButton = (Button) findViewById(R.id.startButton);
        strtButton.setEnabled(true);
        stpButton = (Button) findViewById(R.id.stopButton);
        stpButton.setEnabled(false);

        bemol = (ImageView) findViewById(R.id.bemol);
        sharp = (ImageView) findViewById(R.id.sharp);
        ok = (ImageView)findViewById(R.id.ok);
        fn = (ImageView)findViewById(R.id.frameNote);
        textFr = (TextView) findViewById(R.id.textFr);
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/jd_lcd_rounded.ttf");
        textFr.setTypeface(tf);
        textFr.setTextColor(GREEN);


        type.initializeStrings(this);
        textViewList = new ArrayList<TextView> (Arrays.asList(eightTV,seventhTV,sixthTV,fifthTV,forthTV,thirdTV,secondTV,firstTV));

        type.setDefaultTextViews(this);

        turnLightsOff();

    }


    public void seUpTypes(int tc){
        ConversorType t ;
        switch (tc){
            case 0:
                t = new ConversorType();
                t.initialize();
                type = new TunerType6();
                setTitle("Guitar Tuner - Standard");
                break;
            case 1:
                t = new ConversorType8();
                t.initialize();
                t.setTuning(ConversorType8.STANDARD,ConversorType8.STANDARD_NOTES);
                type = new TunerType8();
                setTitle("Guitar Tuner - Standard");
                break;
            case 2:
                t= new ConversorType4();
                t.setTuning(ConversorType4.STANDARD,ConversorType4.STANDARD_NOTES);
                type = new TunerType4();
                setTitle("Bass Tuner - Standard");
                break;
            case 3:
                t= new ConversorType();
                t.setTuning(TunerTypeB6.STANDARD_BASS,TunerTypeB6.STANDARD_BASS_NOTES);
                type = new TunerTypeB6();
                setTitle("Bass Tuner - Standard");
                break;
            default:
                t = new ConversorType();
                t.initialize();
                type = new TunerType6();

        }
        noteConversor = new NoteConversor(t);
    }

    public void startRecording(View v){

        strtButton.setEnabled(false);
        stpButton.setEnabled(true);
        isListening = true;

        ptask = new ProcessingTask(this,noteConversor);
        ptask.execute();


    }

    public void stopRecording(View v){
        turnLightsOff();
        stopRecording();
    }

    private void stopRecording(){
        isListening = false;
        strtButton.setEnabled(true);
        stpButton.setEnabled(false);
        textFr.setText(" ");
    }

    public void updateTxtFr(String text){
        type.updateTxtFr(text,this);

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
                seventhS.setColorFilter(RED);
                break;
            case 8:
                eightS.setColorFilter(RED);
                break;
            case 9:
                bemol.setColorFilter(RED);
                break;
            case 10:
                sharp.setColorFilter(RED);
                break;
            case 11:
                ok.setColorFilter(GREEN);
                break;

        }
    }
    public void turnLightsOff(){

        forthS.clearColorFilter();
        thirdS.clearColorFilter();
        secondS.clearColorFilter();
        firstS.clearColorFilter();
        bemol.clearColorFilter();
        sharp.clearColorFilter();
        ok.clearColorFilter();

        type.turnsLightsOff(this);
    }

    public boolean isListening(){
        return isListening;
    }

    public void displayToast(String text){
        Toast toast = Toast.makeText(this.getApplicationContext(), text,Toast.LENGTH_SHORT);
        toast.show();

    }

    @Override
    public void onDestroy(){

        isListening = false;
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        type.inflateMenu(inflater, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            stopRecording();

            boolean res = type.onItemSelected(item, this);
            if(!res) {return super.onOptionsItemSelected(item);}

        return res;

    }


    @Override
    public void onBackPressed(){
        isListening = false;
        super.onBackPressed();
    }

    public void setTextView(ArrayList<String> notes){

        int h = 0;
        if(notes.size() == 6){
           h=2;
        }
        if(notes.size() == 4){
            h=4;
        }
        int index= 0;
        for(int j= h; j<textViewList.size(); j++){
            textViewList.get(j).setText(notes.get(index));
            index++;
        }

    }



    public void setFirstS(ImageView firstS) {
        this.firstS = firstS;
    }

    public void setSecondS(ImageView secondS) {
        this.secondS = secondS;
    }

    public void setThirdS(ImageView thirdS) {
        this.thirdS = thirdS;
    }

    public void setForthS(ImageView forthS) {
        this.forthS = forthS;
    }

    public void setFifthS(ImageView fifthS) {
        this.fifthS = fifthS;
    }

    public void setSixthS(ImageView sixthS) {
        this.sixthS = sixthS;
    }

    public void setSeventhS(ImageView seventhS) {
        this.seventhS = seventhS;
    }

    public void setEightS(ImageView eightS) {
        this.eightS = eightS;
    }

    public void setSeventhTV(TextView seventhTV) {
        this.seventhTV = seventhTV;
    }

    public void setEightTV(TextView eightTV) {
        this.eightTV = eightTV;
    }

    public void setFirstTV(TextView firstTV) {
        this.firstTV = firstTV;
    }

    public void setSecondTV(TextView secondTV) {
        this.secondTV = secondTV;
    }

    public void setThirdTV(TextView thirdTV) {
        this.thirdTV = thirdTV;
    }

    public void setForthTV(TextView forthTV) {
        this.forthTV = forthTV;
    }

    public void setFifthTV(TextView fifthTV) {
        this.fifthTV = fifthTV;
    }

    public void setSixthTV(TextView sixthTV) {
        this.sixthTV = sixthTV;
    }

    public ImageView getEightS() {
        return eightS;
    }

    public ImageView getSeventhS() {
        return seventhS;
    }

    public ImageView getSixthS() {
        return sixthS;
    }

    public ImageView getFifthS() {
        return fifthS;
    }

    public void setNoteConversor(NoteConversor noteConversor) {
        this.noteConversor = noteConversor;
    }

    public TextView getTextFr() {
        return textFr;
    }

    public ImageView getFn() {
        return fn;
    }

    public void setTextFr(TextView textFr) {
        this.textFr = textFr;
    }



}
