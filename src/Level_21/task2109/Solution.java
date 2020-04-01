package Level_21.task2109;

import java.util.Objects;

/*
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public Object clone() throws CloneNotSupportedException {
            return new A(getI(),getJ());
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof B)) return false;
            B b = (B) o;
            return Objects.equals(name, b.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public B clone() throws CloneNotSupportedException{
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        public boolean equals(Object o) {
            return super.equals(o);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public C clone() throws CloneNotSupportedException {
            return new C(super.getI(),super.getJ(),super.getName());
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        A a = new A(1,2);
        B b = new B(a.getI(),a.getJ(),"3");
        C c = new C(a.getI(),a.getJ(),b.getName());
        System.out.println(b.clone());

    }

}
