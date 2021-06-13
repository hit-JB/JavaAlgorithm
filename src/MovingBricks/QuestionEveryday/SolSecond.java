package MovingBricks.QuestionEveryday;

import java.lang.management.BufferPoolMXBean;
import java.math.BigInteger;
import java.sql.ClientInfoStatus;
import java.time.Period;
import java.util.*;

import MovingBricks.QuestionEveryday.*;
public class SolSecond {
    public static void main(String[] args){
        SolSecond sol = new SolSecond();
        int[][]matrix = {{1},{3}};
        System.out.println(sol.searchMatrix(matrix,3));
    }

    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return null;
        ListNode list = new ListNode(0,head);
        ListNode temp = list;
        ListNode t;
        while(list!= null && list.next!=null){
            t = list.next;
            while(t!= null && t.val == val)
                t = t.next;
            list.next = t;
            list = t;
        }
        return temp.next;
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates.length ==1)
        {
            if(candidates[0] == target)
            {
                List<List<Integer>> ret= new ArrayList<>();
                List<Integer> temp = new ArrayList<>();
                temp.add(candidates[0]);
                ret.add(temp);
                return ret;
            }
            else{
                List<List<Integer>> ret= new ArrayList<>();
                return ret;
            }
        }
        ArrayList<Integer> l = new ArrayList<>();
        for (int i=0;i<candidates.length;i++)
            l.add(candidates[i]);
        l.sort(Integer::compare);
        for(int i =0;i<candidates.length;i++)
            candidates[i] = l.get(i);
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        combinationBackTracking(candidates,target,-1,temp,ret,0);
        return ret;
    }
    public void combinationBackTracking(int[] candidates,int target,int k,List<Integer> temp,
                                        List<List<Integer>> ret,int sum){
      if(k==candidates.length){
            if(sum == target)
                ret.add(new ArrayList<>(temp));
      }
        else{
            if(sum == target){
                ret.add(new ArrayList<>(temp));
            }else if(sum < target){
                int sum_ = sum;
                int i =k+1;
                while(i<candidates.length) {
//                    if(!temp.isEmpty() && candidates[i] < temp.get(temp.size() -1))
//                        continue;
                    sum += candidates[i];
                    temp.add(candidates[i]);
                    combinationBackTracking(candidates, target, i, temp, ret, sum);
                    temp.remove(temp.size() - 1);
                    sum = sum_;
                    while(i+1 < candidates.length && candidates[i] == candidates[i+1])
                        i++;
                    i++;
                }
            }
        }
    }
    public int jump(int[] nums) {
        if(nums.length==1)
            return 1;
        int[] minimal_counts = new int[nums.length];
        Arrays.fill(minimal_counts,Integer.MAX_VALUE);
        minimal_counts[0] = 0;
        int min ;
        for(int i=1;i<nums.length;i++){
            min = Integer.MAX_VALUE;
            for(int j=0;j<i;j++){
                if(nums[j] < Integer.MAX_VALUE && nums[j] >= i-j){
                    min = Math.min(min,minimal_counts[j] + 1);
                }
            }
            minimal_counts[i] = min;
        }

        return minimal_counts[nums.length-1];
    }
    public int trap(int[] height) {
        if(height.length <3)
            return 0;
        int water = 0;
        int left=0 ,right = 0;
        int mid;
        int min_height;
        int[] nums = new int[height.length];
        while(right < height.length){
            right = left+1;
            if(height[right] <=height[right-1]) {
                while (right<height.length && height[right] <= height[right-1])
                    right++;
                mid = right - 1;
                while (right< height.length && height[right] > height[right-1])
                    right++;
                min_height = Math.min(height[left], height[right-1]);
                nums[left] = height[left];
                nums[right-1] = height[right-1];
                if (height[mid] <= height[left]) {
                    for (int j = left + 1; j < right-1; j++) {
                        if(height[j]>=min_height)
                        {
                            nums[j] = height[j];
                            continue;
                        }
                        nums[j] = min_height;
                        water += min_height - height[j];
                    }
                }
            }
            else{
                while(right < height.length && height[right] > height[right-1]) {
                    nums[right] = height[right];
                    right++;
                }

            }
            left =right-1;
        }
        for(int e:nums)
            System.out.print(e+" ");
        System.out.println();
        if(water==0)
            return water;
        else{
            return water + trap(nums);
        }
    }
    public int trapByStack(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }
    public int trapByDynamic(int[] height){
        int[] left_max = new int[height.length];
        int[] right_max = new int[height.length];
        left_max[0] = height[0];
        right_max[height.length-1] = height[height.length-1];
        for(int i=1;i<height.length;i++){
            left_max[i] = Math.max(left_max[i-1],height[i]);
        }
        for(int j=height.length-2;j>-1;j--)
            right_max[j] = Math.max(right_max[j+1],height[j]);
        int water = 0;
        for(int i = 1;i<height.length-1;i++)
            water += Math.min(left_max[i],right_max[i]) - height[i];
        return water;
    }
    public int findMaxForm(String[] strs, int m, int n) {
        if(strs.length==0)
            return 0;
       int[][] prev = new int[m+1][n+1];
       int[][] cur = new int[m+1][n+1];
       int ones = 0;
       int zeros =0;
       for(int i=1;i<strs.length;i++){
           for(int r=0;r<cur.length;r++)
               System.arraycopy(cur[r], 0, prev[r], 0, cur[0].length);
           zeros=0;ones=0;
           for(char c : strs[i].toCharArray())
           {
               if(c=='0')
                   zeros++;
               else
                   ones++;
           }
           for(int row=0;row<m+1;row++)
               for(int column = 0;column<n+1;column++){
                   if(row-zeros<0||column-ones<0){
                       cur[row][column] = prev[row][column];
                   }
                   else
                       cur[row][column] = Math.max(prev[row-zeros][column-ones]+1,prev[row][column]);
               }
       }
       return cur[m][n];
    }
    public void insertSort(int[] nums){
        int key;
        int j;
        for(int i=1;i<nums.length;i++){
            j = i-1;
            key = nums[i];
            while(j>-1 && nums[j] > key){
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = key;
        }
    }
    public void mergeSort(int[] nums,int p,int r){
        if(p<r){
            int q = (p+r) /2;
            mergeSort(nums,p,q);
            mergeSort(nums,q+1,r);
            merge(nums,p,q,r);
        }
    }
    public void merge(int[] nums,int p,int q,int r){

        int [] array1 = new int[q-p+1];
        int [] array2 = new int[r-q];
        System.arraycopy(nums,p,array1,0,array1.length);
        System.arraycopy(nums,q+1,array2,0,array2.length);
        int index1 = 0;
        int index2 = 0;
        for(int i = p;i< r+1;i++){
            nums[i] = index1 < array1.length && index2<array2.length?
                    (array1[index1] < array2[index2]?array1[index1++]:array2[index2++]):
                    index1==array1.length?array2[index2++]:array1[index1++];
        }
    }
    public int findTargetSumWays(int[] nums, int target) {
        if(nums.length==0 || nums.length==1 && nums[0] != target)
            return 0;
        int[] sum = new int[nums.length],_sum = new int[nums.length];
        sum[nums.length-1] = nums[nums.length-1];
        _sum[nums.length-1] = -nums[nums.length-1];
        for(int i = nums.length-2;i>=0;i--){
            sum[i] =nums[i] + sum[i+1];
            _sum[i] = -sum[i];
        }
        int[] count = new int[]{0};
        dfsSum(nums,sum,_sum,0,target,count);
        return count[0];
    }
    public void dfsSum(int[] nums,int[] sum,int[] _sum,int k,int target,int[] count){
        if(k== nums.length)
        {
            if(target == 0)
                count[0] +=1;
            return;
        }
        if(target > sum[k] || target< _sum[k])
           return;
        int target_ = target;
        target = target - nums[k];
        dfsSum(nums,sum,_sum,k+1,target,count);
        target = target_ + nums[k];
        dfsSum(nums,sum,_sum,k+1,target,count);
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        List<Integer> Nums = new ArrayList<>();
        for(int i=0;i<nums.length;i++)
            Nums.add(nums[i]);
        permuteBackTracking(Nums,temp,ret);
        return ret;
    }
    public void permuteBackTracking(List<Integer> nums,List<Integer> temp,List<List<Integer>> ret){
        if(nums.size()==0)
        {
            ret.add(new ArrayList<>(temp));
            return;
        }
        List<Integer> _temp = new ArrayList<>(nums);
        for(int i=0;i<_temp.size();i++)
        {
            temp.add(nums.get(i));
            nums.remove(i);
            permuteBackTracking(nums,temp,ret);
            nums = new ArrayList<>(_temp);
            temp.remove(temp.size()-1);
        }
    }
    public int lastStoneWeightII(int[] nums) {
        if(nums.length==1)
           return nums[0];
        int[] sum = new int[nums.length];
        sum[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length-2;i>=0;i--){
            sum[i] =nums[i] + sum[i+1];
        }
        int[] count = new int[]{sum[0]};
        StoneDfs(nums,sum,0,0,count);
        return count[0];
    }
    public void StoneDfs( int[] nums,int[] sum,int k,int target,int[] count)
    {
        if(k== nums.length)
        {
            if(target >=0 && target < count[0])
                count[0] = target;
            return;
        }
        if(target + sum[k] < 0)
            return;
        int target_ = target;
        target = target - nums[k];
        StoneDfs(nums,sum,k+1,target,count);
        target = target_ + nums[k];
        StoneDfs(nums,sum,k+1,target,count);
    }
    public int lastStoneWeightIIByDynamic(int[] stones){
        if(stones.length==1)
            return stones[0];
        int sum = 0;
        for(int i=0;i<stones.length;i++)
            sum += stones[i];
        int[][] dp = new int[stones.length+1][sum /2+1];
        for(int i=1;i<stones.length+1;i++)
            for(int j=1;j<sum /2+1 ;j++)
            {
                dp[i][j] = Math.max(j-stones[i-1]>=0?dp[i-1][j-stones[i-1]]+stones[i-1]:0, dp[i-1][j]);
            }
        return sum - 2 * dp[stones.length][sum /2];
    }
    public void rotate180(int[][] matrix) {
        double half_row = matrix.length / 2.,half_column = matrix[0].length / 2.;
        int row = matrix.length,column = matrix[0].length;
        for(int i=0;i<half_row;i++){
            if(i==(int) half_row){
                for(int j=0;j<half_column;j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[row-1-i][column-1-j];
                    matrix[row-1-i][column-1-j] = temp;
                }
            }
            else{
                for(int j=0;j<column;j++){
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[row-1-i][column-1-j];
                    matrix[row-1-i][column-1-j] = temp;
                }
            }
        }
    }
    public void rotate90(int[][] matrix){
        double half_row = matrix.length / 2.,half_column = matrix[0].length / 2.;
        int row = matrix.length,column = matrix[0].length;
        int temp,index1,index2;
        for(int i=0;i<half_row;i++){
            for(int j=i;j<column-1-i;j++){
              index1 = i;index2 = j;
              temp = matrix[i][j];
              for(int k=0;k<3;k++){
                  matrix[index1][index2] = matrix[row-1-index2][index1];
                  int s = index1;
                  index1 = row-1-index2;
                  index2 = s;
              }
              matrix[index1][index2] = temp;
            }
        }
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        List<String> temp = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        List<List<String>> ret;
        String num;
        for(String e:strs){
            char[] ch = e.toCharArray();
            Arrays.sort(ch);
            num=String.valueOf(ch);

            if(map.containsKey(num)){
                map.get(num).add(e);
            }
            else{
                map.put(num,new ArrayList<>());
                map.get(num).add(e);
            }
        }
        ret = new ArrayList<>(map.values());
        return ret;
    }
    public double Pow(double x, int n) {
        System.out.println(n);
        if(n==0)
            return 1;
        if(n==1)
            return x;
        double temp = Pow(x,n/2);
        if(temp == Double.MAX_VALUE)
            return Double.MAX_VALUE;
        if(n%2==1)
        {

            return x * temp * temp;
        }
        else{
            return temp * temp;
        }
    }
    public int totalNQueens(int n) {
        List<Integer> locations = new ArrayList<>();
        int[] count= new int[]{0};
        dfsQuees(0,locations,count,n);
        return count[0];
    }
    public void dfsQuees(int k,List<Integer> location,int[] count,int n){
        if(k==n)
        {
            count[0] +=1;

        }
        for(int i=0;i<n;i++){
            int j=0;
            while (j<location.size())
            {
                int loc = location.get(j);
                if(loc == i || Math.abs(j-k) == Math.abs(i-loc))
                    break;
                j++;
            }
            if(j==location.size())
            {
                location.add(i);
                dfsQuees(k+1,location,count,n);
                location.remove(location.size()-1);
            }
        }
    }
    public int maxSubArray(int[] nums) {
        if(nums.length==1)
            return nums[0];
       int[] sum = new int[nums.length+1];
       sum[0] = 0;
       for(int i=0;i<nums.length;i++){
           sum[i+1] = sum[i] + nums[i];
       }
       int max = Integer.MIN_VALUE,min = 0;
       int min_id = 0;
       for(int i=0;i<sum.length-1;i++){
           if(sum[i] < min) {
               min = sum[i];
           }
           if(sum[i+1] - min > max)
               max = sum[i+1] -min;
       }
       return max;
    }
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[] count = new int[]{0};
        int sumProfit=0,sumPeople=0;
        dfsSchemes(n,minProfit,group,profit,count,0,sumProfit,sumPeople,0);
        return count[0];
    }
    public void dfsSchemes(int n, int minProfit, int[] group, int[] profit,int[] count,int k,int sumProfit,int sumPeople,int label)
    {
        if(sumPeople > n)
            return;
        if(sumProfit >= minProfit&&label == 1) {
            count[0] += 1;
        }
        if(k==group.length)
            return;
            int _sumPeople = sumPeople;
            int _sumProfit = sumProfit;
            sumPeople += group[k];
            sumProfit += profit[k];
            dfsSchemes(n,minProfit,group,profit,count,k+1,sumProfit,sumPeople,1);
            sumPeople = _sumPeople;
            sumProfit = _sumProfit;
            dfsSchemes(n,minProfit,group,profit,count,k+1,sumProfit,sumPeople,0);
    }
    public int profitableSchemesDp(int n, int minProfit, int[] group, int[] profit)
    {
                int[][] dp = new int[n + 1][minProfit + 1];
                for (int i = 0; i <= n; i++) {
                    dp[i][0] = 1;
                }
                int len = group.length, MOD = (int)1e9 + 7;
                for (int i = 1; i <= len; i++) {
                    int members = group[i - 1], earn = profit[i - 1];
                    for (int j = n; j >= members; j--) {
                        for (int k = minProfit; k >= 0; k--) {
                            dp[j][k] = (dp[j][k] + dp[j - members][Math.max(0, k - earn)]) % MOD;
                        }
                    }
                }
                return dp[n][minProfit];
            }
    public boolean canJump(int[] nums) {
        int start = 0,end=0;
        int left=0,right=0;
        for(int i=0;i<nums.length -1;i++){
            left = i;right=i+nums[i];
            if(left > end)
                return false;
            end = Math.max(right, end);
        }
        return end >= nums.length-1;
    }
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        list.sort((o1,o2)->{
            return Integer.compare(o1[0], o2[0]);
        });
        int start=list.get(0)[0],end=list.get(0)[1];
        List<int[]> ret = new ArrayList<>();
      for(int i=0;i<list.size();i++)
      {
          int[] e = list.get(i);
          if(e[0] <= end )
          {
              end = Math.max(e[1],end);

          }
          else
          {
              ret.add(new int[] {start,end});
              start = e[0];
              end = e[1];
              if(i==list.size()-1)
                  ret.add(new int[] {start,end});
          }
      }

      int[][] array = new int[ret.size()][2];
      int j=0;
      for(int[] e : ret)
      {
          array[j] = e;
          j++;
      }
        return array;
    }
    public int lengthOfLastWord(String s) {
        if(s.length()==1)
        {
            if(s.charAt(0) ==' ')
                return 0;
            return 1;
        }
        int length = 0;
        int i=0;
        while(i<s.length()) {
            while (i<s.length()&&s.charAt(i) == ' ')
            {
                i++;

            }
            if(i<s.length())
                length=0;
            length = 0;
            while(i<s.length() && s.charAt(i)!=' ')
            {
                length++;
                i++;
            }
        }
        return length;
    }
    public String getPermutation(int n, int k) {
        List<Integer> Num = new ArrayList<>();
        for(int i=1;i<=n;i++)
            Num.add(i);
        long value = 1;

        StringBuilder s= new StringBuilder();
        while(k!=0) {
            long[] i = countI(k);
            value = i[1];
            if (i[0] == 1) {
                for (int j = 0; j <= Num.size()-1; j++)
                    s.append(Num.get(j));
                return s.toString();
            }
            long num = (long) k * i[0] / value;

            if(k==value)
            {
                long size = Num.size() - i[0];
                for(int j=0;j<size;j++)
                    s.append(Num.remove(0));
                Num.sort(Integer::compare);
                for(int j=Num.size()-1;j>=0;j--)
                    s.append(Num.get(j));
                return s.toString();
            }
            else{
                k = Math.floorMod(k , (int) (value / i[0]));
                long size = Num.size() - i[0];
                for(int j=0;j<size;j++)
                s.append(Num.remove(0));
                Num.sort(Integer::compare);
                s.append(k==0?Num.remove((int)num-1):Num.remove((int) num));
                if(k==0)
                {
                    for(int j=Num.size()-1;j>=0;j--)
                        s.append(Num.get(j));
                }
            }
        }
        return  s.toString();
    }
    public long[] countI(int k)
    {
        long value = 1;
        long i=1;
        while(value < k)
        {
            i++;
            value = value * i;
        }
        return new long[]{i,value};
    }
    public void dfsPermute(int n,int i,long k,int[] count,int _count,List<Integer> ret)
    {
        if(_count == i)
        {
            count[0] +=1;
            return;
        }
        for(int j = n-i+1;j<=n;j++)
        {
            if(ret.contains(j))
                continue;
            ret.add(j);
            dfsPermute(n,i,k,count,_count+1,ret);
            if(count[0] == k)
                return;
            ret.remove(ret.size() -1);
        }

    }
    public int uniquePaths(int m, int n) {
        int[][] map = new int[m][n];
        int[] count = new int[]{0};
        map[0][0] = 1;
        dfsPaths(map,count,0,0);
        return count[0];

    }
    public void dfsPaths(int[][] map,int[] count,int row, int column)
    {
        if(row >=0 && row < map.length && column < map[0].length && column >= 0)
        {
            if(row == map.length-1 && column == map[0].length-1 ) {
                count[0] += 1;
                return;
            }
           for(int[] direction :new int[][]{{0,1},{1,0}})
           {
               int directionRow = direction[0],directionColumn = direction[1];
               if(row + directionRow >= map.length || row + directionRow < 0||column + directionColumn >= map[0].length ||
                       column + directionColumn <0||map[row + directionRow][column + directionColumn] == 1)
                   continue;
               map[row + directionRow][column + directionColumn] = 1;
               dfsPaths(map,count,row + directionRow,
                       column+directionColumn);
               map[row + directionRow][column + directionColumn] = 0;
           }
        }
    }
    public int uniquePathsDp(int m,int n)
    {
        if(m==1 || n==1)
            return 1;
        int[][] dp = new int[m][n];
        for(int j = 0;j<dp[0].length;j++)
        {
            dp[0][j] = 1;
        }
        for(int i =1;i<m;i++)
        {
            for(int j = 0;j<n;j++)
            {
                dp[i][j] = dp[i-1][j] +(j-1>=0?dp[i][j-1]:0);
            }
        }
        return dp[m-1][n-1];
    }
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                int k = 0;
                for(;j-k * coins[i-1] >0;k++)
                    dp[i][j] += dp[i-1][j-k * coins[i-1]];
                if(j - k * coins[i-1] ==0)
                    dp[i][j] +=1;
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    public int numSquares(int n) {
        double num = Math.sqrt((double) n);
        System.out.println(num);
        if(((int) num) == num)
            return 1;
        int Num = (int) num;
        int[][] dp = new int[Num+1][n+1];
        for(int i=0;i<dp[0].length;i++)
            dp[0][i] = 1000;
        for(int i=1;i<dp.length;i++)
        {
            for(int j=1;j<dp[0].length;j++)
            {
                if(i *i > j)
                {
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    if(i*i == j)
                        dp[i][j] =1;
                    else{
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],1+dp[i][j-i*i]),1+dp[i-1][j-i*i]);
                    }
                }
            }
        }
        for(int[] e1:dp)
        {
            for(int e : e1)
                System.out.print(e+" ");
            System.out.println();
        }
        return dp[Num][n];
    }
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int i=0;i<dp.length;i++)
        {
            for(int j=0;j<dp[0].length;j++)
            {
                if(i==0 && j==0)
                    continue;
                dp[i][j] = Math.min(i-1 >=0?dp[i-1][j] + grid[i][j]: Integer.MAX_VALUE,j-1>=0?dp[i][j-1]
                        +grid[i][j]:Integer.MAX_VALUE);
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    public String addBinary(String a, String b) {
        boolean[] x1 = new  boolean[a.length()];
        boolean[] x2 = new boolean[b.length()];
        for(int i=0;i<a.length();i++)
            x1[i] = a.charAt(i) == '1';
        for(int i=0;i<b.length();i++)
            x2[i] = b.charAt(i) =='1';
        List<Boolean> ret= new ArrayList<>();
        boolean count= false;
        for(int i=0;i<Math.min(a.length(),b.length());i++)
        {
            boolean e1 =x1[x1.length-1-i],e2 = x2[x2.length-1-i];
            ret.add((!count)&&(!e1 && e2 || e1 && !e2)||
                    count &&!(!e1 && e2 || e1 && !e2) );
            count = e1 && e2 || (count && (e1 || e2));
        }
        if(a.length() > b.length())
        {
           for(int i=a.length()-b.length()-1;i>=0;i--)
           {
               boolean e1 = x1[i],e2 = count;
               ret.add(!e1 && e2 || e1 && !e2);
               count = e1 && e2;
           }
        }
        else if(a.length() < b.length()){
            for(int i=b.length()-a.length();i>=0;i--)
            {
                boolean e1 = x1[i],e2 = count;
                ret.add(!e1 && e2 || e1 && !e2);
                count = e1 && e2;
            }
        }
       if(count)
           ret.add(count);
       StringBuilder s = new StringBuilder();
       for(int i =ret.size()-1;i>=0;i--)
           s.append(ret.get(i)?1:0);
       return s.toString();

    }
    public List<String> fullJustify(String[] words, int maxWidth) {
        return null;
    }
    public boolean isNumber(String s) {
        enum state{
            f("n", "d"),n("nd","e"),nd("e","dn"),
            d("dn"),dn("e"),e("ef","en"),ef("en"),en
                    ("");
           private final String[] next;
           state(String ...states)
           {
               next = new String[states.length];
               System.arraycopy(states, 0, next, 0, next.length);
           }
           public state toNext(char c)
           {
               if((this == state.n || this == en || this == dn) && c == 'n')
                   return this;
               if(c=='d'&&this==d || c=='e'&&(this==e || this==ef || this==en))
                   return null;
              String s= String.valueOf(c);
              for(String e :next)
              {
                  if(e.contains(s))
                      return state.valueOf(e);
              }
              return null;
           }
        }
        s = s.trim();
        state initial = null;
        if(s.charAt(0) == '.')
            initial = state.d;
        else if(s.charAt(0) == '+'||s.charAt(0)=='-')
            initial = state.f;
        else if(s.charAt(0)-48 >= 0 &&s.charAt(0)-57<=0)
            initial = state.n;
        if(initial == null)
            return false;
        for(int i=1;i<s.length();i++)
        {
            char c = s.charAt(i);
            if(c-48>=0&&c-57<=0)
                c = 'n';
            else if(c == '+'||c=='-')
                c = 'f';
            else if(c=='.')
                c='d';
            else if(c=='E'||c=='e')
                c='e';
            else
                return false;
            initial = initial.toNext(c);
            if(initial == null)
                return false;
        }
        return initial==state.n||initial==state.nd||initial==state.dn||
                initial==state.en;
    }
    public String largestNumber(int[] cost, int target) {
        String[][] dp = new String[cost.length+1][target+1];
        Arrays.fill(dp[0],"0");
        for(int i=1;i<dp.length;i++)
        {
            dp[i][0] = "0";
            for(int j=1;j<dp[0].length;j++)
            {

                if(j < cost[i-1])
                    dp[i][j]= dp[i-1][j];
                else
                {
                    String max = dp[i-1][j];
                    max = compareString(max, dp[i][j - cost[i - 1]].equals("0") && j-cost[i-1]!= 0 ?"0":
                            j-cost[i-1] == 0?String.valueOf(i):i+dp[i][j-cost[i-1]]);
//                    StringBuilder a = new StringBuilder(String.valueOf(i));
//                    int k = 1;
//                    for(;j-k* cost[i-1] >=0;k++) {
//                        max = compareString(max, dp[i][j - k * cost[i - 1]].equals("0") ? "0" :
//                                a + dp[i][j - k * cost[i - 1]]);
//                        a.append(i);
//                    }
//                   if(j - cost[i-1] * (k-1)==0)
//                       max = compareString(max,a.substring(0,a.length()-1));
                   dp[i][j] = max;
                }

            }

        }
        return dp[dp.length-1][dp[0].length-1];
    }
    public String getMax(String s,int i)
    {

        int j = s.length()-1;
        while(j>=0 && s.charAt(j)<= i+48)
            j--;
        return s.substring(0,j+1) + i +s.substring(j+1);
    }
    public String compareString(String s1,String s2)
    {
        if(s1.length() > s2.length())
            return s1;
        else if(s1.length() == s2.length())
        {
            for(int i=0;i<s1.length();i++) {
                if (s1.charAt(i) > s2.charAt(i))
                    return s1;
                if(s1.charAt(i) < s2.charAt(i))
                    return s2;
            }
            return s1;
        }
        else
            return s2;
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        int[][] direction = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int[][] map = new int[matrix.length][matrix[0].length];
        int i=0,j=0,k=0;
        List<Integer> ret= new ArrayList<>();

        while( j < map[0].length&& j >=0 && i<map.length && i>=0 && map[i][j] != 1 )
        {
            ret.add(matrix[i][j]);
            map[i][j] =1;
            if(i+direction[k][0] >= map.length || i+direction[k][0] <0||
            j+direction[k][1] >= map[0].length || j + direction[k][1] < 0 ||
                    map[i+direction[k][0]][j+direction[k][1]]==1)
            {
                System.out.println("rotate ");
                k = (k+1) % 4;
            }

            i = i+direction[k][0];
            j = j+direction[k][1];
        }
        return ret;
    }
    public String simplifyPath(String path) {
        Stack<String> s = new Stack<>();
        int i=0;
        while(i<path.length())
        {
            while(i<path.length() && path.charAt(i)=='/')
                i++;
            int k = i;
            while(i<path.length()&&path.charAt(i)!='/')
                i++;
            String c = path.substring(k,i);
            switch (c)
            {
                case ".":break;
                case "..":if(!s.isEmpty())s.pop();break;
                default:if(!c.equals(""))s.add(c);break;
            }
        }
        StringBuilder ret = new StringBuilder();
        if(s.isEmpty())
            return "/";
        ret.append("/");
        for (String value : s) {
            ret.append(value);
            ret.append("/");
        }
        return ret.toString().substring(0,ret.length()-1);
    }
    boolean isBadVersion(int n)
    {
        return n >= 1702766719;
    }
    public int firstBadVersion(int n) {
        if(isBadVersion(1))
            return 1;
        int mid = n/2;
        int l = 0;
        int r = n;
        while(r-l >1)
        {
            boolean version = isBadVersion(mid);
            if(version && !isBadVersion(mid-1))
                return mid;
            if(version)
            {
                r = mid;
                mid = l+(r-l)/2;
            }
            else
            {
                l = mid;
                mid =  l+(r-l)/2;
            }
        }
        return r;
    }
    public int climbStairs(int n) {
        if(n==1)
            return 1;
        if(n==2)
            return 2;
        int[] stairs = new int[n];
        stairs[0] = 1;
        stairs[1] = 2;
        for(int i=2;i<n;i++)
        {
            stairs[i] = stairs[i-1] + stairs[i-2];
        }
        return stairs[n-1];
    }
    public int minDistance(String word1, String word2) {//delete insert change
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i =0;i<dp[0].length;i++)
        {
            dp[0][i] = i;
        }
        for(int i =1;i<dp.length;i++)
        {
            dp[i][0] = i;
            for(int j =1;j<dp[0].length;j++)
            {
                if(word1.charAt(i-1)!=word2.charAt(j-1))
                dp[i][j] = Math.min(1+dp[i-1][j],Math.min(dp[i-1][j-1]+1,dp[i][j-1] +1));
                else
                    dp[i][j] = Math.min(1+dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1] +1));
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    public void setZeroes(int[][] matrix) {
        List<Integer> row = new ArrayList<>();
        List<Integer> column = new ArrayList<>();
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
                if(matrix[i][j]==0)
                {
                    if(! row.contains(i))
                        row.add(i);
                    if(! column.contains(j))
                        column.add(j);
                }
            }
        }
        for(int r :row) {
            for (int j = 0; j < matrix[0].length; j++)
                matrix[r][j] = 0;
        }
        for(int c:column)
        {
            for(int i=0;i<matrix.length;i++)
                matrix[i][c] = 0;
        }
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int begin = 0,end = matrix.length-1,mid = (begin + end) /2;
        int last = matrix[0].length-1;
        while(begin+1<end)
        {
            if(matrix[mid][last] < target)
            {
                begin = mid;
                mid = begin+(end - begin) / 2;
            }
            else if(matrix[mid][last] > target)
            {
                end = mid;
                mid = begin+(end - begin) / 2;
            }
            else
            {
                return true;
            }
        }
        int i=0,j=matrix[0].length-1;
        mid = (i+j) /2;
        int[] row = matrix[matrix[begin][last] >= target?begin:end];
        if(row[last] == target)
            return true;
        while(i+1<j)
        {
            if(row[mid] < target)
            {
                i = mid;
                mid = i+(j-i) /2;
            }
            else if(row[mid] > target)
            {
                j = mid;
                mid = i+(j-i) /2;
            }
            else
                return true;
        }
        return row[i] == target || row[j] == target;
    }
    

 }
