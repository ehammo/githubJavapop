package br.ufpe.cin.ehammo.githubjavapop.model.interfaces;

import java.util.List;

import br.ufpe.cin.ehammo.githubjavapop.model.Repository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by eduardo on 06/02/2018.
 */

public interface RepositoryInterface {

    @GET("search/repositories/")
    Call<List<Repository>> getRepositories(
            @Query("q") String language,
            @Query("sort") String sort,
            @Query("page") int page
    );

}
