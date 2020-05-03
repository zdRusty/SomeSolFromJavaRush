package Level_36.task3608.controller;

import Level_36.task3608.model.Model;

public class Controller {
    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void onShowAllUsers(){
        model.loadUsers();
    }
}
