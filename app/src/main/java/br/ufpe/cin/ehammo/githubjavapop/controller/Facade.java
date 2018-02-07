package br.ufpe.cin.ehammo.githubjavapop.controller;

import android.util.Log;

import br.ufpe.cin.ehammo.githubjavapop.model.APIResponse;
import retrofit2.Callback;

/**
 * Created by eduardo on 06/02/2018.
 */

public class Facade {

    private static Facade instance;
    private PullRequestController pullRequestController;
    private RepositoryController repositoryController;

    public Facade() {
        pullRequestController = new PullRequestController();
        repositoryController = new RepositoryController();
    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }


    public void getRepositories(int page, Callback<APIResponse> callBack) {
        Log.d("Facade", repositoryController.getRepositoryService().getRepositories("language:Java", "stars", page).request().url().toString());
        repositoryController.getRepositoryService().getRepositories("language:Java", "stars", page).enqueue(callBack);
    }

}
