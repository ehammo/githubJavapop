package br.ufpe.cin.ehammo.githubjavapop.controller;

import java.util.List;

import br.ufpe.cin.ehammo.githubjavapop.model.Repository;
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


    private void getRepositories(int page, Callback<List<Repository>> callBack) {
        repositoryController.getRepositoryService().getRepositories("language:Java", "stars", page).enqueue(callBack);
    }

}
