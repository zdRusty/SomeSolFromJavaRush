package level_20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
    Читаем и пишем в файл: JavaRush
    Задача на сериализацию. Нужно прописать логику методов save и load.
    Реализацию интерфейса Serializable и Externalizable в классe Solution.JavaRush использовать запрещено.
    Вариант решения с реализацией Serializable в классе User с использованием ObjectInputStream/ObjectOutputStream валидатор не пускает.
*/
public class Solution2 {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here


            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);

            User user0 = new User();
            Date birth0 = sdf.parse("01/01/2001");
            user0.setFirstName("Ivan");
            user0.setLastName("Ivanov");
            user0.setBirthDate(birth0);
            user0.setMale(true);
            user0.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user0);

            User user1 = new User();
            Date birth1 = sdf.parse("02/02/2002");
            user1.setFirstName("Petr");
            user1.setLastName("Petrov");
            user1.setBirthDate(birth1);
            user1.setMale(true);
            user1.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user1);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object

            System.out.println(loadedObject.equals(javaRush));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method
            ObjectOutputStream ous = new ObjectOutputStream(outputStream);
            ous.writeInt(users.size());
            for (User x : users) {
                ous.writeObject(x);
            }
            ous.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            int size = ois.readInt();
            for(int i=0;i<size;i++){
                User user = (User) ois.readObject();
                users.add(user);
            }
            ois.close();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}

