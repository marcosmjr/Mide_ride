package com.exemple.mindride.Banco_de_Dados;

import android.media.MediaPlayer;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.exemple.mindride.Telas_Iniciais.Cadastro;
import com.exemple.mindride.Telas_Iniciais.Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConectaBD{

    private static boolean validacao = false;
    private static boolean usuarioExistente = false;

    public static boolean isValidacao() {
        return validacao;
    }

    public static void setValidacao(boolean validacao) {
        ConectaBD.validacao = validacao;
    }

    public static boolean isUsuarioExistente() {
        return usuarioExistente;
    }

    public static void setUsuarioExistente(boolean usuarioInexistente) {
        ConectaBD.usuarioExistente = usuarioInexistente;
    }

    // Criar a conexão com o banco (busca as configurações do arquivo JSON)
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    // Cria uma referencia para o ponto inicial do banco
    DatabaseReference reference  = database.getReference();


    Login classeLogin = new Login();
    Cadastro classeCadastro = new Cadastro();



    private boolean  val (EditText email){
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email.getText());
        boolean matchFound = m.matches();
        if (!matchFound) {
            return false;
        }
        return true;
    }

    public boolean MetodoLogar(){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pegaID = classeLogin.getLogin().getText().toString().replace(".", "_");
                pegaID = pegaID.toLowerCase();
                try {
                        if (dataSnapshot.child("Cadastro").child(pegaID).exists()) {
                            if (dataSnapshot.child("Cadastro").child(pegaID).child("Senha").getValue().equals(classeLogin.getSenha().getText().toString())) {

                               validacao = true;

                              //  Toast.makeText(classeLogin.getComando().getContext(), "Correto!", Toast.LENGTH_SHORT).show();





                            } else {
                                validacao = false;
                               // Toast.makeText(classeLogin.getComando().getContext(), "Senha Incorreta!!", Toast.LENGTH_LONG).show();

                            }
                        } else {
                            validacao = false;
                          //  Toast.makeText(classeLogin.getComando().getContext(), "Email Incorreto", Toast.LENGTH_SHORT).show();

                        }
                }catch (Exception e) {
                if(e.toString().equals("java.lang.NullPointerException: Attempt to invoke virtual method 'boolean java.lang.Object.equals(java.lang.Object)' on a null object reference")){
                    //Toast.makeText(classeLogin.getComando().getContext(), "Insira um Email Existente!!", Toast.LENGTH_SHORT).show();
                }else{
                   e.printStackTrace();
                }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return validacao;

    }



    public void MetodoCadastrar(){
        final String selecionado;
        if(classeCadastro.getMacho().isChecked()){
            selecionado = "Masculino";
        }else{
            selecionado = "Feminino";
        }

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pegaID  = classeCadastro.getEmail().getText().toString().replace(".","_");
                pegaID = pegaID.toLowerCase();
                if (val(classeCadastro.getEmail()) == false || classeCadastro.getEmail().getText().toString().length() < 10){
                    Toast.makeText(classeCadastro.getComando().getContext(), "O Email Está Inválido!", Toast.LENGTH_LONG).show();
                }else if(classeCadastro.getNome().getText().toString().length()<8 ){
                    Toast.makeText(classeCadastro.getComando().getContext(), "Nome é Pequeno Demais!!", Toast.LENGTH_LONG).show();
                }else if(classeCadastro.getSenha().getText().toString().length()<7){
                    Toast.makeText(classeCadastro.getComando().getContext(), "A Senha Tem Que Ter no Mínimo 8 Caracteres!!", Toast.LENGTH_LONG).show();
                }else if(classeCadastro.getDia().getSelectedItemPosition() == 0 || classeCadastro.getMes().getSelectedItemPosition() == 0 || classeCadastro.getAno().getSelectedItemPosition() == 0){
                    Toast.makeText(classeCadastro.getComando().getContext(), "Insira uma Data Válida!", Toast.LENGTH_SHORT).show();
                }else if(!classeCadastro.getTermos().isChecked()){
                    Toast.makeText(classeCadastro.getComando().getContext(), "Aceite os Termos de Condição!!", Toast.LENGTH_LONG).show();
                }else if(dataSnapshot.child("Cadastro").child(pegaID).exists()) {
                    Toast.makeText(classeCadastro.getComando().getContext(), "Já Há um Email Cadastrado!", Toast.LENGTH_SHORT).show();
                }else if(val(classeCadastro.getEmail()) == true  && classeCadastro.getEmail().getText().toString().length() >12 &&
                        classeCadastro.getNome().getText().toString().length() > 8 && classeCadastro.getSenha().getText().toString().length() > 7 && classeCadastro.getTermos().isChecked()){
                    reference.child("Cadastro").child(pegaID).child("Email").setValue(classeCadastro.getEmail().getText().toString());
                    reference.child("Cadastro").child(pegaID).child("Senha").setValue(classeCadastro.getSenha().getText().toString());
                    reference.child("Cadastro").child(pegaID).child("Nome").setValue(classeCadastro.getNome().getText().toString());
                    reference.child("Cadastro").child(pegaID).child("Data Nasc").setValue(classeCadastro.getDia().getSelectedItem().toString() +"/"+classeCadastro.getMes().getSelectedItem().toString() +"/"+classeCadastro.getAno().getSelectedItem().toString());
                    reference.child("Cadastro").child(pegaID).child("Sexo").setValue(selecionado);
                    classeCadastro.getEmail().setText("");
                    classeCadastro.getNome().setText("");
                    classeCadastro.getSenha().setText("");
                    Toast.makeText(classeCadastro.getComando().getContext(), "Cadastrado!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    /*Metodo de busca no banco de dado do FireBase, as informações serão usada para prencher automaticamente o banco de dados
    * interno em caso de o usuário existir mas o banco de dados SQLite estiver vasio*/
    public void buscaOnline(){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pegaID = classeLogin.getLogin().getText().toString().replace(".", "_");
                pegaID = pegaID.toLowerCase();
                    try{
                        if (dataSnapshot.child("Cadastro").child(pegaID).exists()) {

                            BD_Interno_Dados.setEmailUsuario(dataSnapshot.child("Cadastro").child(pegaID).child("Email").getValue().toString());
                            BD_Interno_Dados.setSenhaUsuario(dataSnapshot.child("Cadastro").child(pegaID).child("Senha").getValue().toString());
                            BD_Interno_Dados.setNomeUsuario(dataSnapshot.child("Cadastro").child(pegaID).child("Nome").getValue().toString());
                            String dataNascimeto = dataSnapshot.child("Cadastro").child(pegaID).child("Data Nasc").getValue().toString();
                            String[] vetorData = dataNascimeto.split("/");
                            BD_Interno_Dados.setDia(Integer.parseInt(vetorData[0]));
                            BD_Interno_Dados.setMes(vetorData[1]);
                            BD_Interno_Dados.setAno(Integer.parseInt(vetorData[2]));
                            BD_Interno_Dados.setGeneroUsuario(dataSnapshot.child("Cadastro").child(pegaID).child("Sexo").getValue().toString());
                            BD_Interno_Dados.setAceitaTermos(1);

                            /* Teste do da classe BD_interno_Dados  */
                           /* System.out.println("**************** Classe ConectaBD ******************");
                            System.out.println("Busca cadastro : " + dataSnapshot.child("Cadastro").child(pegaID).toString());
                            System.out.println("Verifica o email no BD_Interno_Dados ==> " + dadosUsuario.getEmailUsuario());
                            System.out.println("Busca senha : " + dataSnapshot.child("Cadastro").child(pegaID).child("Senha").getValue().toString());
                            System.out.println("Verifica a sena no BD_Interno_Dados ==> " + dadosUsuario.getSenhaUsuario());
                            System.out.println("Busca nome : " + dataSnapshot.child("Cadastro").child(pegaID).child("Nome").getValue().toString());
                            System.out.println("Verifica a nome no BD_Interno_Dados ==> " + dadosUsuario.getNomeUsuario());
                            System.out.println("Busca Data de Nascimento : " + dataSnapshot.child("Cadastro").child(pegaID).child("Data Nasc").getValue().toString());
                            System.out.println("Verifica a data de nscimento no BD_Interno_Dados ");
                            System.out.println("Dia ==> " + dadosUsuario.getDia());
                            System.out.println("Mês ==> " + dadosUsuario.getMes());
                            System.out.println("Ano ==> " + dadosUsuario.getAno());
                            System.out.println("*****************************************************");*/

                            usuarioExistente = true;
                        }
                    }catch (Exception e){
                       // Toast.makeText(classeCadastro.getComando().getContext(), "Faltam preencher campo no banco de dados externo", Toast.LENGTH_SHORT).show();

                        usuarioExistente = false;
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });











    }


    private MediaPlayer mediaPlayer;



    /* public void contaMusicas(){
        final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        try {
            final StorageReference storageReference = firebaseStorage.getReferenceFromUrl("gs://mind-ride-aplicativo.appspot.com/marlinho.mp3");
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                @Override
                public void onSuccess(Uri uri) {
                    final String audioUrl = uri.toString();

                    // enviar como parâmetro para o método sendUrlToMediaPlayer()
                    sendUrlToMediaPlayer(audioUrl);
                }
            });*
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }*/
   /* void sendUrlToMediaPlayer(String url) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            // enviar a StreamUrl para o player
            mediaPlayer.setDataSource(url);

            // esperar que ele fique pronto e após ficar pronto tocar o áudio
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });

            mediaPlayer.prepareAsync();
        } catch (IOException err) {
            Log.e("Audio Error", err.toString());
        }
    }*/
}
