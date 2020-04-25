package Level_24.task13;

public class Canvas {
    private int width;
    private int height;
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new char[height+2][width+2];
    }

    public void setPoint (double x, double y, char c){
        int ix = (int) Math.round(x);
        int iy = (int) Math.round(y);
        if(ix>=0 && iy>=0 && ix<matrix[0].length && iy<matrix.length) matrix[iy][ix]=c;
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]!=0) setPoint(x+j,y+i,c);
            }
        }
    }

    public void clear(){
        matrix = new char[height+2][width+2];
    }

    public void print(){
        for(char[] x: matrix){
            for(char y: x){
                System.out.print(y);
            }
            System.out.println();
        }
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

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }
}
