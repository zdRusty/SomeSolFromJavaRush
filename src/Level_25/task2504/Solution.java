package Level_25.task2504;

/*
Switch для нитей
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for(Thread x: threads){
            switch (x.getState()){
                case NEW: x.start();
                    break;
                case TIMED_WAITING:
                case WAITING:
                case BLOCKED:
                    x.interrupt();
                    break;
                case RUNNABLE: x.isInterrupted();
                    break;
                case TERMINATED:
                    System.out.println(x.getPriority());
                    break;
            }
        }
    }

    public static void main(String[] args) {
    }
}

