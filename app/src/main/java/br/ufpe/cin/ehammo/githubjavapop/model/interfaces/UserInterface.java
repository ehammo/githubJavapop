package br.ufpe.cin.ehammo.githubjavapop.model.interfaces;

import br.ufpe.cin.ehammo.githubjavapop.model.Owner;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eduardo on 07/02/2018.
 */

public interface UserInterface {

    @GET("/users/{login}")
    Call<Owner> getUser(@Path("login") String login);

}
