package com.exemple.mindride.Binaural;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.exemple.mindride.R;


public class MainBinaural extends AppCompatActivity {
    private EditText frequenciaDireto;
    private EditText frequenciaEsquerdo;
    private EditText tempoExecucao;
    private SeekBar volumeDireito;
    private SeekBar volumeEsquerdo;
    private SeekBar sb_frequenciaDireto;
    private SeekBar sb_frequenciaEsquerdo;
    private static CheckBox acionaBatimento;
    private Switch sw_Delta;
    private Switch sw_Teta;
    private Switch sw_Alfa;
    private Switch sw_Beta;

    private static Boolean iniciar = true;

    private Button playBinauralSolo;


    final static  Audio iniciaAudio = new Audio();

    final static int frequenciaMaxima = 300;
    final static int frequenciaBase = 150;
    final static int frequenciaTeta = 148;
    final static int frequenciaDelta = 142;
    final static int frequenciaAlfa = 138;
    final static int frequenciaBeta = 130;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binaural);

        frequenciaDireto = findViewById(R.id.et_frequenciaDireito);
        frequenciaEsquerdo = findViewById(R.id.et_frequenciaEsquerdo);
        tempoExecucao = findViewById(R.id.et_tempoExecucao);
        volumeDireito = findViewById(R.id.sb_volumeDireito);
        volumeEsquerdo = findViewById(R.id.sb_volumeEsquerdo);
        sb_frequenciaDireto = findViewById(R.id.sb_frequenciaDireto);
        sb_frequenciaEsquerdo =findViewById(R.id.sb_frequenciaEsquerdo);
        acionaBatimento = findViewById(R.id.cb_acionaBatimento);
        sw_Delta = findViewById(R.id.sw_Delta);
        sw_Teta = findViewById(R.id.sw_Teta);
        sw_Alfa = findViewById(R.id.sw_Alfa);
        sw_Beta = findViewById(R.id.sw_Beta);

       

        playBinauralSolo = findViewById(R.id.bt_play);


        /*Valor mínimo do tempo de execução 1 minuto*/
        tempoExecucao.setText(String.valueOf(ValoresAudio.getTempoExecucao()));



        acionaBatimento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!acionaBatimento.isChecked() || acionaBatimento.equals(null)){
                    ValoresAudio.setAcionaBinaural(false);
                }else{
                    ValoresAudio.setAcionaBinaural(true);

                }

                try {
                    ValoresAudio.setTempoExecucao(Integer.parseInt(tempoExecucao.getText().toString()));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Tempo de execução deve ser maior ou igual a 1 minuto", Toast.LENGTH_LONG).show();
                }

                if(Integer.parseInt(frequenciaDireto.getText().toString()) > frequenciaMaxima){
                    ValoresAudio.setFrequenciaDireto(frequenciaMaxima);
                    sb_frequenciaDireto.setProgress(frequenciaMaxima);
                    frequenciaDireto.setText(frequenciaMaxima);
                    Toast.makeText(getApplicationContext(), "Valor maximo "+ frequenciaMaxima + " Hz", Toast.LENGTH_SHORT).show();
                }else{
                    sb_frequenciaDireto.setProgress(Integer.parseInt(frequenciaDireto.getText().toString()));
                    ValoresAudio.setFrequenciaDireto(Integer.parseInt(frequenciaDireto.getText().toString()));

                }

                if(Integer.parseInt(frequenciaEsquerdo.getText().toString()) > frequenciaMaxima){
                    ValoresAudio.setFrequenciaEsquerdo(frequenciaMaxima);
                    sb_frequenciaEsquerdo.setProgress(frequenciaMaxima);
                    frequenciaEsquerdo.setText(frequenciaMaxima);
                    Toast.makeText(getApplicationContext(), "Valor maximo " + frequenciaMaxima + " Hz", Toast.LENGTH_SHORT).show();
                }else{
                    sb_frequenciaEsquerdo.setProgress(Integer.parseInt(frequenciaEsquerdo.getText().toString()));
                    ValoresAudio.setFrequenciaEsquerdo(Integer.parseInt(frequenciaEsquerdo.getText().toString()));

                }

            }
        });


        tempoExecucao.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(tempoExecucao.getText().toString().equals("") || Integer.parseInt(tempoExecucao.getText().toString()) == 0 ){
                    Toast.makeText(getApplicationContext(),"Tempo de execução deve ser maior ou igual a 1 minuto", Toast.LENGTH_LONG).show();
                    tempoExecucao.setText("1");
                }else{
                    try {
                        ValoresAudio.setTempoExecucao(Integer.parseInt(tempoExecucao.getText().toString()));
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Tempo de execução deve ser maior ou igual a 1 minuto", Toast.LENGTH_LONG).show();
                    }
                }

                return false;
            }
        });




        /*****************Controle de volume*****************/
        volumeDireito.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float valorVolumeDireito = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorVolumeDireito = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ValoresAudio.setVolumeDireito(valorVolumeDireito);
                if(ValoresAudio.getAudioAcionado()){
                    Audio.stop();
                    Audio.audio(ValoresAudio.getFrequenciaDireto(), ValoresAudio.getFrequenciaEsquerdo(),
                            ValoresAudio.getVolumeDireito(), ValoresAudio.getVolumeEsquerdo(), ValoresAudio.getTempoExecucao());
                }


            }
        });

        volumeEsquerdo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float valorVolumeEsquerdo = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorVolumeEsquerdo = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ValoresAudio.setVolumeEsquerdo(valorVolumeEsquerdo);
                if(ValoresAudio.getAudioAcionado()){
                    Audio.stop();
                    Audio.audio(ValoresAudio.getFrequenciaDireto(), ValoresAudio.getFrequenciaEsquerdo(),
                            ValoresAudio.getVolumeDireito(), ValoresAudio.getVolumeEsquerdo(), ValoresAudio.getTempoExecucao());
                }

            }
        });


        /******************Faz com que os valores do texto sejam atualizados no SeekBar o mesmos ao contrário***********/
        sb_frequenciaDireto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int valorDireito = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorDireito = progress;
                frequenciaDireto.setText(String.valueOf(valorDireito));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                //Poe as chaves frequencia fixa em false para quaisquer outras frequencias.
                sw_Delta.setChecked(false);
                sw_Teta.setChecked(false);
                sw_Alfa.setChecked(false);
                sw_Beta.setChecked(false);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ValoresAudio.setFrequenciaDireto(valorDireito);


            }
        });

        frequenciaDireto.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(Integer.parseInt(frequenciaDireto.getText().toString()) > frequenciaMaxima){
                    ValoresAudio.setFrequenciaDireto(frequenciaMaxima);
                    sb_frequenciaDireto.setProgress(frequenciaMaxima);
                    frequenciaDireto.setText(String.valueOf(frequenciaMaxima));
                    Toast.makeText(getApplicationContext(), "Valor maximo " + frequenciaMaxima + " Hz", Toast.LENGTH_SHORT).show();
                }else{
                    sb_frequenciaDireto.setProgress(Integer.parseInt(frequenciaDireto.getText().toString()));

                }

                return false;

            }
        });



        /******************Faz com que os valores do texto sejam atualizados no SeekBar o mesmos ao contrário***********/
        sb_frequenciaEsquerdo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int valorEsquerdo = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorEsquerdo = progress;
                frequenciaEsquerdo.setText(String.valueOf(valorEsquerdo));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                //Poe as chaves frequencia fixa em false para quaisquer outras frequencias.
                sw_Delta.setChecked(false);
                sw_Teta.setChecked(false);
                sw_Alfa.setChecked(false);
                sw_Beta.setChecked(false);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ValoresAudio.setFrequenciaEsquerdo(valorEsquerdo);
                frequenciaEsquerdo.setText(String.valueOf(valorEsquerdo));

            }
        });


        frequenciaEsquerdo.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(Integer.parseInt(frequenciaEsquerdo.getText().toString()) > frequenciaMaxima){
                    ValoresAudio.setFrequenciaEsquerdo(frequenciaMaxima);
                    sb_frequenciaEsquerdo.setProgress(frequenciaMaxima);
                    frequenciaEsquerdo.setText(String.valueOf(frequenciaMaxima));
                    Toast.makeText(getApplicationContext(), "Valor maximo " + frequenciaMaxima + " Hz", Toast.LENGTH_SHORT).show();
                }else{
                    sb_frequenciaEsquerdo.setProgress(Integer.parseInt(frequenciaEsquerdo.getText().toString()));

                }

                return false;
            }
        });



        /***********Controla as as chaves de seleção das frequencias fixas*************/

        sw_Delta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                sw_Teta.setChecked(false);
                sw_Alfa.setChecked(false);
                sw_Beta.setChecked(false);

                ValoresAudio.setBt_Beta(false);
                ValoresAudio.setBt_Alfa(false);
                ValoresAudio.setBt_Teta(false);

                if(isChecked){
                    sw_Delta.setChecked(true);
                    ValoresAudio.setBt_Delta(true);
                }else{
                    sw_Delta.setChecked(false);
                    ValoresAudio.setBt_Delta(false);
                }

                ValoresAudio.setFrequenciaDireto(frequenciaBase);
                ValoresAudio.setFrequenciaEsquerdo(frequenciaTeta);
                sb_frequenciaDireto.setProgress(frequenciaBase);
                frequenciaDireto.setText(String.valueOf(frequenciaBase));
                sb_frequenciaEsquerdo.setProgress(frequenciaTeta);
                frequenciaEsquerdo.setText(String.valueOf(frequenciaTeta));
            }
        });


        sw_Teta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                sw_Delta.setChecked(false);
                sw_Alfa.setChecked(false);
                sw_Beta.setChecked(false);

                ValoresAudio.setBt_Delta(false);
                ValoresAudio.setBt_Alfa(false);
                ValoresAudio.setBt_Beta(false);

                if(isChecked){
                    sw_Teta.setChecked(true);
                    ValoresAudio.setBt_Teta(true);
                }else{
                    sw_Teta.setChecked(false);
                    ValoresAudio.setBt_Teta(false);
                }

                ValoresAudio.setFrequenciaDireto(frequenciaBase);
                ValoresAudio.setFrequenciaEsquerdo(frequenciaDelta);
                sb_frequenciaDireto.setProgress(frequenciaBase);
                frequenciaDireto.setText(String.valueOf(frequenciaBase));
                sb_frequenciaEsquerdo.setProgress(frequenciaDelta);
                frequenciaEsquerdo.setText(String.valueOf(frequenciaDelta));
            }
        });





        sw_Alfa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                sw_Teta.setChecked(false);
                sw_Delta.setChecked(false);
                sw_Beta.setChecked(false);

                ValoresAudio.setBt_Delta(false);
                ValoresAudio.setBt_Teta(false);
                ValoresAudio.setBt_Beta(false);

                if(isChecked){
                    sw_Alfa.setChecked(true);
                    ValoresAudio.setBt_Alfa(true);
                }else{
                    sw_Alfa.setChecked(false);
                    ValoresAudio.setBt_Alfa(false);
                }

                ValoresAudio.setFrequenciaDireto(frequenciaBase);
                ValoresAudio.setFrequenciaEsquerdo(frequenciaAlfa);
                sb_frequenciaDireto.setProgress(frequenciaBase);
                frequenciaDireto.setText(String.valueOf(frequenciaBase));
                sb_frequenciaEsquerdo.setProgress(frequenciaAlfa);
                frequenciaEsquerdo.setText(String.valueOf(frequenciaAlfa));

            }
        });



        sw_Beta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                sw_Teta.setChecked(false);
                sw_Alfa.setChecked(false);
                sw_Delta.setChecked(false);

                ValoresAudio.setBt_Delta(false);
                ValoresAudio.setBt_Alfa(false);
                ValoresAudio.setBt_Teta(false);

                if(isChecked){
                    sw_Beta.setChecked(true);
                    ValoresAudio.setBt_Beta(true);
                }else{
                    sw_Beta.setChecked(false);
                    ValoresAudio.setBt_Beta(false);
                }

                ValoresAudio.setFrequenciaDireto(frequenciaBase);
                ValoresAudio.setFrequenciaEsquerdo(frequenciaBeta);
                sb_frequenciaDireto.setProgress(frequenciaBase);
                frequenciaDireto.setText(String.valueOf(frequenciaBase));
                sb_frequenciaEsquerdo.setProgress(frequenciaBeta);
                frequenciaEsquerdo.setText(String.valueOf(frequenciaBeta));
            }
        });

        /******************************************************************/


        playBinauralSolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    playBinaural();

                    if (ValoresAudio.getAudioAcionado()) {
                        playBinauralSolo.setText("Interromper");
                    } else {
                        playBinauralSolo.setText("Iniciar");
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Ocorreu um erro na execução", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    protected void onResume() {
        super.onResume();


            frequenciaDireto.setText(String.valueOf((int)ValoresAudio.getFrequenciaDireto()));
            sb_frequenciaDireto.setProgress((int)ValoresAudio.getFrequenciaDireto());
            frequenciaEsquerdo.setText(String.valueOf((int)ValoresAudio.getFrequenciaEsquerdo()));
            sb_frequenciaEsquerdo.setProgress((int)ValoresAudio.getFrequenciaEsquerdo());
            tempoExecucao.setText(String.valueOf(ValoresAudio.getTempoExecucao()));
            volumeDireito.setProgress((int)ValoresAudio.getVolumeDireito());
            volumeEsquerdo.setProgress((int)ValoresAudio.getVolumeEsquerdo());
            acionaBatimento.setChecked(ValoresAudio.getAcionaBinaural());

            sw_Delta.setChecked(ValoresAudio.getBt_Delta());
            sw_Teta.setChecked(ValoresAudio.getBt_Teta());
            sw_Alfa.setChecked(ValoresAudio.getBt_Alfa());
            sw_Beta.setChecked(ValoresAudio.getBt_Beta());



        if(ValoresAudio.getAudioAcionado()){
            playBinauralSolo.setText("Interromper");
        }else{
            playBinauralSolo.setText("Iniciar");
        }


    }



    public static void playBinaural(){


            if(iniciar) { // inicia o audio


                    iniciaAudio.audio(ValoresAudio.getFrequenciaDireto(), ValoresAudio.getFrequenciaEsquerdo(),
                            ValoresAudio.getVolumeDireito(), ValoresAudio.getVolumeEsquerdo(), ValoresAudio.getTempoExecucao());

                    iniciar = false;


            }else{ // interrompe o audio

                iniciaAudio.stop();

                iniciar = true;
            }



    }



}