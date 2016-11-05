package com.example.leo.tunner.Task;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.widget.TextView;
import java.lang.Short;

import com.example.leo.tunner.NoteDisplay.NoteConversor;
import com.example.leo.tunner.PitchRecognitionPack.AMDF;
import com.example.leo.tunner.PitchRecognitionPack.DynamicWavelet;
import com.example.leo.tunner.PitchRecognitionPack.FastYin;
import com.example.leo.tunner.PitchRecognitionPack.McLeodPitchMethod;
import com.example.leo.tunner.PitchRecognitionPack.PitchDetectionResult;
import com.example.leo.tunner.PitchRecognitionPack.Yin;
import com.example.leo.tunner.MainActivity;

public class ProcessingTask extends AsyncTask<Float, Float, Float> {


    private static short[] samples;
    private static final int SAMPLE_RATE = 48000;// 48000;
    private static final int NUMBER_OF_SAMPLES = 1024;
    private static int N;


    MainActivity mAct;
    TextView textFr;
    public ProcessingTask(MainActivity ma){
        mAct = ma;

    }


    @Override
    protected Float doInBackground(Float... params) {

        //McLeodPitchMethod mpm = new McLeodPitchMethod(SAMPLE_RATE, NUMBER_OF_SAMPLES);
        //AMDF amdf = new AMDF(SAMPLE_RATE, NUMBER_OF_SAMPLES);
        Yin yinM = new Yin(SAMPLE_RATE, NUMBER_OF_SAMPLES);
        //DynamicWavelet dw = new DynamicWavelet(SAMPLE_RATE, NUMBER_OF_SAMPLES);
        //FastYin fy = new FastYin(SAMPLE_RATE, NUMBER_OF_SAMPLES);

        //NoteConversor nc = new NoteConversor();
        N = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        samples = new short[NUMBER_OF_SAMPLES];

        AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.VOICE_RECOGNITION, SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, N * 10);
        recorder.startRecording();


        while(mAct.isListening()) {


                recorder.read(samples, 0, samples.length);
                //PitchDetectionResult pr = mpm.getPitch(shortToFloat(samples));
                //PitchDetectionResult pr = amdf.getPitch(shortToFloat(samples));
                PitchDetectionResult pr = yinM.getPitch(shortToFloat(samples)); //Best Results
                //PitchDetectionResult pr = dw.getPitch(shortToFloat(samples)); //Bad Results
                //PitchDetectionResult pr = fy.getPitch(shortToFloat(samples)); //Goood Results




            Double ad1 = 0.0;
            Float res = ad1.floatValue();
                if(pr.isPitched()) {
                    //st = nc.getNote(pr.getPitch(),mAct);
                    res = pr.getPitch();
                }
                 publishProgress(res);

        }
        recorder.stop();
        recorder.release();
        Double ad = 180.0;
        Float a = ad.floatValue();

        return a;
    }




    @Override
    protected void onProgressUpdate(Float... values) {

        //mAct.turnLightsOff();
        //NoteConversor nc = new NoteConversor();
        //String st = nc.getNote(values[0],mAct);
        String st = values[0].toString();

        mAct.updateTxtFr(st);
    }

    @Override
    protected void onPostExecute(Float result) {
        mAct.updateTxtFr(result.toString());

    }

    public float[] shortToFloat(short[] data){
        float[] converted = new float[data.length];

        for(int i = 0; i< data.length; i++){

            Short n = data[i];

            converted[i] = n.floatValue();
        }

        return converted;
    }

}
