package Level_25.task2515;

public class Bomb extends BaseObject{

    public Bomb(double x, double y) {
        super(x, y, 1);
    }

    public void move() {
        this.setY(this.getY()+1);
    }

    public void draw(Canvas canvas) {
        canvas.setPoint(this.getX(),this.getY(),'B');
    }
}
