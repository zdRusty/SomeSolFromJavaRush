package Level_25.task2515;

public class Canvas {
    private int width;
    private int height;
    private char[][] matrix;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new char[height][width];
    }

    public void setPoint(double x, double y, char c) {
        int iX = (int) Math.round(x);
        int iY = (int) Math.round(y);

        if (0 <= iX && iX < matrix[0].length &&
                0 <= iY && iY < matrix.length) matrix[iY][iX] = c;

    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[j][i]!=0) setPoint(x+i,y+j,c);
            }
        }
    }

    public void clear(){
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                setPoint(i,j,' ');
            }
        }
    }

    public void print(){
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}