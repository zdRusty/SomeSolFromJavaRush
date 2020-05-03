package Level_36.task3608.model;

import Level_36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model{
    private ModelData modelData = new ModelData();
    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("1",1,1));
        users.add(new User("2",2,2));
        users.add(new User("3",3,3));
        users.add(new User("4",4,4));
        users.add(new User("5",5,5));
        modelData.setUsers(users);
    }
}
