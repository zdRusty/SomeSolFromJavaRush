package Level_24.task07;

import java.util.List;

/*
Реализация интерфейса используя локальный класс
*/
public class Solution {
    public static void main(String[] args) {
        List<Pet> pet = Util.getPets();
        List<Sayable> pets = Util.convertPetToSayable(pet);
        Util.printDialog(pets);
    }
}
