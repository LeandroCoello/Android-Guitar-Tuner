package com.example.leo.tunner.Task;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.widget.TextView;
import java.lang.Short;

import com.example.leo.tunner.PitchRecognitionPack.AMDF;
import com.example.leo.tunner.PitchRecognitionPack.DynamicWavelet;
import com.example.leo.tunner.PitchRecognitionPack.FastYin;
import com.example.leo.tunner.PitchRecognitionPack.McLeodPitchMethod;
import com.example.leo.tunner.PitchRecognitionPack.PitchDetectionResult;
import com.example.leo.tunner.PitchRecognitionPack.Yin;
import com.example.leo.tunner.MainActivity;

public class ProcessingTask extends AsyncTask<String, String, String> {

    private static final int RECORDER_SAMPLERATE = 8000;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    private static short[] data;
    private static final int SAMPLE_RATE = 48000;// 48000;
    private static final int SAMPLES = 1024;
    private static int N;
   // Double res = 0.0 ;


    MainActivity mAct;
    TextView textFr;
    public ProcessingTask(MainActivity ma){
        mAct = ma;

    }


    @Override
    protected String doInBackground(String... params) {

        McLeodPitchMethod mpm = new McLeodPitchMethod(SAMPLE_RATE,SAMPLES);
        //AMDF amdf = new AMDF(SAMPLE_RATE, SAMPLES);
        //Yin yinM = new Yin(SAMPLE_RATE,SAMPLES);
        //DynamicWavelet dw = new DynamicWavelet(SAMPLE_RATE,SAMPLES);
        //FastYin fy = new FastYin(SAMPLE_RATE,SAMPLES);


        N = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        data = new short[SAMPLES];


        AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.VOICE_RECOGNITION, SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, N * 10);
        recorder.startRecording();
        //NoiseSuppressor.create(recorder.getAudioSessionId());
        publishProgress("210");

        while(mAct.isListening()) {

                recorder.read(data, 0, data.length);
                PitchDetectionResult pr = mpm.getPitch(shortToFloat(data));
                //PitchDetectionResult pr = amdf.getPitch(shortToFloat(data));
                //PitchDetectionResult pr = yinM.getPitch(shortToFloat(data)); //Best Results
                //PitchDetectionResult pr = dw.getPitch(shortToFloat(data)); //Bad Results
                //PitchDetectionResult pr = fy.getPitch(shortToFloat(data)); //Goood Results





            String st = "0";
                if(pr.isPitched()) {
                    st = Float.toString(pr.getPitch());
                }
                 publishProgress(st);

        }
        recorder.stop();
        recorder.release();
        String a = "180";

        return a;
    }




    @Override
    protected void onProgressUpdate(String... values) {
        mAct.updateTxtView(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        mAct.updateTxtView(result);

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
