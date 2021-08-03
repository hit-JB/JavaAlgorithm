package MovingBricks.QuestionEveryday;

import com.sun.source.tree.Tree;

import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SolIV {
    public static void main(String[] args){
        SolIV sol = new SolIV();
        int[] pushed = {1,2}, popped = {2,2};
        System.out.println(sol.validateStackSequences(pushed,popped));

    }
    public SolIV(){}
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for(int e:arr)list.add(e);
        list.sort(Integer::compare);
        for(int i=0;i<list.size()-1;i++)
            if(list.get(i + 1) - list.get(i) >1)
                list.set(i+1,list.get(i)+1);
            return list.get(list.size()-1);
    }
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> stones = new ArrayList<>();
        for(int e:asteroids)stones.add(e);
        int size;
        do{
             size = stones.size();
            int i=0;
            while(i<stones.size()-1){
           int left = stones.get(i);
           int right = stones.get(i+1);
           if(left>0 && right<0){
               int leftover = left+right;
               if(leftover==0) {
                   stones.remove(i);
                   stones.remove(i);
               }else{
                   stones.set(i,leftover>0?left:right);
                   stones.remove(i+1);
               }
           }
           else{
               i++;
           }
       }}while (size!=stones.size());
        int[] ret = new int[stones.size()];
        for(int j=0;j<stones.size();j++){
            ret[j] = stones.get(j);
        }
        System.out.println(stones);
        return ret;
    }
    public int[] asteroidCollisionByStack(int[] asteroids){
        Stack<Integer> stack = new Stack<>();
        for(int e:asteroids){
            if(stack.isEmpty())
                stack.push(e);
            else if(stack.peek()>0 && e<0){
                if(stack.peek() +e >0)
                    continue;
                while(!stack.isEmpty() && stack.peek() > 0 && stack.peek() + e <=0){
                    if(stack.peek() + e ==0) {
                        stack.pop();
                        stack.push(0);
                    }else
                    stack.pop();
                }
                if(stack.isEmpty() || stack.peek()<0)
                    stack.push(e);
                else if(stack.peek()==0)
                    stack.pop();
            }else{
                stack.push(e);
            }
        }
        System.out.println(stack);
        int[] ret =new int[stack.size()];
        for(int j=0;j<stack.size();j++)
            ret[j] = stack.get(j);
        return ret;
    }
    public int numDifferentIntegers(String word) {
        Set<String> ret = new HashSet<>();
        StringBuilder builder  = new StringBuilder();
        int i=0;
        while(i<word.length()){

            while (i<word.length() && word.charAt(i) >='0'&&word.charAt(i)<='9')
            {
                builder.append(word.charAt(i));
                i++;
            }
            i++;
            if(!builder.isEmpty()){
               while (builder.length() >0 && builder.charAt(0)=='0')
               {
                        builder.deleteCharAt(0);
                }
                if(builder.isEmpty())
                    ret.add("0");
                else
                    ret.add(builder.toString());
                builder.delete(0,builder.length());
            }
        }
        System.out.println(ret);
        return ret.size();
    }
    public int search(int[] nums, int target) {
        if(nums.length==0)
            return 0;
        int left = searchSingle(nums,target,true);
        int right = searchSingle(nums,target,false);
        if(left==-1 && right==-1)
            return 0;
        return right-left+1;
    }
    public int searchSingle(int[] nums,int target,boolean direction){
        if(nums.length==0 || nums.length==1 && nums[0] != target)
            return -1;
        if(nums.length == 1)
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
    public int[] getConcatenation(int[] nums) {
        int[] ret = new int[nums.length * 2];
        System.arraycopy(nums,0,ret,0,nums.length);
        System.arraycopy(nums,0,ret,nums.length,nums.length);
        return ret;
    }
    public int countPalindromicSubsequence(String s) {
        StringBuilder  temp = new StringBuilder();
        Set<String> ret = new HashSet<>();
        countDfs(s,0,temp,ret);
        return ret.size();
    }
    public void countDfs(String s,int k,StringBuilder temp,Set<String> set){
        if(k==s.length() || temp.length()==3){
            if(temp.length()==3)
            {
                if(temp.charAt(2)==temp.charAt(0))
                set.add(temp.toString());
            }
            return;
        }
        for(int i=k;i<s.length();i++){
            char c = s.charAt(i);
            temp.append(c);
            countDfs(s,i+1,temp,set);
            temp.deleteCharAt(temp.length()-1);
        }
    }
    public int colorTheGrid(int m, int n) {//red green blue//1,2,3
        double mod = Math.pow(10,9)+7;
        long[] count = new long[1];
        int[][] map = new int[m][n];
        colorDfs(m-1,n-1,0,0,map,count);
        return (int)count[0];

    }
    public void colorDfs(int m,int n,int row,int column,int[][] map,long[] count){
        if(row==m && column-1 == n){
            if(m>0 && n>0) {
                if (map[row - 1][column - 1] != map[row][column - 1] &&
                        map[row][column - 2] != map[row][column - 1]) {
                    count[0] = (count[0] + 1) % (long) (Math.pow(10, 9) + 7);
                }
            }else if(m==0 || n==0){
                if(m==0 && n==0)
                    count[0] +=1;
                else if(m==0)
                {
                    if(map[row][column-1]!= map[row][column-2])
                        count[0]++;
                }else{
                    if(map[row][column-1] != map[row-1][column-1])
                        count[0]++;
                }
            }
            return;
        }
        if(row-1>=0)
        {
            if(column-1>=0 && map[row-1][column-1]==map[row][column-1])
                return;
        }
        if(column-2>=0)
        {
            if(map[row][column-1]==map[row][column-2])
                return;
        }
        if(column==n+1) {
            row = row + 1;
            column=0;
        }
        for(int k=1;k<=3;k++)
        {
            map[row][column] = k;
            colorDfs(m,n,row,column+1,map,count);
        }

    }
    public int countPalindromicSubsequenceII(String s){
        int ret=0;
        for(int i=0;i<26;i++){
            char c  = (char) (i+'a');
            char[] arr = new char[26];
            int left = s.indexOf(c);
            int right = s.lastIndexOf(c);
            if(right-left>1){
                for(char e:s.substring(left+1,right).toCharArray())
                    arr[e-'a']++;
            }
            for(int e:arr)
                if(e>0)
                    ret+=1;
        }
        return ret;
    }
    public int colorTheGridII(int m,int n){//1=<m<=5
        int mod = (int) (Math.pow(10,9)+7);
        int column =(int) Math.pow(3,m);
        Map<Integer,Set<Integer>> map = new HashMap<>();
        initializeMap(map,column,m);
        int[][]dp = new int[n][column];
        for(int i=0;i<column;i++)
        {
            int[] code = encode(i,m);
            dp[0][i] = 1;
            for(int j=0;j<code.length-1;j++) {
                if (code[j] == code[j + 1])
                {
                    dp[0][i] = 0;
                    break;
                }
            }
        }
        for(int j=1;j<n;j++){
            for(int i=0;i<column;i++){
                Set<Integer> set = map.getOrDefault(i,null);
                if(set!=null)
                for(int e:set){
                    dp[j][i] += dp[j-1][e];
                    dp[j][i] %= mod;
                }
            }
        }
        int sum=0;
        for(int e:dp[n-1])
            sum +=e;
        return sum;
    }
    public void initializeMap(Map<Integer,Set<Integer>> map,int num,int max_row){
        for(int i=0;i<num;i++){
                for(int j=0;j<num;j++){
                        if(checkPair(i,j,max_row))
                        {
                            map.computeIfAbsent(i, k -> new HashSet<>());
                            map.get(i).add(j);
                        }
                }
            }
    }
    public boolean checkPair(int num1,int num2,int max_row)
    {
        int[] code1 = encode(num1,max_row),code2=encode(num2,max_row);
        for(int i=0;i<code1.length-1;i++) {
            if (code1[i + 1] == code1[i] || code2[i] == code2[i + 1])
                return false;
            if(code1[i]==code2[i])
                return false;
        }
        return code1[code1.length - 1] != code2[code2.length - 1];

    }
    public int[] encode(int num,int max_row){
        int[] code = new int[max_row];
        int i=0;
        while(num >= 3)
        {
            code[i++] = num % 3;
            num = num / 3;
        }
        code[i] = num;
        return code;
    }
    public TreeNode canMerge(List<TreeNode> trees) {
        HashMap<Integer,TreeNode> root = new HashMap<>();
        Set<Integer> value = new HashSet<>();
        for(TreeNode e:trees){
            if(e.left!=null)
                value.add(e.left.val);
            if(e.right!=null)
                value.add(e.right.val);
        }
        TreeNode head=null;
        for(TreeNode e:trees){
            if(value.contains(e.val))
                continue;
            root.put(e.val,e);
            head = e;
        }
        if(root.size()!=1)
            return null;
        for(TreeNode e:trees)
            root.put(e.val,e);
        root.remove(head.val);
        List<Integer> sort = new ArrayList<>();
        inorder(head,sort,root);
        for(int i=0;i<sort.size()-1;i++) {
            if (sort.get(i + 1) <= sort.get(i))
                return null;
        }
        if(!root.isEmpty())
            return null;
        return head;
    }
    public void inorder(TreeNode head,List<Integer> sort,HashMap<Integer,TreeNode> root){
        if(head.left!=null){
            if(root.containsKey(head.left.val)) {
                head.left = root.remove(head.left.val);
            }
            inorder(head.left,sort,root);
        }
        sort.add(head.val);
        if(head.right!=null){
            if(root.containsKey(head.right.val)) {
                head.right = root.remove(head.right.val);
            }
            inorder(head.right,sort,root);
        }
    }
    public int countTriples(int n) {
        Set<Integer> pow = new HashSet<>();
        int sum = 0;
        for(int i=1;i<=n;i++)
            pow.add(i * i);
        for(int i=1;i<=n;i++){
            int c = i*i;
            for(int j=1;j<i;j++){
                if(pow.contains(c-j*j))
                    sum++;
            }
        }
        return sum;
    }
    public int nearestExit(char[][] maze, int[] entrance) {
        int row_length = maze.length,column_length = maze[0].length;
        Set<String> visited= new HashSet<>();
        Queue<String> path = new ArrayDeque<>();
        path.add(pathEncode(entrance[0],entrance[1]));
        visited.add(pathEncode(entrance[0],entrance[1]));
        int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};
        int step=0;
        while(!path.isEmpty()){
            int size = path.size();
            step ++;
            for(int i=0;i<size;i++){
                String top = path.remove();
                int index = top.indexOf('+');
                int row = Integer.parseInt(top.substring(0,index)),
                        column = Integer.parseInt(top.substring(index+1));
                for(int k=0;k<4;k++){
                    int next_row = row + direction[k][0],
                            next_column = column + direction[k][1];
                    String point = pathEncode(next_row,next_column);
                    if(next_row<row_length && next_row >=0 && next_column < column_length &&
                    next_column >=0 && maze[next_row][next_column]=='.' && !visited.contains(point)){
                        if(next_column==0 || next_column == column_length-1 ||
                        next_row==0 || next_row==row_length-1)
                            return step;
                        path.add(point);
                        visited.add(point);
                    }
                }
            }
        }
        return -1;
    }
    public String pathEncode(int row,int column){
        return row +"+"+ column;
    }
    public boolean sumGame(String num) { //Alice equal Bob not equal
        int left_count=0,right_count=0;
        int left_sum=0,right_sum=0;
        for(int i=0;i<num.length() /2;i++)
        {
            char c = num.charAt(i);
            if(c=='?')
                left_count++;
            else{
                left_sum += c-'0';
            }
        }
        for(int i=num.length() /2;i<num.length();i++){
            char c = num.charAt(i);
            if(c=='?')
                right_count++;
            else{
                right_sum += c-'0';
            }
        }
        if(right_count+left_count==0)
            return !(left_sum==right_sum);
        if((right_count + left_count) % 2==1)
            return true;
        int delta = left_sum - right_sum;
        int delta_count = right_count -left_count;
        return !((delta_count / 2) * 9 == delta);
    }
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int[][] map= new int[passingFees.length][passingFees.length];
        for(int[] e:map)
            Arrays.fill(e,Integer.MAX_VALUE);
        for(int[] e:edges){
            map[e[0]][e[1]] = map[e[1]][e[0]] = Math.min(e[2],map[e[1]][e[0]]);
        }
        return 0;
    }
    public int canBeTypedWords(String text, String brokenLetters) {
        int [] map = new int [26];
        for(char c: brokenLetters.toCharArray())
            map[c-'a']=1;
        int i=0;
        int sum = 0;
        while(i<text.length()){
            boolean flag = false;
            while(i<text.length() && text.charAt(i)!=' '){
                if (map[text.charAt(i) - 'a'] == 1) {
                    flag = true;
                }
                i++;
            }
            if(!flag)
                sum++;
            while(i<text.length() && text.charAt(i)==' ')
                i++;
        }
        return sum;
    }
    public int addRungs(int[] rungs, int dist) {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int e:rungs)queue.add(e);
        Stack<Integer> ret = new Stack<>();
        ret.add(0);
        int sum=0;
        while(!queue.isEmpty()){
            if(queue.peek() - ret.peek() >dist){
                int i = (queue.peek() - ret.peek()) / dist;
                if((queue.peek()-ret.peek()) % dist ==0) {
                    ret.add(ret.peek() + (i-1) * dist);
                    sum += i - 1;
                }
                else{
                    ret.add(ret.peek() + i * dist);
                    sum += i;
                }
            }
            ret.add(queue.remove());
        }
        return sum;
    }
    public long maxPoints(int[][] points) {
        int row = points.length,column = points[0].length;
        long[][] dp=new long[row][column];
        for(int j=0;j<column;j++)
            dp[0][j] = points[0][j];
        long max_prev = Long.MIN_VALUE;
        long max_prev_ = max_prev;
        for(int i=1;i<row;i++){
            for(int j=0;j<column;j++){
                long max = Long.MIN_VALUE;
                for(int k=0;k<column;k++) {
                    max = Math.max(dp[i - 1][k] - Math.abs(k - j), max);
                }
                dp[i][j] = max + points[i][j];
                max_prev = Math.max(max_prev,dp[i][j]);
            }
            max_prev_ = max_prev;
        }
        long ret =Long.MIN_VALUE;
        for(long e:dp[row-1])
            ret = Math.max(e,ret);
        return ret;
    }
    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int[] ret = new int[queries.length];
        for(int i=0;i<queries.length;i++){
            ret[i] = maxGeneticSingle(parents,queries[i]);
        }
        return ret;
    }
    public int maxGeneticSingle(int[] parents,int[] pair){
        int node = pair[0],val = pair[1];
        int ret = Integer.MIN_VALUE;
        while(node!=-1){
            ret = Math.max(val^node,ret);
            node = parents[node];
        }
        return ret;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] nums = {2 ,3 ,5 ,7 ,11 ,13 ,17 ,19 ,23 ,29
                ,31 ,37 ,41 ,43 ,47 ,53 ,59 ,61 ,67 ,71
                ,73 ,79,83 ,89 ,97,101};
        Map<Long,List<String>> map = new HashMap<>();
        for(String e:strs){
            long code = encodeString(e,nums);
            if(!map.containsKey(code))
                map.put(code,new ArrayList<>());
            map.get(code).add(e);
        }
        return new ArrayList<>(map.values());
    }
    public long encodeString(String e,int[] nums){
        long ret = 1;
        for(char c:e.toCharArray()){
            ret *=nums[c-'a'];
        }
        return ret;
    }
    public int maxFrequency(int[] nums, int k) {
        if(nums.length==1)
            return 1;
        List<Integer> sort = new ArrayList<>();
        for(int e:nums)sort.add(e);
        sort.sort(Integer::compare);
        int start = 0,end=0;
        int sum = 0;
        int max=Integer.MIN_VALUE;
        while (end<sort.size()){
            if(sum<=k){
                max = Math.max(max,end-start+1);
                end++;
                if(end==sort.size())
                    break;
                sum +=(end-start) * (sort.get(end)-sort.get(end-1));
            }else{
                sum -=(sort.get(end)-sort.get(start));
                start++;
            }
        }
        return max;
    }
    public boolean canBeIncreasing(int[] nums) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for(int e:nums){
            if(stack.isEmpty())
                stack.push(e);
            else{
                if(e>stack.peek())
                    stack.push(e);
                else{
                    if(stack.size()>1)
                    {
                        int top = stack.pop();
                        if(e>stack.peek())
                        {
                            stack.push(e);
                        }else
                        {
                            stack.push(top);
                        }
                        count++;
                        if(count>1)
                            return false;
                    }else{
                        stack.pop();
                        stack.push(e);
                        count++;
                        if(count>1)return false;
                    }
                }
            }
        }
        return true;
    }
    public String removeOccurrences(String s, String part) {
        if(part.length()>s.length())
            return s;
        String ret = null;
      while (ret != s)
      {
          ret = s;
          s = s.replaceFirst(part,"");
      }
      return s;
    }
    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        for(int i=1;i<=length / 2;i++){
            if(length % i==0)
            {
                if(s.substring(0,i).repeat(length / i).equals(s))
                    return true;
            }
        }
        return false;
    }
    public int halfQuestions(int[] questions) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int e:questions){
            map.put(e, map.getOrDefault(e,0)+1);
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((e1,e2)-> -Integer.compare(e1.getValue(),e2.getValue()));
        int count = 0;
        int sum = 0;
        int size = questions.length;
        for(Map.Entry<Integer,Integer> e:list){
            count++;
            sum+=e.getValue();
            if(sum>= size / 2)
                return count;
        }
        return map.size();
    }
    public int largestArea(String[] grid) {
        int[] area = new int[1];
        int[][] visited = new int[grid.length][grid[0].length()];
        for(int i=0;i<grid.length;i++)
        {
            String s = grid[i];
            for(int j=0;j<s.length();j++){
                if(visited[i][j]==0 && s.charAt(j)!='0')
                {
                    boolean[] is = new boolean[]{true};
                    int[] count = new int[]{1};
                    dfsLargestArea(i,j,grid,visited,area,is,count);
                }
            }
        }
        return area[0];
    }
    public void dfsLargestArea(int row,int column,
                               String[] grid,int[][] visited,int[] area,boolean[] is,int[] count){
        visited[row][column] = 1;
        int[][] direction = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        for(int k=0;k<4;k++){
            int next_row = row+direction[k][0];
            int next_column = column+ direction[k][1];
            if(next_row<0 || next_row>=grid.length || next_column <0 || next_column >= grid[0].length()
            || grid[next_row].charAt(next_column)=='0'){
                is[0] = false;
            } else if(visited[next_row][next_column] == 0 && grid[next_row].charAt(next_column)==grid[row].charAt(column))
            {
                count[0]++;
                dfsLargestArea(next_row,next_column,grid,visited,area,is,count);
            }
        }
        if(is[0])
            area[0] = Math.max(area[0],count[0]);
    }
    public int eatenApples(int[] apples, int[] days) {
        int sum = 0;
        int temp = 0;
        int start = 0;
        int i =0;
       while(i<apples.length){
            if(temp>=i-start){
                if(temp==i-start)
                    temp += Math.min(apples[i],days[i]);
               else
                {
                    int remain = 0;
                    if(apples[i]!=0) {
                        if (apples[i] >= days[i])
                           remain= days[i] - 1;
                        else
                            remain= apples[i];
                    }
                    temp = Math.max(i-start+1+remain,temp);
                }
            }
            else{
                sum += temp;
                start = i;
                temp = Math.min(apples[i],days[i]);
            }
            i++;
        }
        if(temp>= i-start)
            sum+=temp;
        return sum;
    }
    public int regionsBySlashes(String[] grid) {
        return 0;
    }
    public int minOperations(int[] nums) {
        Stack<Integer> increase = new Stack<>();
        int operations = 0;
        for(int e:nums){
            if(increase.isEmpty())
                increase.add(e);
            else{
                while(e<=increase.peek())
                {
                    e++;
                    operations++;
                }
                increase.push(e);
            }
        }
        return operations;
    }
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] ret = new int[queries.length];
        int i =0;
        for(int[] circle:queries){
            double x = circle[0],y = circle[1], r= circle[2];
            int count = 0;
            for(int[] point:points){
                double x_p = point[0],y_p = point[1];
                if(Math.sqrt(Math.pow((x-x_p),2) + Math.pow(y-y_p,2))<=r)
                    count++;
            }
            ret[i] = count;
            i++;
        }
        return ret;
    }
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int num = 0;
        int[] ret = new int[nums.length];
        for(int e:nums)
            num ^=e;
        for(int i=nums.length;i>0;){
            ret[nums.length-i] = maxXor(num,maximumBit);
            i--;
            num ^=nums[i];
        }
        return ret;
    }
    public int maxXor(int e,int maxBit){
        int num = 0;
        for(int i=maxBit-1;i>=0;i--){
            if((e>>i & 1) ==0)
               num = 2* num +1;
            else
                num = 2*num;
        }
        return num;
    }
    public int titleToNumber(String columnTitle) {
        int sum = 0;
        for(char c:columnTitle.toCharArray()){
            sum = sum * 27 + (c-'A'+1);
        }
        return sum;
    }
    public int cuttingRope(int n) {
        int [] dp = new int[n];
        for(int i=1;i<n;i++)
        {
            for(int j=i-1;j>=i / 2;j--){
                dp[i] = Math.max((j+1) * (i-j),Math.max(dp[j] * (i-j),dp[i]));
            }
        }
        return dp[n-1];
    }
    public static class CQueue {
        private Stack<Integer> save = new Stack<>(),delete = new Stack<>();
        public CQueue() {

        }

        public void appendTail(int value) {
            save.add(value);
        }

        public int deleteHead() {
            if(delete.isEmpty())
            {
                while(!save.isEmpty())
                    delete.add(save.pop());
                return delete.size()>0?delete.pop():-1;
            }
            return delete.pop();
        }
    }
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(B==null)return true;
        if(A.left!=null){
            boolean left = isSubStructure(A.left,B);
            if(left)
                return true;
        }
        if(isSubTree(A,B))
            return true;
        if(A.right!=null)
        {
            return isSubStructure(A.right,B);
        }
        return false;
    }
    public boolean isSubTree(TreeNode a, TreeNode b){
        if(a==null)
            return false;
        boolean left = true,right = true;
        if(a.val!=b.val)
            return false;
        if(b.left!=null)
            left = isSubTree(a.left,b.left);
        if(b.right!=null)
            right = isSubTree(a.right,b.right);
        return left && right;
    }
    public int findNthDigit(int n) {
        if(n<10)
            return n;
        long sum = 10;
        long  pow = 10;
        long count = 2;
        long num = 9;
        while(sum<=n) {
            sum += 9 * pow * count;
            num = num + pow * 9;
            pow = pow *10;
            count ++;
        }
        sum = sum-(pow / 10) * (count - 1) * 9;
        num = num - (pow / 10) * 9;
        long remain = n-sum+1;
        long tail = remain / (count - 1);
        long  t = remain - tail * (count-1);
        if(t==0)
        {
            String s = String.valueOf(num + tail);
            return s.charAt(s.length()-1)-'0';
        }
        else{
            String s = String.valueOf(num+tail+1);
            return s.charAt((int)t-1)-'0';
        }
    }
    public long numberOfWeeks(int[] milestones) {
        if(milestones.length<1)
            return 1;
        long sum = 0;
        for(int e:milestones)sum+=e;
        int length = milestones.length;
        long[] dp = new long[length+1];
        long[] dp_= new long[length+1];
        Arrays.fill(dp, 0);
        Arrays.fill(dp_,0);
        for(int i=1;i<=sum / 2;i++){
            for(int j=1;j<dp.length;j++){
                dp[j] = Math.max(dp[j-1],dp_[j]);
                if(dp[j-1] + milestones[j-1] <= i)
                    dp[j]= Math.max(dp[j-1] + milestones[j-1],dp[j]);
//                if(dp_[j] + milestones[j-1] <= i)
//                    dp[j] = Math.max(dp_[j] + milestones[j-1],dp[j]);
            }
            System.arraycopy(dp,0,dp_,0,dp.length);
        }
        long count =  dp[dp.length-1];
        long another = sum -count;
        if(count == another)
            return count * 2L;
        else
            return Math.min(count,another) * 2L + 1;
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] map = new int[n][n];
        for (int[] ints : map) Arrays.fill(ints, Integer.MAX_VALUE);
        for(int[] e:times) {
            map[e[0]-1][e[1]-1] = e[2];
        }
        Map<Integer,Integer> s = new HashMap<>();
        Map<Integer,Integer> u = new HashMap<>();
        for(int i=0;i<map[0].length;i++) {
            u.put(i,map[k-1][i]);
        }
        u.put(k-1,0);
        while(!u.isEmpty())
        {
            List<Map.Entry<Integer,Integer>> sort = new ArrayList<>(u.entrySet());
            sort.sort(Comparator.comparingInt(Map.Entry::getValue));
            Map.Entry<Integer,Integer> e = sort.get(0);
            u.remove(e.getKey());
            s.put(e.getKey(),e.getValue());
            for(Map.Entry<Integer,Integer> adj:u.entrySet())
            {
                if(map[e.getKey()][adj.getKey()]!=Integer.MAX_VALUE)
                {
                    u.put( adj.getKey(),Math.min(adj.getValue(),e.getValue()+map[e.getKey()][adj.getKey()]));
                }
            }
        }
        int max =0;
        for (Map.Entry<Integer,Integer> e:s.entrySet())
            max = Math.max(max,e.getValue());
        return max == Integer.MAX_VALUE?-1:max;
    }
    public int[] singleNumbers(int[] nums) {
        int xor=0;
        for(int e:nums)
            xor ^=e;
        int i=0;
        while((xor>>i & 1)==0){
            i++;
        }
        int ret = 0;
        for(int e:nums){
            if((e>>i & 1)==0)
                ret ^=e;
        }
        return new int[]{ret,xor^ret};
    }
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0,j=0;
        do{
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
            if(i<pushed.length)
                stack.push(pushed[i]);
            i++;
        }while(!stack.isEmpty() && i<=pushed.length);
        return stack.isEmpty();
    }
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

}
