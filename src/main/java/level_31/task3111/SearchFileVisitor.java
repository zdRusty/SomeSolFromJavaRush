package level_31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfContent = "";
    private String partOfName = "";
    private int minSize = 0;
    private int maxSize = Integer.MAX_VALUE;
    private final List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        String str = Files.lines(file).collect(Collectors.joining());

        if (attrs.isRegularFile() &&
                file.getFileName().toString().contains(partOfName) &&
                str.contains(partOfContent) &&
                content.length > minSize &&
                content.length < maxSize) {
            foundFiles.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public void setPartOfName(String name) {
        partOfName = name;
    }

    public void setPartOfContent(String content) {
        partOfContent = content;
    }

    public void setMinSize(int size) {
        minSize = size;
    }

    public void setMaxSize(int size) {
        maxSize = size;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
