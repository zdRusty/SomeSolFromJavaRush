package Level_36.task3608;

import Level_36.task3608.controller.Controller;
import Level_36.task3608.model.MainModel;
import Level_36.task3608.model.Model;
import Level_36.task3608.view.UsersView;

public class Solution {
    public static void main(String[] args) {
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        Controller controller = new Controller();

        usersView.setController(controller);
        controller.setModel(model);
        controller.setUsersView(usersView);

        usersView.fireEventShowAllUsers();
        usersView.fireEventShowDeletedUsers();
    }
}