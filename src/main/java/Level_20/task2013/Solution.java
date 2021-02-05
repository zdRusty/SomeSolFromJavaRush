package Level_20.task2013;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

/*
Externalizable Person
*/
public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person(){
        }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            age = in.readInt();
            mother = (Person) in.readObject();
            father = (Person) in.readObject();
            children = (List<Person>) in.readObject();
        }

        @Override
        public String toString() {
            return firstName+" "+lastName+" "+age;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // код для проверки
        /*
        Person pers = new Person("Ivan","Ivanov",30);
        pers.setFather(new Person("Petr","Ivanov",55));
        pers.setMother(new Person("Nina","Petrova",53));

        List<Person> ch = new ArrayList<>(){};
        ch.add(new Person("Sasha","Ivanov",5));
        ch.add(new Person("Lena","Ivanova",3));
        pers.setChildren(ch);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));

        System.out.println(pers+", "+pers.mother+", "+pers.father);
        pers.children.forEach(System.out::println);

        pers.writeExternal(out);
        out.close();
        Person loadPers = new Person();
        loadPers.readExternal(in);
        in.close();
        System.out.println(loadPers+", "+loadPers.mother+", "+loadPers.father);
        loadPers.children.forEach(System.out::println);
        */
    }
}