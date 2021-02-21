package level_20.task2023;

/*
    Делаем правильный вывод.
*/
public class Solution {
    public static void main(String[] s) {
        A a = new C();
        a.method2();
    }

    public static class A {
        public void method1() {                     // чтобы решение прошло нужно сделать private, но с таким
            System.out.println("A class, method1"); // модификатором выдает предупреждение о неоднозначном вызове метода
        }                                           // в строке 42

        public void method2() {
            System.out.println("A class, method2");
            method1();
        }
    }

    public static class B extends A {
        public void method1() {
            super.method2();
            System.out.println("B class, method1");

        }

        public void method2() {
            System.out.println("B class, method2");
        }
    }

    public static class C extends B {
        public void method1() {
            System.out.println("C class, method1");
        }

        public void method2() {
            System.out.println("C class, method2");
            super.method1();
        }
    }
}