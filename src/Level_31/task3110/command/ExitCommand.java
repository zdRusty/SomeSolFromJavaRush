package Level_31.task3110.command;

import Level_31.task3110.ConsoleHelper;

public class ExitCommand implements Command{
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("До встречи!");
    }
}
