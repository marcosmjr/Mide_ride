package com.exemple.mindride.MusicRelact;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.mindride.Player.Configuracao_barra;
import com.exemple.mindride.Player.Inicio;
import com.exemple.mindride.Player.MySingleton;
import com.exemple.mindride.Player.Player;
import com.exemple.mindride.R;

//Felipe Corno

public class AdapterMusicas  extends RecyclerView.Adapter<AdapterMusicas.MyViewHolder> {
    @NonNull
    @Override
    //O método cria as 1ºs visualizações
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista, parent, false);
        return new MyViewHolder(itemLista);
    }


    public static String nomeAtriz;
    public static String nomeMusica;



Context mContext;
    public static int restauraMusica = 0;
    public AdapterMusicas(Context context){
        mContext = context;
    }
    private static MediaPlayer mediaPlayer = MySingleton.getInstance();
    //Criando uma classe dentro de uma classe, pede para criar um construtor
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lNomeMusica;
        TextView lAutor;
        ImageView lAlbum;
        ImageButton lNfavorito;
        ImageButton lOptionsLista;
        LinearLayout item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lNomeMusica = itemView.findViewById(R.id.nomeMusicaAlbum);
            lAutor = itemView.findViewById(R.id.textAutor);
            lAlbum = itemView.findViewById(R.id.lAlbum);
            lNfavorito = itemView.findViewById(R.id.lFavorito);
            lOptionsLista = itemView.findViewById(R.id.lOptionsMusica);
            item = itemView.findViewById(R.id.itemDaView);
        }
    }

    @Override
    // O método que exibe os dados da view
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final Adaptador_autor adaptador_autor = new Adaptador_autor();
        final Configuracao_barra configuracao_barra  = new Configuracao_barra(mContext);



        /*
        Deixando de forma esclarecida, uma de minhas metas era deixar essa função de buscar musicas favoritadas de uma forma onde o programa pudesse identificar de forma automatica
        pela instancia da firebase. Porém, pela falta de tempo e os decorridos atrasos por conta da COVID-19 decidi deixar essa função funcionando da maneira que era para funcionar
        inicialmente, porém, manualmente. Ou seja, sem uma instancia identificadora da firebase e completos de IFs que montarão a composição na tela.
        Peço perdão aos professores por não conseguir fazer isso a tempo! Porém , praticamente todas as funções automaticas feitas, exceto pelas feito do banco de dados interno
        foram feitas a partir de mim mesmo!
        Leandro Safra
         */



        if(adaptador_autor.getTestNumerFor() == 666) {

            // adaptador_autor.pegaDadosInterno();

            holder.lNomeMusica.setText(Inicio.lista.get(position).toString());
            holder.lAutor.setText(Inicio.listaArtista.get(position).toString());
            String indiceImagem = "666";
            if(Inicio.listaArtista.get(position).toString() == "Sweet Carol"){
                holder.lAlbum.setBackgroundResource(R.drawable.sweet);
                indiceImagem = "0";
            }else if(Inicio.listaArtista.get(position).toString() == "Bella Sant ASMR"){
                holder.lAlbum.setBackgroundResource(R.drawable.bella_sant);
                indiceImagem = "1";
            }else if(Inicio.listaArtista.get(position).toString() == "Emanuelly Raquel ASMR"){
                holder.lAlbum.setBackgroundResource(R.drawable.emanu_raquel);
                indiceImagem = "2";
            }else if(Inicio.listaArtista.get(position).toString() == "Gaucha ASMR"){
                holder.lAlbum.setBackgroundResource(R.drawable.gaucha);
                indiceImagem = "3";
            }else{
                holder.lAlbum.setBackgroundResource(R.drawable.logo_mind_ride_sem_texto_fundo_252626);
            }


            final String finalIndiceImagem = indiceImagem;
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    nomeAtriz = holder.lAutor.getText().toString();
                    nomeMusica = holder.lNomeMusica.getText().toString();

                    Intent intent = new Intent(mContext,Player.class);
                    intent.putExtra("nomeMusica",holder.lNomeMusica.getText());
                    intent.putExtra("nomeAutor",holder.lAutor.getText());
                    intent.putExtra("indiceImagem", finalIndiceImagem);
                    //Setando url na variavel da classe Adaptador Autor
                    if(Inicio.listaArtista.get(position).toString() == "Sweet Carol"){

                        adaptador_autor.setPegaURLocal(adaptador_autor.getSweetMusicsLinks()[position]);

                    }else if(Inicio.listaArtista.get(position).toString() == "Bella Sant ASMR"){

                        adaptador_autor.setPegaURLocal(adaptador_autor.getBellaMusicsLinks()[0]);

                    }else if(Inicio.listaArtista.get(position).toString() == "Emanuelly Raquel ASMR"){

                        adaptador_autor.setPegaURLocal(adaptador_autor.getEmanuRaquelLinks()[0]);

                    }else if(Inicio.listaArtista.get(position).toString() == "Gaucha ASMR"){

                        adaptador_autor.setPegaURLocal(adaptador_autor.getGauchaLinks()[0]);

                    }else{
                       //Nothing
                    }


                    if(configuracao_barra.pegaDadodeMusica == adaptador_autor.getPegaURLocal()){
                        System.out.println("Entrou no IF");
                        mContext.startActivity(intent);
                        mediaPlayer.start();

                    }else{
                        System.out.println("Entrou no ELSE");
                        restauraMusica = 1;
                        mediaPlayer.reset();
                        mediaPlayer.stop();
                        mContext.startActivity(intent);
                    }
                }
            });




        }else if(adaptador_autor.getTestNumerFor() == 190){
            //Sweet
            holder.lNomeMusica.setText(adaptador_autor.getSweetMusicsNames()[position]);
            holder.lAutor.setText(adaptador_autor.getAutor_Names()[3]);
            holder.lAlbum.setBackgroundResource(R.drawable.sweet);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                nomeAtriz = holder.lAutor.getText().toString();
                nomeMusica = holder.lNomeMusica.getText().toString();

                    Intent intent = new Intent(mContext,Player.class);
                    intent.putExtra("nomeMusica",holder.lNomeMusica.getText());
                    intent.putExtra("nomeAutor",holder.lAutor.getText());
                    intent.putExtra("indiceImagem","0");
                    //Setando url na variavel da classe Adaptador Autor
                    adaptador_autor.setPegaURLocal(adaptador_autor.getSweetMusicsLinks()[position]);

                    if(configuracao_barra.pegaDadodeMusica == adaptador_autor.getPegaURLocal()){
                     System.out.println("Entrou no IF");
                        mContext.startActivity(intent);
                        mediaPlayer.start();

                    }else{
                        System.out.println("Entrou no ELSE");
                        restauraMusica = 1;
                        mediaPlayer.reset();
                        mediaPlayer.stop();
                        mContext.startActivity(intent);
                    }
                }
            });
        }else if(adaptador_autor.getTestNumerFor() == 191){
            //Bella
            holder.lNomeMusica.setText(adaptador_autor.getBellaMusicsNames()[position]);
            holder.lAutor.setText(adaptador_autor.getAutor_Names()[0]);
            holder.lAlbum.setBackgroundResource(R.drawable.bella_sant);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    nomeAtriz = holder.lAutor.getText().toString();
                    nomeMusica = holder.lNomeMusica.getText().toString();

                    Intent intent = new Intent(mContext, Player.class);
                    intent.putExtra("nomeMusica",holder.lNomeMusica.getText());
                    intent.putExtra("nomeAutor",holder.lAutor.getText());
                    intent.putExtra("indiceImagem","1");
                    //Setando url na variavel da classe Adaptador Autor
                    adaptador_autor.setPegaURLocal(adaptador_autor.getBellaMusicsLinks()[position]);

                    if(configuracao_barra.pegaDadodeMusica == adaptador_autor.getPegaURLocal()){

                        mContext.startActivity(intent);
                        mediaPlayer.start();

                    }else{
                        restauraMusica = 1;
                        mediaPlayer.reset();
                        mediaPlayer.stop();
                        mContext.startActivity(intent);
                    }


                }
            });
        } else if (adaptador_autor.getTestNumerFor() == 192){
            //EmanuRaquel
            holder.lNomeMusica.setText(adaptador_autor.getEmanuRaquelNames()[position]);
            holder.lAutor.setText(adaptador_autor.getAutor_Names()[1]);
            holder.lAlbum.setBackgroundResource(R.drawable.emanu_raquel);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    nomeAtriz = holder.lAutor.getText().toString();
                    nomeMusica = holder.lNomeMusica.getText().toString();

                    Intent intent = new Intent(mContext, Player.class);
                    intent.putExtra("nomeMusica",holder.lNomeMusica.getText());
                    intent.putExtra("nomeAutor",holder.lAutor.getText());
                    intent.putExtra("indiceImagem","2");

                    adaptador_autor.setPegaURLocal(adaptador_autor.getEmanuRaquelLinks()[position]);

                    //Setando url na variavel da classe Adaptador Autor
                    if(configuracao_barra.pegaDadodeMusica == adaptador_autor.getPegaURLocal()){

                        mContext.startActivity(intent);
                        mediaPlayer.start();

                    }else{
                        restauraMusica = 1;
                        mediaPlayer.reset();
                        mediaPlayer.stop();
                        mContext.startActivity(intent);
                    }

                }
            });
        }else if (adaptador_autor.getTestNumerFor() == 193){
            //gaucha
            holder.lNomeMusica.setText(adaptador_autor.getGauchaNames()[position]);
            holder.lAutor.setText(adaptador_autor.getAutor_Names()[2]);
            holder.lAlbum.setBackgroundResource(R.drawable.gaucha);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    nomeAtriz = holder.lAutor.getText().toString();
                    nomeMusica = holder.lNomeMusica.getText().toString();

                    Intent intent = new Intent(mContext, Player.class);
                    intent.putExtra("nomeMusica",holder.lNomeMusica.getText());
                    intent.putExtra("nomeAutor",holder.lAutor.getText());
                    intent.putExtra("indiceImagem","3");

                    adaptador_autor.setPegaURLocal(adaptador_autor.getGauchaLinks()[position]);

                    //Setando url na variavel da classe Adaptador Autor
                    if(configuracao_barra.pegaDadodeMusica == adaptador_autor.getPegaURLocal()){

                        mContext.startActivity(intent);
                        mediaPlayer.start();

                    }else{
                        restauraMusica = 1;
                        mediaPlayer.reset();
                        mediaPlayer.stop();
                        mContext.startActivity(intent);
                    }
                }
            });
        }else{

        }
    }
    @Override
    public int getItemCount() { // Tamanho da recyclerView //Número de elementos do recyclerView
        //A ideia aqui é conseguir pegar o total de arquivos dentro da database.. de qualquer lugar serve , apenas precisamos para montar algo legal encima.. pois o return irá retornar
        // o total de itens presentes na tela com as informações do onBindViewHolder na activity lista_reciclavel.
             Adaptador_autor adaptador_autor = new Adaptador_autor();
        if(adaptador_autor.getTestNumerFor() == 666){

            return Inicio.lista.size();


        }else if(adaptador_autor.getTestNumerFor() == 190){
        return adaptador_autor.getSweetMusicsLinks().length;

    }else if(adaptador_autor.getTestNumerFor() == 191){
        return adaptador_autor.getBellaMusicsLinks().length;

    } else if (adaptador_autor.getTestNumerFor() == 192){
        return adaptador_autor.getEmanuRaquelLinks().length;

    }else if (adaptador_autor.getTestNumerFor() == 193){
        return adaptador_autor.getGauchaLinks().length;

    }else{
        return 0;
      }
   }

    }