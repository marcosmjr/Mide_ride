package com.exemple.mindride.Player;

import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.mindride.Banco_de_Dados.OrganizaUsuario;
import com.exemple.mindride.Binaural.Audio;
import com.exemple.mindride.Binaural.MainBinaural;
import com.exemple.mindride.Binaural.ValoresAudio;
import com.exemple.mindride.MusicRelact.Adaptador_autor;
import com.exemple.mindride.MusicRelact.AdapterMusicas;
import com.exemple.mindride.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class Configuracao_barra  extends RecyclerView.Adapter<Configuracao_barra.MyViewHolder> implements MediaPlayer.OnPreparedListener {

    private static final String TAG = "RecyclerViewAdapter";

    public static String pegaDadodeMusica ;
    private Context mContext;
    public static  ImageButton iPlayButton,iFavoriteButton;
    public Configuracao_barra(Context context) {
        mContext = context;
    }


    View itemLista;
    @NonNull
    @Override
    //O método cria as 1ºs visualizações
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.barra_player, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    public static MediaPlayer mediaPlayer = MySingleton.getInstance();   /*<<<<=========MUDANÇAS AQUI************/

    //Criando uma classe dentro de uma classe, pede para criar um construtor
    public class MyViewHolder extends RecyclerView.ViewHolder {
         ImageButton iAnteriorButton;
         ImageButton iNextButton;
         ImageButton binaural;
         boolean alternaBotaoFav = false;

         int favoriteButao = 0;


        final OrganizaUsuario organizaUsuario = new OrganizaUsuario();
        final Adaptador_autor adaptador_autor = new Adaptador_autor();
        final AdapterMusicas adapterMusicas = new AdapterMusicas(mContext);



        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            iFavoriteButton = itemView.findViewById(R.id.iFavoriteButton);
            iPlayButton = itemView.findViewById(R.id.iPlayButton);
            iAnteriorButton = itemView.findViewById(R.id.iAnteriorButton);
            iNextButton = itemView.findViewById(R.id.iProximaButton);
            binaural = itemView.findViewById(R.id.iNauralButton);


            binaural.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(OrganizaUsuario.logadoGuest == false) {
                        Intent intent = new Intent(mContext, MainBinaural.class);
                        mContext.startActivity(intent);

                    }else{
                        Toast.makeText(mContext, "É Necessário uma Conta Logada para Acessar essa Opção!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            iAnteriorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

            iNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { // Mudanças aqui


                }
            });



            String formataLink = adaptador_autor.getPegaURLocal();
            formataLink = formataLink.replace(".", "_");
            formataLink = formataLink.replace("/", "@");
            final String finalFormataLink = formataLink;
            organizaUsuario.verificaMarca(adapterMusicas.nomeAtriz, adapterMusicas.nomeMusica, formataLink, iFavoriteButton);


            //Favoritar musica!
            iFavoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
            if(OrganizaUsuario.logadoGuest == false) {
                organizaUsuario.verificaMusica(adapterMusicas.nomeAtriz, adapterMusicas.nomeMusica, finalFormataLink, iFavoriteButton);

            }else{
                Toast.makeText(mContext, "É Necessário uma Conta Logada para Acessar essa Opção!!!", Toast.LENGTH_SHORT).show();
            }
            }
            });









            audioSolo();
            if(mediaPlayer.isPlaying()){

                iPlayButton.setBackgroundResource(R.drawable.pause);

            }else{

                iPlayButton.setBackgroundResource(R.drawable.play);

            }
            iPlayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mediaPlayer.isPlaying()){
                    iPlayButton.setBackgroundResource(R.drawable.play);
                    mediaPlayer.pause();


                        /*********************Pausa Binaural Beat ***************/

                        if(ValoresAudio.getAudioAcionado()){
                            Audio.pause();
                        }

                        /**************************************************/


                    }else{
                        iPlayButton.setBackgroundResource(R.drawable.pause);
                        mediaPlayer.start();

                        /*********************Inicia Binaural Beat ***************/

                        if(ValoresAudio.getAcionaBinaural() && !ValoresAudio.getAudioAcionado()){
                            try{
                            Audio.audio(ValoresAudio.getFrequenciaDireto(), ValoresAudio.getFrequenciaEsquerdo(),
                                    ValoresAudio.getVolumeDireito(), ValoresAudio.getVolumeEsquerdo(), ValoresAudio.getTempoExecucao());
                            }catch (Exception ex){
                            Toast.makeText(mContext,"Ocorreu um erro na execução do Binaural Beat", Toast.LENGTH_SHORT).show();
                            }
                        }

                        /**************************************************/
                    }
                }
            });

        }

        protected void audioSolo() {
            final Adaptador_autor adaptador_autor = new Adaptador_autor();
            if (!mediaPlayer.isPlaying() && mediaPlayer.getCurrentPosition() != 0 && AdapterMusicas.restauraMusica == 1) {
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                        final StorageReference storageReference = firebaseStorage.getReferenceFromUrl(adaptador_autor.getPegaURLocal());
                        pegaDadodeMusica = adaptador_autor.getPegaURLocal();
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                            @Override
                            public void onSuccess(Uri uri) {
                                final String audioUrl = uri.toString();

                                // enviar como parâmetro para o método sendUrlToMediaPlayer()



                                            sendUrlToMediaPlayer(audioUrl);
                                            iPlayButton.setBackgroundResource(R.drawable.pause);




                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        iPlayButton.setBackgroundResource(R.drawable.play);
                        mediaPlayer.reset();
                    }
                }
        }
        public void sendUrlToMediaPlayer(final String url) {
            try {
                // enviar a StreamUrl para o player
                if(!mediaPlayer.isPlaying() && mediaPlayer.getCurrentPosition() != 0) {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync();
                    AdapterMusicas.restauraMusica = 0;
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                            iPlayButton.setBackgroundResource(R.drawable.pause);

                          /*********************Inicia Binaural Beat ***************/

                           if(ValoresAudio.getAcionaBinaural() && !ValoresAudio.getAudioAcionado()){
                               try {
                                   Audio.audio(ValoresAudio.getFrequenciaDireto(), ValoresAudio.getFrequenciaEsquerdo(),
                                           ValoresAudio.getVolumeDireito(), ValoresAudio.getVolumeEsquerdo(), ValoresAudio.getTempoExecucao());
                               }catch (Exception ex){
                                   Toast.makeText(mContext,"Ocorreu um erro na execução do Binaural Beat", Toast.LENGTH_SHORT).show();
                               }
                            }

                            /**************************************************/

                        }
                    });
                }else{
                    mediaPlayer.reset();
                    iPlayButton.setBackgroundResource(R.drawable.play);

                    /*********************Interrompe Binaural Beat ***************/

                    if(ValoresAudio.getAudioAcionado()){
                        Audio.stop();
                    }

                    /**************************************************/


                }
            } catch (IOException err) {
                Log.e("Audio Error", err.toString());
                mediaPlayer.reset();
                iPlayButton.setBackgroundResource(R.drawable.play);
            }
        }
    }



    public RecyclerView.LayoutManager reciclaBarra(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext.getApplicationContext());
      return  layoutManager;
    }

    @Override
    // O método que exibe os dados da view
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    }

    @Override
    public int getItemCount() { // Tamanho da recyclerView //Número de elementos do recyclerView

        final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        //A ideia aqui é conseguir pegar o total de arquivos dentro da database.. de qualquer lugar serve , apenas precisamos para montar algo legal encima.. pois o return irá retornar
        // o total de itens presentes na tela com as informações do onBindViewHolder na activity lista_reciclavel.
        return 1;
    }
}
