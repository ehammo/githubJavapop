package br.ufpe.cin.ehammo.githubjavapop.model.interfaces;

import java.util.List;

import br.ufpe.cin.ehammo.githubjavapop.model.PullRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eduardo on 06/02/2018.
 */

public interface PullRequestInterface {

    @GET("repos/{owner}/{rep}/pulls")
    Call<List<PullRequest>> getPullRequests(
            @Path("owner") String owner,
            @Path("rep") String repository
    );

}
