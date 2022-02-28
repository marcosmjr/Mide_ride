package com.exemple.mindride.Banco_de_Dados;

import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.exemple.mindride.R;
import com.exemple.mindride.Telas_Iniciais.Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrganizaUsuario {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference();


    public void logado() {
        String pegaID = Login.getLogin().getText().toString().replace(".", "_");
        pegaID = pegaID.toLowerCase();
        logadoGuest = false;

       // System.out.println("Logado");


    }

    public void deslogado() {
      //  System.out.println("Deslogado");
        Login.setLogin("");
        Login.setSenha("");
        logadoGuest = false;

    }
    public static boolean logadoGuest = false;





    //Metodo para verificar se existe uma musica e uma playlist de determinada atriz na conta do usuario..
    public void verificaMusica(final String atriz, final String nomeMusica, final String linkMusica, final ImageButton verifica) {
        String pegaID = Login.getLogin().getText().toString().replace(".", "_");
        pegaID = pegaID.toLowerCase();

        final String finalPegaID = pegaID;
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child(atriz).child(nomeMusica).child(linkMusica).exists()) {

                    //Caso ja exista , exclua.
                    reference.child("Cadastro").child(finalPegaID).child("Playlists").child(atriz).child(nomeMusica).setValue(null);
                    verifica.setBackgroundResource(R.drawable.nfavorito);
                } else {
                    //Caso nao exista, adicione
                    reference.child("Cadastro").child(finalPegaID).child("Playlists").child(atriz).child(nomeMusica).child(linkMusica).setValue(linkMusica);
                    verifica.setBackgroundResource(R.drawable.favorito);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    //Verifica se a musica ja foi favoritada para marcar a mesma no drawable!
    public void verificaMarca(final String atriz, final String nomeMusica, final String linkMusica, final ImageButton verifica) {
        String pegaID = Login.getLogin().getText().toString().replace(".", "_");
        pegaID = pegaID.toLowerCase();
        final String finalPegaID = pegaID;
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Cadastro").child(finalPegaID).child("Playlists").child(atriz).child(nomeMusica).child(linkMusica).exists()) {
                    verifica.setBackgroundResource(R.drawable.favorito);
                } else {
                    verifica.setBackgroundResource(R.drawable.nfavorito);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}