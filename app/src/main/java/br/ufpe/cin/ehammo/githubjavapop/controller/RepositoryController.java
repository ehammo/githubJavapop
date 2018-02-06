package br.ufpe.cin.ehammo.githubjavapop.controller;

import br.ufpe.cin.ehammo.githubjavapop.model.RetrofitClient;
import br.ufpe.cin.ehammo.githubjavapop.model.interfaces.RepositoryInterface;

/**
 * Created by eduardo on 06/02/2018.
 */

public class RepositoryController {

    private RepositoryInterface RepositoryInterface;

    public RepositoryController() {
    }

    public RepositoryInterface getRepositoryService() {
        if (RepositoryInterface == null)
            RepositoryInterface = RetrofitClient.getClient().create(RepositoryInterface.class);
        return RepositoryInterface;
    }

}
