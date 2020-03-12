package Level_20.task2005;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/*
    Очень странные дела
*/

public class Solution {
    public static void main(String[] args) {

        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            //String your_file_name = "C:\\Users\\BorderMan\\IdeaProjects\\JavaRushTest\\src\\test\\1.txt";
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            //---------------------------------------------
            Human ivanov2 = new Human("Ivanov2", new Asset("home2"), new Asset("car2"));
            ivanov2.save(outputStream);
            outputStream.flush();
            //------------

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson
            System.out.println(ivanov2.equals(somePerson));
            System.out.println(ivanov.hashCode());
            System.out.println(somePerson.hashCode());
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (!Objects.equals(name, human.name)) return false;
            return Objects.equals(assets, human.assets);                        // исправлен equals()
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;//(int) (Math.random() * 100);                        // исправлен hashCode()
        }

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(this.name);
            if (this.assets.size() > 0) {
                for (Asset current : this.assets)
                    printWriter.println(current.getName());
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            this.name = reader.readLine();
            String assetName;
            while ((assetName = reader.readLine()) != null)
                this.assets.add(new Asset(assetName));
            reader.close();
        }
    }
}
