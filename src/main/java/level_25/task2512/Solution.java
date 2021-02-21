package level_25.task2512;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        List<Throwable> list = new ArrayList<>();
        rec(e,list);
        Collections.reverse(list);
        list.forEach(System.out::println);
        System.out.println(e.toString());
    }

    private Throwable rec (Throwable e, List<Throwable> list){
        if (e.getCause()!=null) {
            list.add(e.getCause());
            return rec(e.getCause(),list);
        }
        else return e;
    }

    public static void main(String[] args) throws Exception {

//        код для проверки
//        new Solution().uncaughtException(new Thread(),new Exception("exc", new RuntimeException("run",new IllegalAccessException("ill"))));

    }
}
 