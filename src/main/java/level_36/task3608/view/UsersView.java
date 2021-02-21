package level_36.task3608.view;

import level_36.task3608.controller.Controller;
import level_36.task3608.model.ModelData;

public class UsersView implements View{

    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        if(modelData.isDisplayDeletedUserList()) {
            System.out.println("All deleted users:");
        }
        else System.out.println("All users:");
        modelData.getUsers().stream().forEach(x->System.out.println("\t"+x));
        System.out.println("===================================================");

    }

    @Override
    public void setController(Controller controller) {
        this.controller=controller;
    }

    public void fireEventShowAllUsers() {
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers(){
        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id){
        controller.onOpenUserEditForm(id);
    }
}
