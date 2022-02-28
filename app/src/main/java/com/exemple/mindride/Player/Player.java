package com.exemple.mindride.Player;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.mindride.Banco_de_Dados.OrganizaUsuario;
import com.exemple.mindride.MusicRelact.Adaptador_autor;
import com.exemple.mindride.MusicRelact.AdapterMusicas;
import com.exemple.mindride.R;

public class Player extends AppCompatActivity {

    private ImageButton pFavorite;
    private boolean alternaBotaoFav = false;

    private ImageButton pPlay;
    private boolean alternaPlay = false;

    private ImageButton pVoltar;
    private ImageButton pLista;
    private ImageButton pOptions;

    private ImageButton imgAlbum, equalizadorAlbum, favoriteAlbum;
    private TextView nomeAlbum, nomeAlbum2, nomeMusicaAlbum, nomeAutorAlbum;
    private int cont = 0;
    private RecyclerView barraPlayer;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        Configura_som configura_som2 = new Configura_som(getApplicationContext());
        if (configura_som2.mediaPlayer.isPlaying()) {
            Toast.makeText(this, "Seu Audio Adicional foi Parado para Você Obter Melhor Experiência!", Toast.LENGTH_SHORT).show();
            System.out.println("Entrou!! uhu");
            configura_som2.pegaNomeBotao = 0;
            configura_som2.mediaPlayer.reset();

        }
        if (configura_som2.mediaPlayer2.isPlaying()) {
            System.out.println("Entrou 2!! uhu");
            configura_som2.pegaNomeBotao2 = 0;
            configura_som2.mediaPlayer2.reset();


        }
        if (configura_som2.mediaPlayer3.isPlaying()) {
            System.out.println("Entrou 3!! uhu");
            configura_som2.pegaNomeBotao3 = 0;
            configura_som2.mediaPlayer3.reset();
        }


        pVoltar = findViewById(R.id.pVoltar);
        pLista = findViewById(R.id.pLista);
        pOptions = findViewById(R.id.pOptions);

        //Relações com o Album para programar a invisibilidade e a vísibilidade da classe.
        pFavorite = findViewById(R.id.favoriteAlbum);
        imgAlbum = findViewById(R.id.imgAlbum);
        equalizadorAlbum = findViewById(R.id.equalizadorAlbum);
        favoriteAlbum = findViewById(R.id.favoriteAlbum);
        nomeAlbum = findViewById(R.id.nomeAlbum);
        nomeAlbum2 = findViewById(R.id.nomeAlbum2);
        nomeMusicaAlbum = findViewById(R.id.nomeMusicaAlbum);
        nomeAutorAlbum = findViewById(R.id.nomeAutorAlbum);


        barraPlayer = findViewById(R.id.barraDOplayer2);







        //Reset da Barra



        RecyclerView.LayoutManager layoutManager2;
        Configuracao_barra barra = new Configuracao_barra(getApplicationContext());
        layoutManager2 = barra.reciclaBarra();

        barraPlayer.setLayoutManager(null);
        barraPlayer.setHasFixedSize(false); /*fixa o numero de view que será reciclada */
        barraPlayer.setAdapter(null);


        barraPlayer.setLayoutManager(layoutManager2);
        barraPlayer.setHasFixedSize(true); /*fixa o numero de view que será reciclada */
        barraPlayer.setAdapter(barra); //precisa configurar o adaptador








        //RECEBENDO DADOS DAS OUTRAS ACTIVITYS
        Intent recebeDados = getIntent();
        String pegaNomeMusica = recebeDados.getStringExtra("nomeMusica");
        String pegaNomeAutor = recebeDados.getStringExtra("nomeAutor");
        String pegaIndiceImagem = recebeDados.getStringExtra("indiceImagem");

        nomeMusicaAlbum.setText(pegaNomeMusica);
        nomeAutorAlbum.setText(pegaNomeAutor);
        nomeAlbum2.setText(pegaNomeAutor);

        System.out.println(pegaNomeMusica + " " + pegaNomeAutor + " " + pegaIndiceImagem);

        if (pegaIndiceImagem == "0" || pegaIndiceImagem.equals("0")) {
            imgAlbum.setBackgroundResource(R.drawable.sweet);
        } else if (pegaIndiceImagem == "1" || pegaIndiceImagem.equals("1")) {
            imgAlbum.setBackgroundResource(R.drawable.bella_sant);
        } else if (pegaIndiceImagem == "2" || pegaIndiceImagem.equals("2")) {
            imgAlbum.setBackgroundResource(R.drawable.emanu_raquel);
        } else if (pegaIndiceImagem == "3" || pegaIndiceImagem.equals("3")) {
            imgAlbum.setBackgroundResource(R.drawable.gaucha);
        } else {

        }
        //RecyclerView
        recyclerView = findViewById(R.id.reciclaTab);
        equalizadorAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // Mudanças aqui

            }
        });

        pVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(Player.this, Inicio.class);
                startActivity(f);
                finish();
            }
        });

        pLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(Player.this, ListaReciclavel.class);
                startActivity(f);
                finish();
            }
        });

        //Seção da Recycler View
        Configura_som configura_som = new Configura_som(this);
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager3);
        recyclerView.setHasFixedSize(true); /*fixa o numero de view que será reciclada */
        recyclerView.setAdapter(configura_som); //precisa configurar o adaptador

        recyclerView.setVisibility(View.GONE);

        //Abre a seleção de musicas ASMR personalizadas
        pOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont++;
                if (cont == 2) {
                    deixaVisivel();
                    cont = 0;
                } else {
                    deixaInvisivel();
                }
            }
        });
    }

    //Metodo que deixa as coisas Invisiveis
    public void deixaInvisivel() {
        //Tudo fica Invisivel
        imgAlbum.setVisibility(View.GONE);
        nomeAlbum.setVisibility(View.GONE);
        nomeAlbum2.setVisibility(View.GONE);
        nomeMusicaAlbum.setVisibility(View.GONE);
        nomeAutorAlbum.setVisibility(View.GONE);


        //Recycler view ficará visível
        recyclerView.setVisibility(View.VISIBLE);
    }

    //Metodo que deixa as coisas Vísiveis
    public void deixaVisivel() {
        imgAlbum.setVisibility(View.VISIBLE);
        nomeAlbum.setVisibility(View.VISIBLE);
        nomeAlbum2.setVisibility(View.VISIBLE);
        nomeMusicaAlbum.setVisibility(View.VISIBLE);
        nomeAutorAlbum.setVisibility(View.VISIBLE);


        //RecyclerView fica inv
        recyclerView.setVisibility(View.GONE);

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


            if (Configuracao_barra.mediaPlayer.isPlaying()) {
                Configuracao_barra.iPlayButton.setBackgroundResource(R.drawable.pause);
            } else {
                Configuracao_barra.iPlayButton.setBackgroundResource(R.drawable.play);
            }

        } else {
            barraPlayer.setVisibility(View.GONE);
        }
    }
}
