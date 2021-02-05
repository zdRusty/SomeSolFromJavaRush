package Level_36.task3608.view;

import Level_36.task3608.controller.Controller;
import Level_36.task3608.model.ModelData;

public interface View {
    void refresh(ModelData modelData);
    void setController(Controller controller);
}
