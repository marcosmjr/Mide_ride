package com.exemple.mindride.Player;

import android.app.Application;
import android.media.MediaPlayer;

public final class MySingleton extends Application {

    static MediaPlayer instance;

    public static MediaPlayer getInstance() {

        if (instance == null)
        {
            instance = new MediaPlayer();

        }


        return instance;
    }


}