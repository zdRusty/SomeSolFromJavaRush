package Level_20.task2001;

/*
    Задача на сериализацию. Нужно прописать логику методов save и load.
    Реализацию интерфейса Serializable использовать запрещено.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Solution {
    public static void main(String[] args) {
        //путь к файлу outputStream/inputStream
        try {
            File your_file_name = File.createTempFile("tmp", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson
            System.out.println(ivanov.equals(somePerson));

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

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (!Objects.equals(name, human.name)) return false;
            return Objects.equals(assets, human.assets);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write(name+"\n");
            for (Asset x : assets) {
                    bw.write(x.getName()+"\n");
                    bw.write(x.getPrice()+"\n");
            }
            bw.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            name = br.readLine();
            while (br.ready()) {
                String assName = br.readLine();
                double price = Double.parseDouble(br.readLine());
                assets.add(new Asset(assName, price));
            }
            br.close();
        }

        @Override
        public String toString() {
            return name+" "+assets;
        }
    }
}

