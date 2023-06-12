import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String ransomNote = "dooo";
        String magazine = "doo";

        System.out.println(new Solution().canConstruct(ransomNote, magazine));
    }
}

//MY SOLUTION WORKS BUT IS O(3n). only beats 12% in time efficiency and 20 in memory.
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> ransomMap = new HashMap<>();
        Map<Character, Integer> magazineMap = new HashMap<>();

        for (int i = 0; i < ransomNote.length(); i++) {
            if (ransomMap.containsKey(ransomNote.charAt(i))) {
                ransomMap.put(ransomNote.charAt(i), ransomMap.get(ransomNote.charAt(i)) + 1);
            } else {
                ransomMap.put(ransomNote.charAt(i), 1);
            }

        }

        for (int i = 0; i < magazine.length(); i++) {
            if (magazineMap.containsKey(magazine.charAt(i))) {
                magazineMap.put(magazine.charAt(i), magazineMap.get(magazine.charAt(i)) + 1);
            } else {
                magazineMap.put(magazine.charAt(i), 1);
            }
        }

        for (char letter : ransomMap.keySet()) {

            if ((!magazineMap.containsKey(letter)) || ransomMap.get(letter) > magazineMap.get(letter)) {
                return false;
            }// i originally had the if statement set to a condition that would return true else returned false. but i was getting false true's
            // and thought it may be sending back true before it gets to a false condition
        }
             return true;
    }
}
// this is clever but somehow had worse runtime and memory than mine even though mine conntained 2 maps and 3 for loops...
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map= new HashMap<>();

        for(int i=0;i<magazine.length();i++){ // here, hashhmap created for magazine just like mine
            if(map.containsKey(magazine.charAt(i)))
                map.put(magazine.charAt(i), map.get(magazine.charAt(i))+1);
            else map.put(magazine.charAt(i),1);
        }

        for(int i=0;i<ransomNote.length();i++){ // instead of creating aa second map, this says, if note contains the letter,
            // decrement the value. if the value ever gets down to 0, that means that more of that letter is needed by
            // ransom than magazine can provide and it returns false
            if(map.containsKey(ransomNote.charAt(i)) && map.get(ransomNote.charAt(i))>0){
                map.put(ransomNote.charAt(i), map.get(ransomNote.charAt(i))-1);
            }else return false;
        }

        return true;
    }
}

// here is the top solution
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] alphabets_counter = new int[26]; // an array of 26 values creates, not 26 indexes

        for (char c : magazine.toCharArray())
            alphabets_counter[c-'a']++; // 'c' is the char variable. ASCII value of 'a' == 97.
        // suppose 'c' = d. ASCII of d is 100. 100-97 = 3. 3 is also the appropriate index for d in the alphabeet char
        // array. a is index 0, z index 25, etc. So starting at a base value of 0 for all indexes, each time the appropriate
        // index of the char assigned to variable 'c' is encountered, its index value is incremented by 1 for the magazine string

        for (char c : ransomNote.toCharArray()){ // same thing here except now whenever a char is found, its value is
            // decremented. if at any point an index is found to be at zero, a needed letter is missing and false is returned
            if (alphabets_counter[c-'a'] == 0) return false;
            alphabets_counter[c-'a']--;
        }
        return true;
    }
}