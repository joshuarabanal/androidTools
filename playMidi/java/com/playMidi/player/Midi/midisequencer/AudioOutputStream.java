package com.playMidi.player.Midi.midisequencer;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import com.playMidi.AudioTools.wavFormat.WaveOutputStream;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ra on 09/07/2017.
 */

public class AudioOutputStream {
    public int sampleRate;
    private AudioTrack t;
    private WaveOutputStream out;
    public AudioOutputStream(Context c){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                AudioManager am = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);
                sampleRate = Integer.parseInt(am.getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE));
            }
            else{
                sampleRate = 44100;
            }


            t = new AudioTrack(
                    AudioManager.STREAM_MUSIC,
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    10000, AudioTrack.MODE_STREAM);

    }
    public AudioOutputStream(File f, int sampleRate) throws IOException {
        this.sampleRate = sampleRate;
        out = new WaveOutputStream(f, sampleRate);

    }
    public int write(short[] buffer, int start, int length) throws Exception {
        if(t != null){
            return t.write(buffer, start, length);
        }
        else if(out != null){
            out.write(buffer, start, length);
            return length;
        }
        else{
            throw new Exception("both AudioTrack and WaveOutputStream are NULL");
        }
    }
    public void play(){
        if(t!= null){ t.play(); }
        else{
            Log.w("play called", "on null audio track");
        }
    }
    public void pause(){

        if(t != null){ t.pause(); }
        else{ Log.w("pause called","on null audio track"); }
    }
    public void release(){
        if(t != null){ t.release(); t = null; }
    }
}
