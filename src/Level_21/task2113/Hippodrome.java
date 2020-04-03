package Level_21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;
    private List<Horse> horses = new ArrayList<>();

    public static void main(String[] args) {
        game = new Hippodrome();
        Horse h1 = new Horse("h1",3,0);
        Horse h2 = new Horse("h2",3,0);
        Horse h3 = new Horse("h3",3,0);
        game.horses.add(h1);
        game.horses.add(h2);
        game.horses.add(h3);
    }

    public List<Horse> getHorses() {
        return horses;
    }
}
