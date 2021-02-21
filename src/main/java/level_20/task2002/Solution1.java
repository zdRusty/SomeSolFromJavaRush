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
*/
public class Solution1 {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);
            JavaRush javaRush = new JavaRush();

            //initialize users field for the javaRush object here
            User user = new User();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date birth = sdf.parse("01/01/2001");
            //Date birth = new Date();
            user.setFirstName("Ivan");
            user.setLastName("Ivanov");
            user.setBirthDate(birth);
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user);

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
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            if (users.size()>0) {
                bw.write("yes"+"\n");
                for (User x : users) {
                    bw.write(x.getFirstName() + "\n");
                    bw.write(x.getLastName() + "\n");
                    bw.write(x.getBirthDate().getTime() + "\n");
                    bw.write(x.isMale() + "\n");
                    bw.write(x.getCountry() + "\n");
                }
            }
            else bw.write("no");
            bw.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String flag = br.readLine();
            if (flag.equals("yes")) {
                while (br.ready()) {
                    User user = new User();
                    user.setFirstName(br.readLine());
                    user.setLastName(br.readLine());
                    long l = Long.parseLong(br.readLine());
                    Date birth = new Date(l);
                    user.setBirthDate(birth);
                    user.setMale(Boolean.parseBoolean(br.readLine()));
                    String country = br.readLine();
                    if (country.equals("RUSSIA")) user.setCountry(User.Country.RUSSIA);
                    if (country.equals("UKRAINE")) user.setCountry(User.Country.UKRAINE);
                    if (country.equals("OTHER")) user.setCountry(User.Country.OTHER);
                    users.add(user);
                }
            }
            br.close();
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


