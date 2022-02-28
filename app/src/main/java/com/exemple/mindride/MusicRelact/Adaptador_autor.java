package com.exemple.mindride.MusicRelact;

public class Adaptador_autor {
    //Photo,Music,Music
   protected static final String[] autor_Names = {"Bella Sant ASMR","Emanuelly Raquel ASMR","Gaucha ASMR","Sweet Carol"};

   protected static final String[] sweetMusicsLinks = {"gs://mind-ride-aplicativo.appspot.com/Playlists/Sweet Carol/Musica-Escovando_seu_Cabelo!Album-Sweet_Carol!Autor-Sweet_Carol.mp3",
            "gs://mind-ride-aplicativo.appspot.com/Playlists/Sweet Carol/Musica-Sons_de_Plastico!Album-Sweet_Carol!Autor-Sweet_Carol.mp3"};

   protected static final String[] bellaMusicLinks = {"gs://mind-ride-aplicativo.appspot.com/Playlists/Bella Sant ASMR/Musica-Relaxando_com_Esponja!Album-Bella_Sant_ASMR!Autor-Bella_Sant_ASMR.mp3"};

    protected static  final String[] emanuRaquelLinks = {"gs://mind-ride-aplicativo.appspot.com/Playlists/Emanuelly Raquel ASMR/Musica-ASMR_Tapping_Guitarra!Album-Emanuelly_ASMR!Autor-Emanuelly_Raquel_ASMR.mp3"};

    protected static final String[]    gauchaLinks = {"gs://mind-ride-aplicativo.appspot.com/Playlists/Gaucha ASMR/Musica-ASMR_Chovendo_sem_Energia!Album-Gaucha_ASMR!Autor-Gaucha_ASMR.mp3"};


    protected static final String[] sweetMusicsNames = {"Escovando seu Cabelo", "Sons com Plastico"};

    protected static final String[] bellaMusicsNames = {"Relaxando com Esponja"};

    protected static  final String[] emanuRaquelNames = {"Tapping com uma Guitarra"};

    protected static final String[]    gauchaNames = {"Chovendo sem Energia"};


    protected static final String[] allPhotos = {"gs://mind-ride-aplicativo.appspot.com/Playlists/Sweet Carol/Sweet Carol.jpg","gs://mind-ride-aplicativo.appspot.com/Playlists/Bella Sant ASMR/Bella Sant ASMR.jpg","gs://mind-ride-aplicativo.appspot.com/Playlists/Emanuelly Raquel ASMR/Emanuelly Raquel ASMR.jpg","gs://mind-ride-aplicativo.appspot.com/Playlists/Gaucha ASMR/Gaucha ASMR.jpg"};


    public static String[] getAutor_Names() {
        return autor_Names;
    }

    public static String[] getSweetMusicsLinks() {
        return sweetMusicsLinks;
    }

    public static String[] getBellaMusicsLinks() {
        return bellaMusicLinks;
    }

    public static String[] getEmanuRaquelLinks() {
        return emanuRaquelLinks;
    }

    public static String[] getGauchaLinks() {
        return gauchaLinks;
    }

    public static String[] getAllPhotos() {
        return allPhotos;
    }

    protected static int testNumerFor = 0;

    public static int getTestNumerFor() {
        return testNumerFor;
    }

    public static String[] getSweetMusicsNames() {
        return sweetMusicsNames;
    }

    public static String[] getBellaMusicsNames() {
        return bellaMusicsNames;
    }

    public static String[] getEmanuRaquelNames() {
        return emanuRaquelNames;
    }

    public static String[] getGauchaNames() {
        return gauchaNames;
    }

    public static void setTestNumerFor(int testNumerFor) {
        Adaptador_autor.testNumerFor = testNumerFor;
    }


    protected static String pegaURLocal = "";
    public String getPegaURLocal() {
        return pegaURLocal;
    }

    public void setPegaURLocal(String pegaURLocal) {
        this.pegaURLocal = pegaURLocal;
    }






}

