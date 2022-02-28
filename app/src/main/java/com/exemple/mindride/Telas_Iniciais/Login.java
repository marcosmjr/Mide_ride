package com.exemple.mindride.Telas_Iniciais;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.exemple.mindride.Banco_de_Dados.BD_Interno;
import com.exemple.mindride.Banco_de_Dados.BD_Interno_Dados;
import com.exemple.mindride.Banco_de_Dados.ConectaBD;
import com.exemple.mindride.Banco_de_Dados.OrganizaUsuario;
import com.exemple.mindride.Binaural.Audio;
import com.exemple.mindride.Binaural.ValoresAudio;
import com.exemple.mindride.MusicRelact.Adaptador_autor;
import com.exemple.mindride.Player.Configura_som;
import com.exemple.mindride.Player.Inicio;
import com.exemple.mindride.Player.MySingleton;
import com.exemple.mindride.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends Fragment  {

    private static boolean restrigeBotao_iHomo = false;

    public static void setRestrigeBotao_iHomo(boolean restrigeBotao_iHomo) {
        Login.restrigeBotao_iHomo = restrigeBotao_iHomo;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static MediaPlayer mediaPlayer = MySingleton.getInstance();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Login() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public void onStart(){
        super.onStart();

        organizaUsuario.deslogado();

        Configura_som configura_som2 = new Configura_som(comando.getContext());
        if(configura_som2.mediaPlayer.isPlaying()){
            Toast.makeText(comando.getContext(), "Seu Audio Adicional foi Parado para Você Obter Melhor Experiência!", Toast.LENGTH_SHORT).show();
            System.out.println("Entrou!! uhu");
            configura_som2.pegaNomeBotao = 0;
            configura_som2.mediaPlayer.reset();

        }if(configura_som2.mediaPlayer2.isPlaying()){
            System.out.println("Entrou 2!! uhu");
            configura_som2.pegaNomeBotao2 = 0;
            configura_som2.mediaPlayer2.reset();


        }if(configura_som2.mediaPlayer3.isPlaying()){
            System.out.println("Entrou 3!! uhu");
            configura_som2.pegaNomeBotao3 = 0;
            configura_som2.mediaPlayer3.reset();
        }


        mediaPlayer.reset();
        mediaPlayer.stop();

        Adaptador_autor adaptador_autor = new Adaptador_autor();
        adaptador_autor.setPegaURLocal("");


        /*********************Interrompe Binaural Beat ***************/

        if(ValoresAudio.getAudioAcionado() && ValoresAudio.getAcionaBinaural()) {
            Audio.stop();
        }

        /**************************************************/

        try {
            //Retorna do banco de dados o estado do ChekBox Manter-me Conectado

            BD_Interno bd_interno = new BD_Interno(comando.getContext());
            bd_interno.buscaEstado("1");

            if(BD_Interno_Dados.getEstadoConfiguracao() == 1 ) {
                manterConctado.setChecked(true);
                login.setText(BD_Interno_Dados.getEmailUsuario());
                senha.setText(BD_Interno_Dados.getSenhaUsuario());
                ConectaBD conectaBD = new ConectaBD();
                conectaBD.MetodoLogar();
                conectaBD.buscaOnline();
            }else{
                manterConctado.setChecked(false);
                login.setText("");
                senha.setText("");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    //Direcioando as Variáveis
    private static EditText login,senha;
    private static View comando;
    private static CheckBox manterConctado;

   private  OrganizaUsuario organizaUsuario = new OrganizaUsuario();

    public static EditText getLogin() {
        return login;
    }

    public static EditText getSenha() {
        return senha;
    }

    public static void setLogin(String login) {
        Login.login.setText(login);
    }

    public static void setSenha(String senha) {
        Login.senha.setText(senha);
    }

    public static View getComando() {
        return comando;
    }

    public static CheckBox getMaterConctado() {
        return manterConctado;
    }

    public static void setMaterConctado(CheckBox materConctado) {
        Login.manterConctado = materConctado;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        comando = inflater.inflate(R.layout.login, container, false);
        login = comando.findViewById(R.id.lLogin);
        senha = comando.findViewById(R.id.lSenha);
        manterConctado = comando.findViewById(R.id.cb_manterConectado);
        manterConctado.setChecked(false);


        try {
            //Retorna do banco de dados o estado do ChekBox Manter-me Conectado

            BD_Interno bd_interno = new BD_Interno(comando.getContext());
            bd_interno.buscaEstado("1");

            if(BD_Interno_Dados.getEstadoConfiguracao() == 1 ) {
                manterConctado.setChecked(true);
                login.setText(BD_Interno_Dados.getEmailUsuario());
                senha.setText(BD_Interno_Dados.getSenhaUsuario());
                ConectaBD conectaBD = new ConectaBD();
                conectaBD.MetodoLogar();
                conectaBD.buscaOnline();

                if(restrigeBotao_iHomo == false) {

                    Intent cliente = new Intent(comando.getContext().getApplicationContext(), Inicio.class);
                    startActivity(cliente);

                }else{

                    restrigeBotao_iHomo = false;
                }

            }else{
                manterConctado.setChecked(false);
                login.setText("");
                senha.setText("");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }


        manterConctado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked == true){

                    ConectaBD conectaBD = new ConectaBD();
                    conectaBD.MetodoLogar();
                    conectaBD.buscaOnline();
                }else{

                    BD_Interno_Dados bd_Interno_Dados = new BD_Interno_Dados();
                    BD_Interno bd_interno = new BD_Interno(comando.getContext());

                    //Cria a linha 1 que guardará a configuração de login caso não exista
                    BD_Interno_Dados.setIdConfiguracao(1);
                    try {
                        bd_interno.cadastroEstado(bd_Interno_Dados);
                    }catch (SQLException ex){
                        ex.printStackTrace();
                    }

                    //Guarda o estado 0 ou seja, não manter conectado
                        BD_Interno_Dados.setIdConfiguracao(1);
                        BD_Interno_Dados.setNomeConfiguracao("Mantenha-me Conectado");
                        BD_Interno_Dados.setEstadoConfiguracao(0);
                        BD_Interno_Dados.setEmailUsuario("");
                        BD_Interno_Dados.setSenhaUsuario("");
                        login.setText("");
                        senha.setText("");
                        ConectaBD.setValidacao(false);


                        bd_interno.atualizaEstado(bd_Interno_Dados);



                }

            }
        });


        login.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                ConectaBD conectaBD = new ConectaBD();
                conectaBD.MetodoLogar();
                conectaBD.buscaOnline();


                return false;
            }
        });



        senha.setOnKeyListener(new View.OnKeyListener() {
               @Override
               public boolean onKey(View v, int keyCode, KeyEvent event) {

                   ConectaBD conectaBD = new ConectaBD();
                   conectaBD.MetodoLogar();
                   conectaBD.buscaOnline();

                   return false;
               }
           });


                comando.findViewById(R.id.lLogar).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ConectaBD conectaBD = new ConectaBD();
                        conectaBD.MetodoLogar();
                        conectaBD.buscaOnline();
                        
                        String comparaEmailDados =  BD_Interno_Dados.getEmailUsuario();
                        String comparaLogin = getLogin().getText().toString();


                        if (conectaBD.MetodoLogar() == true && comparaEmailDados.equals(comparaLogin)) {

                            //Busca os valores de sons do banco_Interno_Sons para a classe Banco_Interno_Dados
                            //Esses valores serão utilizados para preencher as listas com os nomes dos ultimos sons ouvido pelo usuário
                            try {
                                BD_Interno bd_interno_sons = new BD_Interno(comando.getContext());
                                bd_interno_sons.buscaCadastroSons(login.getText().toString());
                            } catch (Exception e) {
                                try {
                                    //Verifica a existencia de um usuário no banco de dados interno para justificar a lista de sons do usuário vazia
                                    BD_Interno bd_interno_usuario = new BD_Interno(comando.getContext());
                                    bd_interno_usuario.buscaCadastroUsuario(login.getText().toString());

                                } catch (Exception e1) {

                                    //Se o usuário existe na base da dados externa mas não na interna ele cadastra ou atualiza na base de dados interna
                                    if (ConectaBD.isUsuarioExistente()) {
                                        try {
                                            /*Cadastro no banco de dados interno */
                                            BD_Interno_Dados db = new BD_Interno_Dados();
                                            BD_Interno cadastrar = new BD_Interno(comando.getContext());
                                            cadastrar.cadastroUsuario(db);
                                        } catch (Exception e2) {
                                            /*Atualiza no banco de dados interno */
                                            BD_Interno_Dados db = new BD_Interno_Dados();
                                            BD_Interno atualizar = new BD_Interno(comando.getContext());
                                            atualizar.atualizaCadastro(db);
                                        }
                                    }

                                }
                            }


                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference  = database.getReference();
                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Intent cliente = new Intent(comando.getContext().getApplicationContext(), Inicio.class);
                                    String pegaID = getLogin().getText().toString().replace(".", "_");
                                    pegaID = pegaID.toLowerCase();

                                    try {
                                        Toast.makeText(comando.getContext(), "Bem-vindo : " + dataSnapshot.child("Cadastro").child(pegaID).child("Nome").getValue().toString(), Toast.LENGTH_SHORT).show();
                                    }catch (Exception e){
                                        Toast.makeText(comando.getContext(), "Bem-vindo , Querido Erro!", Toast.LENGTH_SHORT).show();
                                    }

                                    organizaUsuario.logado();
                                    startActivity(cliente);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }else{
                            Toast.makeText(getComando().getContext(), "Email e/ou Senha Incorretos!", Toast.LENGTH_SHORT).show();
                        }

                        BD_Interno_Dados bd_Interno_Dados = new BD_Interno_Dados();
                        BD_Interno bd_interno = new BD_Interno(comando.getContext());


                        //Cria a linha 1 que guardará a configuração de login caso não exista
                        BD_Interno_Dados.setIdConfiguracao(1);
                        try {
                            bd_interno.cadastroEstado(bd_Interno_Dados);
                        }catch (SQLException ex){
                            ex.printStackTrace();
                        }

                        //Guarda o estado manter conectado ou não
                        if(manterConctado.isChecked() == true){
                            BD_Interno_Dados.setIdConfiguracao(1);
                            BD_Interno_Dados.setNomeConfiguracao("Mantenha-me Conectado");
                            BD_Interno_Dados.setEstadoConfiguracao(1);
                            BD_Interno_Dados.setEmailUsuario(getLogin().getText().toString());
                            BD_Interno_Dados.setSenhaUsuario(getSenha().getText().toString());

                            bd_interno.atualizaEstado(bd_Interno_Dados);

                        }else if (manterConctado.isChecked() == false){
                            BD_Interno_Dados.setIdConfiguracao(1);
                            BD_Interno_Dados.setNomeConfiguracao("Mantenha-me Conectado");
                            BD_Interno_Dados.setEstadoConfiguracao(0);
                            BD_Interno_Dados.setEmailUsuario("");
                            BD_Interno_Dados.setSenhaUsuario("");


                            bd_interno.atualizaEstado(bd_Interno_Dados);

                        }


                    }
                });


        return comando;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
