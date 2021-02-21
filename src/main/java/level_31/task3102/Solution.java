package level_31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/*
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new ArrayList<>();
        File fileRoot = new File(root);
        List<File> list = new ArrayList<>(Arrays.asList(fileRoot.listFiles()));
        Queue<File> files = new ArrayDeque<>();
        files.add(fileRoot);
        while (!files.isEmpty()) {
            if (files.peek().isDirectory()) {
                for (File x : files.peek().listFiles()) {
                    files.offer(x);
                }
            } else {
                list.add(files.peek());
            }
            files.poll();
        }

        for(File x: list){
            if(!x.isDirectory()&&!result.contains(x.getAbsolutePath())) result.add(x.getAbsolutePath());
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            getFileTree("C:\\Tut").forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}