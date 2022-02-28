package com.exemple.mindride.Player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.mindride.Banco_de_Dados.OrganizaUsuario;
import com.exemple.mindride.MusicRelact.Adaptador_autor;
import com.exemple.mindride.MusicRelact.AdapterMusicas;
import com.exemple.mindride.R;

public class ListaReciclavel extends AppCompatActivity {

    private RecyclerView listaMusica;
    private RecyclerView barraPlayer;
    private static MediaPlayer mediaPlayer = MySingleton.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_reciclavel);
        barraPlayer = findViewById(R.id.barraDOplayer);
        listaMusica = findViewById(R.id.listaMusica);


        //configurar adapter

        AdapterMusicas adapterMusicas = new AdapterMusicas(getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaMusica.setLayoutManager(layoutManager);
        listaMusica.setHasFixedSize(true); /*fixa o numero de view que será reciclada */
        listaMusica.setAdapter(adapterMusicas); //precisa configurar o adaptador



        RecyclerView.LayoutManager layoutManager2;
        Configuracao_barra barra = new Configuracao_barra(getApplicationContext());
        layoutManager2 = barra.reciclaBarra();
        barraPlayer.setLayoutManager(layoutManager2);
        barraPlayer.setHasFixedSize(true); /*fixa o numero de view que será reciclada */
        barraPlayer.setAdapter(barra); //precisa configurar o adaptador


        Adaptador_autor adaptador_autor = new Adaptador_autor();
        if(adaptador_autor.getPegaURLocal() != ""){
            barraPlayer.setVisibility(View.VISIBLE);

            if(mediaPlayer.isPlaying()){
                Configuracao_barra.iPlayButton.setBackgroundResource(R.drawable.pause);
            }else{
                Configuracao_barra.iPlayButton.setBackgroundResource(R.drawable.play);
            }

        }else{
            barraPlayer.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        barraPlayer.setLayoutManager(null);
        barraPlayer.setHasFixedSize(false); /*fixa o numero de view que será reciclada */
        barraPlayer.setAdapter(null);

        RecyclerView.LayoutManager layoutManager2;
        Configuracao_barra barra = new Configuracao_barra(getApplicationContext());
        layoutManager2 = barra.reciclaBarra();
        barraPlayer.setLayoutManager(layoutManager2);
        barraPlayer.setHasFixedSize(true); /*fixa o numero de view que será reciclada */
        barraPlayer.setAdapter(barra); //precisa configurar o adaptador

        Adaptador_autor adaptador_autor = new Adaptador_autor();
        if (adaptador_autor.getPegaURLocal() != "") {
            barraPlayer.setVisibility(View.VISIBLE);


            OrganizaUsuario organizaUsuario = new OrganizaUsuario();

            AdapterMusicas adapterMusicas = new AdapterMusicas(getApplicationContext());

            String formataLink = adaptador_autor.getPegaURLocal();
            formataLink = formataLink.replace(".", "_");
            formataLink = formataLink.replace("/", "@");
            organizaUsuario.verificaMarca(adapterMusicas.nomeAtriz,adapterMusicas.nomeMusica, formataLink,Configuracao_barra.iFavoriteButton);


            if (mediaPlayer.isPlaying()) {
                Configuracao_barra.iPlayButton.setBackgroundResource(R.drawable.pause);
            } else {
                Configuracao_barra.iPlayButton.setBackgroundResource(R.drawable.play);
            }

        } else {
            barraPlayer.setVisibility(View.GONE);
        }
    }
}
