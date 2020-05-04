package Level_36.task3608.view;

import Level_36.task3608.controller.Controller;
import Level_36.task3608.model.ModelData;

public class UsersView implements View{

    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        System.out.println("All users:");
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
}
