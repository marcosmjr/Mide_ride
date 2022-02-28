package com.exemple.mindride.Player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.mindride.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class Configura_som  extends RecyclerView.Adapter<Configura_som.MyViewHolder> implements MediaPlayer.OnPreparedListener {

    private static final String TAG = "RecyclerViewAdapter";
    private Context mContext;
    public static MediaPlayer mediaPlayer  = new MediaPlayer();   /*<<<<=========MUDANÇAS AQUI************/
    public static MediaPlayer mediaPlayer2 = new MediaPlayer();
    public static MediaPlayer mediaPlayer3 = new MediaPlayer();

    //Setting de boolean para trocar background
    public int pegaNomeBotao  = 0;
    public int pegaNomeBotao2 = 0;
    public int pegaNomeBotao3 = 0;



    public Configura_som(Context context) {
        mContext = context;
    }

    View itemLista;
    @NonNull
    @Override
    //O método cria as 1ºs visualizações
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.opcoes_som, parent, false);
        return new MyViewHolder(itemLista);

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }


    //Criando uma classe dentro de uma classe, pede para criar um construtor
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //Lista da Agua
        private ImageButton playPscina,playDrenagem,playFloresta,playCorrente;
        //Lista de Ambientes
        private ImageButton playEscola;
        //Lista de Chuvas
        private ImageButton playTrovao,playChuva,playChuvaFloresta,playChuvaPingando;
        //Lista de Coração
        private ImageButton playBatida1,playBatida2,playBatidaLoop;
        //Lista de Escova de Cabelo
        private ImageButton playEscovaPlastico,playEscova,playEscovaProfunda,playEscovando;
        //Lista de Esponjas
        private ImageButton playApertandoEsponja,playFritandoEsponja,playLadrilhosEsponja;
        //Lista de Floresta
        private ImageButton playLoopPassaros,playPasseioFerias;
        //Lista de Galope
        private ImageButton playGalopando,playGalopandoLoop;
        //Lista de Guitarra
        private ImageButton playGuitarra1,playGuitarra2,playGuitarra3,playGuitarra4,playGuitarra5;
        //Lista de Mantra
        private ImageButton playMantra1,playMantra2,playMantra3,playMantra4,playMantra5,playMantra6;
        //Lista de Meditação
        private ImageButton playMeditacaoSinos,playMeditacaoTacas;
        //Lista de Papel
        private ImageButton playPapelDesmoronando,playPapelDobrando,playPapelDesenhando,playPapelRasgando;
        //Lista Psicodelica
        private ImageButton playPsicodelico1,playPsicodelico2,playPsicodelico3,playPsicodelico4,playPsicodelico5;
        //Lista de Ventos
        private ImageButton playVento1,playVento2,playVento3;
        //Lista de Sinos
        private ImageButton playSino1,playSino2,playSino3;




        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            //Encontros de Agua
            playPscina = itemView.findViewById(R.id.pscinaPlay);
            TocaMusica(playPscina,"gs://mind-ride-aplicativo.appspot.com/ASMR/agua/173930__johnsonbrandediting__water-pour.mp3");
            playDrenagem = itemView.findViewById(R.id.drenagemPlay);
            TocaMusica(playDrenagem,"gs://mind-ride-aplicativo.appspot.com/ASMR/agua/391475__coltures__water-draining.wav");
            playFloresta = itemView.findViewById(R.id.florestaPlay);
            TocaMusica(playFloresta,"gs://mind-ride-aplicativo.appspot.com/ASMR/agua/403047__teadrinker__water-forest-stream-08-l.wav");
            playCorrente = itemView.findViewById(R.id.correntePlay);
            TocaMusica(playCorrente,"gs://mind-ride-aplicativo.appspot.com/ASMR/agua/415151__burghrecords__stream-running-water-aviemore-scotland.wav");
            //Encontro de Ambiente
            playEscola = itemView.findViewById(R.id.escolaPlay);
            TocaMusica(playEscola,"gs://mind-ride-aplicativo.appspot.com/ASMR/ambiente/428139__erick-son__audio-ambiente-escolar-1-sound-of-school-environment.mp3");
            //Encontro de Chuvas
            playTrovao = itemView.findViewById(R.id.trovaoPlay);
            TocaMusica(playTrovao,"gs://mind-ride-aplicativo.appspot.com/ASMR/chuva/237729__flathill__rain-and-thunder-4.wav");
            playChuva = itemView.findViewById(R.id.chuvaPlay);
            TocaMusica(playChuva,"gs://mind-ride-aplicativo.appspot.com/ASMR/chuva/265627__director89__rain-atmo.wav");
            playChuvaFloresta = itemView.findViewById(R.id.chuvaFlorestaPlay);
            TocaMusica(playChuvaFloresta,"gs://mind-ride-aplicativo.appspot.com/ASMR/chuva/457447__innorecords__rain-sound-and-rainforest.mp3");
            playChuvaPingando = itemView.findViewById(R.id.chuvaPingandoPlay);
            TocaMusica(playChuvaPingando,"gs://mind-ride-aplicativo-aplicativo.appspot.com/ASMR/chuva/476202__djmistressm__rain-drips.mp3");
            //Encontro de Corações <3
            playBatida1 = itemView.findViewById(R.id.batidaPlay1);
            TocaMusica(playBatida1,"gs://mind-ride-aplicativo.appspot.com/ASMR/coracao/369017__patobottos__heartbeats-61.wav");
            playBatida2 = itemView.findViewById(R.id.batidaPlay12);
            TocaMusica(playBatida2,"gs://mind-ride-aplicativo.appspot.com/ASMR/coracao/396706__grandscient__heartbeat.wav");
            playBatidaLoop = itemView.findViewById(R.id.batidaLoopPlay);
            TocaMusica(playBatidaLoop,"gs://mind-ride-aplicativo.appspot.com/ASMR/coracao/410378__b-train__heartbeat-looper.wav");
            //Encontro de Escovas
            playEscovaPlastico = itemView.findViewById(R.id.escovaPlasticoPlay);
            TocaMusica(playEscovaPlastico,"gs://mind-ride-aplicativo.appspot.com/ASMR/escova_de_cabelo/199299__katireh__plastic-hairbrush-2.wav");
            playEscova = itemView.findViewById(R.id.escovaPlay);
            TocaMusica(playEscova,"gs://mind-ride-aplicativo.appspot.com/ASMR/escova_de_cabelo/365499__caitlin-100__hairbrush.mp3");
            playEscovando = itemView.findViewById(R.id.escovandoPlay);
            TocaMusica(playEscovando,"gs://mind-ride-aplicativo.appspot.com/ASMR/escova_de_cabelo/475701__shelbyshark__hairbrushing.mp3");
            playEscovaProfunda = itemView.findViewById(R.id.escovaProfundaPlay);
            TocaMusica(playEscovaProfunda,"gs://mind-ride-aplicativo.appspot.com/ASMR/escova_de_cabelo/419791__14gpanskamuzatko-matej__15-hair-brush-deep.wav");
            //Encontro de Esponjas
            playApertandoEsponja = itemView.findViewById(R.id.apertandoEsponjaPlay);
            TocaMusica(playApertandoEsponja,"gs://mind-ride-aplicativo.appspot.com/ASMR/esponja/331984__dmunk__squeezing-sponge.wav");
            playFritandoEsponja = itemView.findViewById(R.id.fritandoEsponjaPlay);
            TocaMusica(playFritandoEsponja,"gs://mind-ride-aplicativo.appspot.com/ASMR/esponja/402135__mickael-leroi__mop-a-frying-pan-1.wav");
            playLadrilhosEsponja = itemView.findViewById(R.id.ladrilhosEsponjaPlay);
            TocaMusica(playLadrilhosEsponja,"gs://mind-ride-aplicativo.appspot.com/ASMR/esponja/402154__mickael-leroi__sponge-floor-tiles-2.wav");
            //Encontro de Florestas
            playLoopPassaros = itemView.findViewById(R.id.loopDePassarosPlay);
            TocaMusica(playLoopPassaros,"gs://mind-ride-aplicativo.appspot.com/ASMR/floresta/231537__vkproduktion__forest-birds-loop-02.wav");
            playPasseioFerias = itemView.findViewById(R.id.passeioDeFeriasPlay);
            TocaMusica(playPasseioFerias,"gs://mind-ride-aplicativo.appspot.com/ASMR/floresta/274833__rempen__forest-dry-leaves-walk.wav");
            playPasseioFerias = itemView.findViewById(R.id.passeioDeFeriasPlay);
            TocaMusica(playPasseioFerias,"gs://mind-ride-aplicativo.appspot.com/ASMR/floresta/456122__burghrecords__birds-outdoors.wav");
            //Encontro de Galope
            playGalopando = itemView.findViewById(R.id.galopandoPlay);
            TocaMusica(playGalopando,"gs://mind-ride-aplicativo.appspot.com/ASMR/galope/274898__podsburgh__maxheadroom-s-galloping-horse-39-sec.mp3");
            playGalopandoLoop = itemView.findViewById(R.id.galopandoLoopPlay);
            TocaMusica(playGalopandoLoop,"gs://mind-ride-aplicativo.appspot.com/ASMR/galope/322522__deadxcreed__harse-gallop-loop2.wav");
            //Encontro de Guitarras
            playGuitarra1 = itemView.findViewById(R.id.guitarra1Play);
            TocaMusica(playGuitarra1,"gs://mind-ride-aplicativo.appspot.com/ASMR/guitarra/167114__ibcharlie-parker__guitar.mp3");
            playGuitarra2 = itemView.findViewById(R.id.guitarra2Play);
            TocaMusica(playGuitarra2,"gs://mind-ride-aplicativo.appspot.com/ASMR/guitarra/240479__uneekstringz__guitar-summer.mp3");
            playGuitarra3 = itemView.findViewById(R.id.guitarra3Play);
            TocaMusica(playGuitarra3,"gs://mind-ride-aplicativo.appspot.com/ASMR/guitarra/362832__4barrelcarb__nylon-string-guitar-with-steel-string-bass.mp3");
            playGuitarra4 = itemView.findViewById(R.id.guitarra4Play);
            TocaMusica(playGuitarra4,"gs://mind-ride-aplicativo.appspot.com/ASMR/guitarra/417499__markiboy123__f-add11-moment.mp3");
            playGuitarra5 = itemView.findViewById(R.id.guitarra5Play);
            TocaMusica(playGuitarra5,"gs://mind-ride-aplicativo.appspot.com/ASMR/guitarra/76136__memz__guitar.mp3");
            //Encontro de Mantas
            playMantra1 = itemView.findViewById(R.id.mantra1Play);
            TocaMusica(playMantra1,"gs://mind-ride-aplicativo.appspot.com/ASMR/mantra/145785__lamat__aum-02-528hz.mp3");
            playMantra2 = itemView.findViewById(R.id.mantra2Play);
            TocaMusica(playMantra2,"gs://mind-ride-aplicativo.appspot.com/ASMR/mantra/240151__the-very-real-horst__tao-chi-prayer-wheels-01-30.wav");
            playMantra3 = itemView.findViewById(R.id.mantra3Play);
            TocaMusica(playMantra3,"gs://mind-ride-aplicativo.appspot.com/ASMR/mantra/411523__stinone__bamboomantra.wav");
            playMantra4 = itemView.findViewById(R.id.mantra4Play);
            TocaMusica(playMantra4,"gs://mind-ride-aplicativo.appspot.com/ASMR/mantra/456079__toiletrolltube__remix-of-294834-drone-4.wav");
            playMantra5 = itemView.findViewById(R.id.mantra5Play);
            TocaMusica(playMantra5,"gs://mind-ride-aplicativo.appspot.com/ASMR/mantra/499682__isohoo__monks-mantra.wav");
            playMantra6 = itemView.findViewById(R.id.mantra6Play);
            TocaMusica(playMantra6,"gs://mind-ride-aplicativo.appspot.com/ASMR/mantra/502672__isohoo__buddhism-ceremony.wav");
            //Encontro de Meditação
            playMeditacaoTacas = itemView.findViewById(R.id.meditacaoTacaPlay);
            TocaMusica(playMeditacaoTacas,"gs://mind-ride-aplicativo.appspot.com/ASMR/meditacao/473813__sandhwani__meditation-bowls.wav");
            playMeditacaoSinos = itemView.findViewById(R.id.meditacaoSinoPlay);
            TocaMusica(playMeditacaoSinos,"gs://mind-ride-aplicativo.appspot.com/ASMR/meditacao/42095__fauxpress__bell-meditation.mp3");
            //Encontro de Papeis
            playPapelDesenhando = itemView.findViewById(R.id.papelDesenhandoPlay);
            TocaMusica(playPapelDesenhando,"gs://mind-ride-aplicativo.appspot.com/ASMR/papel/387926__rylandbrooks__pencil-drawing-on-paper.mp3");
            playPapelDesmoronando = itemView.findViewById(R.id.papelDesmoronandoPlay);
            TocaMusica(playPapelDesmoronando,"gs://mind-ride-aplicativo.appspot.com/ASMR/papel/214854__bash360__crumbling-paper.mp3");
            playPapelDobrando = itemView.findViewById(R.id.papelDobrandoPlay);
            TocaMusica(playPapelDobrando,"gs://mind-ride-aplicativo.appspot.com/ASMR/papel/367683__funwithsound__paper-fold.mp3");
            playPapelRasgando = itemView.findViewById(R.id.papelRasgandoPlay);
            TocaMusica(playPapelRasgando,"gs://mind-ride-aplicativo.appspot.com/ASMR/papel/394840__digpro120__crumpling-paper.mp3");
            //Encontro Psicodelico
            playPsicodelico1 = itemView.findViewById(R.id.psicodelico1Play);
            TocaMusica(playPsicodelico1,"gs://mind-ride-aplicativo.appspot.com/ASMR/psicodelico/11198__proutlip__industrial.mp3");
            playPsicodelico2 = itemView.findViewById(R.id.psicodelico2Play);
            TocaMusica(playPsicodelico2,"gs://mind-ride-aplicativo.appspot.com/ASMR/psicodelico/172089__burninvernon__santa-on-acid.mp3");
            playPsicodelico3 = itemView.findViewById(R.id.psicodelico3Play);
            TocaMusica(playPsicodelico3,"gs://mind-ride-aplicativo.appspot.com/ASMR/psicodelico/202708__npeo__colours.wav");
            playPsicodelico4 = itemView.findViewById(R.id.psicodelico4Play);
            TocaMusica(playPsicodelico4,"gs://mind-ride-aplicativo.appspot.com/ASMR/psicodelico/456513__jalastram__session-one.mp3");
            playPsicodelico5 = itemView.findViewById(R.id.psicodelico5Play);
            TocaMusica(playPsicodelico5,"gs://mind-ride-aplicativo.appspot.com/ASMR/psicodelico/58769__suonho__erh-p1-2o2bb12-14-alien-sounds-floating-fortress-rwrk.wav");
            //Encontro de Ventos
            playVento1 = itemView.findViewById(R.id.vento1Play);
            TocaMusica(playVento1,"gs://mind-ride-aplicativo.appspot.com/ASMR/vento/29532__eliasheuninck__wind3.wav");
            playVento2 = itemView.findViewById(R.id.vento2Play);
            TocaMusica(playVento2,"gs://mind-ride-aplicativo.appspot.com/ASMR/vento/457318__stek59__autumn-wind-and-dry-leaves.wav");
            playVento3 = itemView.findViewById(R.id.vento3Play);
            TocaMusica(playVento3,"gs://mind-ride-aplicativo.appspot.com/ASMR/vento/459977__florianreichelt__soft-wind.mp3");
            //Encontro de Sinos
            playSino1 = itemView.findViewById(R.id.sino1Play);
            TocaMusica(playSino1,"gs://mind-ride-aplicativo.appspot.com/ASMR/sino/139103__mxsmanic__bells-ringing-at-notre-dame-cathedral-in-paris-france.mp3");
            playSino2 = itemView.findViewById(R.id.sinos2Play);
            TocaMusica(playSino2,"gs://mind-ride-aplicativo.appspot.com/ASMR/sino/195309__the-very-real-horst__tibetan-silver-bells.mp3");
            playSino3 = itemView.findViewById(R.id.sinos3Play);
            TocaMusica(playSino3,"gs://mind-ride-aplicativo.appspot.com/ASMR/sino/253104__tithe__bell-tower-at-nc-state-university.mp3");
        }
    }
    public void TocaMusica(final ImageButton botao ,final String text) {

            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pegaNomeBotao == 0 && pegaNomeBotao2 != botao.getId() && pegaNomeBotao3 != botao.getId()) {
                        pegaNomeBotao = botao.getId();
                        System.out.println("Primeiro : "+pegaNomeBotao );
                    }

                    else   if (pegaNomeBotao2 == 0  && pegaNomeBotao != botao.getId() && pegaNomeBotao3 != botao.getId()) {
                        pegaNomeBotao2 = botao.getId();
                        System.out.println("Segundo : "+pegaNomeBotao2);

                    }

                    else  if (pegaNomeBotao3 == 0 && pegaNomeBotao != botao.getId() && pegaNomeBotao2 != botao.getId())  {
                        pegaNomeBotao3 = botao.getId();
                        System.out.println("Terceiro : "+pegaNomeBotao3);
                    }

                    //para o audio iniciar
                    if (!mediaPlayer.isPlaying() && pegaNomeBotao == botao.getId() && mediaPlayer.getCurrentPosition() != 0) {
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                        try {
                            final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                            final StorageReference storageReference = firebaseStorage.getReferenceFromUrl(text);
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                                @Override
                                public void onSuccess(Uri uri) {
                                    final String audioUrl = uri.toString();

                                    // enviar como parâmetro para o método sendUrlToMediaPlayer()
                                    if(!mediaPlayer.isPlaying()) {
                                        sendUrlToMediaPlayer(audioUrl,botao);
                                    }else{
                                        mediaPlayer.reset();
                                        pegaNomeBotao = 0;
                                        botao.setBackgroundResource(R.drawable.play);
                                    }
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            botao.setBackgroundResource(R.drawable.play);
                            mediaPlayer.reset();
                            pegaNomeBotao = 0;
                        }
                    } else if (pegaNomeBotao == botao.getId() && mediaPlayer.isPlaying()) {
                        botao.setBackgroundResource(R.drawable.play);
                        mediaPlayer.reset();
                        pegaNomeBotao = 0;
                        System.out.println("Parou numero 1");
                    }




                    //Até aqui funciona
                    else if (!mediaPlayer2.isPlaying() && pegaNomeBotao2 == botao.getId() && mediaPlayer2.getCurrentPosition() != 0) {

                        try {
                            final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                            final StorageReference storageReference = firebaseStorage.getReferenceFromUrl(text);
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                                @Override
                                public void onSuccess(Uri uri) {
                                    final String audioUrl = uri.toString();

                                    // enviar como parâmetro para o método sendUrlToMediaPlayer()
                                    if(!mediaPlayer2.isPlaying()) {
                                        sendUrlToMediaPlayer2(audioUrl,botao);
                                    }else{
                                        mediaPlayer2.reset();
                                        pegaNomeBotao2 = 0;
                                        botao.setBackgroundResource(R.drawable.play);
                                    }
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            botao.setBackgroundResource(R.drawable.play);
                            mediaPlayer2.reset();
                            pegaNomeBotao2 = 0;
                        }
                    }

                    else if (mediaPlayer2.isPlaying() && pegaNomeBotao2 == botao.getId()) {
                        botao.setBackgroundResource(R.drawable.play);
                        mediaPlayer2.reset();
                        pegaNomeBotao2 = 0;
                        System.out.println("Parou numero 2");
                    }


                    else if (!mediaPlayer3.isPlaying() && pegaNomeBotao3 == botao.getId() && mediaPlayer3.getCurrentPosition() != 0) {
                        try {
                            final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                            final StorageReference storageReference = firebaseStorage.getReferenceFromUrl(text);
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                                @Override
                                public void onSuccess(Uri uri) {
                                    final String audioUrl = uri.toString();

                                    // enviar como parâmetro para o método sendUrlToMediaPlayer()
                                    if(!mediaPlayer3.isPlaying() && mediaPlayer3.getCurrentPosition() != 0) {
                                        sendUrlToMediaPlayer3(audioUrl,botao);
                                    }else{
                                        mediaPlayer3.reset();
                                        pegaNomeBotao3 = 0;
                                        botao.setBackgroundResource(R.drawable.play);
                                    }
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            botao.setBackgroundResource(R.drawable.play);
                            mediaPlayer3.reset();
                            pegaNomeBotao3 = 0;
                        }
                    } else if (mediaPlayer3.isPlaying() && pegaNomeBotao3 == botao.getId()) {
                        botao.setBackgroundResource(R.drawable.play);
                        mediaPlayer3.reset();
                        pegaNomeBotao3 = 0;
                        System.out.println("Parou numero 3");
                    }
                    else if (pegaNomeBotao != botao.getId() && pegaNomeBotao2 != botao.getId() && pegaNomeBotao3 != botao.getId()){
                        Toast.makeText(mContext, "Máximo permitido são 3 musicas ao mesmo tempo!", Toast.LENGTH_SHORT).show();
                    }else{
                        System.out.println("TA BUGADO");
                        if(botao.getId() == pegaNomeBotao){
                            botao.setBackgroundResource(R.drawable.play);
                            mediaPlayer.reset();
                            pegaNomeBotao = 0;
                            System.out.println("Parou numero 1");
                        }
                        if(botao.getId() == pegaNomeBotao2){
                            botao.setBackgroundResource(R.drawable.play);
                            mediaPlayer2.reset();
                            pegaNomeBotao2 = 0;
                            System.out.println("Parou numero 2");
                        }  if(botao.getId() == pegaNomeBotao3){
                            botao.setBackgroundResource(R.drawable.play);
                            mediaPlayer3.reset();
                            pegaNomeBotao3 = 0;
                            System.out.println("Parou numero 1");
                        }
                    }
                }
            });



        }




    void sendUrlToMediaPlayer(String url, final ImageButton botao) {
        try {
            // enviar a StreamUrl para o player

            if(!mediaPlayer.isPlaying() && mediaPlayer.getCurrentPosition() != 0) {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                        botao.setBackgroundResource(R.drawable.pause);
                    }
                });
            }else{
                mediaPlayer.reset();
                pegaNomeBotao = 0;
            }
        } catch (IOException err) {
            Log.e("Audio Error", err.toString());
            mediaPlayer.reset();
            pegaNomeBotao = 0;
        }
    }


    void sendUrlToMediaPlayer2(String url2, final ImageButton botao) {
        try {
            // enviar a StreamUrl para o player

            if(!mediaPlayer2.isPlaying() && mediaPlayer2.getCurrentPosition() != 0) {
                mediaPlayer2.setDataSource(url2);
                mediaPlayer2.prepareAsync();
                mediaPlayer2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer2.start();
                        botao.setBackgroundResource(R.drawable.pause);
                    }
                });
            }else{
                mediaPlayer2.reset();
                pegaNomeBotao2 = 0;
            }
        } catch (IOException err) {
            Log.e("Audio Error", err.toString());
            mediaPlayer2.reset();
            pegaNomeBotao2 = 0;
        }
    }


    void sendUrlToMediaPlayer3(String url3, final ImageButton botao) {
        try {
            // enviar a StreamUrl para o player

            if(!mediaPlayer3.isPlaying() && mediaPlayer3.getCurrentPosition() != 0) {
                mediaPlayer3.setDataSource(url3);
                mediaPlayer3.prepareAsync();
                mediaPlayer3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer3.start();
                        botao.setBackgroundResource(R.drawable.pause);
                    }
                });
            }else{
                botao.setBackgroundResource(R.drawable.play);
                mediaPlayer3.reset();
                pegaNomeBotao3 = 0;
            }
        } catch (IOException err) {
            Log.e("Audio Error", err.toString());
            botao.setBackgroundResource(R.drawable.play);
            mediaPlayer3.reset();
            pegaNomeBotao3 = 0;
        }
    }

    @Override
    // O método que exibe os dados da view
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    }




    @Override
    public int getItemCount() { // Tamanho da recyclerView //Número de elementos do recyclerView
        //A ideia aqui é conseguir pegar o total de arquivos dentro da database.. de qualquer lugar serve , apenas precisamos para montar algo legal encima.. pois o return irá retornar
        // o total de itens presentes na tela com as informações do onBindViewHolder na activity lista_reciclavel.
        return 1;
    }



}
