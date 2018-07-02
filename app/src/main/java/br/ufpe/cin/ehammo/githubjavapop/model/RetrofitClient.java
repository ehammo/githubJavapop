package br.ufpe.cin.ehammo.githubjavapop.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eduar on 24/03/2017.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://api.github.com/";

    public static Retrofit getClient() {
        String baseUrl = BASE_URL;
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
