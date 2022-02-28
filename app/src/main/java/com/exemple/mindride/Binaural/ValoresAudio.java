package com.exemple.mindride.Binaural;

public class ValoresAudio {

    private static double frequenciaDireto = 0;
    private static double frequenciaEsquerdo = 0;
    private static int tempoExecucao = 1;
    private static float volumeDireito = 70;
    private static float volumeEsquerdo = 70;
    private static Boolean acionaBinaural = false;
    private static Boolean bt_Delta = false, bt_Teta = false, bt_Alfa = false, bt_Beta = false;
    private static Boolean audioAcionado = false;

    public ValoresAudio() {
    }

    public static double getFrequenciaDireto() {
        return frequenciaDireto;
    }

    public static void setFrequenciaDireto(double frequenciaDireto) {
        ValoresAudio.frequenciaDireto = frequenciaDireto;
    }

    public static double getFrequenciaEsquerdo() {
        return frequenciaEsquerdo;
    }

    public static void setFrequenciaEsquerdo(double frequenciaEsquerdo) {
        ValoresAudio.frequenciaEsquerdo = frequenciaEsquerdo;
    }

    public static int getTempoExecucao() {
        return tempoExecucao;
    }

    public static void setTempoExecucao(int tempoExecucao) {
        ValoresAudio.tempoExecucao = tempoExecucao;
    }

    public static float getVolumeDireito() {
        return volumeDireito;
    }

    public static void setVolumeDireito(float volumeDireito) {
        ValoresAudio.volumeDireito = volumeDireito;
    }

    public static float getVolumeEsquerdo() {
        return volumeEsquerdo;
    }

    public static void setVolumeEsquerdo(float volumeEsquerdo) {
        ValoresAudio.volumeEsquerdo = volumeEsquerdo;
    }

    public static Boolean getAcionaBinaural() {
        return acionaBinaural;
    }

    public static void setAcionaBinaural(Boolean acionaBatimento) {
        ValoresAudio.acionaBinaural = acionaBatimento;
    }

    public static Boolean getBt_Delta() {
        return bt_Delta;
    }

    public static void setBt_Delta(Boolean bt_Delta) {
        ValoresAudio.bt_Delta = bt_Delta;
    }

    public static Boolean getBt_Teta() {
        return bt_Teta;
    }

    public static void setBt_Teta(Boolean bt_Teta) {
        ValoresAudio.bt_Teta = bt_Teta;
    }

    public static Boolean getBt_Alfa() {
        return bt_Alfa;
    }

    public static void setBt_Alfa(Boolean bt_Alfa) {
        ValoresAudio.bt_Alfa = bt_Alfa;
    }

    public static Boolean getBt_Beta() {
        return bt_Beta;
    }

    public static void setBt_Beta(Boolean bt_Beta) {
        ValoresAudio.bt_Beta = bt_Beta;
    }

    public static Boolean getAudioAcionado() {
        return audioAcionado;
    }

    public static void setAudioAcionado(Boolean audioAcionado) {
        ValoresAudio.audioAcionado = audioAcionado;
    }
}
