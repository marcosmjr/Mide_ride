package com.exemple.mindride.Player;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.mindride.Banco_de_Dados.OrganizaUsuario;
import com.exemple.mindride.Binaural.MainBinaural;
import com.exemple.mindride.MusicRelact.Adaptador_autor;
import com.exemple.mindride.MusicRelact.AdapterMusicas;
import com.exemple.mindride.R;
import com.exemple.mindride.Telas_Iniciais.Login;
import com.exemple.mindride.Telas_Iniciais.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Inicio extends AppCompatActivity {


    public static List lista = new ArrayList();
    public static List listaArtista = new ArrayList();
    private RecyclerView barraPlayer;
    private ImageButton sugestao1,sugestao2,sugestao3,sugestao4;
    private ImageButton relaxar1,relaxar2;
    private ImageButton dormir1,dormir2;
    private ImageButton iHomo;
    private ImageButton ib_BinauralBeat;
    private TextView lbPlaylist;
    private ImageButton suaPlaylist;
    private static MediaPlayer mediaPlayer = MySingleton.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        barraPlayer = findViewById(R.id.barraDOplayer);

        RecyclerView.LayoutManager layoutManager2;
        final Configuracao_barra barra = new Configuracao_barra(getApplicationContext());
        layoutManager2 = barra.reciclaBarra();
        barraPlayer.setLayoutManager(layoutManager2);
        barraPlayer.setHasFixedSize(true); /*fixa o numero de view que será reciclada */
        barraPlayer.setAdapter(barra); //precisa configurar o adaptador


        sugestao1 = findViewById(R.id.sugestao1);
        sugestao2 = findViewById(R.id.sugestao2);
        sugestao3 = findViewById(R.id.sugestao3);
        sugestao4 = findViewById(R.id.sugestao4);

        relaxar1 = findViewById(R.id.relaxar1);
        relaxar2 = findViewById(R.id.relaxar2);

        dormir1 = findViewById(R.id.dormir1);
        dormir2 = findViewById(R.id.dormir2);

        iHomo = findViewById(R.id.iHomoButton);
        lbPlaylist = findViewById(R.id.suasPlaylist);
        suaPlaylist = findViewById(R.id.suaPlayList);
        ib_BinauralBeat = findViewById(R.id.ib_BinauralBeat);    // Mudanças aqui <<<<<<<<<

        iHomo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.setRestrigeBotao_iHomo(true);
                Intent intent = new Intent(Inicio.this, MainActivity.class);
                startActivity(intent);
            }
        });


        ib_BinauralBeat.setOnClickListener(new View.OnClickListener() { // Mudanças aqui <<<<<<<<<
            @Override
            public void onClick(View v) {

                if(OrganizaUsuario.logadoGuest == false) {
                    Intent intent = new Intent(Inicio.this, MainBinaural.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "É Necessário uma Conta Logada para Acessar essa Opção!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        if(OrganizaUsuario.logadoGuest == true){
        lbPlaylist.setVisibility(View.INVISIBLE);
        suaPlaylist.setVisibility(View.GONE);
        }else{
            String pegaID = Login.getLogin().getText().toString().replace(".", "_");
            pegaID = pegaID.toLowerCase();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference reference = database.getReference();
            final String finalPegaID = pegaID;

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").exists()) {
                        lbPlaylist.setVisibility(View.INVISIBLE);
                        suaPlaylist.setVisibility(View.GONE);


                    } else {

                        lbPlaylist.setVisibility(View.VISIBLE);
                        suaPlaylist.setVisibility(View.VISIBLE);

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });



        }

















    suaPlaylist.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        try {
            //FASE DE TESTES
                lista.removeAll(lista);
                listaArtista.removeAll(listaArtista);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference reference = database.getReference();
            String pegaID = Login.getLogin().getText().toString().replace(".", "_");
            pegaID = pegaID.toLowerCase();

            final String finalPegaID = pegaID;

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                String musica, musica1, musica2, musica3, musica4;

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child("Sweet Carol").exists()) {
                        System.out.println("Entrou na Carol");


                        if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child("Sweet Carol").child("Escovando seu Cabelo").exists()) {
                            System.out.println("Entrou na Carol , Escovando Cabelo");
                            musica = ("Escovando seu Cabelo");
                            lista.add(musica);
                            listaArtista.add("Sweet Carol");
                        }
                        if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child("Sweet Carol").child("Sons com Plastico").exists()) {
                            System.out.println("Entrou na Carol de Plastico");
                            musica1 = ("Sons com Plastico");
                            lista.add(musica1);
                            listaArtista.add("Sweet Carol");
                        }
                    }
                    if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child("Bella Sant ASMR").exists()) {
                        System.out.println("Entrou na Bella Sant");


                        if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child("Bella Sant ASMR").child("Relaxando com Esponja").exists()) {
                            System.out.println("Entrou na Bella Sant com esponja");
                            musica2 = ("Relaxando com Esponja");
                            lista.add(musica2);
                            listaArtista.add("Bella Sant ASMR");
                        }
                    }


                    if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child("Emanuelly Raquel ASMR").exists()) {
                        System.out.println("Emanuelly");

                        if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child("Emanuelly Raquel ASMR").child("Tapping com uma Guitarra").exists()) {
                            System.out.println("Emanuelly com guitarra");
                            musica3 = ("Tapping com uma Guitarra");
                            lista.add(musica3);
                            listaArtista.add("Emanuelly Raquel ASMR");
                        }
                    }


                    if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child("Gaucha ASMR").exists()) {
                        System.out.println("Gaucha");


                        if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child("Gaucha ASMR").child("Chovendo sem Energia").exists()) {
                            System.out.println("Gaucha sem energia");
                            musica4 = ("Chovendo sem Energia");
                            lista.add(musica4);
                            listaArtista.add("Gaucha ASMR");
                        }
                }
                    if (OrganizaUsuario.logadoGuest == false) {
                        Adaptador_autor.setTestNumerFor(666);
                        Intent intent = new Intent(Inicio.this, ListaReciclavel.class);
                        System.out.println(lista);
                        System.out.println(listaArtista);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Inicio.this, "Favor entrar em contato com o Desenvolvedor.", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        }catch (Exception e ){
            Toast.makeText(Inicio.this,"Não foi Possível Iniciar a Sessão de Favoritos!! "+"\n"+ e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
    });




        montaImagemPlay(sugestao1,0);
        montaImagemPlay(sugestao2,1);
        montaImagemPlay(sugestao3,2);
        montaImagemPlay(sugestao4,3);

        montaImagemPlay(dormir1,1);
        montaImagemPlay(dormir2,3);

        montaImagemPlay(relaxar1,0);
        montaImagemPlay(relaxar2,2);





        Adaptador_autor adaptador_autor = new Adaptador_autor();
        if(adaptador_autor.getPegaURLocal() != ""){
            barraPlayer.setVisibility(View.VISIBLE);

            if(mediaPlayer.isPlaying()){
                Configuracao_barra.iPlayButton.setBackgroundResource(R.drawable.pause);
                System.out.println("Continue do onCreate!");
            }else{
                Configuracao_barra.iPlayButton.setBackgroundResource(R.drawable.play);
                System.out.println("Pause do onCreate!");
            }

        }else{
            barraPlayer.setVisibility(View.GONE);
        }
    }







    @Override
    protected void onRestart() {
        super.onRestart();

        if(OrganizaUsuario.logadoGuest == true){
            lbPlaylist.setVisibility(View.INVISIBLE);
            suaPlaylist.setVisibility(View.GONE);
        }else{
            String pegaID = Login.getLogin().getText().toString().replace(".", "_");
            pegaID = pegaID.toLowerCase();

            final String finalPegaID = pegaID;
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference reference = database.getReference();

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").exists()) {
                        lbPlaylist.setVisibility(View.INVISIBLE);
                        suaPlaylist.setVisibility(View.GONE);


                    } else {

                        lbPlaylist.setVisibility(View.VISIBLE);
                        suaPlaylist.setVisibility(View.VISIBLE);

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });



        }


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






    //Monta a imagem e coloca numa imageButton por método. Bastando apenas colocar o método, e por parâmetro a ImageButton..
    //e por ultimo passar a imagem por um int, que receberá da classe Adaptador_autor
    public void montaImagemPlay(final ImageButton button, final int numerPhoto){
        try {
            Adaptador_autor adaptador_autor = new Adaptador_autor();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            //por parametro do array que o método tem, passamos o int que especificaremos no método.
            String getLink = adaptador_autor.getAllPhotos()[numerPhoto];

            StorageReference storageRef = storage.getReferenceFromUrl(getLink);
            final File localFile = File.createTempFile("Photo " + numerPhoto, "jpg");
            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    bitmap = Bitmap.createScaledBitmap(bitmap, 240, 240, false);
                    button.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            });
        } catch (IOException e) {
        }

        //Aqui pegamos o botão q foi clicado e verificamos qual é a imagem dele por parametro int
        //Já que faremos algo mais manual,assim será mais rapido de mostrar já que é uma lista pré montada.
        //Caso não fosse, iria passar o linkImage como parâmetro e verificar qual artista seria, assim poderiamos ver em MUITAS playlists e não haveria este trabalho manual
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adaptador_autor adaptador_autor = new Adaptador_autor();
               if(numerPhoto == 0){
                   Adaptador_autor.setTestNumerFor(190);
                   Intent intent = new Intent(Inicio.this, ListaReciclavel.class);
                   startActivity(intent);
               }else if(numerPhoto == 1){
                   Adaptador_autor.setTestNumerFor(191);
                   Intent intent = new Intent(Inicio.this, ListaReciclavel.class);
                   startActivity(intent);

               }else if(numerPhoto == 2){
                   Adaptador_autor.setTestNumerFor(192);
                   Intent intent = new Intent(Inicio.this, ListaReciclavel.class);
                   startActivity(intent);

               }else if(numerPhoto == 3){
                   Adaptador_autor.setTestNumerFor(193);
                   Intent intent = new Intent(Inicio.this, ListaReciclavel.class);
                   startActivity(intent);
               }
            }
        });
    }





}
