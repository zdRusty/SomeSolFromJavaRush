package Level_25.task2515;

public class Rocket extends BaseObject{

    public Rocket(double x, double y) {
        super(x, y, 1);
    }

    public void move() {
        this.setY(this.getY()-1);
    }

    public void draw(Canvas canvas) {
        canvas.setPoint(this.getX(),this.getY(),'R');
    }
}
