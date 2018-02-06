package br.ufpe.cin.ehammo.githubjavapop.controller;

import br.ufpe.cin.ehammo.githubjavapop.model.RetrofitClient;
import br.ufpe.cin.ehammo.githubjavapop.model.interfaces.PullRequestInterface;

/**
 * Created by eduardo on 06/02/2018.
 */

public class PullRequestController {

    private PullRequestInterface pullRequestInterface;

    public PullRequestController() {
    }

    public PullRequestInterface getPullRequestService() {
        if (pullRequestInterface == null)
            pullRequestInterface = RetrofitClient.getClient().create(PullRequestInterface.class);
        return pullRequestInterface;
    }


}
