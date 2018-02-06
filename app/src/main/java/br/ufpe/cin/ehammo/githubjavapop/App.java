package br.ufpe.cin.ehammo.githubjavapop;

import android.app.Application;
import android.content.Context;

/**
 * Created by eduardo on 06/02/2018.
 */

public class App extends Application {

    private static Application sApplication;
    private static String BASE_URL = "https://api.github.com/";

    public static Application getApplication() {
        return sApplication;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
