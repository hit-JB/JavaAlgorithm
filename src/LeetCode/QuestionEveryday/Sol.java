package LeetCode.QuestionEveryday;

import javax.annotation.processing.SupportedSourceVersion;
import javax.print.attribute.standard.JobImpressionsSupported;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;

public class Sol {
    public static void main(String[] args){
        Sol ans = new Sol();
        int[] nums = {2,3,6,7};
        List<List<Integer>> ret = ans.combinationSum(nums,7);
        for(List<Integer> e: ret)
            System.out.println(e);

    }
    public Sol(){

    }
    public int myAtoi(String s) {
        return 0;
    }
    public int maxArea(int[] height) {
        if(height.length ==0 || height.length ==1)
            return 0;
        else
            if(height.length== 2)
                return Math.min(height[0],height[1]);
            else{
                int area = 0;
                int last_index = height.length -1;
                for(int i=0;i<height.length-1;i++){
                    if((last_index-i) * Math.min(height[i],height[last_index]) > area)
                        area = (last_index-i) * Math.min(height[i],height[last_index]);
                }
                int[] sub_height = new int[height.length-1];
                for(int i=0;i<sub_height.length;i++)
                    sub_height[i] = height[i];
                return Math.max(maxArea(sub_height),area);
            }
    }
    public int maxAreaByDoublePoints(int[] height){
        if(height.length == 0 ||height.length ==1)
            return 0;
        int start = 0,end = height.length-1;
        int maxArea = 0;
        while(start != end)
        {
            maxArea = Math.max(maxArea,(end-start) * Math.min(height[start],height[end]));
            int i = height[start] > height[end] ? end: start;
            if( i==start) {
                while (i !=end && height[i] <= height[start] )
                    i++;
                start = i;
            }
            else{
                while(i != start && height[i] <= height[end])
                    i--;
                end = i;
            }
        }
        return maxArea;
    }
    public String longestCommonPrefix(String[] strs) {
        String comStr = strs[0];
        for(int i=1;i<strs.length;i++)
        {
            comStr = commStrDouble(comStr,strs[i]);
        }
        return comStr;
    }
    public String commStrDouble(String s1,String s2){

        for(int i=0;i<Math.min(s1.length(),s2.length());i++){
            if(s1.charAt(i) == s2.charAt(i))
                continue;
            return s1.substring(0,i);
        }
        return s1.length() > s2.length()? s2:s1;
    }
    public int reverse(int x) {
        long factor = 10;
        boolean label = x >= 0;
        long num = 0;

        if(!label)
            x = -x;
        for(int i=0;;i++){
           if( i==0 && x%factor ==x) {
               return label ?x:-x;
           }
           else
               if(x % factor == x)
               {
                   num = num*10 +  (long) x*10 / factor;
                   num = label?num:-num;
                   return (int) num == num ? (int) num :0;
               }
               else{
                   num = num*10 + (x % factor) * 10 / factor;
                   factor = factor*10;
               }
       }
    }
    public int romanToInt(String s) {
        Map<String,Integer> roman = new HashMap<>();
        String[] alpha = {"I","V","X","L","C","D","M"};
        Integer[] value = {1,5,10,50,100,500,1000};
        for(int i=0;i<alpha.length;i++)
            roman.put(alpha[i],value[i]);
        int sum = 0;
        int j=0;
        while(j<s.length()-1){
            String cur = String.valueOf(s.charAt(j));
            String next = String.valueOf(s.charAt(j+1));
            if(roman.get(cur) >= roman.get(next)) {
                sum += roman.get(cur);
                j++;
            }
            else {
                sum -= roman.get(cur);
                sum += roman.get(next);
                j +=2;
            }
        }
        if(j == s.length() -1)
            sum += roman.get(String.valueOf(s.charAt(s.length()-1)));
        return sum;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> Nums = new ArrayList<>();
        for (int num : nums) {
            //if (!Nums.contains(num))
                Nums.add(num);
        }
        Nums.sort(Integer::compare);

        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> sol = new ArrayList<>();
        for(int i=0;i<Nums.size()-2;i++){
            if(Nums.get(i) > 0)
                continue;
            for(int j=i+1;j<Nums.size()-1;j++){
                if(Nums.get(i) + Nums.get(j) >0)
                    continue;
                for(int k=j+1;k<Nums.size();k++){
                    if(Nums.get(i) + Nums.get(j) + Nums.get(k) == 0){
                        if(sol.size()!=0 && Nums.get(i).equals(sol.get(sol.size() - 1).get(0)) &&
                                Nums.get(j).equals(sol.get(sol.size() - 1).get(1)) &&
                        Nums.get(k).equals(sol.get(sol.size()-1).get(2)))
                            continue;
                        temp.add(Nums.get(i));
                        temp.add(Nums.get(j));
                        temp.add(Nums.get(k));
                        if(! sol.contains(temp));
                        sol.add(new ArrayList<>(temp));
                        temp.clear();
                    }
                }
            }
        }
        return sol;
    }
    public List<List<Integer>> threeSumByDouble(int[] nums){
        List<Integer> Nums = new ArrayList<>();
        for (int num : nums) {
            Nums.add(num);
        }
        List<List<Integer>> sol = new ArrayList<>();
        Nums.sort(Integer::compare);
        for(int i=0;i<Nums.size() - 2;i++){
            if(i!=0 && Nums.get(i).equals(Nums.get(i - 1)))
                continue;
            int start = i+1;
            int end = Nums.size()-1;
            Integer n=Nums.get(i);
            while(start != end){
                Integer s = Nums.get(start),e = Nums.get(end);
                if(s+e > -n)
                    end--;
                else
                    if(s+e < -n)
                        start++;
                    else{
                        List<Integer> temp = new ArrayList<>();
                        temp.add(s);
                        temp.add(e);
                        temp.add(n);
                        sol.add(temp);
                        while(start != end && Nums.get(start).equals(s))
                            start ++;
                    }
            }
        }
        return sol;
    }
    public int threeSumClosest(int[] nums, int target) {
        List<Integer> Nums = new ArrayList<>();
        for (int num : nums) {
            Nums.add(num);
        }
        List<List<Integer>> sol = new ArrayList<>();
        Nums.sort(Integer::compare);
        Integer[] nu = Nums.toArray(new Integer[0]);
        int min = 1000;
        int sum = 0;
        int start,end;
        for(int i=0;i< nu.length-2;i++){
            if(i!=0 && nu[i].equals(nu[i-1]))
                continue;
            int object = target- nu[i];
            start = i+1;end = nu.length-1;
            while(start != end){
                int s = nu[start],e = nu[end];
                if(Math.abs(object-(s+e)) < min) {
                    sum = s + e + nu[i];
                    min = Math.abs(object-(s+e));
                }
                else
                    if(Math.abs(object -(s+e)) >=min + e-2)
                        break;
                if(s+e > object)
                    end--;
                else
                    if(s+e < object)
                        start ++;
                    else
                        return target;
            }
        }
        return sum;
    }
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0)
            return new ArrayList<>();
        String[] numberAlphabets = new String[]
                { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> sol = new ArrayList<>();
        Stack<String> s = new Stack<>();
        explore(digits,0,sol,s,numberAlphabets);
        return sol;
    }
    public void explore(String digits,int k,List<String> sol,Stack<String> s,String[] alphaNumber){
        if(k==digits.length()) {
            StringBuilder result = new StringBuilder();
            for(String e:s)
                result.append(e);
            sol.add(result.toString());
        }
        else
        {
            for(int i=0;i<alphaNumber[digits.charAt(k) - 50].length();i++)
            {
                String temp = alphaNumber[digits.charAt(k) - 50].substring(i,i+1);
                s.push(temp);
                explore(digits,k+1,sol,s,alphaNumber);
                s.pop();
            }
        }

    }
    public int totalHammingDistance(int[] nums) {
        int ret = 0;
        int one_hot = 0;
        for(int i =0;i<31;i++){
            for(int j = 0;j<nums.length;j++){
                one_hot += nums[j]>>i & 0x1;
            }
            ret += one_hot * (nums.length - one_hot);
            one_hot = 0;
        }
        return ret;
    }
    public void hashMapTest(){
        Map<String,Integer> m = new HashMap<>();
        String[] s ={"A","B","C","D","E","AA","BB","CD"};
        Integer[] num = {1,2,3,4,5,6,7,8};
        for(int i=0;i<s.length;i++)
            m.put(s[i],num[i]);

    }
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int count = 0;
        int localMatrixSum = 0;
        int delta = 0;
        int localSum = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                int [][] local_matrix = new int[matrix.length-i][matrix[0].length-j];
                local_matrix[0][0] = matrix[i][j];
                if(matrix[i][j] ==target)
                    count ++;
                for(int n = j+1;n<matrix[0].length;n++) {
                    local_matrix[0][n - j] = local_matrix[0][n - j - 1] + matrix[i][n];
                    if(local_matrix[0][n-j] == target)
                        count++;
                }
                for(int m = i+1;m<matrix.length;m++){
                    local_matrix[m-i][0] = local_matrix[m-i-1][0] + matrix[m][j];
                    if(local_matrix[m-i][0] == target)
                        count ++;
                    for(int n = j+1;n<matrix[0].length;n++)
                    {
                        local_matrix[m-i][n-j] = local_matrix[m-i-1][n-j] + local_matrix[m-i][n-j-1]+
                        matrix[m][n] - local_matrix[m-i-1][n-j-1];
                        if(local_matrix[m-i][n-j] == target)
                            count ++;
                    }
                }
            }
        }
        return count;
    }
    public int subColumn(int[][] matrix,int i,int m,int n){
        int sum = 0;
        for(int x = i;x<=m;x++)
            sum +=matrix[x][n];
        return sum;
    }
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        subParenthesis(n,n,"",ret);
        return ret;
    }
    public void subParenthesis(int left,int right,String cur,List<String> ret){
        if(left ==0 && right ==0)
            ret.add(cur);
        else {
            if (left > 0)
                subParenthesis(left - 1, right, cur + "(", ret);
            if(right > left)
                subParenthesis(left,right-1,cur + ")",ret);
        }
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<Integer> Nums = new ArrayList<>();
        for (int num : nums) {
            Nums.add(num);
        }
        List<List<Integer>> ret = new ArrayList<>();
        Nums.sort(Integer::compare);
        int start = 0,end = start + 1;
        int start_,end_;
        int obj,temp;
        int order = 0;
        int start_prev = 0,end_prev = 0;
        List<Integer> sol = new ArrayList<>();
        while(start < Nums.size() - 3) {
            end = start+1;
            while (end !=Nums.size() - 2) {
                start_ = end+1;end_ = Nums.size()-1;
                obj = target - (Nums.get(start) + Nums.get(end));
                while (start_ != end_) {
                    temp = Nums.get(start_) + Nums.get(end_);
                    if (temp > obj)
                        end_--;
                    else if (temp < obj)
                        start_++;
                    else {
                        sol.add(Nums.get(start));
                        sol.add(Nums.get(end));
                        sol.add(Nums.get(start_));
                        sol.add(Nums.get(end_));
                        ret.add(new ArrayList<>(sol));
                        sol.clear();
                        while((start_+1) != end_ && (Nums.get(start_+1).equals(Nums.get(start_))))
                            start_++;
                        start_++;
                    }
                }
                while((end+1)!=Nums.size()-2 && Nums.get(end + 1).equals(Nums.get(end)))
                    end++;
                end++;
            }
            while((start+1) != Nums.size() - 3 &&Nums.get(start+1).equals(Nums.get(start)))
                start++;
            start++;
        }
        return ret;
    }
    public int maxPower(String s) {
        if(s.equals(""))
            return 0;
        int max = 1;
        int i=0;
        int count = 1;
        while(i<s.length()-1)
        {
            while(i<s.length()-1 && s.charAt(i) == s.charAt(i+1))
            {
                i++;
                count++;
            }
            i++;
            if(count > max)
                max = count;
            count = 1;
        }
        return max;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length ==1)
            return lists[0];
       int mid = lists.length /2;
       ListNode[] l1 = new ListNode[mid];
       ListNode[] l2 = new ListNode[lists.length-mid];
       for(int i=0;i<mid;i++)
           l1[i] = lists[i];
       for(int j=mid;j<lists.length;j++)
           l2[j-mid] = lists[j];
       return mergeTwoLists(mergeKLists(l1),mergeKLists(l2));
    }
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
            ListNode l1_=l1,l2_=l2;
            ListNode ret = l1_.val > l2_.val? l2_:l1_;
            ListNode ret_head = ret;
            if(ret ==l2_)
                l2_ = l2_.next;
            else
                l1_ = l1_.next;
            while(l1_ != null && l2_ != null){
                if(l1_.val < l2_.val)
                {
                    ret.next = l1_;
                    l1_ = l1_.next;
                }
                else{
                    ret.next = l2_;
                    l2_ = l2_.next;
                }
                ret = ret.next;
            }
            if(l1_ ==null)
                ret.next = l2_;
            else
                ret.next = l1_;
            return ret_head;
    }
    public void nextPermutation(int[] nums) {
        int i = nums.length -1;
        while(i != 0){
            if(nums[i-1] < nums[i])
            {
               int j = nums.length-1;
               while(j!= i && nums[j] <= nums[i-1])
                   j--;
               int temp = nums[i-1];
               nums[i-1] = nums[j];
               nums[j] =temp;
               for(int k=i;k<(nums.length + i) / 2;k++)
               {
                   temp = nums[k];
                   nums[k] = nums[nums.length-1-(k-i)];
                   nums[nums.length-1-k+i] = temp;
               }
               return;
            }
            i--;
        }
        int temp;
        for(int j= 0;j<nums.length /2;j++){
            temp = nums[j];
            nums[j] = nums[nums.length-1-j];
            nums[nums.length-1-j] = temp;
        }
    }
    public int divide(int dividend, int divisor) {
        if(dividend ==0)
            return 0;
        int label = dividend>0 && divisor>0 || dividend <0 && divisor<0? 1:-1;
        long delta = Math.abs((long) dividend);
        long div = Math.abs((long)divisor);
        long ret = 0;
        int i=0;
        long temp ;
        while(delta >= div){
            i = 0;
            temp = div;
            while(temp<=delta){
                temp = temp <<1;
                i++;
            }
            ret +=Math.pow(2,i-1);
            delta = delta - (temp>>1);
        }
        System.out.println(ret);
        return (int) (label * ret) == (label * ret) ? (int) (label * ret):(int) (Math.pow(2,31) -1);
    }
    public boolean isPowerOfFour(int n) {
        if(n==1|| n==4)
            return true;
        if(n<0)
            return false;
        int count = n & 0x55555554;
        int index = 0;
        for(int i=0;i<31;i++) {
            index = (count >> i & 0x1) == 1 ? index + 1 : index;
            if(index ==2)
                return false;
        }
        return index ==1;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }
    public int search(int[] nums, int target) {
        if(nums.length==0)
            return -1;
        if(nums[0] > target)
        {
            int i=nums.length-1;
            for(;nums[i-1]<nums[i];i--){
                if(nums[i] == target)
                    return i;
            }
            if(nums[i] == target)
                return i;
        }
        else{
            int i=0;
            for(;nums[i+1] > nums[i];i++){
                if(nums[i] == target)
                    return i;
            }
            if(nums[i] == target)
                return i;
        }
        return -1;
    }
    public int searchByDivided(int[] nums,int target){
        if(nums.length==1)
            return nums[0] == target?0:-1;
        int mid = nums.length /2;
        if(nums[mid] == target)
            return mid;
        else if(nums[mid] > target){
            int[] nums_l = new int[mid];
            System.arraycopy(nums, 0, nums_l, 0, mid);
            return searchByDivided(nums_l,target);
        }
        else{
            int[] nums_r = new int[nums.length-mid-1];
            if (nums.length - (mid + 1) >= 0)
                System.arraycopy(nums, mid + 1, nums_r, 0, nums.length - (mid + 1));
            int result = searchByDivided(nums_r,target);
            return result==-1?-1: mid +1+result;
        }
    }
    public int longestValidParentheses(String s) {
        if(s.length()==0)
            return 0;
        char last = s.charAt(s.length() - 1);
        if(last =='(')
            return longestValidParentheses(s.substring(0,s.length()-1));
        Stack<Character> S=new Stack<>();
        S.push(s.charAt(s.length()-1));
        int i=s.length()-2;
        for (; i != -1 && !S.isEmpty(); i--) {
            if(s.charAt(i) == '(' && S.get(S.size()-1)==')')
                S.pop();
            else
                S.push(s.charAt(i));
        }
        if(S.isEmpty())
            return Math.max(longestValidParentheses(s.substring(0,i+1) + s.substring(i+2,s.length()-1))+2,
                    longestValidParentheses(s.substring(0,s.length()-1)));
        else
            return longestValidParentheses(s.substring(0,s.length()-1));
    }
    public int longestValid(String s){
        if(s.length()==0 || s.length()==1)
            return 0;
        Stack<Character> S = new Stack<>();
        int[] index = new int[s.length()];
        int start ,end;
        int max_length = 0;
        int i=0;
        for(;i<s.length();i++){
            if(S.size()>0 && s.charAt(i) == ')' && S.get(S.size()-1)=='(') {
                S.pop();
                index[i] = S.size();
            }
            else {
                S.push(s.charAt(i));
                index[i] = S.size();
            }
        }
        for(int e:index)
            System.out.print(" "+e);
        System.out.println();
//        int[] order = new int[index.length];
//        Arrays.fill(order,-1);
//        for(int j=0;j<s.length();j++)
//        {
//            if(s.charAt(j)=='(' && order[index[j]] ==-1)
//                order[index[j]] = j;
//            else
//                if(s.charAt(j)==')' && order[index[j]+1] != -1)
//                    max_length = Math.max(max_length,j-order[index[j]+1]+1);
//                else
//                    if(s.charAt(j)==')' && order[index[j]+2] !=-1)
//                        order[index[j] + 2] = -1;
//        }
        for(int j=0;j<s.length();j++){
            int k=j+1;
            while(k<s.length() && index[k] > index[j] - 2){
                if(index[k] == index[j] -1 && s.charAt(k) == ')')
                    max_length = Math.max(max_length,k-j+1);
                k++;
            }
        }
        return max_length;
    }
    public int[] searchRange(int[] nums,int target){
        int left = searchSingle(nums,target,true);
        int right = searchSingle(nums,target,false);
        return new int[]{left,right};
    }
    public int searchSingle(int[] nums,int target,boolean direction){
        if(nums.length==0 || nums.length==1 && nums[0] != target)
            return -1;
        if(nums.length==1 && nums[0] ==target)
            return 0;
        int mid = nums.length / 2;
        if(nums[mid] ==target)
        {
            if(direction)
            {
                int[] array = new int[mid ];
                System.arraycopy(nums,0,array,0,array.length);
                int order = searchSingle(array,target, true);
                return order==-1?mid:order;
            }
            else{
                int[] array = new int[nums.length-mid];
                System.arraycopy(nums,mid,array,0,array.length);
                int order = searchSingle(array,target,false);
                if(order ==-1)
                    return -1;
                else
                    return mid + order;
            }
        }else if(nums[mid] < target){
            int[] array = new int[nums.length-mid];
            System.arraycopy(nums,mid,array,0,array.length);
            int order = searchSingle(array,target,direction);
            if(order ==-1)
                return -1;
            else
                return mid + searchSingle(array,target,direction);
        }else{
            int[] array = new int[mid];
            System.arraycopy(nums,0,array,0,array.length);
            return  searchSingle(array,target,direction);
        }
    }
    public boolean[] canEat(int[] candiesCount, int[][] queries) {//queries_i = {favoriteTypei, favoriteDayi, dailyCapi};
        boolean[] eat = new boolean[queries.length];
        long[] sumCandies = new long[candiesCount.length];
        sumCandies[0] = candiesCount[0];
        for(int i=1;i<candiesCount.length;i++) {

            sumCandies[i] = sumCandies[i - 1] + candiesCount[i];
        }
        int sum_each ,sum_all;
        long eat_single;
        long u1,u2;

        for(int i=0;i<queries.length;i++){
            sum_each = queries[i][1] +1;sum_all=(queries[i][1]+1) * queries[i][2];
            eat_single = sumCandies[queries[i][0]];
            u1 = queries[i][0] -1 >=0?sumCandies[queries[i][0]-1]:0;
            u2 = sumCandies[queries[i][0]];

            eat[i] = !(sum_all < u1 || sum_each > u2) ;
        }
        return eat;
    }
    public int searchInsert(int[] nums, int target) {
        if(target > nums[nums.length-1])
            return nums.length;
        if(target < nums[0])
            return 0;
        return searchByIndex(0,nums.length-1,nums,target);
    }
    int searchByIndex(int start,int end,int[] nums,int target){
        int mid = (start + end) / 2;
        if(mid == start ) {
            if(nums[start] < target && nums[end] > target)
                return start + 1;
            if(nums[mid] == target)
                return mid;
            if(nums[end] ==target)
                return end;
        }
        if(nums[mid] == target)
            return mid;
        else if(nums[mid] < target)
            return searchByIndex(mid,end,nums,target);
        else
            return searchByIndex(start,mid,nums,target);

    }
    public boolean checkSubarraySum(int[] nums, int k) {
        long[] sums = new long[nums.length + 1];
        sums[0] = 0;
        for(int i=0;i<nums.length;i++)
            sums[i+1] = sums[i] + (long) nums[i];
        Map<Long,Integer> map = new HashMap<>();
        for(int i=0;i<sums.length;i++){
            Long res = sums[i] % k;
            if(!map.containsKey(res)) {
                map.put(res,i);
            }
            else{
                if(i-map.get(res) >= 2)
                    return true;
            }
        }
        return false;
    }
    public boolean isValid(String s) {
        if(s.length() % 2 ==1 || s.length()==0)
            return false;
        Stack<Character> S = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==')')
            {
                if(S.isEmpty()||S.peek()!='(')
                    return false;
                S.pop();
            }
            else if(s.charAt(i)==']')
            {
                if(S.isEmpty()||S.peek()!='[')
                    return false;
                S.pop();
            }else if(s.charAt(i)=='}')
            {
                if(S.isEmpty()||S.peek()!='{')
                    return false;
                S.pop();
            }
            else
                S.push(s.charAt(i));

        }
        return S.isEmpty();
    }
    public boolean isMatch(String s, String p) {

        return false;
    }
    public boolean isValidSudoku(char[][] board) {
        Map<Integer,List<Integer>> row = new HashMap<>(),column = new HashMap<>();
        for(int i=1;i<board.length+1;i++)
        {
            row.put(i,new ArrayList<>());
            column.put(i,new ArrayList<>());
        }
        int[][] count = new int[board.length][board[0].length];
        for(int i=0;i<board.length / 3;i++){
            for(int j=0;j<board[0].length / 3;j++){
                for(int m=i*3;m<3*i+3;m++)
                {
                    for(int n=j*3;n<j*3+3;n++)
                    {
                        char c = board[m][n];
                        if(c !='.')
                        {
                            count[i*3+j][c-49] ++;
                            if(count[i*3+j][c-49]>1)
                                return false;
                            if(row.get(c-48).contains(m) || column.get(c-48).contains(n))
                                return false;
                            else{
                                row.get(c-48).add(m);
                                column.get(c-48).add(n);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    public int findMaxLengthWithC(int[] nums) {
        if(nums.length==1||(nums.length==2 && nums[0]==nums[1]))
            return 0;
        if(nums.length == 2)
            return 2;
        int last_index;
        int count_zeros,count_ones = 0;
        int[] max_length = new int[nums.length];
        max_length[0] = 0;
        max_length[1] = nums[0] == nums[1] ? 0:2;
        for(int i=2;i<nums.length;i++){
            last_index = i;
            count_zeros = 0;
            count_ones = 1;
            while(last_index > -1 && nums[last_index]==nums[i]){
                count_zeros ++;
                last_index--;
            }
            if(last_index == -1)
            {
                max_length[i] = max_length[i-1];
            }
            else{
                while(last_index > 0 && nums[last_index-1] == nums[last_index] ) {
                    count_ones++;
                    last_index--;
                }
            }
            max_length[i] = Math.max(max_length[i-1],count_ones >= count_zeros ? 2 * count_zeros:0);
        }
        return max_length[nums.length-1];
    }
    public int findMaxLength(int[] nums){
        if(nums.length==1||(nums.length==2 && nums[0]==nums[1]))
            return 0;
        if(nums.length == 2)
            return 2;
        Map<Integer,Integer> map = new HashMap<>();
        int max_length = 0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i] == 0)
                nums[i]=-1;
        }
        int[] sum =new int[nums.length+1];
        sum[0] = 0;
        for(int i=0;i<nums.length;i++)
            sum[i+1] = sum[i] + nums[i];
        for(int i=0;i<sum.length;i++){
            if(map.containsKey(sum[i]))
            {
                max_length = Math.max(max_length,i-map.get(sum[i]));
            }
            else
            {
                map.put(sum[i],i);
            }
        }
        return max_length;
    }
    public int[] twoSum(int[] nums, int target) {
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++) {
            list.add(Map.entry(nums[i],i));
        }
        list.sort((o1,o2)->{
            if(o1.getKey() > o2.getKey())
                return 1;
            else
                if(o1.getKey()==o2.getKey())
                    return 0;
                else
                    return -1;
        });
        int p=0,q=list.size()-1;
        int e1,e2;
        int id1,id2;
        while(p!=q){
            e1= list.get(p).getKey();
            id1 = list.get(p).getValue();
            e2 = list.get(q).getKey();
            id2 = list.get(q).getValue();
            if(e1 + e2 == target)
                return new int[]{id1,id2};
            else if(e1+e2 < target)
                p++;
            else
                q--;
        }
        return null;
    }
    public String countAndSay(int n) {
        if(n==1)
            return "1";
        if(n==2)
            return "11";
        List<Integer> list_prev = new ArrayList<>();
        List<Integer> list_cur = new ArrayList<>();
        list_prev.add(1);
        list_prev.add(1);
        int k;
        int j;
        for(int i=0; i< n-2;i++){
            j=0;
            k=1;
            while(j<list_prev.size()-1){
                if(list_prev.get(j).equals(list_prev.get(j+1)))
                    k++;
                else{
                    list_cur.add(k);
                    list_cur.add(list_prev.get(j));
                    k=1;
                }
                j++;
            }
            if(list_prev.get(j).equals(list_prev.get(j-1)))
            {
                list_cur.add(k);
                list_cur.add(list_prev.get(j-1));
            }
            else
            {
                list_cur.add(1);
                list_cur.add(list_prev.get(j));
            }
            list_prev.clear();
            list_prev.addAll(list_cur);
            list_cur.clear();

        }
        StringBuilder s= new StringBuilder();
        for(Integer e:list_prev)
            s.append(e);
        return s.toString();
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1;
            p2 = p2 == null ? headA : p2;
        }
        return p1;
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        BacktrackingSum(temp,ret,candidates,target,0,0);
        return ret;
    }
    public void BacktrackingSum(List<Integer> temp,List<List<Integer>> ret,int[] candidates, int target ,int sum,int k){
        if(temp.size() ==0){
            for(int i=k;i<candidates.length;i++){
                temp.add(candidates[i]);
                BacktrackingSum(temp,ret,candidates,target,candidates[i],i);
                temp.remove(temp.size()-1);
            }
        }
        else{
            if(sum == target) {
                ret.add(new ArrayList<>(temp));

            }
            else if(sum < target){
                int sum_ = sum;
                for(int i=k;i<candidates.length;i++)
                {
                    sum += candidates[i];
                    temp.add(candidates[i]);
                    BacktrackingSum(temp,ret,candidates,target,sum,i);
                    temp.remove(temp.size()-1);

                    sum = sum_;
                }
            }else{

            }

        }
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val;next = null; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
