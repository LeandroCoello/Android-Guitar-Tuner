package com.example.leo.tunner.Activity;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.tunner.NoteDisplay.ConversorType;
import com.example.leo.tunner.NoteDisplay.ConversorType4;
import com.example.leo.tunner.NoteDisplay.ConversorType8;
import com.example.leo.tunner.NoteDisplay.NoteConversor;
import com.example.leo.tunner.R;
import com.example.leo.tunner.Task.ProcessingTask;

import java.util.ArrayList;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.TRANSPARENT;

public class Tuner extends AppCompatActivity {

    private ProcessingTask ptask;
    private NoteConversor noteConversor;
    private float fr = 0;
    private boolean isListening;
    private int layout_portrait, layout_landscape, typeCode;
    private Button strtButton, stpButton;
    private ImageView eightS,seventhS,sixthS, fifthS, forthS, thirdS, secondS, firstS, bemol, sharp, ok, fn;
    private TextView textFr, firstTV,secondTV, thirdTV, forthTV, fifthTV, sixthTV, seventhTV, eightTV;
    private Type type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        Configuration conf = this.getResources().getConfiguration();

        type = new Type();

        ArrayList<Integer> params = getIntent().getExtras().getIntegerArrayList("params");
        layout_portrait = params.get(0);
        layout_landscape = params.get(1);
        typeCode = params.get(2);

        setNoteConversor(typeCode);

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

        type.initializeStrings(typeCode,this);


        turnLightsOff();

    }



    public void setNoteConversor(int id){
        ConversorType t ;
        switch (typeCode){
            case 0:
                t = new ConversorType();
                t.initialize();
                break;
            case 1:
                t = new ConversorType8();
                break;
            case 2:
                t= new ConversorType4();
                break;
            default:
                t = new ConversorType();
                t.initialize();

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
        stopRecording();
    }

    private void stopRecording(){
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

        type.turnsLightsOff(typeCode,this);
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

        if(typeCode == 0) {
            inflater.inflate(R.menu.main_menu, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        stopRecording();

        switch (item.getItemId()) {
            case R.id.half_down_tune:
                displayToast("Half down tune selected!");
                updateTextViews("half_down");

                ConversorType ctype = new ConversorType();
                ctype.setEbTuning();
                noteConversor = new NoteConversor(ctype);

                return true;

            case R.id.standard_tune:

                displayToast("Standard tune selected!");
                updateTextViews("standard");

                ConversorType ctype1 = new ConversorType();
                ctype1.initialize();
                noteConversor = new NoteConversor(ctype1);

                return true;

            case R.id.open_tune:
                displayToast("Open tune selected!");
                updateTextViews("standard");
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }



    public void updateTextViews(String tune){



        switch(tune) {

            case "standard":

                sixthTV.setText("6E");
                fifthTV.setText("5A");
                forthTV.setText("4D");
                thirdTV.setText("3G");
                secondTV.setText("2B");
                firstTV.setText("1E");

                fn.setScaleX(1.0f);


                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textFr.getLayoutParams();
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                lp.addRule(RelativeLayout.ALIGN_START,R.id.frameNote);
                lp.leftMargin = 44;
                textFr.setLayoutParams(lp);

                break;

            case "half_down":
                sixthTV.setText("Eb");
                fifthTV.setText("Ab");
                forthTV.setText("C#");
                thirdTV.setText("F#");
                secondTV.setText("Bb");
                firstTV.setText("Eb");


                fn.setScaleX(1.5f);


                RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp2.addRule(RelativeLayout.CENTER_VERTICAL);
                lp2.addRule(RelativeLayout.ALIGN_START,R.id.frameNote);
                lp2.setMargins(21,0,0,0);
                textFr.setLayoutParams(lp2);
                break;
        }
    }

    @Override
    public void onBackPressed(){
        isListening = false;
        super.onBackPressed();
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

}
