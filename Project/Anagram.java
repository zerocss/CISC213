package Project;

import java.util.Arrays;

public class Anagram {

    public static boolean Compare(String word1, String word2) {

        word1 = word1.toLowerCase().replaceAll("\\s+","");      //remove any space and convert to lowercase
        word2 = word2.toLowerCase().replaceAll("\\s+","");

        char char1[] = word1.toCharArray();         //turn first word into char array
        char char2[] = word2.toCharArray();         //turn second word into char array

        //use Arrays utility to sort
        Arrays.sort(char1);
        Arrays.sort(char2);

        if(word1.length() != word2.length())        //check if words are the same length
            return false;
        else if(Arrays.equals(char1, char2))        //see if sorted arrays equal each other
            return true;
        else
            return false;
    }
//----------------------------------------------------------------------------------------------------------------
    public static boolean Palindrome(String word) {

        char test[] = word.toLowerCase().toCharArray();     //make all lower case and then make into char array

        int i = 0;                                          //beginning of index
        int j = word.length() - 1;                          //end of index

        if(word.equals(""))                                 //inf blank, return false
            return false;

        while(j > i) {
            if(test[i] != test[j])                          //if any index doesn't equal the other, return false
                return false;
            i++;
            j--;
        }
        return true;
    }

}
