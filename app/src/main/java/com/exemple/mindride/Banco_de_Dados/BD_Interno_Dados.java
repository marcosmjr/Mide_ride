package com.exemple.mindride.Banco_de_Dados;

public class BD_Interno_Dados {

    /************ Dados do Usuário **********/

    // private int idUsuario = 0;
    private static String emailUsuario = "";
    private static String nomeUsuario = "";
    private static String senhaUsuario = "";
    private static int dia = 0;
    private static String mes = "";
    private static int ano = 0;
    private static String generoUsuario = "";
    private static int aceitaTermos = 0; // O SQLite não tem tipe boleano, o boleano é definido com: false --> 0 e true --> 1

    /***************************************/

    /************ Dados de aquivos de som **********/

    private static int idMusica = 0;
    private static String nomeMusica = "";
    private static String nomeArtista = "";
    private static String genero = "";
    private static String enderecoArquivos = "";
    private static int ouvidoNumeroVezes = 0;
    private static int marcadoFavoritos = 0;
    private static int pertenceUsuario = 0;
    private static String imagemEndereco = "";

    //frequencias do gerador de batimento, nos lados esquedo e direito
    private static int frequenciaEsquerdo = 0;
    private static int frequenciaDireito = 0;

    /***********************************************/

    /***********************Login******************/
    private static int idConfiguracao = 0;
    private static String nomeConfiguracao = "";
    private static int estadoConfiguracao = 0;
    /**********************************************/


    /*******Construtor Vasio*******/
    public BD_Interno_Dados(){}

    /****************************************Construtores do CRUD************************************************/

    /*******Construtor do Update dos Sons*******/
    public BD_Interno_Dados(int idMusica, String nomeMusica, String nomeArtista, String genero, String enderecoArquivos, int ouvidoNumeroVezes, int marcadoFavoritos, int pertenceUsuario, String imagemEndereco, String emailUsuario, int frequenciaEsquerdo, int frequenciaDireito) {
        BD_Interno_Dados.idMusica = idMusica;
        BD_Interno_Dados.nomeMusica = nomeMusica;
        BD_Interno_Dados.nomeArtista = nomeArtista;
        BD_Interno_Dados.genero = genero;
        BD_Interno_Dados.enderecoArquivos = enderecoArquivos;
        BD_Interno_Dados.ouvidoNumeroVezes = ouvidoNumeroVezes;
        BD_Interno_Dados.marcadoFavoritos = marcadoFavoritos;
        BD_Interno_Dados.pertenceUsuario = pertenceUsuario;
        BD_Interno_Dados.imagemEndereco = imagemEndereco;
        BD_Interno_Dados.emailUsuario = emailUsuario;
        BD_Interno_Dados.frequenciaEsquerdo = frequenciaEsquerdo;
        BD_Interno_Dados.frequenciaDireito = frequenciaDireito;
    }


    /*******Construtor do Cadastro de Usuário e Update*******/
    public BD_Interno_Dados(String emailUsuario, String nomeUsuario, String senhaUsuario, int dia, String mes, int ano, String generoUsuario, int aceitaTermos) {
        BD_Interno_Dados.emailUsuario = emailUsuario;
        BD_Interno_Dados.nomeUsuario = nomeUsuario;
        BD_Interno_Dados.senhaUsuario = senhaUsuario;
        BD_Interno_Dados.dia = dia;
        BD_Interno_Dados.mes = mes;
        BD_Interno_Dados.ano = ano;
        BD_Interno_Dados.generoUsuario = generoUsuario;
        BD_Interno_Dados.aceitaTermos = aceitaTermos;
    }
    /*******Construtor do Cadastro de Sons*******/
    public BD_Interno_Dados(String nomeMusica, String nomeArtista, String genero, String enderecoArquivos, int ouvidoNumeroVezes, int marcadoFavoritos, int pertenceUsuario, String imagemEndereco, String emailUsuario, int frequenciaEsquerdo, int frequenciaDireito) {
        BD_Interno_Dados.nomeMusica = nomeMusica;
        BD_Interno_Dados.nomeArtista = nomeArtista;
        BD_Interno_Dados.genero = genero;
        BD_Interno_Dados.enderecoArquivos = enderecoArquivos;
        BD_Interno_Dados.ouvidoNumeroVezes = ouvidoNumeroVezes;
        BD_Interno_Dados.marcadoFavoritos = marcadoFavoritos;
        BD_Interno_Dados.pertenceUsuario = pertenceUsuario;
        BD_Interno_Dados.imagemEndereco = imagemEndereco;
        BD_Interno_Dados.emailUsuario = emailUsuario;
        BD_Interno_Dados.frequenciaEsquerdo = frequenciaEsquerdo;
        BD_Interno_Dados.frequenciaDireito = frequenciaDireito;
    }

    public BD_Interno_Dados(int idConfiguracao, String nomeConfiguracao, int estadoConfiguracao, String emailUsuario, String senhaUsuario) {
        BD_Interno_Dados.idConfiguracao = idConfiguracao;
        BD_Interno_Dados.nomeConfiguracao = nomeConfiguracao;
        BD_Interno_Dados.estadoConfiguracao = estadoConfiguracao;
        BD_Interno_Dados.emailUsuario = emailUsuario;
        BD_Interno_Dados.senhaUsuario = senhaUsuario;

    }

    /************ Getter e Setter do Usuário***************/

    public static String getEmailUsuario() {
        return emailUsuario;
    }

    public static void setEmailUsuario(String emailUsuario) {
        BD_Interno_Dados.emailUsuario = emailUsuario;
    }

    public static String getNomeUsuario() {
        return nomeUsuario;
    }

    public static void setNomeUsuario(String nomeUsuario) {
        BD_Interno_Dados.nomeUsuario = nomeUsuario;
    }

    public static String getSenhaUsuario() {
        return senhaUsuario;
    }

    public static void setSenhaUsuario(String senhaUssuario) {
        BD_Interno_Dados.senhaUsuario = senhaUssuario;
    }

    public static int getDia() {
        return dia;
    }

    public static void setDia(int dia) {
        BD_Interno_Dados.dia = dia;
    }

    public static String getMes() {
        return mes;
    }

    public static void setMes(String mes) {
        BD_Interno_Dados.mes = mes;
    }

    public static int getAno() {
        return ano;
    }

    public static void setAno(int ano) {
        BD_Interno_Dados.ano = ano;
    }

    public static String getGeneroUsuario() {
        return generoUsuario;
    }

    public static void setGeneroUsuario(String GeneroUsuario) {
        BD_Interno_Dados.generoUsuario = GeneroUsuario;
    }

    public static int getAceitaTermos() {
        return aceitaTermos;
    }

    public static void setAceitaTermos(int aceitaTermos) {
        BD_Interno_Dados.aceitaTermos = aceitaTermos;
    }


    /************ Getter e Setter dos Sons***************/
    public static int getIdMusica() {
        return idMusica;
    }

    public static void setIdMusica(int idMusica) {
        BD_Interno_Dados.idMusica = idMusica;
    }

    public static String getNomeMusica() {
        return nomeMusica;
    }

    public static void setNomeMusica(String nomeMusica) {
        BD_Interno_Dados.nomeMusica = nomeMusica;
    }

    public static String getNomeArtista() {
        return nomeArtista;
    }

    public static void setNomeArtista(String nomeArtista) {
        BD_Interno_Dados.nomeArtista = nomeArtista;
    }

    public static String getGenero() {
        return genero;
    }

    public static void setGenero(String genero) {
        BD_Interno_Dados.genero = genero;
    }

    public static String getEnderecoArquivos() {
        return enderecoArquivos;
    }

    public static void setEnderecoArquivos(String enderecoArquivos) {
        BD_Interno_Dados.enderecoArquivos = enderecoArquivos;
    }

    public static int getOuvidoNumeroVezes() {
        return ouvidoNumeroVezes;
    }

    public static void setOuvidoNumeroVezes(int ouvidoNumeroVezes) {
        BD_Interno_Dados.ouvidoNumeroVezes = ouvidoNumeroVezes;
    }

    public static int getMarcadoFavoritos() {
        return marcadoFavoritos;
    }

    public static void setMarcadoFavoritos(int marcadoFavoritos) {
        BD_Interno_Dados.marcadoFavoritos = marcadoFavoritos;
    }

    public static int getPertenceUsuario() {
        return pertenceUsuario;
    }

    public static void setPertenceUsuario(int pertenceUsuario) {
        BD_Interno_Dados.pertenceUsuario = pertenceUsuario;
    }

    public static String getImagemEndereco() {
        return imagemEndereco;
    }

    public static void setimagemEndereco(String imagemEndereco) {
        BD_Interno_Dados.imagemEndereco = imagemEndereco;
    }

    public static int getFrequenciaEsquerdo() {
        return frequenciaEsquerdo;
    }

    public static void setFrequenciaEsquerdo(int frequenciaEsquerdo) {
        BD_Interno_Dados.frequenciaEsquerdo = frequenciaEsquerdo;
    }

    public static int getFrequenciaDireito() {
        return frequenciaDireito;
    }

    public static void setFrequenciaDireito(int frequenciaDireito) {
        BD_Interno_Dados.frequenciaDireito = frequenciaDireito;
    }

    public static int getIdConfiguracao() {
        return idConfiguracao;
    }

    public static void setIdConfiguracao(int idConfiguracao) {
        BD_Interno_Dados.idConfiguracao = idConfiguracao;
    }

    public static String getNomeConfiguracao() {
        return nomeConfiguracao;
    }

    public static void setNomeConfiguracao(String nomeConfiguracao) {
        BD_Interno_Dados.nomeConfiguracao = nomeConfiguracao;
    }

    public static int getEstadoConfiguracao() {
        return estadoConfiguracao;
    }

    public static void setEstadoConfiguracao(int estadoConfiguracao) {
        BD_Interno_Dados.estadoConfiguracao = estadoConfiguracao;
    }
}





