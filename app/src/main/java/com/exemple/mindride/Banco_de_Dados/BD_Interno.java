package com.exemple.mindride.Banco_de_Dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/* Material de referencia:
 * https://developer.android.com/training/data-storage/sqlite
 * https://www.youtube.com/watch?v=IZxgPNh4p-A&list=PLssIKrX2yyQHFUIF7jIPe9z4VuLU9gY5D
 */

public class BD_Interno extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_INTERNO = "bd_interno";

    private static final String TABELA_USUARIO = "tabela_usuario";
    //  private static final String COLUNA_ID = "id";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_SENHA = "senha";
    private static final String COLUNA_DIA = "dia";
    private static final String COLUNA_MES = "mes";
    private static final String COLUNA_ANO = "ano";
    private static final String COLUNA_GENERO = "genero";
    private static final String COLUNA_TERMOS = "termos";


    private static final String TABELA_SONS_USUARIO = "tabela_sons_usuario";
    private static final String COLUNA_ID = "id_musica";
    private static final String COLUNA_NOME_MUSICA = "nome_musica";
    private static final String COLUNA_NOME_ARTISTA = "nome_artista";
    private static final String COLUNA_GENERO_SONS = "genero";
    private static final String COLUNA_ENDERECO_ARQUIVOS = "endereco_arquivos";
    private static final String COLUNA_OUVIDO = "ouvido_numero_vezes";
    private static final String COLUNA_FAVORITOS = "marcado_favoritos";
    private static final String COLUNA_PERTENCE = "pertence_usuario";
    private static final String COLUNA_IMAGEM = "endereco_imagem";
    private static final String COLUNA_LOGIN = "login";
    private static final String COLUNA_FREQUENCIA_ESQUERDO = "frequencia_esquerdo";
    private static final String COLUNA_FREQUENCIA_DIREITO = "frequencia_direito";


    private static final String TABELA_CONFIGURACAO = "tabela_configuracao";
    private static final String COLUNA_ID_CONFIGURACAO = "id_configuracao";
    private static final String COLUNA_NOME_ESTADO = "nome_item_configuracao";
    private static final String COLUNA_ESTADO_CONFIGURACAO = "estado_configuracao";
    private static final String COLUNA_NOME_LOGIN  = "login";
    private static final String COLUNA_SENHA_LOGIN = "senha";






    /************** Contrutor ************/
    public BD_Interno(@Nullable Context context) {
        super(context, BANCO_INTERNO, null, VERSAO_BANCO);
    }

    /************** Métodos Obrigatórios ************/
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Atributo QUERY_COLUNA ao qual é atribuido os comandos do SQLite
        // que será usado para criar a tabela e as colunas da tabela
        String QUERY_COLUNA_USUARIO = "CREATE TABLE " + TABELA_USUARIO + "("
                + COLUNA_EMAIL + " TEXT PRIMARY KEY, "
                + COLUNA_NOME + " TEXT, "
                + COLUNA_SENHA + " TEXT,"
                + COLUNA_DIA + " INTEGER, "
                + COLUNA_MES + " TEXT, "
                + COLUNA_ANO + " INTEGER, "
                + COLUNA_GENERO + " TEXT, "
                + COLUNA_TERMOS + " INTEGER)"; // O SQLite não tem tipe boleano, o boleano é definido com: false --> 0 e true --> 1
        db.execSQL(QUERY_COLUNA_USUARIO);

        String QUERY_COLUNA_SONS = "CREATE TABLE " + TABELA_SONS_USUARIO + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_NOME_MUSICA + " TEXT, "
                + COLUNA_NOME_ARTISTA + " TEXT, "
                + COLUNA_ENDERECO_ARQUIVOS + " TEXT,"
                + COLUNA_OUVIDO + " INTEGER, "
                + COLUNA_FAVORITOS + " INTEGER, "
                + COLUNA_PERTENCE + " INTEGER, "
                + COLUNA_GENERO_SONS + " TEXT, "
                + COLUNA_IMAGEM + " TEXT, "
                + COLUNA_LOGIN + " TEXT, "
                + COLUNA_FREQUENCIA_ESQUERDO + " INTEGER, "
                + COLUNA_FREQUENCIA_DIREITO + " INTEGER, "
                + "FOREIGN KEY (" + COLUNA_LOGIN + ") REFERENCES " + TABELA_USUARIO + "(" + COLUNA_EMAIL + "));" ;

        db.execSQL(QUERY_COLUNA_SONS);



        String QUERY_CONFIGURACAO = "CREATE TABLE " + TABELA_CONFIGURACAO + "("
                + COLUNA_ID_CONFIGURACAO + " INTEGER PRIMARY KEY, "
                + COLUNA_NOME_ESTADO + " TEXT, "
                + COLUNA_ESTADO_CONFIGURACAO + " INTEGER,"
                + COLUNA_NOME_LOGIN + " TEXT, "
                + COLUNA_SENHA_LOGIN + " TEXT);";

        db.execSQL(QUERY_CONFIGURACAO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /******************** CRUD Usuário *********************/

    public void cadastroUsuario(BD_Interno_Dados usuario){

        // Referencia o banco de dados
        SQLiteDatabase db  = this.getWritableDatabase();

        //Responsável por captar dados para o banco
        ContentValues values = new ContentValues();

        values.put(COLUNA_EMAIL, usuario.getEmailUsuario());
        values.put(COLUNA_NOME, usuario.getNomeUsuario());
        values.put(COLUNA_SENHA, usuario.getSenhaUsuario());
        values.put(COLUNA_DIA, usuario.getDia());
        values.put(COLUNA_MES, usuario.getMes());
        values.put(COLUNA_ANO, usuario.getAno());
        values.put(COLUNA_GENERO, usuario.getGeneroUsuario());
        values.put(COLUNA_TERMOS, usuario.getAceitaTermos());

        db.insert(TABELA_USUARIO, null, values);
        db.close();
    }

    public void apagaUsuario(BD_Interno_Dados usuario){

        // Referencia o banco de dados
        SQLiteDatabase db  = this.getWritableDatabase();

        db.delete(TABELA_USUARIO, COLUNA_EMAIL + " = ? ", new String[] {usuario.getEmailUsuario()});

        db.close();

    }

    public BD_Interno_Dados buscaCadastroUsuario(String email){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_USUARIO, new String[] {COLUNA_EMAIL, COLUNA_NOME,
                        COLUNA_SENHA, COLUNA_DIA, COLUNA_MES, COLUNA_ANO,
                        COLUNA_GENERO, COLUNA_TERMOS}, COLUNA_EMAIL + " = ? ",
                new String[] {email}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        BD_Interno_Dados bd_interno_dados = new BD_Interno_Dados(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getString(4),
                Integer.parseInt(cursor.getString(5)), cursor.getString(6), Integer.parseInt(cursor.getString(7)));

        return bd_interno_dados;
    }

    public void atualizaCadastro(BD_Interno_Dados usuario){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_EMAIL, usuario.getEmailUsuario());
        values.put(COLUNA_NOME, usuario.getNomeUsuario());
        values.put(COLUNA_SENHA, usuario.getSenhaUsuario());
        values.put(COLUNA_DIA, usuario.getDia());
        values.put(COLUNA_MES, usuario.getMes());
        values.put(COLUNA_ANO, usuario.getAno());
        values.put(COLUNA_GENERO, usuario.getGeneroUsuario());
        values.put(COLUNA_TERMOS, usuario.getAceitaTermos());

        db.update(TABELA_USUARIO, values, COLUNA_EMAIL + " = ?", new String[] {usuario.getEmailUsuario()});
    }


    /******************** CRUD Sons*********************/

    public void cadastroSons(BD_Interno_Dados sons){

        // Referencia o banco de dados
        SQLiteDatabase db  = this.getWritableDatabase();

        //Responsavel por captar dasdo para o banco
        ContentValues values = new ContentValues();

        values.put( COLUNA_NOME_MUSICA, sons.getNomeMusica());
        values.put(COLUNA_NOME_ARTISTA, sons.getNomeArtista());
        values.put(COLUNA_GENERO, sons.getGenero());
        values.put(COLUNA_ENDERECO_ARQUIVOS, sons.getEnderecoArquivos());
        values.put(COLUNA_OUVIDO, sons.getOuvidoNumeroVezes());
        values.put(COLUNA_FAVORITOS, sons.getMarcadoFavoritos());
        values.put(COLUNA_PERTENCE, sons.getPertenceUsuario());
        values.put(COLUNA_IMAGEM, sons.getImagemEndereco());
        values.put(COLUNA_LOGIN, sons.getEmailUsuario());
        values.put(COLUNA_FREQUENCIA_ESQUERDO, sons.getFrequenciaEsquerdo());
        values.put(COLUNA_FREQUENCIA_DIREITO, sons.getFrequenciaDireito());

        db.insert(TABELA_SONS_USUARIO, null, values);
        db.close();
    }

    public void apagarSonsUsuario(BD_Interno_Dados sonsUsuario){

        // Referencia o banco de dados
        SQLiteDatabase db  = this.getWritableDatabase();

        db.delete(TABELA_SONS_USUARIO, COLUNA_LOGIN + " = ? ", new String[] {sonsUsuario.getEmailUsuario()});

        db.close();

    }

    public BD_Interno_Dados buscaCadastroSons(String login){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_SONS_USUARIO, new String[] {COLUNA_ID, COLUNA_NOME_MUSICA, COLUNA_NOME_ARTISTA,
                        COLUNA_GENERO, COLUNA_ENDERECO_ARQUIVOS, COLUNA_OUVIDO, COLUNA_FAVORITOS,
                        COLUNA_PERTENCE, COLUNA_IMAGEM, COLUNA_LOGIN, COLUNA_FREQUENCIA_ESQUERDO, COLUNA_FREQUENCIA_DIREITO},
                COLUNA_LOGIN + " = ? ", new String[] {login}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        BD_Interno_Dados bd_interno_dados = new BD_Interno_Dados(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)),
                Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), cursor.getString(8), cursor.getString(9),
                Integer.parseInt(cursor.getString(10)), Integer.parseInt(cursor.getString(11)));

        return bd_interno_dados;
    }

    public void atualizaCadastroSons(BD_Interno_Dados sons){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_MUSICA, sons.getNomeMusica());
        values.put(COLUNA_NOME_ARTISTA, sons.getNomeArtista());
        values.put(COLUNA_GENERO, sons.getGenero());
        values.put(COLUNA_ENDERECO_ARQUIVOS, sons.getEnderecoArquivos());
        values.put(COLUNA_OUVIDO, sons.getOuvidoNumeroVezes());
        values.put(COLUNA_FAVORITOS, sons.getMarcadoFavoritos());
        values.put(COLUNA_PERTENCE, sons.getPertenceUsuario());
        values.put(COLUNA_IMAGEM, sons.getImagemEndereco());
        values.put(COLUNA_LOGIN, sons.getEmailUsuario());
        values.put(COLUNA_FREQUENCIA_ESQUERDO, sons.getFrequenciaEsquerdo());
        values.put(COLUNA_FREQUENCIA_DIREITO, sons.getFrequenciaDireito());

        db.update(TABELA_SONS_USUARIO, values, COLUNA_ID + " = ?", new String[] {String.valueOf(sons.getIdMusica())});
    }


    /******************** CRUD Configuração *********************/

    public void cadastroEstado(BD_Interno_Dados configuracao){

        // Referencia o banco de dados
        SQLiteDatabase db  = this.getWritableDatabase();

        //Responsável por captar dados para o banco
        ContentValues values = new ContentValues();

        values.put(COLUNA_ID_CONFIGURACAO, configuracao.getIdConfiguracao());
        values.put(COLUNA_NOME_ESTADO, configuracao.getNomeConfiguracao());
        values.put(COLUNA_ESTADO_CONFIGURACAO, configuracao.getEstadoConfiguracao());
        values.put(COLUNA_NOME_LOGIN, configuracao.getEmailUsuario());
        values.put(COLUNA_SENHA_LOGIN, configuracao.getSenhaUsuario());


        db.insert(TABELA_CONFIGURACAO, null, values);
        db.close();
    }


    public BD_Interno_Dados buscaEstado(String id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_CONFIGURACAO, new String[] {COLUNA_ID_CONFIGURACAO, COLUNA_NOME_ESTADO,
                        COLUNA_ESTADO_CONFIGURACAO, COLUNA_NOME_LOGIN, COLUNA_SENHA_LOGIN }, COLUNA_ID_CONFIGURACAO + " = ? ",
                new String[] {id}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        BD_Interno_Dados bd_interno_dados = new BD_Interno_Dados(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4));

        return bd_interno_dados;
    }

    public void atualizaEstado(BD_Interno_Dados configuracao){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ID_CONFIGURACAO, configuracao.getIdConfiguracao());
        values.put(COLUNA_NOME_ESTADO, configuracao.getNomeConfiguracao());
        values.put(COLUNA_ESTADO_CONFIGURACAO, configuracao.getEstadoConfiguracao());
        values.put(COLUNA_NOME_LOGIN, configuracao.getEmailUsuario());
        values.put(COLUNA_SENHA_LOGIN, configuracao.getSenhaUsuario());

        db.update(TABELA_CONFIGURACAO, values, COLUNA_ID_CONFIGURACAO + " = ?", new String[] {String.valueOf(configuracao.getIdConfiguracao())});
    }

}