package level_34.task3403;

/*
Разложение на множители с помощью рекурсии
*/

public class Solution {
    public void recurse(int n) {
        if (n > 1) {
            int p = 2;
            while (n % p != 0) {
                p++;
            }
            System.out.print(p + " ");
            recurse(n / p);
        }
    }
}
