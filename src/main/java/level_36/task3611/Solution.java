package level_36.task3611;

import java.util.Set;
import java.util.TreeSet;

/*
Сколько у человека потенциальных друзей?
*/

public class Solution {

    private Set<Integer> set = new TreeSet<>();
    private Set<Integer> out = new TreeSet<>();
    private Set<Integer> temp = new TreeSet<>();

    private boolean[][] humanRelationships;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.humanRelationships = generateRelationships();

        Set<Integer> allFriendsAndPotentialFriends = solution.getAllFriendsAndPotentialFriends(4, 2);
        System.out.println(allFriendsAndPotentialFriends);                              // Expected: [0, 1, 2, 3, 5, 7]
        Set<Integer> potentialFriends = solution.removeFriendsFromSet(allFriendsAndPotentialFriends, 4);
        System.out.println(potentialFriends);                                           // Expected: [2, 5, 7]
    }

    public Set<Integer> getAllFriendsAndPotentialFriends(int index, int deep) {

        if (deep>0) runNet(index);
        while (deep>0) {
            set.addAll(temp);
            for (Integer x : set) {
                if (!out.contains(x)) {
                    runNet(x);
                }
            }
            deep--;
        }
        return set;//напишите тут ваш код
    }

    private void runNet(int index){
        out.add(index);
        for (int i = 0; i < index; i++) {
            if (humanRelationships[index][i]) {
                if (!out.contains(i)){
                    temp.add(i);
                }
            }
        }

        for (int i = index + 1; i < humanRelationships.length; i++) {
            if (humanRelationships[i][index]) {
                if (!out.contains(i)){
                    temp.add(i);
                }
            }
        }
    }

    // Remove from the set the people with whom you already have a relationship
    public Set<Integer> removeFriendsFromSet(Set<Integer> set, int index) {
        for (int i = 0; i < humanRelationships.length; i++) {
            if ((i < index) && (index < humanRelationships.length) && humanRelationships[index][i]) {
                set.remove(i);
            } else if ((i > index) && humanRelationships[i][index]) {
                set.remove(i);
            }
        }
        return set;
    }

    // Return test data
    private static boolean[][] generateRelationships() {
        return new boolean[][]{
                {true},                                                                 //0
                {true,  true},                                                          //1
                {false, true,  true},                                                   //2
                {false, false, false, true},                                            //3
                {true,  true,  false, true,  true},                                     //4
                {true,  false, true,  false, false, true},                              //5
                {false, false, false, false, false, true,  true},                       //6
                {false, false, false, true,  false, false, false, true}                 //7
//                 0      1      2      3      4      5      6     7
        };
    }
}