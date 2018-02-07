package br.ufpe.cin.ehammo.githubjavapop.controller;

import android.util.Log;

import java.util.List;

import br.ufpe.cin.ehammo.githubjavapop.model.APIResponse;
import br.ufpe.cin.ehammo.githubjavapop.model.Owner;
import br.ufpe.cin.ehammo.githubjavapop.model.PullRequest;
import retrofit2.Callback;

/**
 * Created by eduardo on 06/02/2018.
 */

public class Facade {

    private static Facade instance;
    private PullRequestController pullRequestController;
    private RepositoryController repositoryController;
    private UserController userController;

    public Facade() {
        pullRequestController = new PullRequestController();
        repositoryController = new RepositoryController();
        userController = new UserController();
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

    public void getUser(String login, Callback<Owner> callback) {
        Log.d("Facade", userController.getUserService().getUser(login).request().url().toString());
        userController.getUserService().getUser(login).enqueue(callback);
    }

    public void getPullRequests(String login, String repository, Callback<List<PullRequest>> callback) {
        pullRequestController.getPullRequestService().getPullRequests(login, repository).enqueue(callback);
    }

}
