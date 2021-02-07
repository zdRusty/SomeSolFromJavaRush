package Level_25.task2515;

public class SpaceShip extends BaseObject{

    private double dx=0;

    public SpaceShip(double x, double y) {
        super(x, y, 3);
    }

    public void moveLeft(){
        dx=-1;
    }

    public void moveRight(){
        dx=1;
    }

    public void draw(Canvas canvas) {
    }

    public void move() {
        this.setX(this.getX()+dx);
        checkBorders(0,Space.game.getWidth(), 0, Space.game.getHeight());
    }

    public void fire(){
        Rocket rocket1 = new Rocket(this.getX()+2,this.getY());
        Rocket rocket2 = new Rocket(this.getX()-2,this.getY());
        Space.game.getRockets().add(rocket1);
        Space.game.getRockets().add(rocket2);
    }
}
