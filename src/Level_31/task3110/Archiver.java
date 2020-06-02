package Level_31.task3110;

import Level_31.task3110.exception.WrongZipFileException;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Archiver {
    public static void main(String[] args) {
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
//            System.out.println("Введите путь к архиву");
//            Path archivePath = Paths.get(br.readLine());
//            ZipFileManager zipArchive = new ZipFileManager(archivePath);
//
//            System.out.println("Введите путь к файлу, который будет архивирован");
//            Path filePath = Paths.get(br.readLine());
//            zipArchive.createZip(filePath);
//            new ExitCommand().execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
                if(operation==Operation.EXIT) break;
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        } while (operation!=Operation.EXIT);
    }

    public static Operation askOperation() throws IOException {
        Map<Integer,String> map = new TreeMap<>();
        map.put(Operation.CREATE.ordinal(),"упаковать файлы в архив");
        map.put(Operation.CONTENT.ordinal(),"просмотреть содеджимое архива");
        map.put(Operation.ADD.ordinal(),"добавить файл в архив");
        map.put(Operation.EXTRACT.ordinal(),"распаковать архив");
        map.put(Operation.REMOVE.ordinal(),"удалить файл из архива");
        map.put(Operation.EXIT.ordinal(),"выход");
        for(Map.Entry<Integer,String> x: map.entrySet()){
            System.out.println(x.getKey()+" - "+x.getValue());
        }

        ConsoleHelper.writeMessage("Выберите команду. Укажите ее порядковый номер");
        return Operation.values()[ConsoleHelper.readInt()];
    }
}
