package MovingBricks.QuestionEveryday;

import java.util.Arrays;

public class longestPalindrome {
    public static void main(String[] args){
        String subs = longestPalindrome("abab");
        System.out.println(subs);
    }
    public static String longestPalindrome(String s){//using p{i,j} to indicate the subString is palindrome
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        int start= 0,end = 0;
        for(int i=s.length()-1;i>=0;i--){
            for(int j=i;j<s.length();j++)
            {
                if(j==i)
                    isPalindrome[i][j] = true;
                else {
                    if (j == i + 1)
                        isPalindrome[i][j] = (s.charAt(i) == s.charAt(j));
                    else
                        isPalindrome[i][j] = (isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j));
                }
                if(isPalindrome[i][j])
                {
                    if( j-i > end-start)
                    {
                        start = i;
                        end = j;
                    }
                }

            }
        }
        return s.substring(start,end+1);
    }
}
