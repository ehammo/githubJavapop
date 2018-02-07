package br.ufpe.cin.ehammo.githubjavapop.controller;

import br.ufpe.cin.ehammo.githubjavapop.model.RetrofitClient;
import br.ufpe.cin.ehammo.githubjavapop.model.interfaces.UserInterface;

/**
 * Created by eduardo on 07/02/2018.
 */

public class UserController {

    private br.ufpe.cin.ehammo.githubjavapop.model.interfaces.UserInterface UserInterface;

    public UserController() {
    }

    public UserInterface getUserService() {
        if (UserInterface == null)
            UserInterface = RetrofitClient.getClient().create(UserInterface.class);
        return UserInterface;
    }
}
