package Level_20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
    Знакомство с properties

    В методе fillInPropertiesMap считайте имя файла с консоли и заполни карту properties данными из файла.
    Реализуй логику записи в файл и чтения из файла для карты properties.


    Требования:
    1.	Метод fillInPropertiesMap должен считывать данные с консоли.
    2.	Метод fillInPropertiesMap должен создавать FileInputStream, передавая считанную строку в качестве параметра.
    3.	Метод fillInPropertiesMap должен вызывать метод load передавая только что созданный FileInputStream в качестве параметра.
    4.	Метод save должен сохранять карту properties в полученный в качестве параметра объект типа OutputStream.
    5.	Метод load должен восстанавливать состояние карты properties из полученного в качестве параметра объекта типа InputStream.

*/
    public class Solution {
        public static Map<String, String> properties = new HashMap<>();

        public void fillInPropertiesMap(){
            //implement this method
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
                FileInputStream fis = new FileInputStream(br.readLine());
                load(fis);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method
            Properties prop = new Properties();
            prop.putAll(properties);
            prop.store(outputStream,"");
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method
            Properties propLoad = new Properties();
            propLoad.load(inputStream);
            for(String x: propLoad.stringPropertyNames()){
                properties.put(x,propLoad.getProperty(x));
            }
        }

        public static void main(String[] args){
            //  код для проверки
            /*
            new Solution().fillInPropertiesMap();
            for(Map.Entry<String,String> x: properties.entrySet()){
                System.out.println(x.getKey()+" "+x.getValue());
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
                FileOutputStream outputStream = new FileOutputStream(br.readLine());
                new Solution().save(outputStream);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            */

        }
    }

