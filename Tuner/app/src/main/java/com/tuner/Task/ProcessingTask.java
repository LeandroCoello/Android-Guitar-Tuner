package com.tuner.Task;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;

import java.lang.Short;

import com.tuner.Activity.Tuner;
import com.tuner.NoteDisplay.NoteConversor;
import com.tuner.PitchRecognitionPack.FastYin;
import com.tuner.PitchRecognitionPack.PitchDetectionResult;

public class ProcessingTask extends AsyncTask<Float, Float, Float> {


    private static short[] samples;
    private static final int SAMPLE_RATE = 44100;// 48000;
    private static final int NUMBER_OF_SAMPLES = 5120;
    private static int N;


    Tuner tuner;
    NoteConversor noteConversor;

    public ProcessingTask(Tuner t, NoteConversor nc){

        tuner = t;
        noteConversor = nc;
    }


    @Override
    protected Float doInBackground(Float... params) {

        //McLeodPitchMethod mpm = new McLeodPitchMethod(SAMPLE_RATE, NUMBER_OF_SAMPLES);
        //AMDF amdf = new AMDF(SAMPLE_RATE, NUMBER_OF_SAMPLES);
        //Yin yinM = new Yin(SAMPLE_RATE, NUMBER_OF_SAMPLES);
        //DynamicWavelet dw = new DynamicWavelet(SAMPLE_RATE, NUMBER_OF_SAMPLES);
        FastYin fy = new FastYin(SAMPLE_RATE, NUMBER_OF_SAMPLES);

        N = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        samples = new short[NUMBER_OF_SAMPLES];

        AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.VOICE_RECOGNITION, SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, N * 10);
        recorder.startRecording();



        while(tuner.isListening()) {

            recorder.read(samples, 0, samples.length);
            //float[] infsamples = increaseGain(shortToFloat(samples),3f);
            float[] infsamples = shortToFloat(samples);

            //PitchDetectionResult pr = mpm.getPitch(infsamples); //Bad Results
            //PitchDetectionResult pr = amdf.getPitch(infsamples); //Bad Results
            //PitchDetectionResult pr = yinM.getPitch(infsamples); //Best Results
            //PitchDetectionResult pr = dw.getPitch(infsamples); //Bad Results
            PitchDetectionResult pr = fy.getPitch(infsamples); //Goood Results


            Double ad1 = 0.0;
            Float res = ad1.floatValue();
                if(pr.isPitched()) {
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

        tuner.turnLightsOff();

        String st = noteConversor.getNote(values[0], tuner);
        tuner.updateTxtFr(st);
    }

    @Override
    protected void onPostExecute(Float result) {
        tuner.updateTxtFr(" ");
        tuner.turnLightsOff();

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
