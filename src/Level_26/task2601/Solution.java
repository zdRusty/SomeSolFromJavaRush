package Level_26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        int med = median(array);
        Arrays.sort(array, Comparator.comparingInt(o -> Math.abs(med - o)));
        return array;
    }

    private static int median (Integer[] arr){
        Arrays.sort(arr);
        if (arr.length%2!=0) return arr[(arr.length-1)/2];
        else return (arr[((arr.length-1)/2)]+arr[(arr.length+1)/2])/2;
    }
}