package Level_20.task2017;

import java.io.ObjectInputStream;
import java.io.Serializable;

/*
    Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {

        try {
            Object obj = objectStream.readObject();
            if ((obj instanceof A)&&!(obj instanceof B)) return (A)obj;
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            super();
            System.out.println("inside B");
        }
    }

    public static void main(String[] args){

    }
}
