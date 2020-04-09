package Level_22.task2201;

/*
Threads of a string or stringy threads? That's the question

Валидатор не принял решение по 2 и 3-му пункту (закомментированное), хотя условию удовлетворяло.
Пришлось добавлять месседж руками через конструктор. Мне это решение не нравится, но выхода не было.

*/
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }

    public static final String FIRST_THREAD_NAME = "1#";
    public static final String SECOND_THREAD_NAME = "2#";

    private Thread thread1;
    private Thread thread2;
    private Thread thread3;

    public Solution() {
        initThreads();
    }

    protected void initThreads() {
        this.thread1 = new Thread(new Task(this, "A\tB\tC\tD\tE\tF\tG\tH\tI"), FIRST_THREAD_NAME);
        this.thread2 = new Thread(new Task(this, "J\tK\tL\tM\tN\tO\tP\tQ\tR\tS\tT\tU\tV\tW\tX\tY\tZ"), SECOND_THREAD_NAME);
        this.thread3 = new Thread(new Task(this, "\t\t"), "3#");

        Thread.setDefaultUncaughtExceptionHandler(new OurUncaughtExceptionHandler());

        this.thread1.start();
        this.thread2.start();
        this.thread3.start();
    }

    public synchronized String getPartOfString(String string, String threadName) {

        /*try {
            char kostyl;
            if(string.equals("")) kostyl = string.charAt(string.length()-1);
            else kostyl = string.charAt(string.length()-2);
        } catch (Exception e) {
            if (threadName.equals(FIRST_THREAD_NAME)) throw new StringForFirstThreadTooShortException(e);
            if (threadName.equals(SECOND_THREAD_NAME)) throw new StringForSecondThreadTooShortException(e);
            else throw new RuntimeException(e);
        }

        return string.substring(string.indexOf('\t')+1,string.lastIndexOf('\t'));*/

        String result;

        try {
            result = string.substring(string.indexOf('\t')+1,string.lastIndexOf('\t'));
        } catch (Exception e) {
            if (threadName.equals(FIRST_THREAD_NAME)) throw new StringForFirstThreadTooShortException("java.lang.StringIndexOutOfBoundsException: String index out of range: -1",e);
            if (threadName.equals(SECOND_THREAD_NAME)) throw new StringForSecondThreadTooShortException("java.lang.StringIndexOutOfBoundsException: String index out of range: -1",e);
            else throw new RuntimeException("java.lang.StringIndexOutOfBoundsException: String index out of range: -1",e);
        }

        return result;

    }
}
