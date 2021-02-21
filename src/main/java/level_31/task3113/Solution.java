package level_31.task3113;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/*
Что внутри папки?
*/

public class Solution {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String path = br.readLine();
        Path dir = Paths.get(path);
        if(Files.isDirectory(dir)){
            showInfo(dir);
        }
        else {
            System.out.println(path+" - не папка");
        }
    }

    public static void showInfo(Path dir) throws IOException {
        MyVisitor visitor = new MyVisitor();
        Files.walkFileTree(dir,visitor);
        System.out.println("Всего папок - "+visitor.getDirCount());
        System.out.println("Всего файлов - "+visitor.getFileCount());
        System.out.println("Общий размер - "+visitor.getUsedSpace());
    }
}

class MyVisitor extends SimpleFileVisitor<Path>{

    private AtomicInteger dirCount=new AtomicInteger(-1);
    private AtomicInteger fileCount=new AtomicInteger(0);
    private AtomicLong usedSpace=new AtomicLong(0);

    public AtomicInteger getDirCount() {
        return dirCount;
    }

    public AtomicInteger getFileCount() {
        return fileCount;
    }

    public AtomicLong getUsedSpace() {
        return usedSpace;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        fileCount.addAndGet(1);
        usedSpace.addAndGet(attrs.size());

        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        dirCount.addAndGet(1);
        return super.preVisitDirectory(dir, attrs);
    }
}