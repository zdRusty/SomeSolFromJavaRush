package Level_36.task3608.controller;

import Level_36.task3608.model.Model;
import Level_36.task3608.view.UsersView;

public class Controller {
    private Model model;
    private UsersView usersView;

    public void setModel(Model model) {
        this.model = model;
    }

    public void onShowAllUsers(){
        model.loadUsers();
        usersView.refresh(model.getModelData());
    }

    public void setUsersView(UsersView usersView) {
        this.usersView = usersView;
    }

    public void onShowAllDeletedUsers(){
        model.loadDeletedUsers();
        usersView.refresh(model.getModelData());
    }
}
