package com.exemple.mindride.Binaural;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

/*Referencia:
https://developer.android.com/reference/android/media/AudioTrack
https://developer.android.com/reference/android/media/AudioFormat
https://riptutorial.com/android/example/28432/generate-tone-of-a-specific-frequency
 */

public class Audio {

    private static AudioTrack audioDireito;
    private static AudioTrack audioEsquerdo;

    private static final int AJUSTE_VOLUME = 500;

    public Audio() {


    }

    public static void audio(double frequenciaDireito, double frequenciaEsquedo, float volumeDireito, float volumeEsquerdo, int duracao){

        int duracaoS = duracao * 60;

        int count = (int) (22050.0 * 2.0 * (duracaoS)) & ~1;
        short[] samples = new short[count];
        short[] samples1 = new short[count];
        int size = count * (Short.SIZE / 8);

        for (int i = 0; i < count; i += 2) {
            short sample = (short) (Math.sin(2 * Math.PI * i / (22050.0 / frequenciaDireito)) * 0x7FFF * .75);
            samples[i + 0] = 0;
            samples[i + 1] = sample;

            short sample1 = (short) (Math.sin(2 * Math.PI * i / (22050.0 / frequenciaEsquedo)) * 0x7FFF * .75);
            samples1[i + 0] = sample1;
            samples1[i + 1] = 0;
        }
        audioDireito = new AudioTrack(AudioManager.STREAM_MUSIC, 22050, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT, size, AudioTrack.MODE_STATIC);
        audioEsquerdo = new AudioTrack(AudioManager.STREAM_MUSIC, 22050,AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT, size, AudioTrack.MODE_STATIC);
        audioDireito.setNotificationMarkerPosition(count / 2);
        audioDireito.write(samples, 0, count);
        audioEsquerdo.write(samples1,0,count);
        audioDireito.setVolume(volumeDireito/AJUSTE_VOLUME);
        audioEsquerdo.setVolume(volumeEsquerdo/AJUSTE_VOLUME);
        audioDireito.play();
        audioEsquerdo.play();
        ValoresAudio.setAudioAcionado(true);

    }


    public static void stop(){
        audioDireito.stop();
        audioEsquerdo.stop();
        ValoresAudio.setAudioAcionado(false);
    }

    public static void pause(){
        audioDireito.pause();
        audioEsquerdo.pause();
        ValoresAudio.setAudioAcionado(false);
    }
}

