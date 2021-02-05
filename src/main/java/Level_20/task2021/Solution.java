package Level_20.task2021;

import java.io.*;

/*
    Сериализация под запретом
*/
public class Solution implements Serializable {

    public static class SubSolution extends Solution {

        private void writeObject (ObjectOutputStream out) throws IOException{
            throw new NotSerializableException("SubSolution");
        }
        private void readObject (ObjectInputStream in) throws IOException, ClassNotFoundException {
            throw new NotSerializableException("SubSolution");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // код для проверки
        /*
        SubSolution sSave = new SubSolution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        out.writeObject(sSave);
        out.close();
        SubSolution sLoad = (SubSolution) in.readObject();
        in.close();
        System.out.println(sLoad.equals(sSave));
        */
    }
}
