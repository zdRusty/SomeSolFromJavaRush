package level_24.task04;

/*
Рефакторинг Rectangle
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static double getHeight(HasHeight rectangle) {
        return rectangle.getHeight();
    }

    public static double getWidth(HasWidth rectangle) {
        return rectangle.getWidth();
    }


    public static class Rectangle{
        private Point point1;
        private Point point2;

        public Rectangle(double x1, double y1, double x2, double y2) {
            point1 = new Point(x1, y1);
            point2 = new Point(x2, y2);
        }

        public HasHeight castToHasHeight() {
            HasHeight hasHeight = new HasHeight() {
                @Override
                public double getHeight() {
                    return Math.abs(point1.getY() - point2.getY());
                }
            };
            return hasHeight;
        }

        public HasWidth castToHasWidth() {
            HasWidth hasWidth = new HasWidth() {
                @Override
                public double getWidth() {
                    return Math.abs(point1.getX() - point2.getX());
                }
            };
            return hasWidth;
        }
    }
}