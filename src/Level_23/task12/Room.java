package Level_23.task12;

import java.awt.event.KeyEvent;

public class Room {
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;

    public static Room game;

    public Room(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
        game = this;
    }

    public Snake getSnake() {
        return snake;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    /**
     * Основной цикл программы.
     * Тут происходят все важные действия
     */
    public void run() {
        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //пока змея жива
        while (snake.isAlive()) {
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если равно символу 'q' - выйти из игры.
                if (event.getKeyChar() == 'q') return;

                //Если "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(SnakeDirection.LEFT);
                    //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.setDirection(SnakeDirection.RIGHT);
                    //Если "стрелка вверх" - сдвинуть фигурку вверх
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.setDirection(SnakeDirection.UP);
                    //Если "стрелка вниз" - сдвинуть фигурку вниз
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.setDirection(SnakeDirection.DOWN);
            }

            snake.move();   //двигаем змею
            print();        //отображаем текущее состояние игры
            sleep();        //пауза между ходами
        }

        System.out.println("Game Over!");
    }

    public void print() {
        //Создаем массив, куда будем "рисовать" текущее состояние игры
        //Рисуем все кусочки змеи
        //Рисуем мышь
        //Выводим все это на экран

        char[][] field = new char[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == snake.getSections().get(0).getX() && y == snake.getSections().get(0).getY())
                    field[y][x] = 'X';
                else if (x == mouse.getX() && y == mouse.getY())
                    field[y][x] = '^';
                else field[y][x] = '.';
            }
        }

        for(int s=1;s<snake.getSections().size();s++) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (x == snake.getSections().get(s).getX() && y == snake.getSections().get(s).getY())
                        field[y][x] = 'x';
                }
            }
        }

        for(char[] y: field){
            for(char x: y){
                System.out.print(x);
            }
            System.out.println();
        }
    }

    public void eatMouse() {
        createMouse();
    }

    public void createMouse() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);

        mouse = new Mouse(x, y);
    }

    public static void main(String[] args) {
        game = new Room(20, 20, new Snake(10, 10));
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
    }

    public void sleep(){
        int level = snake.getSections().size();
        int[] delays = new int[15];
        for(int i=0;i<15;i++){
            delays[i]=500-20*i;
        }
        try {
            if(level>0&&level<=15) Thread.sleep(delays[level-1]);
            if(level>15) Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
