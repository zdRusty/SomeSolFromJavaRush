package Level_31.task3101;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/

public class Solution {
    public static void main(String[] args) {
        File resultFileAbsolutePath = new File(args[1]);
        File path = new File(args[0]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        List<File> list = new ArrayList<>();
        exploreDir(path,list);
        list.sort(Comparator.comparing(File::getName));
        try (FileWriter fileWriter = new FileWriter(allFilesContent)) {
            for (File x : list) {
                FileReader fileReader = new FileReader(x);
                while (fileReader.ready()) {
                    fileWriter.write(fileReader.read());
                }
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exploreDir (File file, List<File> xFiles){
        if(file.isDirectory()){
            for(File x: file.listFiles()){
                if(x.isDirectory()){
                    exploreDir(x,xFiles);
                }else{
                    if(x.length()<=50) xFiles.add(x);
                }
            }
        }
    }
}
