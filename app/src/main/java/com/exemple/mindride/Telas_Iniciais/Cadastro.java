package com.exemple.mindride.Telas_Iniciais;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.exemple.mindride.Banco_de_Dados.BD_Interno;
import com.exemple.mindride.Banco_de_Dados.BD_Interno_Dados;
import com.exemple.mindride.Banco_de_Dados.ConectaBD;
import com.exemple.mindride.Player.MySingleton;
import com.exemple.mindride.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Cadastro.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Cadastro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cadastro extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static MediaPlayer mediaPlayer = MySingleton.getInstance();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Cadastro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cadastro.
     */
    // TODO: Rename and change types and number of parameters
    public static Cadastro newInstance(String param1, String param2) {
        Cadastro fragment = new Cadastro();
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
        mediaPlayer.reset();
        mediaPlayer.stop();
    }


    // Criar a conexão com o banco (busca as configurações do arquivo JSON)
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    // Cria uma referencia para o ponto inicial do banco
    DatabaseReference reference  = database.getReference();

    //Método validador de email





    private static EditText email ,nome,senha;
    private static Spinner dia,mes,ano;
    private static CheckBox termos;
    private static RadioButton macho, femea;
    private static View comando;

    public EditText getEmail() {
        return email;
    }

    public EditText getNome() {
        return this.nome;
    }

    public EditText getSenha() {
        return this.senha;
    }

    public Spinner getDia() {
        return this.dia;
    }

    public Spinner getMes() {
        return this.mes;
    }

    public Spinner getAno() {
        return this.ano;
    }

    public RadioButton getMacho() {
        return this.macho;
    }
    public View getComando(){
        return this.comando;
    }

    public CheckBox getTermos() {
        return this.termos;
    }

    //Testa se a sintaxe do email é valida
    private boolean  val (EditText email){
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email.getText());
        boolean matchFound = m.matches();
        if (!matchFound) {
            return false;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        comando = inflater.inflate(R.layout.cadastro, container, false);

        email = comando.findViewById(R.id.cEmail);
        senha = comando.findViewById(R.id.cSenha);
        nome = comando.findViewById(R.id.cNome);
        dia = comando.findViewById(R.id.cDia);
        mes = comando.findViewById(R.id.cMes);
        ano = comando.findViewById(R.id.cAno);
        termos = comando.findViewById(R.id.cTermos);
        macho = comando.findViewById(R.id.cMasc);
        femea = comando.findViewById(R.id.cFem);




        termos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                termos.setChecked(false);
                AlertDialog.Builder alertConfig = new AlertDialog.Builder(comando.getContext(),R.style.Theme_Design_NoActionBar);
                alertConfig.setTitle("Termos de Uso");
                alertConfig.setMessage("Um bolo fofinho cai muito bem em diferentes momentos do dia, seja para um café da manhã, sobremesa ou lanche da tarde. Essa textura é macia e deliciosa e você pode obter esse resultado independente do sabor que escolher para o seu quitute!\n" +
                        "\n" +
                        "CONTINUA APÓS O ANÚNCIO\n" +
                        "\n" +
                        "\n" +
                        "Você pode escolher sabores mais leves e neutros, sabores de frutas, chocolate, cenoura e até mesmo opções sem glúten, lactose ou veganas! Assim, é possível ter um leque imenso de opções e não cair na rotina quando quiser comer um bolinho supergostoso.\n" +
                        "\n" +
                        "Para que você conheça algumas opções e possa testar casa, basta conferir a lista abaixo:\n" +
                        "\n" +
                        "\n" +
                        "Bolo fofinho simples\n" +
                        "FOTO: REPRODUÇÃO / PANELATERAPIA\n" +
                        "1. Bolo fofinho de fubá: o bolo de fubá é um clássico para muita gente, mas você conhece uma receita em que a massa fica extremamente fofa? Se você não sabe como obter um resultado desses em um bolo de fubá, confira todas as dicas para uma textura incrível.\n" +
                        "\n" +
                        "2. Bolo fofinho simples: que tal conhecer uma receita de massa básica, que pode ser combinada com uma grande quantidade de coberturas e recheios ou até mesmo aromatizada com ingredientes simples, como essência de baunilha, raspas de limão e outros?\n" +
                        "\n" +
                        "3. Bolo fofinho de iogurte: quer preparar o bolo de iogurte mais fofo da vida? Então, confira as dicas desta receita, que conta com uma massa de liquidificador, ideal para quem não quer perder muito tempo na cozinha, mas deseja um resultado delicioso.\n" +
                        "\n" +
                        "4. Bolo fofinho de creme de leite: nessa receita, você aprende a fazer uma massa bem fofinha, mas que ao mesmo tempo é amanteigada. Para testar essa opção em casa, você vai precisar de ovos, manteiga, açúcar, creme de leite, extrato de baunilha, leite, farinha de trigo e fermento em pó. A lista de ingredientes não é extensa e essa é uma ótima opção para ser servida com um café quentinho.\n" +
                        "\n" +
                        "5. Bolo fofinho de nada: bolo de nada? Sim, esse é o sabor dessa receita, que pode ser aromatizada com os ingredientes que você preferir, como chocolate, por exemplo. Porém, o simples também é bem gostoso. Uma boa pedida é servir com uma fatia com manteiga, ela irá derreter na massa e o resultado vai deixar qualquer um com água na boca.\n" +
                        "\n" +
                        "6. Bolo fofinho de baunilha: uma massa de baunilha combina muito bem com diversas situações, já que pode ser servida sem nada ou até mesmo incrementada para garantir um bolo mais sofisticado ou até mesmo com cara de festa. Essa é uma opção que não conta com um preparo complicado, mas pode surpreender quem experimentar um pedaço.\n" +
                        "\n" +
                        "CONTINUA APÓS O ANÚNCIO\n" +
                        "\n" +
                        "\n" +
                        "7. Bolo fofinho de vinagre: vinagre no bolo, é isso mesmo? Sim! Aqui, esse ingrediente entra em cena para deixar a massa bem fofinha. Ah, e na hora de comer o bolo, pode ficar tranquilo, pois ele não fica nem com cheiro e nem com sabor de vinagre. Esse item é usado apenas para dar uma textura mais gostosa ao bolo.\n" +
                        "\n" +
                        "8. Bolo simples sem fermento: sabia que é possível fazer um bolo com massa fofinha sem utilizar fermento? Essa receita é a prova disso! O resultado é um sabor neutro, então, você pode incrementá-la com os ingredientes de sua preferência.\n" +
                        "\n" +
                        "9. Bolo fofinho de rapadura: quer um bolo com gostinho especial, fácil de ser feito e que fique com uma textura bem fofa? Então, você precisa conhecer todas as dicas desta opção. O preparo não é nada complicado, mas você pode se surpreender com o resultado final.\n" +
                        "\n" +
                        "10. Bolo fofinho de nata: para testar essa receita em casa você vai precisar apenas de nata, açúcar, ovos, farinha de trigo e fermento em pó. Mesmo com uma lista de ingredientes tão curtinha, o sabor neutro é bem gostoso e a textura é bem macia.\n" +
                        "\n" +
                        "Bolo fofinho de chocolate").setPositiveButton(
                        "Aceitar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                termos.setChecked(true);
                            }
                        }).setNegativeButton("Recusar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        termos.setChecked(false);
                    }
                });
                AlertDialog alert = alertConfig.create();
                alert.show();

            }
        });


        comando.findViewById(R.id.cCadastrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConectaBD conecta = new ConectaBD();
                conecta.MetodoCadastrar();

                /* converte valores para serem inserida no
                 * banco de dados do SQLite */
                String genero;
                if(getMacho().isChecked()){
                    genero = "masculino";
                }else{ genero = "feminino";}

                int valorTermos;
                if(getTermos().isChecked()){
                    valorTermos = 1;
                }else {valorTermos = 0;}


                if(val(getEmail()) == true  && getEmail().getText().toString().length() >12 &&
                        getNome().getText().toString().length() > 8 && getSenha().getText().toString().length() > 7 && getTermos().isChecked()) {

                    /*Passa dados para classe BD_Inteno_Dados*/
                    BD_Interno_Dados db = new BD_Interno_Dados(email.getText().toString(), nome.getText().toString(), senha.getText().toString(),
                            Integer.parseInt(dia.getSelectedItem().toString()), mes.getSelectedItem().toString(), Integer.parseInt(ano.getSelectedItem().toString()), genero, valorTermos);
                    /*Cadastro no banco de dados interno */
                    BD_Interno cadastrar = new BD_Interno(comando.getContext());
                    cadastrar.cadastroUsuario(db);


                }

                /* Testa se o cadastro  pelo SQLite esta funcionando*/
                /*BD_Interno db = new BD_Interno(comando.getContext());
                BD_Interno_Dados dados = db.buscaCadastroSons("teste1@teste.com"); // busca por email do usuario cadastrado
                System.out.println("Busca " + dados.getEmailUsuario() + " " + dados.getSenhaUssuario() + " " + dados.getNomeUsuario()+" " + dados.getDia() + "/" + dados.getMes() + "/" + dados.getAno() + " " + dados.getGeneroUsuario() + " " + dados.getAceitaTermos());
                   */

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
