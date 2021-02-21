package level_35.task3502;

/*
Знакомство с дженериками
*/

import java.util.List;

public class Solution<L extends List<Solution.SomeClass>> {
    public static class SomeClass <N extends Number> {
    }

    public static void main(String[] args) {

    }
}