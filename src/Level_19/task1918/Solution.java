package Level_19.task1918;

/*
    Парсер файла с HTML кодом с использованием библиотеки Jsoup.
    Работает для всех возможных случаев, но не выдержал борьбу с валидатором.
*/

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();                                            // считываем имя файла
        br.close();
        BufferedReader fr = new BufferedReader(new FileReader(fileName));
        List<String> list = fr.lines().collect(Collectors.toList());                // построчно заполняем list
        fr.close();
        String s = list.stream()
                .map(x->new StringBuilder().append(x))                              // склеиваем в одну строку
                .collect(Collectors.joining());

        String tag = "span";
        Document html = Jsoup.parse(s);                                             // парсим строку с помощью Jsoup
        Elements tags = html.getElementsByTag(tag);
        tags.forEach(System.out::println);                                          // построчно выводим результат

    }
}



