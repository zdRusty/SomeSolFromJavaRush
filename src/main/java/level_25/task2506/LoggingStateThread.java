package level_25.task2506;

public class LoggingStateThread extends Thread{
    Thread target;
    public LoggingStateThread(Thread target) {
        this.target=target;
    }

    @Override
    public void run() {
        State state;
        State temp = null;
        do {
            state = target.getState();
            if (state != temp){
                System.out.println(state);
                temp = state;
            }
        } while (state != State.TERMINATED);
    }
}
