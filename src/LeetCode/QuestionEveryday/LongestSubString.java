package LeetCode.QuestionEveryday;

import java.util.Arrays;

public class LongestSubString {
    public static void main(String[] args){
        String a = "tmmzuxt";


        System.out.println(lengthOfLongestSubstring(a));
    }
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        int[] pos = new int[128];
        Arrays.fill(pos, -1);
        int length = 0;
        int max_length = 0;
        int i=0;
        int start = 0;
        while(i<s.length())
        {

           while( i<s.length()  && (pos[s.charAt(i)] == -1 ||(pos[s.charAt(i)]!=-1 && pos[s.charAt(i)] < start)))
           {
               pos[s.charAt(i)] = i;
               length ++;
               i += 1;
           }
           max_length = Math.max(max_length,length);
           if( i == s.length())
               break;
           length = i-pos[s.charAt(i)];
           start = pos[s.charAt(i)] + 1;
           pos[s.charAt(i)] = i;
           i += 1;
        }
        return max_length;
    }
}
