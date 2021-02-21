package level_21.task2101;

import java.util.ArrayList;
import java.util.List;

/*
Определяем адрес сети
*/
public class Solution1 {
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
        List<String> list = new ArrayList<>();
        List<String> bin = new ArrayList<>();
        for (byte aByte : bytes) {
            list.add(Integer.toBinaryString(aByte));
        }

        for(String x: list){
            StringBuilder sb = new StringBuilder();
            if (x.length()<8){
                int zero = 8-x.length();
                sb.append("0".repeat(Math.max(0, zero)));
                sb.append(x);
                bin.add(sb.toString());
            }
            else
                bin.add(x.substring(x.length()-8));
        }

        for(int i=0;i<bin.size();i++){
            if (i!=bin.size()-1) System.out.print(bin.get(i)+" ");
            else System.out.print(bin.get(i)+"\n");
        }
    }
}
