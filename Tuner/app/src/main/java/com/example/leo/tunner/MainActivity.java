package com.example.leo.tunner;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
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
import com.example.leo.tunner.NoteDisplay.NoteConversor;
import com.example.leo.tunner.Task.ProcessingTask;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.TRANSPARENT;


public class MainActivity extends AppCompatActivity {


    private ProcessingTask ptask;
    private NoteConversor noteConversor;
    private float fr = 0;
    private boolean isListening;


    private Button strtButton, stpButton;
    private ImageView sixthS, fifthS, forthS, thirdS, secondS, firstS, bemol, sharp, ok, f1, f2, f3, f4, f5, f6, fb, fs, fn;
    private TextView textFr, firstTV,secondTV, thirdTV, forthTV, fifthTV, sixthTV;


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
        fn = (ImageView) findViewById(R.id.frameNote);


        sixthTV = (TextView) findViewById(R.id.textView6);
        fifthTV = (TextView) findViewById(R.id.textView5);
        forthTV = (TextView) findViewById(R.id.textView4);
        thirdTV = (TextView) findViewById(R.id.textView3);
        secondTV = (TextView) findViewById(R.id.textView2);
        firstTV = (TextView) findViewById(R.id.textView1);

        turnLightsOff();

        textFr = (TextView) findViewById(R.id.textFr);
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/jd_lcd_rounded.ttf");
        textFr.setTypeface(tf);
        textFr.setTextColor(GREEN);
        ConversorType conv = new ConversorType();
        noteConversor = new NoteConversor(conv);
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

    @Override
    public void onDestroy(){

        isListening = false;
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.eb_tune:
                displayToast("Eb tune selected!");
                updateTextViews("Eb");

                stopRecording();
                ConversorType ctype = new ConversorType();
                ctype.setEbTuning();
                noteConversor = new NoteConversor(ctype);
                noteConversor.getType().setEbTuning();

                return true;

            case R.id.normal_tune:
                displayToast("Normal tune selected!");
                updateTextViews("normal");

                stopRecording();
                noteConversor = new NoteConversor(new ConversorType());

                return true;

            case R.id.open_tune:
                displayToast("Open tune selected!");
                updateTextViews("normal");
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }



    public void updateTextViews(String tune){

        Configuration conf = this.getResources().getConfiguration();


        switch(tune) {

            case "normal":

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

            case "Eb":
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

}
