package level_20.task2022;

import java.io.*;

/*
    Переопределение сериализации в потоке.
*/
public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    public String fileName;

    public Solution(String fileName) throws IOException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName,true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception {
        // код для проверки
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileNameForString = br.readLine();
        String fileNameForObject = br.readLine();
        br.close();
        Solution sol = new Solution(fileNameForString);
        sol.writeObject("some");

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileNameForObject));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileNameForObject));
        out.writeObject(sol);
        out.close();

        Solution loadSol = (Solution) in.readObject();
        in.close();
        loadSol.writeObject("someNew");
        sol.close();
    }
}
