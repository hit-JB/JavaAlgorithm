package MovingBricks.QuestionEveryday;

import java.util.*;

public class SolFour {
    public static void main(String[] args)
    {
        SolFour sol = new SolFour();
        int[] nums= {5,4,3,2,1
};
        for(int e:sol.nextGreaterElements(nums))
            System.out.print(e+" ");
    }
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ret = new ArrayList<>();
        for(int i=0;i<1024;i++)
        {
            int h = i>>6;
            int m = i & 63;
            if(h<12 && m <60 && Integer.bitCount(i) == turnedOn)
            {
                ret.add(h+":"+(m<10?0:"") + m);
            }
        }
        return ret;
    }
    public int maxPathSum(TreeNode root) {
        if(root.left==null && root.right==null)
            return root.val;
        if(root.left==null || root.right==null)
        {
            if(root.left == null)
            {
                int rightDIs = maxPathRoot(root.right);
                int right = maxPathSum(root.right);
                int pathWithRoot = root.val + (Math.max(rightDIs, 0));
                return Math.max(right,pathWithRoot);
            }
            else
            {
                int leftDis = maxPathRoot(root.left);
                int left = maxPathSum(root.left);
                int pathWithRoot = root.val + (Math.max(leftDis, 0));
                return Math.max(left,pathWithRoot);
            }
        }
        int left = maxPathSum(root.left),right = maxPathSum(root.right);
        int leftDis = maxPathRoot(root.left),rightDIs = maxPathRoot(root.right);
        int pathWithRoot = root.val + (Math.max(leftDis, 0))+(Math.max(rightDIs, 0));
        return Math.max(Math.max(left,right),pathWithRoot);
    }
    public int maxPathRoot(TreeNode root) {
        if(root==null)
            return 0;
        if(root.left==null && root.right ==null)
            return root.val;
        else
            return Math.max(Math.max(maxPathRoot(root.left),maxPathRoot(root.right)),0) + root.val;
    }
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int length = s.length();
        StringBuilder builder = new StringBuilder(),builder1 = new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            char c1 = s.charAt(i),c2 =s.charAt(length-i-1);
            if(c1>=97 && c1 <=122 ||c1 >=48 && c1<=57 )
                builder.append(c1);
            if(c2 >=97 && c2<=122||c2 >=48 && c2<=57)
                builder1.append(c2);
        }
        return builder.toString().equals(builder1.toString());
    }
    public void solve(char[][] board) {
        boolean[][] map = new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j] == 'O' && i<map.length-1 && i>0 &&
                j<map[0].length-1 && j>0 && !map[i][j])
                {
                    boolean[] flag = new boolean[]{false};
                    List<int[]> ful = new ArrayList<>();
                    dfsSolve(board,map,i,j,flag,ful);
                    if(!flag[0])
                    {
                        for(int[] e:ful)
                        {
                            board[e[0]][e[1]] = 'X';
                        }
                    }
                }

            }
        }
    }
    public void dfsSolve(char[][] board,boolean[][] map,int i,int j,boolean[] flag,
                         List<int[]> full) {
        map[i][j] = true;
        full.add(new int[]{i,j});
        int[][] direction = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        for(int k=0;k<4;k++)
        {
            int row= direction[k][0],column = direction[k][1];
            if(i+row >= map.length || i+row <0||j+column>=map[0].length ||
            j+column<0) {
                flag[0] = true;
                continue;
            }
            if(board[i+row][j+column]=='O' && !map[i+row][j+column])
            {
                dfsSolve(board,map,i+row,j+column,flag,full);
            }
        }
    }
    public String[] permutation(String s) {
        if(s.length()==0)
            return new String[]{""};
        List<Character> arr = new ArrayList<>();
        for(char e: s.toCharArray())
            arr.add(e);
        arr.sort(Integer::compare);
        List<String> ret = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        dfsPermutationString(arr,0,ret,builder);
        String[] strings = new String[ret.size()];
        for(int i=0;i<ret.size();i++)
            strings[i] = ret.get(i);
        return strings;
    }
    public void dfsPermutationString(List<Character> arr,int k,List<String> ret,StringBuilder builder) {
        if(arr.size()==0)
        {
            ret.add(builder.toString());
            return;
        }
        int i=0;
        int size = arr.size();
        List<Character> arr_copy = new ArrayList<>(arr);
        while(i<size)
        {
            char c = arr.remove(i);
            builder.append(c);
            dfsPermutationString(arr,0,ret,builder);
            builder.replace(builder.length()-1,builder.length(),"");
            arr = new ArrayList<>(arr_copy);
            while(i+1<arr.size() && arr.get(i+1) == arr.get(i))
                i++;
            i++;
        }
    }
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        dfsPartition(s,ret,temp);
        return ret;
    }
    public void dfsPartition(String s,List<List<String>> ret,List<String> temp)
    {
        if(!temp.isEmpty()&&!isPalindrome(temp.get(temp.size()-1)))
            return;
        if(s.length()==0)
        {
            ret.add(new ArrayList<>(temp));
        }
        int i=1;
        while(i<=s.length())
        {
            String subs = s.substring(0,i);
            temp.add(subs);
            dfsPartition(s.substring(i),ret,temp);
            temp.remove(temp.size()-1);
            i++;
        }
    }
    public int longestConsecutive(int[] nums) {
       int max = 0;
       Set<Integer> s = new HashSet<>();
       for(int e:nums)
           s.add(e);
       for(int e:s)
       {
           int start = e;
           int count = 1;
           if(s.contains(e-1))
               continue;
           while(s.contains(start+1))
           {
               count++;
               start++;
           }
           max = Math.max(max,count);
       }
       return max;
    }
    public GraphNode cloneGraph(GraphNode node) {
        if(node==null)
            return null;
        GraphNode head = new GraphNode(node.val,new ArrayList<>());
        Map<GraphNode,GraphNode> map = new HashMap<>();
        map.put(node,head);
        bfsClone(node,head,map);
        return head;
    }
    public void bfsClone(GraphNode root,GraphNode copy,Map<GraphNode,GraphNode> map)//hashmap 与图的遍历，很有重要
    {
        for(GraphNode e: root.neighbors)
        {
            if(map.containsKey(e))
            {
                copy.neighbors.add(map.get(e));
                continue;
            }
            GraphNode neighbor = new GraphNode(e.val,new ArrayList<>());
            copy.neighbors.add(neighbor);
            map.put(e,neighbor);
            bfsClone(e,neighbor,map);
        }
    }
    public int hammingWeight(int n) {

        int count = 0;

        while(n != 0)
        {

            count += n & 1;
            n = n >>>1; //算术右移高位补一，逻辑右移，高位补零
        }
        return count;
    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int i=0;
        int n =  gas.length;
        while(i<gas.length)
        {
            if(gas[i]-cost[i] <0)
                i++;
            else
            {
                int start = i;
                int sum=0;
                do {
                    sum = sum+gas[i % n]-cost[i % n];
                    i++;
                }while (i % n !=start && sum >=0);
                if(i % n == start)
                    return sum>=0?start:-1;
                if(i % n < start)
                    return -1;
            }
        }
        return -1;
    }
    public int candy(int[] ratings) {
        int[] dp = new int[ratings.length];
        dp[0] = 1;
        int sum = 0;
        for(int i=1;i<ratings.length;i++)
        {
            dp[i] = ratings[i] > ratings[i-1]?dp[i-1]+1:1;
        }
        for(int i=dp.length-1;i>=0;i--)
        {
            if(i-1 >=0 &&
                    ratings[i-1] > ratings[i] && dp[i-1] <= dp[i])
                dp[i-1] = dp[i]+1;
            sum += dp[i];
        }
        return sum;
    }
    public int singleNumber(int[] nums) {
        int ret = 0;
        for(int e:nums)
            ret ^=e;
        return ret;
    }//
    public int singleNumberII(int[] nums) {
        return 0;
    }
    public String reverseWords(String s) {
        s = s.trim();
        System.out.println(s);
        StringBuilder builder = new StringBuilder();
        Stack<Character> C = new Stack<>();
        int i = s.length()-1;
        while(i>=0)
        {
            char c =s.charAt(i);
            if(c!=' ') {
                C.add(c);
                i--;
            }
            else
            {
                while(!C.isEmpty())
                    builder.append(C.pop());
                builder.append(' ');
                while(i>=0 &&s.charAt(i)==' ')
                    i--;
            }
        }
        while(!C.isEmpty())
            builder.append(C.pop());
        return  builder.toString();
    }
    public int evalRPN(String[] tokens) {
        Stack<Integer> S = new Stack<>();
        int i=0;
        while(i<tokens.length)
        {
            String e = tokens[i];
            int num,pop_next;
            switch (e) {
                case "+" -> {
                    num = S.pop();
                    pop_next = S.pop();
                    S.push(num + pop_next);
                }
                case "-" -> {
                    num = S.pop();
                    pop_next = S.pop();
                    S.push(pop_next-num);
                }
                case "*" -> {
                    num = S.pop();
                    pop_next = S.pop();
                    S.push(num * pop_next);
                }
                case "/" -> {
                    num = S.pop();
                    pop_next = S.pop();
                    S.push(pop_next / num);
                }
                default -> S.push(Integer.parseInt(e));
            }
            i++;
        }
        return S.peek();
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<=i;j++) {
                dp[i] = dp[i] || dp[i - j] && wordDict.contains(s.substring(i-j,i));
                if(dp[i])
                    break;
            }
        }
        return dp[dp.length-1];
    }
    public int openLock(String[] deadEnds, String target) {
        Queue<String> Q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Set<String> forbid = new HashSet<>(Arrays.asList(deadEnds));
        if(forbid.contains("0000"))
            return -1;
        if(target.equals("0000"))
            return 0;
        Q.add("0000");
        visited.add("0000");
        int count = 0;
        int dist = 0;
        while(!Q.isEmpty())
        {
            int size = Q.size();
            dist++;
            for(int i=0;i<size;i++)
            {
                StringBuilder top_ = new StringBuilder(Q.remove());
                for(int j=0;j<4;j++)
                {
                    StringBuilder top = new StringBuilder(top_.toString());
                    int c = top.charAt(j)-48;
                    int next = c-1>=0?c-1:9;
                    StringBuilder str_next = top.replace(j,j+1,
                            String.valueOf(next));
                    if(!visited.contains(str_next.toString())
                            && !forbid.contains(str_next.toString()))
                    {

                        if(str_next.toString().equals(target))
                            return dist;
                        Q.add(str_next.toString());
                        visited.add(str_next.toString());
                    }
                    next = (c+1)%10;
                    str_next = top.replace(j,j+1,
                            String.valueOf(next));
                    if(!visited.contains(str_next.toString())
                            && !forbid.contains(str_next.toString()))
                    {
                        if(str_next.toString().equals(target))
                            return dist;
                        Q.add(str_next.toString());
                        visited.add(str_next.toString());
                    }
                }
            }
        }
        return -1;
    }
    public int openLockII(String[] deadEnds, String target)
    {
       return 0;
    }
    public int slidingPuzzle(int[][] board) {//[1,2,3],[4,5,0]]
        if(toString(board).equals("123450"))
            return 0;
        String target = "123450";
        String s = toString(board);
        Set<String> visited = new HashSet<>();
        Queue<String> Q = new ArrayDeque<>();
        Q.add(s);
        visited.add(s);
        int step = 0;
        while(!Q.isEmpty())
        {
            int size = Q.size();
            step++;
            for(int i=0;i<size;i++)
            {
                String top = Q.remove();
                int location = top.indexOf("0");
                for(String e:nextStrings(top ,location))
                {
                    if(!visited.contains(e))
                    {
                        if(e.equals(target))
                            return step;
                        visited.add(e);
                        Q.add(e);
                    }
                }
            }
        }
        return -1;
    }
    public String[] nextStrings(String s,int location)
    {
        List<String> retList = new ArrayList<>();
        String s1 = s.substring(0,3),s2 = s.substring(3,s.length());
        switch (location) {
            case 0 -> {
                retList.add(s.charAt(1) + "0" + s.substring(2));
                retList.add(s.charAt(3) + s.substring(1, 3) + "0" + s.substring(4));
                break;
            }
            case 1 -> {
                retList.add("0" + s.charAt(0) + s.substring(2));
                retList.add(String.valueOf(s.charAt(0)) + s.charAt(2) + "0" + s.substring(3));
                retList.add(String.valueOf(s.charAt(0)) + s.charAt(4) + s.substring(2, 4)+ "0" + s.charAt(5));
                break;
            }
            case 2 -> {
                retList.add(s.charAt(0) + "0" + s.charAt(1) + s.substring(3));
                retList.add(s.substring(0, 2) + s.charAt(5) + s.substring(3, 5) + "0");
                break;
            }
            case 3 -> {
                retList.add("0" + s.substring(1, 3) + s.charAt(0) + s.substring(4));
                retList.add(s.substring(0, 3) + s.charAt(4) + "0" + s.charAt(5));
                break;
            }
            case 4 -> {
                retList.add(s.substring(0, 3) + "0" + s.charAt(3) + s.charAt(5));
                retList.add(s.substring(0, 4) + s.charAt(5) + "0");
                retList.add(s.charAt(0) + "0" + s.substring(2, 4) + s.charAt(1) + s.charAt(5));
                break;
            }
            case 5 -> {
                retList.add(s.substring(0, 2) + "0" + s.substring(3, 5) + s.charAt(2));
                retList.add(s.substring(0, 4) + "0" + s.charAt(4));
                break;
            }
        }

        String[] retArray = new String[retList.size()];
        for(int i=0;i<retList.size();i++)
            retArray[i] = retList.get(i);
        return retArray;
    }
    public String toString(int[][] board)
    {
        StringBuilder s = new StringBuilder();
        for(int[] e1:board)
            for(int e:e1)
                s.append(e);
            return s.toString();
    }
    public int maxProduct(int[] nums) {
        if(nums.length==1)
            return nums[0];
        int i=0;
        int start ,end;
        int max = Integer.MIN_VALUE;
        int zero_count=0;
        while(i<nums.length)
        {
            int negative_=0,negative=0,negative_count = 0;
            while(i<nums.length && nums[i]==0)
            {
                zero_count++;
                i++;
            }
            start = i;
            while(i<nums.length && nums[i]!=0)
            {
                if(nums[i] <0)
                {
                    if(negative_count==0)
                        negative_ =i;
                    negative = i;
                    negative_count++;
                }
                i++;
            }
            end = i-1;
            if(end<start)
                continue;
            max  = Math.max(max,getMax(nums,start,end,negative_,negative,negative_count));

        }
        return max<0 && zero_count!=0?0:max;
    }
    public int getMax(int[] nums,int start ,int end,int negative_,
                      int negative ,int negative_count) {
        if(end-start==0)
            return nums[start];
        int product = 1;
        if(negative_count % 2==0)
        {
            for(int i = start;i<=end;i++)
                product *= nums[i];
            return product;
        }
        else
        {
            int left = 1,right=1;
            for(int i = start;i<negative;i++)
                left *= nums[i];
            for(int i=negative_+1;i<=end;i++)
                right *=nums[i];
            return Math.max(left,right);
        }
    }
    public int maxProductByDp(int[] nums)
    {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int ret = Integer.MIN_VALUE;
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]==0)
            {
                max[i]=0;
                min[i] =0;
            }else if(nums[i] <0)
            {
                max[i] = Math.min(min[i-1],1) * nums[i];
                min[i] = Math.max(max[i-1],1) * nums[i];
            }else{
                max[i] = Math.max(max[i-1],1) * nums[i];
                min[i] = Math.min(min[i-1],1) * nums[i];
            }
            ret = Math.max(ret,max[i]);
        }

        return ret;
    }
    public int snakesAndLadders(int[][] board) {
        int N = board.length, max_num = N * N;
        Queue<Integer> Q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Q.add(1);
        visited.add(1);
        int step=0;
        while(!Q.isEmpty())
        {
            step ++;
            int size = Q.size();
            for(int i=0;i<size;i++)
            {
                int top = Q.remove();
                if(top==max_num)
                {
                    return step-1;
                }
                int[] location = getLocation(top,N);

                if(board[location[0]][location[1]] !=-1)
                {
                    int next = board[location[0]][location[1]];
                    int[] next_loc = getLocation(next,N);
                    if(!visited.contains(next))
                    {
                        if(board[next_loc[0]][next_loc[1]] != -1)
                        {
                            while( !visited.contains(next) && board[next_loc[0]][next_loc[1]] != -1) {
                                visited.add(next);
                                visited.add(board[next_loc[0]][next_loc[1]]);
                                next = board[next_loc[0]][next_loc[1]];
                                next_loc = getLocation(next,N);
                            }
                            Q.add(next);
                        }
                        else {
                            Q.add(next);
                            visited.add(next);
                        }
                    }
                }
                else
                for(int k=1;k<=6;k++)
                {
                    if(top + k > max_num)
                        continue;
                    int next = top +k;
                    int[] next_loc = getLocation(next,N);
                    if(!visited.contains(next))
                    {
                        if(board[next_loc[0]][next_loc[1]] != -1)
                        {
                            while(!visited.contains(next)&&board[next_loc[0]][next_loc[1]] != -1) {
                                visited.add(next);
                                visited.add(board[next_loc[0]][next_loc[1]]);
                                next = board[next_loc[0]][next_loc[1]];
                                next_loc = getLocation(next,N);
                            }
                            Q.add(next);
                        }
                        else {
                            Q.add(top + k);
                            visited.add(top + k);
                        }
                    }
                }
            }
        }
        return -1;
    }
    public int snakesAndLaddersII(int[][] board)
    {
        int N = board.length,max_num = N * N;
        Queue<Integer> Q = new ArrayDeque<>();
        boolean[] visited = new boolean[max_num];
        int step =0;
        Q.add(1);
        visited[0] = true;
        while(!Q.isEmpty())
        {
            step++;
            int size = Q.size();
            for(int i=0;i<size;i++)
            {
                int top = Q.remove();
                for(int k=1;k<=6;k++)
                {
                    int next = top + k;
                    if(next>max_num)
                        continue;
                    int[] pos = getLocation(next,N);
                    if(!visited[next-1])
                    {
                        if(next==max_num)
                            return step;
                        while(!visited[next-1] && board[pos[0]][pos[1]] > 0)
                        {
                            visited[next-1] = true;
                            next = board[pos[0]][pos[1]];
                            if(next==max_num)
                                return step;
                        }
                        Q.add(next);
                    }
                }
            }
        }
        return -1;
    }
    public int[] getLocation(int num,int N)
    {
        int  i = num / N;
        int column;
        if(i * N == num)
        {
            if(i%2==0)
                return new int[]{N-i,0};
            else
                return new int[]{N-i,N-1};
        }
        if(i%2==0)
        {
            column = num - i * N -1;
            return new int[]{N-1-i,column};
        }
        else{
            column = i * N + N -num;
            return new int[]{N-1-i,column};
        }
    }
    public int calculateMinimumHP(int[][] dungeon) {
        int row_length = dungeon.length,column_length = dungeon[0].length;
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        dp[row_length-1][column_length-1] = Math.max(-dungeon[row_length-1][column_length-1],0);
        for(int i=row_length-2;i>=0;i--)
            dp[i][column_length-1] = Math.max(dp[i+1][column_length-1]-dungeon[i][column_length-1],0);
        for(int j=column_length-2;j>=0;j--)
            dp[row_length-1][j] = Math.max(dp[row_length-1][j+1]-dungeon[row_length-1][j],0);


        for(int i=row_length-2;i>=0;i--)
        {
            for(int j=column_length-2;j>=0;j--)
            {
                int min = Math.min(dp[i+1][j] - dungeon[i][j],dp[i][j+1]
                        -dungeon[i][j]);
                dp[i][j] = Math.max(min, 0);
            }
        }
        return dp[0][0]+1;
    }
    public String largestNumber(int[] nums) {
        List<String> strings = new ArrayList<>();
        for(int e:nums)
            strings.add(String.valueOf(e));
        strings.sort(this::maxString);
        if(strings.get(0).equals("0") && strings.get(strings.size() - 1).equals("0"))
            return "0";
        StringBuilder builder = new StringBuilder();
        for(int i=strings.size()-1;i>=0;i--)
            builder.append(strings.get(i));
        return builder.toString();
    }
    public int maxString(String s1,String s2)
    {
        if(s1.length()==s2.length())
        {
           return s1.compareTo(s2);
        }
        else if(s1.length()<s2.length())
        {
            int length = s1.length();
            String sub_s2 = s2.substring(0,s1.length());
            int com = s1.compareTo(sub_s2);
            if(com!=0)
                return com;
            return maxString(s1,s2.substring(length));
        }
        else{
            int length = s2.length();
            String sub_s1 = s1.substring(0,length);
            int com = sub_s1.compareTo(s2);
            if(com!=0)
                return com;
            else
                return maxString(s1.substring(length),s2);
        }
    }
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int node = routes.length;
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<routes.length;i++)
        {
            for(int e:routes[i])
            {
                if(!map.containsKey(e)) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    map.put(e,temp);
                }
                else{
                    map.get(e).add(i);
                }
            }
        }
        int[][] map_ = new int[node][node];
        for(Map.Entry<Integer,List<Integer>> e:map.entrySet())
        {
           List<Integer> temp = e.getValue();
           if(temp.size()<2)
               continue;
           for(int i=0;i<temp.size()-1;i++)
           {
               for(int j=i+1;j<temp.size();j++) {
                   map_[temp.get(i)][temp.get(j)] = 1;
                   map_[temp.get(j)][temp.get(i)] =1;
               }
           }
        }
        Queue<Integer> Q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        int min = Integer.MAX_VALUE;
        for(int start :map.get(source)) {
            boolean flag = false;
            Q.add(start);
            visited.add(start);
            int step = 0;
            while (!Q.isEmpty()&&!flag) {
                step++;
                int size = Q.size();
                for (int i = 0; i < size; i++) {
                    int top = Q.remove();
                    List<Integer> temp = map.get(target);
                    if (temp!=null&&temp.contains(top))
                    {
                        min = Math.min(min,step);
                        flag =true;
                        break;
                    }
                    for (int j = 0; j < node; j++) {
                        if (map_[top][j] == 1 && !visited.contains(j)) {
                            Q.add(j);
                            visited.add(j);
                        }
                    }
                }
            }
            Q.clear();visited.clear();
        }
        return min==Integer.MAX_VALUE?-1:min;
    }
    public String removeDuplicateLetters(String s) {//"bcabc"->"abc","cbacdcbc"->"acdb";
        Stack<String> S = new Stack<>();
        int i=0;
        while(i<s.length())
        {
            String c = String.valueOf(s.charAt(i));
            if(S.isEmpty())
            {
                S.push(c);
            }
            else
            {
                if(!S.contains(c)) {

                    String top = S.peek();
                    while (!S.isEmpty() &&
                            top.compareTo(c) > 0 && s.substring(i + 1).contains(top)) {
                        S.pop();
                        if (S.isEmpty())
                            break;
                        top = S.peek();
                    }
                    S.push(c);
                }
            }
            i++;
        }
        StringBuilder builder = new StringBuilder();
        for (String value : S) builder.append(value);
        return builder.toString();
    }
    public boolean find132pattern(int[] nums) {//i<j<k,nums[i]<nums[k]<nums[j]//[3,1,4,2
        if(nums.length<3)
            return false;
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int e:nums)
            map.put(e,map.getOrDefault(e,0)+1);
        int min = Integer.MAX_VALUE;
        for(int e:nums)
        {
            min = Math.min(min,e);
            if(min < e-1)
            {
                Integer next = map.ceilingKey(min+1);
                if(next!=null && next<e)
                    return true;
            }
            map.put(e,map.get(e)-1);
            if(map.get(e)==0)
                map.remove(e);
        }
        return false;
    }
    public boolean find132patternByStack(int[] nums)
    {
        int n = nums.length;
        Deque<Integer> candidateK = new LinkedList<Integer>();
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) {
                return true;
            }
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);
            }
        }
        return false;
    }
    public String removeKdigits(String num, int k) {//num = "1432219", k = 3}->1219
        StringBuilder builder = new StringBuilder(num);
        for(int i=0;i<k;i++)
        {
            int j=0;
            String subs = builder.toString();
            while(j<builder.length()-1)
            {
                if(subs.charAt(j+1)<subs.charAt(j))
                    break;
                j++;
            }
            builder.deleteCharAt(j);
        }
        while(builder.length()>0&&builder.charAt(0) == '0')
            builder.deleteCharAt(0);
        return builder.length()>0?builder.toString():"0";
    }
    public String removeKdigitsByStack(String num,int k){
        StringBuilder builder = new StringBuilder(num);
        if(k>=num.length())
            return "0";
        int i=0;
        int count = 0;
        while(count < k)
        {
            if(i == builder.length()-1 || builder.charAt(i+1) < builder.charAt(i) )
            {
                builder.deleteCharAt(i);
                count++;
                i = Math.max(i - 1, 0);
            }
            else{
                i++;
            }
        }
        while(builder.length()>0&&builder.charAt(0) == '0')
            builder.deleteCharAt(0);
        return builder.length()>0?builder.toString():"0";
    }
    public String convertToTitle(int columnNumber) {//701,2147483647_>FXSHRXW
        StringBuilder builder = new StringBuilder();
        while(columnNumber>0)
        {
            int num = columnNumber % 26;
            if(num ==0) {
                builder.append('Z');
                columnNumber = (columnNumber - 26) / 26;
            }else
            {
                builder.append((char) (num+64));
                columnNumber = (columnNumber - num) / 26;
            }
        }
        StringBuilder reverseBuilder = new StringBuilder();
        for(int i=builder.length()-1;i>=0;i--)
            reverseBuilder.append(builder.charAt(i));
        return reverseBuilder.toString();
    }
    public int[] nextGreaterElements(int[] nums) {
        int[] nextGt = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<nums.length;i++)
        {
            if(stack.isEmpty())
                stack.push(i);
            else{
                while(!stack.isEmpty() && nums[i]>nums[stack.peek()])
                {
                    int index = stack.pop();
                    nextGt[index] = nums[i];
                }
                stack.push(i);
            }
        }
        for(int i=0;i<nums.length;i++)
        {
            while(!stack.isEmpty() &&
                    nums[i] > nums[stack.peek()] &&
                    i<stack.peek())
            {
                nextGt[stack.pop()] = nums[i];
            }
        }
        for(Integer e:stack)
            nextGt[e] = -1;
        return nextGt;
    }
}

