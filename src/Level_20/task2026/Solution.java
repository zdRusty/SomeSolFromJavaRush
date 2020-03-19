package Level_20.task2026;

import java.util.ArrayList;
import java.util.List;

/*
    Алгоритмы-прямоугольники
*/

public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");

    }

    public static int getRectangleCount(byte[][] a) {
        List<Rect> list = new ArrayList<>();
        while (true){
            Rect rect = Rect.getRect(a);    //находим прямоугольник
            if(rect==null) break;           //работаем пока массив не пустой
            list.add(rect);                 //добавляем в прямоугольник в лист
            Rect.removeRect(a,rect);        //удаляем приямоугольник из массива
        }
        return list.size();
    }
}
