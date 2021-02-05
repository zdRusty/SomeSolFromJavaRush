package Level_21.task2101;

/*
Определяем адрес сети
*/
public class Solution2 {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] result = new byte[4];
        for(int i=0;i<ip.length;i++){
            result[i]= (byte) (ip[i]&mask[i]);
        }
        return result;
    }

    public static void print(byte[] bytes) {

        /*это решение красивее (подсмотрено, доработано)
        так же используем "Integer.toBinaryString()"
        чтобы сохранить дополняющие нули перед числом:
        1) используем побитовое "&" с максимальным числом для типа "byte" - 255(в 16-ной СС - 0xFF, в 2-ной СС 11111111)
        2) в результате получаем это же число с дополняющими нулями
        3) прибавляем число 256 выходящее за диапазон (в 16-ной СС - 0x100, в 2-ной СС 100000000)
        4) в результате получаем это же число с дополняющими нулями и единицей в начале
        5) переводим в строку результат с лишней единицей
        6) удаляем первый элемент из строки
        */

        for(int i=0;i<bytes.length;i++){
            if (i!=bytes.length-1) System.out.print(Integer.toBinaryString((bytes[i] & 0xFF) + 0x100).substring(1)+" ");
            else System.out.print(Integer.toBinaryString((bytes[i] & 0xFF) + 0x100).substring(1)+"\n");
        }
    }
}
