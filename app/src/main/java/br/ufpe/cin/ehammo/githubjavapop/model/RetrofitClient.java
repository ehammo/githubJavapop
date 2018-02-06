package br.ufpe.cin.ehammo.githubjavapop.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.ufpe.cin.ehammo.githubjavapop.App;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eduar on 24/03/2017.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        String baseUrl = App.getBaseUrl();
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
