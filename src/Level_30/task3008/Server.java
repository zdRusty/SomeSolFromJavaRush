package Level_30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String,Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args){
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())){
            ConsoleHelper.writeMessage("Server started");
            while (true){
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendBroadcastMessage(Message message){
        try {
            for(Map.Entry<String,Connection> x: connectionMap.entrySet()){
                x.getValue().send(message);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Message doesn't send");
        }
    }

    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }
    }
}
