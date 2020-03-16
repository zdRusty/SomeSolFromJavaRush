package Level_20.task2014;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
Serializable Solution
*/
public class Solution implements Serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(new Solution(4));

        // код для проверки
        /*
        Solution sol = new Solution(5);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        out.writeObject(sol);
        out.close();
        Solution loadSol = new Solution(10);
        loadSol = (Solution) in.readObject();
        in.close();
        System.out.println(loadSol.string.equals(sol.string));
        */
    }

    private final transient String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}