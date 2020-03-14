package Level_20.task2016;

import java.io.Serializable;

/*
    Минимум изменений
*/
public class Solution {
    public class A implements Serializable {
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        public A() {                                    // +конструктор без параметров
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public class B extends A {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }

        public B() {
            super();
        }                           // +конструктор без параметров
    }

    public class C extends B {
        String name = "C";

        public C(String name) {
            super(name);
            this.name = name;
        }

        public C() {
            super();
        }                           // +конструктор без параметров
    }

    public static void main(String[] args) {

    }
}