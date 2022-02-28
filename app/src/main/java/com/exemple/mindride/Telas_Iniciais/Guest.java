package com.exemple.mindride.Telas_Iniciais;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.exemple.mindride.Banco_de_Dados.OrganizaUsuario;
import com.exemple.mindride.Binaural.Audio;
import com.exemple.mindride.Binaural.ValoresAudio;
import com.exemple.mindride.MusicRelact.Adaptador_autor;
import com.exemple.mindride.Player.Configura_som;
import com.exemple.mindride.Player.Inicio;
import com.exemple.mindride.Player.MySingleton;
import com.exemple.mindride.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Guest.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Guest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Guest extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static MediaPlayer mediaPlayer = MySingleton.getInstance();
    private OrganizaUsuario organizaUsuario = new OrganizaUsuario();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Guest() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Guest.
     */
    // TODO: Rename and change types and number of parameters
    public static Guest newInstance(String param1, String param2) {
        Guest fragment = new Guest();
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

        Configura_som configura_som2 = new Configura_som(getContext());
        if(configura_som2.mediaPlayer.isPlaying()){
            Toast.makeText(getContext(), "Seu Audio Adicional foi Parado para Você Obter Melhor Experiência!", Toast.LENGTH_SHORT).show();
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
    }



    // Criar a conexão com o banco (busca as configurações do arquivo JSON)
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    // Cria uma referencia para o ponto inicial do banco
    DatabaseReference reference  = database.getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View comando = inflater.inflate(R.layout.guest, container, false);



          comando.findViewById(R.id.btEntrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent cliente = new Intent(comando.getContext().getApplicationContext(), Inicio.class);
                organizaUsuario.logadoGuest = true;
                startActivity(cliente);



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
