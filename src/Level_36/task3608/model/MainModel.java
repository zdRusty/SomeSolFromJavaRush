package Level_36.task3608.model;

import Level_36.task3608.model.service.UserService;
import Level_36.task3608.model.service.UserServiceImpl;

public class MainModel implements Model{
    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();
    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(userService.getUsersBetweenLevels(1,100));
    }
}
