package level_33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/*
Десериализация JSON объекта
*/

public class Solution {

    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        File jsonFile = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonFile,clazz);
    }

    public static void main(String[] args) throws IOException {
    }
}