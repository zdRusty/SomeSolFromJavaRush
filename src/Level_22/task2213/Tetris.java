package Level_22.task2213;

public class Tetris {

    public static Tetris game;

    private Field field;
    private Figure figure;

    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public static void main(String[] args) {
        game = new Tetris();
        game.run();
    }

    public void run(){}

    public void step(){}
}
