package Level_23.task12;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;

    public Snake(int x, int y){
        sections = new ArrayList<>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
    }

    public void checkBorders (SnakeSection head){
        int[][] field = new int[Room.game.getHeight()][Room.game.getWidth()];
        try{
            field[head.getX()][head.getY()]='X';
        } catch (IndexOutOfBoundsException e){
            isAlive=false;
        }
    }
    public void checkBody (SnakeSection head){
        if (sections.contains(head)) isAlive = false;
    }

    public List<SnakeSection> getSections() {
        return sections;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public int getX(){
        return sections.get(0).getX();
    }

    public int getY(){
        return sections.get(0).getY();
    }

    public void move(){
        if (isAlive) {
            switch (direction){
                case UP:    move(0,-1);
                case RIGHT: move(1,0);
                case DOWN:  move(0,1);
                case LEFT:  move(-1,0);
            }
        }
    }

    public void move(int dx, int dy){
        SnakeSection head = new SnakeSection(sections.get(0).getX()+dx,sections.get(0).getY()+dy);
        checkBorders(head);
        checkBody(head);
        sections.add(0,head);
        if(head.getX()==Room.game.getMouse().getX()&&head.getY()==Room.game.getMouse().getY()) Room.game.eatMouse();
        else sections.remove(sections.size()-1);
    }


}
