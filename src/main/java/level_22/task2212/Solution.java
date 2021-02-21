package level_22.task2212;

/*
Проверка номера телефона
*/

/*
    created by zdRusty
    Решал от последнего условия к первому.
    Разбил на две части: - общие требования;
                         - требования по количеству цифр.
 */

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        return telNumber!=null
                &&telNumber.matches("[0-9+]*(\\([0-9]{3}\\))*[0-9]+[-]??[0-9]+[-]??[0-9]+")
                &&telNumber.matches("" +
                "^\\+(\\d[-()]??){11}\\d|" +
                "^\\d(\\d[-()]??){8}\\d|" +
                "^\\((\\d[-)]??){9}\\d");
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("12(345)6-78-90"));
    }
}
