package level_30.task3008.client;

import level_30.task3008.Connection;
import level_30.task3008.ConsoleHelper;
import level_30.task3008.Message;
import level_30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

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

        public void run(){
            try {
                String serverAddress = getServerAddress();
                int serverPort = getServerPort();
                Socket socket = new Socket(serverAddress,serverPort);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }

        protected void clientHandshake() throws IOException,ClassNotFoundException{
            while (true){
                Message message = connection.receive();
                if(message.getType()==MessageType.NAME_REQUEST){
                    connection.send(new Message(MessageType.USER_NAME,getUserName()));
                }
                else if(message.getType()==MessageType.NAME_ACCEPTED){
                    notifyConnectionStatusChanged(true);
                    break;
                }
                else throw new IOException("UnexpectedMessageType");
            }
        }

        protected void clientMainLoop() throws IOException,ClassNotFoundException{
            while (true){
                Message message = connection.receive();
                if(message.getType()==MessageType.TEXT) processIncomingMessage(message.getData());
                else if(message.getType()==MessageType.USER_ADDED) informAboutAddingNewUser(message.getData());
                else if(message.getType()==MessageType.USER_REMOVED) informAboutDeletingNewUser(message.getData());
                else throw new IOException("UnexpectedMessageType");
            }
        }

        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName+" adding to conversation.");
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName+" leave out.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected){
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }
    }
}
