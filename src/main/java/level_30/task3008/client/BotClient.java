package level_30.task3008.client;

import level_30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class BotClient extends Client{

    public static void main(String[] args) {
        new BotClient().run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_"+(int)(Math.random()*100);
    }

    public class BotSocketThread extends SocketThread{

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {

            SimpleDateFormat dataF = new SimpleDateFormat("d.MM.yyyy", Locale.ENGLISH);
            SimpleDateFormat dayF = new SimpleDateFormat("d", Locale.ENGLISH);
            SimpleDateFormat monthF = new SimpleDateFormat("MMMM", Locale.ENGLISH);
            SimpleDateFormat yearF = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            SimpleDateFormat timeF = new SimpleDateFormat("H:mm:ss", Locale.ENGLISH);
            SimpleDateFormat hourF = new SimpleDateFormat("H", Locale.ENGLISH);
            SimpleDateFormat minutesF = new SimpleDateFormat("m", Locale.ENGLISH);
            SimpleDateFormat secondsF = new SimpleDateFormat("s", Locale.ENGLISH);

            ConsoleHelper.writeMessage(message);

            if(message!=null&&message.contains(":")) {
                String[] data = message.split(": ");
                if(data.length>1){
                    String userName = data[0];
                    String userText = data[1];
                    String response = "Информация для "+userName+": ";
                    switch (userText) {
                        case ("дата"): sendTextMessage(response + dataF.format(new GregorianCalendar().getTime())); break;
                        case ("день"): sendTextMessage(response + dayF.format(new GregorianCalendar().getTime())); break;
                        case ("месяц"): sendTextMessage(response + monthF.format(new GregorianCalendar().getTime())); break;
                        case ("год"): sendTextMessage(response + yearF.format(new GregorianCalendar().getTime())); break;
                        case ("время"): sendTextMessage(response + timeF.format(new GregorianCalendar().getTime())); break;
                        case ("час"): sendTextMessage(response + hourF.format(new GregorianCalendar().getTime())); break;
                        case ("минуты"): sendTextMessage(response + minutesF.format(new GregorianCalendar().getTime())); break;
                        case ("секунды"): sendTextMessage(response + secondsF.format(new GregorianCalendar().getTime())); break;
                    }
                }
            }
        }
    }
}
