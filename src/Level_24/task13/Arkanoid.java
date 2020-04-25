package Level_24.task13;

import java.util.List;

public class Arkanoid {
    private int width;
    private int height;
    private Ball ball;
    private Stand stand;
    private List<Brick> bricks;
    private boolean isGameOver;
    public static Arkanoid game;

    public Arkanoid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static void main(String[] args) {

    }

    public void run(){

    }

    public void move(){
        ball.move();
        stand.move();
    }

    public void draw(Canvas canvas){
        ball.draw(canvas);
        stand.draw(canvas);
        bricks.forEach(x->x.draw(canvas));
    }

    public void checkBricksBump(){
        for(Brick x: bricks){
            if(x.isIntersec(ball)){
                double angle = Math.random()*360;
                ball.setDirection(angle);
                bricks.remove(x);
                break;
            }
        }
    }

    public void checkStandBump(){
        if(stand.isIntersec(ball)){
            double angle = 90+20*(Math.random()-0.5);
            ball.setDirection(angle);
        }
    }

    public void checkEndGame(){
        if(ball.getY()>game.height) isGameOver=true;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(List<Brick> bricks) {
        this.bricks = bricks;
    }
}
