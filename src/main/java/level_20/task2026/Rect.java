package level_20.task2026;

import java.util.ArrayList;
import java.util.List;

public class Rect {
    private Coord top1;
    private Coord top2;
    private Coord low1;
    private Coord low2;

    public Rect(Coord top1, Coord top2, Coord low1, Coord low2){
        this.top1 = top1;
        this.top2 = top2;
        this.low1 = low1;
        this.low2 = low2;
    }

    public static Rect getRect (byte[][] a){
        Coord top1;
        Coord top2;
        Coord low1;
        Coord low2;
        List<Integer> wide = new ArrayList<>();
        List<Integer> high = new ArrayList<>();

        int z=0;
        for(int x=0;x<a[0].length;x++){
            if(x!=a[0].length-1){
                if(a[z][x]==1&&a[z][x+1]!=0) {
                    wide.add(x);
                    high.add(z);
                }
                if(a[z][x]==1&&a[z][x+1]==0) {
                    wide.add(x);
                    high.add(z);
                    break;
                }
            }
            else if(a[z][x]==1){
                wide.add(x);
                high.add(z);
                break;
            }

            if(x==a[0].length-1&&z==a[0].length-1) break;

            if (wide.size()==0&&x==a[0].length-1){
                z++;
                x=-1;
            }
        }
        if(wide.size()==0||high.size()==0) return null;

        top1 = new Coord(wide.get(0),high.get(0));
        top2 = new Coord(wide.get(wide.size()-1),high.get(0));

        for(int y=high.get(0);y<a.length;y++){
            int x=wide.get(0);
            if(y!=a[y].length-1){
                if(a[y][x]==1&&a[y+1][x]!=0) {
                    high.add(y);
                }
                if(a[y][x]==1&&a[y+1][x]==0) {
                    high.add(y);
                    break;
                }
            }
            else if(a[y][x]==1){
                high.add(y);
                break;
            }
        }

        low1 = new Coord(wide.get(0),high.get(high.size()-1));
        low2 = new Coord(wide.get(wide.size()-1),high.get(high.size()-1));

        return new Rect(top1, top2, low1, low2);
    }

    public static void removeRect (byte[][] a, Rect rect){
        for(int y=rect.top1.y;y<=rect.low1.y;y++){
            for(int x=rect.top1.x;x<=rect.top2.x;x++){
                a[y][x]=0;
            }
        }
    }

    @Override
    public String toString() {
        int wide = top2.x-top1.x;
        int high = low1.y-top1.y;
        return "ширина: "+wide+"; "+"высота: "+high;
    }

    public static class Coord{
        int x;
        int y;

        public Coord(int x, int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString() {
            return x+"; "+y;
        }
    }
}
