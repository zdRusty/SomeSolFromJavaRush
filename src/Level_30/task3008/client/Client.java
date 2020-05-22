package Level_30.task3008.client;

import Level_30.task3008.Connection;
import Level_30.task3008.ConsoleHelper;
import Level_30.task3008.Message;
import Level_30.task3008.MessageType;

import java.io.IOException;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) {
        new Client().run();
    }

    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                wait();
                if (clientConnected){
                    ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
                    String message = ConsoleHelper.readString();
                    while (clientConnected&&!message.equals("exit")){
                        if (shouldSendTextFromConsole()){
                            sendTextMessage(message);
                        }
                        message = ConsoleHelper.readString();
                    }
                }
                else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Error. Program will be close.");
                return;
            }
        }
    }

    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Enter server address.");
        return ConsoleHelper.readString();
    }

    protected int getServerPort(){
        ConsoleHelper.writeMessage("Enter server port.");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Enter username.");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT,text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Failed to send message");
            clientConnected=false;
        }
    }

    public class SocketThread extends Thread{

    }
}
