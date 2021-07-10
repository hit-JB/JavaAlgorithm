package MovingBricks.QuestionEveryday;

import JavaAlgorithm.ComputeGeometry.Jarvis;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.rmi.MarshalledObject;
import java.util.*;

public class SolV {
    public static void main(String[] args)
    {
        SolV sol = new SolV();
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
        System.out.println(sol.minFallingPathSum(matrix));




    }
    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        int index = Integer.MAX_VALUE;
        int end =Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++)
        {
            if(stack.isEmpty()) {
                max = nums[i];
                stack.push(i);
            }
            else{
                if(nums[i]>=max)
                {
                    stack.push(i);
                    max = nums[i];
                }else{
                    while(!stack.isEmpty() && nums[i] < nums[stack.peek()])
                    {
                        index = Math.min(stack.pop(),index);
                    }
                    end = i;
                    stack.push(i);
                }
            }
        }
        if(index==end)
            return 0;
        return end-index+1;
    }
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serial(root,builder);
       return builder.toString();
    }
    public void serial(TreeNode root,StringBuilder builder)
    {
        if(root==null)
            builder.append("n").append(",");
        else{
            builder.append(root.val).append(",");
            serial(root.left,builder);
            serial(root.right,builder);
        }
    }
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> strings = new ArrayList<>(Arrays.asList(dataArray));
        return deser(strings);
    }
    public TreeNode deser(List<String> strings)
    {
        if(strings.get(0).equals("n"))
        {
            strings.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(strings.get(0)));
        strings.remove(0);
        root.left = deser(strings);
        root.right=deser(strings);
        return root;
    }
    public int numWays(int n, int[][] relation, int k) {
        int[][] map = new int[n][n];
      for(int[] e:relation)
          map[e[0]][e[1]] =1;
       int[] ways=new int[1];
       dfsWays(map,k,0,0,ways);
       return ways[0];
    }
    public void dfsWays(int [][] relation,int k,int n,int start,int[] ways){
        if(n==k)
        {
            ways[0] += start==relation.length-1?1:0;
            return;
        }
        for(int i=0;i<relation[0].length;i++) {
            if(relation[start][i] ==1)
                dfsWays(relation, k, n + 1, i, ways);
        }
    }
    public int minPairSum(int[] nums) {//[3,5,2,3],//[3,3],[5,2]
        if(nums.length==2)
            return nums[0]+nums[1];
        List<Integer> list = new ArrayList<>();
        for(int e:nums)
            list.add(e);
        list.sort(Integer::compare);
        int ret = Integer.MIN_VALUE;
        int size = list.size();
        for(int i =0;i<list.size() /2;i++)
            ret = Math.max(ret,list.get(i)+list.get(size-i-1));
        return ret;
    }
    public int arrayPairSum(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int e:nums)
            list.add(e);
        list.sort(Integer::compare);
        int ret=0;
        for(int i=0;i<list.size();i++)
            if(i%2==0)
                ret += list.get(i);
            return ret;
    }
    public int maxIceCream(int[] costs, int coins) {
        int size = costs.length;
        int[][] dp= new int[coins+1][size+1];
        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++){
                dp[i][j] = dp[i][j-1];
                if(i-costs[j-1]>=0){
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-costs[j-1]][j-1]+1);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    public int maxIceCreamBySort(int[] costs,int coins){
        List<Integer> Costs = new ArrayList<>();
        for(int e:costs)
            Costs.add(e);
        Costs.sort(Integer::compare);
        int count =0;
        int sum =0;
        for(int e:Costs)
        {
            sum += e;
            count++;
            if(sum>coins)
                return count-1;
        }
        return count;
    }
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }
    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
    public int maxProfit(int k, int[] prices) {
        int[] max = new int[1];
        maxDfs(0,prices,max,k,0,0);
        return max[0];
    }
    public void maxDfs(int start,int[] prices,int[] max,int k,int count,int sum){
        if(count==k || start==prices.length)
        {
            max[0] = Math.max(sum,max[0]);
            return;
        }
        int i=start;
        while(i<prices.length)
        {
            int end = i+1;
            while(end<prices.length){
                if(prices[end]-prices[i] >0)
                {
                    maxDfs(end+1,prices,max,k,
                            count+1,sum+prices[end]-prices[i]);
                }
                end++;
            }
            i++;
        }
        max[0] = Math.max(sum,max[0]);
    }
    public int maxProfitByDp(int k,int[] prices){
        if(prices.length==0)
            return 0;
        int size = prices.length;
        int [][] buy = new int[size][k],sell = new int[size][k];
        for(int j=0;j<sell[0].length;j++)
        {
            sell[0][j] = 0;
            buy[0][j] = -prices[0];
        }
        for(int i=1;i<size;i++)
        {
            for(int j=0;j<k;j++)
            {
                sell[i][j] = Math.max(sell[i-1][j],buy[i-1][j] + prices[i]);
                buy[i][j] = Math.max(buy[i-1][j],j-1<0?-prices[i]:sell[i-1][j-1]-prices[i]);
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<sell[size-1].length;i++)
            max = Math.max(max,sell[size-1][i]);
        return Math.max(max, 0);
    }
    public String frequencySort(String s) {
        Map<Character,Integer> map = new HashMap<>();
       for(char c:s.toCharArray()) {
           map.put(c, map.getOrDefault(c, 0) + 1);
       }
       List<Map.Entry<Character,Integer>> sort = new ArrayList<>(map.entrySet());
       sort.sort((e1,e2)->Integer.compare(e2.getValue(),e1.getValue()));
       StringBuilder builder = new StringBuilder();
       for(Map.Entry<Character,Integer> e:sort){
           char c = e.getKey();
           int count = e.getValue();
           builder.append(String.valueOf(c).repeat(Math.max(0, count)));
       }
       return builder.toString();
    }
    public String minWindow(String s, String t) {
        if(s.length()<t.length())
            return "";
        Map<Character,Integer> map = new HashMap<>();
        Map<Character,Integer> count = new HashMap<>();
        for(char c :t.toCharArray()) {
            map.put(c,0);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        int start = 0,end = -1;
        int left=0,right=0;
        int l  = Integer.MAX_VALUE;
        while(end<s.length()){
            if(check(map,count))
            {
                if(end-start+1<l)
                {
                    left = start;
                    right = end;
                    l = end-start+1;
                }
                char remove = s.charAt(start);
                if(count.containsKey(remove))
                    map.put(remove,map.get(remove)-1);
                start ++;
            }else{
                end++;
                if(end==s.length())
                    break;
                char c = s.charAt(end);
                if(count.containsKey(c))
                    map.put(c,map.getOrDefault(c,0)+1);
            }
        }
        return right-left+1>=t.length()?s.substring(left,right+1):"";
    }
    public boolean check(Map<Character,Integer> map,Map<Character,Integer> count){
        for(Map.Entry<Character,Integer> e:count.entrySet()){
            if(map.get(e.getKey())<e.getValue())
                return false;
        }
        return true;
    }
    public List<String> findRepeatedDnaSequences(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('A',0);map.put('C',1);map.put('G',2);map.put('T',3);
        Set<String> ret = new HashSet<>();
        if(s.length()<10)
            return new ArrayList<>(ret);
        double num = 0;
        for(int i=0;i<10;i++)
        {
            char c = s.charAt(i);
            num = 4 * num + map.get(c);
        }
        int L =10;
        Set<Double> set = new HashSet<>();
        set.add(num);
       for(int i=1;i<s.length()-L;i++){
           char c_prev = s.charAt(i-1),c_last = s.charAt(i+L-1);
           num = 4 * (num-map.get(c_prev) * Math.pow(4,L-1)) + map.get(c_last);
           if(set.contains(num)){
               ret.add(s.substring(i,i+L));
           }else{
               set.add(num);
           }
       }
       return new ArrayList<>(ret);
    }
    public int[] findErrorNums(int[] nums) {
       List<Integer> list = new ArrayList<>();
       int sum = 0;
       for(int e:nums) {
           list.add(e);
           sum +=e;
       }
       list.sort(Integer::compare);
       int[] ret = new int[2];
       int sum1= (nums.length + 1) * (nums.length) /2;
       for(int i=0;i<list.size()-1;i++){
           if(list.get(i).equals(list.get(i + 1)))
               ret[0] = list.get(i);
       }
       sum1+=list.get(list.size()-1);
       ret[1] = ret[0] - (sum-sum1);
       return ret;
    }
    public int rob(int[] nums) {
        int size = nums.length;
        if(size <=3)
        {
            if(size==1)
                return nums[0];
            if(size ==2)
                return Math.max(nums[0],nums[1]);
            if(size ==3)
                return Math.max(nums[0]+nums[1],nums[1]);
        }
        int[] dp = new int[size];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[0] + nums[2];
        for(int i=3;i<size;i++)
        {
            dp[i] = Math.max(dp[i-3],dp[i-2]) + nums[i];
        }
        return Math.max(dp[size-1],dp[size-2]);
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Queue<TreeNode> Q = new ArrayDeque();
        Q.add(root);
        while(!Q.isEmpty())
        {
            int size = Q.size();
            for(int i=0;i<size;i++){
                TreeNode top = Q.remove();
                if(top.left!=null)
                    Q.add(top.left);
                if(top.right!=null)
                    Q.add(top.right);
                if(i==size-1)
                    ret.add(top.val);
            }
        }
        return ret;
    }
    public String countOfAtoms(String formula) {
        if(formula.length()==0)
            return "";
        Map<Character,Integer> alphaCount = new HashMap<>();
        Stack<String[]> S = new Stack<>();
        int i = 0;
        while(i<formula.length()){
            char c = formula.charAt(i);
            if(c=='('|| c==')')
                S.push(null);
            else if(c>=48 && c<=57){
                StringBuilder builder = new StringBuilder();
                builder.append(c);i++;
                while(i<formula.length()&&formula.charAt(i)>=48 && formula.charAt(i) <= 57)
                {
                    builder.append(formula.charAt(i));
                    i++;
                }
                i--;
                int gain = Integer.parseInt(builder.toString());
                if(S.peek()==null)
                {
                    S.pop();
                    Queue<String[]> Q = new ArrayDeque<>();
                    while(!S.isEmpty() && S.peek()!= null)
                    {
                        String[] top = S.pop();
                        top[1] = String.valueOf(Integer.parseInt(top[1]) * gain);
                        Q.add(top);
                    }
                    S.pop();
                    int size = Q.size();
                    for(int j=0;j<size;j++){
                        S.push(Q.remove());
                    }
                }else{
                    String[] top = S.peek();
                    top[1] = String.valueOf(Integer.parseInt(top[1]) * gain);
                }
            }
            else{
                StringBuilder builder = new StringBuilder();
                builder.append(c);i++;
                while(i<formula.length()&&formula.charAt(i)>=97 && formula.charAt(i)<=122)
                {
                    builder.append(formula.charAt(i));
                    i++;
                }
                i--;
                S.push(new String[]{builder.toString(),"1"});
            }
            i++;
        }
        Map<String,Integer> map = new HashMap<>();
        for(String[] e:S){
            if(e == null)
                continue;
            map.put(e[0],map.getOrDefault(e[0],0)+Integer.parseInt(e[1]));
        }
        StringBuilder builder = new StringBuilder();
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByKey());
        for(Map.Entry<String,Integer> e:list){
            builder.append(e.getKey()).append(e.getValue()==1?"":e.getValue());
        }
        return builder.toString();
    }
    public int minSubArrayLen(int target, int[] nums) {
        int size = nums.length;
        int start = 0,end=-1;
        int sum=0;
        int left = 0, right=-1,l = Integer.MAX_VALUE;
        while(end<size && start < size){
            if(sum>=target){
                if(end-start < l){
                    l = end-start;
                    left = start; right = end;
                }
                sum -=nums[start];
                start++;
            }else {
                end++;
                if(end == size)
                    break;
                sum += nums[end];
            }
        }
        return Math.max(right - left + 1, 0);
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses==1 || prerequisites.length==0)
            return true;
        int[][] map = new int[numCourses][numCourses];

        for(int[] e:prerequisites){
            map[e[1]][e[0]] = 1;
        }
        Map<Integer,Integer> studied= new HashMap<>();
        for(int i=0;i<numCourses;i++){
            studied.put(i,0);
        }
        Stack<Integer> stack =new Stack<>();

        for(int i=0;i<numCourses;i++){
            if(studied.get(i) == 2)
                continue;
            studied.put(i,1);
            stack.push(i);
            while(!stack.isEmpty()){
                int u = stack.peek();
                boolean exit = true;
                for(int j=0;j<numCourses;j++){
                    if(map[u][j] == 1 && studied.get(j) == 1)
                        return false;
                    if(map[u][j] == 1 && studied.get(j) == 0){
                        stack.push(j);
                        studied.put(j,1);
                        exit = false;
                        break;
                    }
                }
                if(exit) {
                    studied.put(u, 2);
                    stack.pop();
                }
            }
        }
        return true;
    }
    public int numSubArraysWithSum(int[] nums, int goal) {
        Map<Integer,Integer> sum = new HashMap<>();
        int[] sum_ = new int[nums.length];
        sum_[0] = nums[0];
        for(int i=1;i<nums.length;i++)
            sum_[i] = sum_[i-1] + nums[i];
        int count = 0;
        for(int e:sum_){
            count += sum.getOrDefault(e-goal,0);
            count += e==goal?1:0;
            sum.put(e,sum.getOrDefault(e,0)+1);
        }
        return count;
    }
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null)
            return 0;
        int dis_left = maxDisToLeaf(root.left)+1;
        int dis_right = maxDisToLeaf(root.right)+1;
        int left = diameterOfBinaryTree(root.left),
                right = diameterOfBinaryTree(root.right);
        int dis = Math.max(left,right);
        return Math.max(dis,dis_left+dis_right);
    }
    public int maxDisToLeaf(TreeNode root){
        if(root==null)
            return -1;
        return Math.max(1+maxDisToLeaf(root.left),1+maxDisToLeaf(root.right));
    }
    public int rob(TreeNode root) {
        return Math.max(robDfs(root,false),robDfs(root,true));
    }
    public int robDfs(TreeNode root,boolean flag){
        if(root==null)
            return 0;
        if(flag)
            return root.val + robDfs(root.left,false) + robDfs(root.right,false);
        else
            return Math.max(robDfs(root.left,true),robDfs(root.left,false))
                    +Math.max(robDfs(root.right,true),robDfs(root.right,false));

    }
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> heights = new ArrayList<>(Arrays.asList(people));
        heights.sort((e1,e2)->{
            if(e1[0]>e2[0])
                return 1;
            else if(e1[0]==e2[0])
                return -Integer.compare(e1[1],e2[1]);
            else
                return -1;
        });
        int[] order = new int[people.length];
        Arrays.fill(order, -1);
        int count = 0;
        for(int i=heights.size()-1;i>=0;i--){
            int[] e = heights.get(i);
            int higher_count = e[1];
            if(order[higher_count]==-1) {
                order[higher_count] = i;
                count++;
            }
            else{
                for(int k=count-1;k>=higher_count;k--)
                    order[k+1] = order[k];
                order[higher_count] = i;
                count++;
            }
        }
        int[][] ret = new int[people.length][2];
        for(int i=0;i<people.length;i++)
            ret[i] = heights.get(order[i]);
        return ret;
    }
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        for(int e:nums){
            if(count == 0) {
                candidate = e;
                count++;
            }
            else{
                if(e==candidate)
                    count++;
                else
                    count--;
            }
        }
        int sum = 0;
        for(int e:nums){
            if(e==candidate)
                sum++;
        }
        return sum>nums.length / 2?candidate:-1;
    }
    public boolean canReorderDoubled(int[] arr) {//array[2*i+1] = 2 * array[2 * i];
        if(arr.length==0 || arr.length % 2 ==1)
            return false;
        List<Integer> list = new ArrayList<>();
        for(int e:arr)list.add(e);
        list.sort(Integer::compare);
        if(list.get(0) >=0 ){
            Queue<Integer> temp = new ArrayDeque<>();
            for(int e:list){
                if(temp.isEmpty())
                    temp.add(e);
                else if(e== 2 * temp.peek())
                {
                    temp.remove();
                }else{
                    temp.add(e);
                }
            }
            return temp.isEmpty();
        }else if (list.get(list.size()-1) <=0){
            Queue<Integer> temp = new ArrayDeque<>();
            for(int i=list.size()-1;i>=0;i--){
                int e = list.get(i);
                if(temp.isEmpty())
                    temp.add(e);
                else if(e== 2 * temp.peek())
                {
                    temp.remove();
                }else{
                    temp.add(e);
                }
            }
            return temp.isEmpty();
        }else{
            int i=0;
            while(i<list.size() && list.get(i) <0)
                i++;
            int start = i-1;
            Queue<Integer> temp = new ArrayDeque<>();
            for(int j=start;j>=0;j--){
                int e = list.get(j);
                if(temp.isEmpty())
                    temp.add(e);
                else if(e == 2 * temp.peek())
                {
                    temp.remove();
                }else{
                    temp.add(e);
                }
            }
            if(!temp.isEmpty())return false;
            for(int j=start+1;j<list.size();j++){
                int e = list.get(j);
                if(temp.isEmpty())
                    temp.add(e);
                else if(e== 2 * temp.peek())
                {
                    temp.remove();
                }else{
                    temp.add(e);
                }
            }
            return temp.isEmpty();
        }

    }
    public int minFallingPathSum(int[][] matrix) {
        int[][] dp =new int[matrix.length][matrix[0].length];
        dp[0] = matrix[0];
        for(int i=1;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                int index = i-1;
                dp[i][j] = Math.min(Math.min(j-1>=0?dp[index][j-1]:Integer.MAX_VALUE,dp[index][j]),
                        j+1<matrix[0].length?dp[index][j+1]:Integer.MAX_VALUE) + matrix[i][j];
            }
        }
        int min = Integer.MAX_VALUE;
        for(int e:dp[matrix.length-1])
            min = Math.min(min,e);
        return min;
    }
    public int longestAwesome(String s) {//
        int encode = 0;
        return 0;
    }
    public boolean check(Map<Character,Integer> map){
        int count = 0;
        for(Map.Entry<Character,Integer> e: map.entrySet()){
            if(e.getValue() % 2==1)
                count++;
            if(count >1)
                return false;
        }
        return true;
    }
}
