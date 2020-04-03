package Level_21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;
    private List<Horse> horses = new ArrayList<>();

    public Hippodrome(){
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

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

    public void run(){
        try {
            for(int i=0;i<100;i++){
                move();
                print();
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void move(){
        getHorses().forEach(Horse::move);
    }

    public void print(){

    }
}
