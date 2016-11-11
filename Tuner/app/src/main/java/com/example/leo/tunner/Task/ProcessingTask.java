package com.example.leo.tunner.Task;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.NoiseSuppressor;
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
    private static final int SAMPLE_RATE = 44100;// 48000;
    private static final int NUMBER_OF_SAMPLES = 5120;
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

        N = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        samples = new short[NUMBER_OF_SAMPLES];

        AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.VOICE_RECOGNITION, SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, N * 10);
        //AutomaticGainControl.create(recorder.getAudioSessionId());
        recorder.startRecording();


        while(mAct.isListening()) {

            recorder.read(samples, 0, samples.length);
            //float[] infsamples = increaseGain(shortToFloat(samples),3f);
            float[] infsamples = shortToFloat(samples);

            //PitchDetectionResult pr = mpm.getPitch(infsamples); //Bad Results
            //PitchDetectionResult pr = amdf.getPitch(infsamples); //Bad Results
            PitchDetectionResult pr = yinM.getPitch(infsamples); //Best Results
            //PitchDetectionResult pr = dw.getPitch(infsamples); //Bad Results
            //PitchDetectionResult pr = fy.getPitch(infsamples); //Goood Results




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

        mAct.turnLightsOff();
        NoteConversor nc = new NoteConversor();
        String st = nc.getNote(values[0],mAct);

        mAct.updateTxtFr(st);
    }

    @Override
    protected void onPostExecute(Float result) {
        mAct.updateTxtFr(" ");

    }

    public float[] shortToFloat(short[] samples){
        float[] converted = new float[samples.length];

        for(int i = 0; i< samples.length; i++){

            Short n = samples[i];

            converted[i] = n.floatValue();
        }

        return converted;
    }

    public float[] increaseGain(float [] samples, float gain){

        for (int i=0; i <samples.length; i++){
            samples[i]= samples[i] * gain;
        }

        return samples;
    }

}
