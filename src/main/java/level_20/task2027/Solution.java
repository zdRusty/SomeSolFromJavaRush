package level_20.task2027;

import java.util.*;

/*
Кроссворд
Вывод соответствует, протестировано на разных вариантах входных данных
Валидатор не принял это решение
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
         /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        Map<Coord,Character> map = new HashMap<>();

        for(int y=0;y<crossword.length;y++){
            for(int x=0;x<crossword[y].length;x++){
                map.put(new Coord(x,y),(char)crossword[y][x]);
            }
        }

        for(String s : words){
            Set<Coord> setStart = new HashSet<>();
            Set<Coord> setEnd = new HashSet<>();
            Set<PairCoords> setPair = new HashSet<>();
            Set<PairCoords> result = new HashSet<>();
            char[] c = s.toCharArray();
            for(Map.Entry<Coord, Character> start: map.entrySet()){
                if (start.getValue().equals(c[0])) setStart.add(new Coord(start.getKey().x,start.getKey().y));
            }
            for(Map.Entry<Coord, Character> end: map.entrySet()){
                if (end.getValue().equals(c[c.length-1])) setEnd.add(new Coord(end.getKey().x,end.getKey().y));
            }
            for(Coord start: setStart){
                for(Coord end: setEnd){
                    setPair.add(new PairCoords(start,end));
                }
            }
            for(PairCoords e: setPair){
                if(blade(crossword,e,c)) result.add(e);
            }
            for(PairCoords e: result){
                Word word = new Word(s);
                word.setStartPoint(e.start.x,e.start.y);
                word.setEndPoint(e.end.x,e.end.y);
                list.add(word);
            }
        }
        return list;
    }

    public static boolean blade (int[][] cross, PairCoords e, char[] c) {
        int x1 = e.start.x;
        int y1 = e.start.y;
        int x2 = e.end.x;
        int y2 = e.end.y;
        StringBuilder sb = new StringBuilder();

        if (x1 == x2 && Math.abs(y1 - y2) == c.length - 1) {
            if (y1 < y2) {
                for (int i = 0; i < c.length; i++) {
                    sb.append((char) cross[y1][x1]);
                    y1++;
                }
            } else {
                for (int i = 0; i < c.length; i++) {
                    sb.append((char) cross[y1][x1]);
                    y1--;
                }
            }
        }

        if (y1 == y2 && Math.abs(x1 - x2) == c.length - 1) {
            if (x1 < x2) {
                for (int i = 0; i < c.length; i++) {
                    sb.append((char) cross[y1][x1]);
                    x1++;
                }
            } else {
                for (int i = 0; i < c.length; i++) {
                    sb.append((char) cross[y1][x1]);
                    x1--;
                }
            }
        }

        if (Math.abs(x1 - x2) == c.length - 1 && Math.abs(y1 - y2) == c.length - 1) {
            if (x1 < x2) {
                if (y1 < y2) {
                    for (int i = 0; i < c.length; i++) {
                        sb.append((char) cross[y1][x1]);
                        x1++;
                        y1++;
                    }
                } else {
                    sb.append((char) cross[y1][x1]);
                    for (int i = 0; i < c.length; i++) {
                        x1++;
                        y1--;
                    }
                }
            } else {
                if (y1 < y2) {
                    for (int i = 0; i < c.length; i++) {
                        sb.append((char) cross[y1][x1]);
                        x1--;
                        y1++;
                    }
                } else {
                    for (int i = 0; i < c.length; i++) {
                        sb.append((char) cross[y1][x1]);
                        x1--;
                        y1--;
                    }
                }
            }
        }

        return (sb.toString().equals(String.valueOf(c)));
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }

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
            return String.format("(%d,%d)",x,y);
        }
    }
    public static class PairCoords{
        Coord start;
        Coord end;

        public PairCoords(Coord start, Coord end){
            this.start=start;
            this.end=end;
        }

        @Override
        public String toString() {
            return start+" - "+end;
        }
    }

}