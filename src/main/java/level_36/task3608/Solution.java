package level_36.task3608;

import level_36.task3608.controller.Controller;
import level_36.task3608.model.MainModel;
import level_36.task3608.model.Model;
import level_36.task3608.view.EditUserView;
import level_36.task3608.view.UsersView;

public class Solution {
    public static void main(String[] args) {
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        Controller controller = new Controller();
        EditUserView editUserView = new EditUserView();

        usersView.setController(controller);
        controller.setModel(model);
        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);
        editUserView.setController(controller);

        usersView.fireEventShowAllUsers();
        usersView.fireEventOpenUserEditForm(126);
        editUserView.fireEventUserDeleted(124L);
        editUserView.fireEventUserChanged("NewName",123,20);
        usersView.fireEventShowDeletedUsers();
    }
}