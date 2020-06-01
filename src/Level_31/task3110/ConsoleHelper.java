package Level_31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString(){
        String result ="";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            result = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int readInt(){
        return Integer.parseInt(readString());
    }
}
