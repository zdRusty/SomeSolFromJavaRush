package level_23.task05;

/*
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] sols = new Solution[2];
        Solution sol1 = new Solution();
        Solution sol2 = new Solution();
        sol1.innerClasses=new InnerClass[]{};
        sol2.innerClasses=new InnerClass[]{};
        sols[0]=sol1;
        sols[1]=sol2;
        return sols;
    }

    public static void main(String[] args) {

    }
}