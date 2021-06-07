package MovingBricks.QuestionEveryday;

import java.math.BigInteger;
import java.time.Period;
import java.util.*;

import MovingBricks.QuestionEveryday.*;
public class SolSecond {
    public static void main(String[] args){
        SolSecond sol = new SolSecond();
        String s1 = "123";
        int[] nms = {1,2,3};
        for(List<Integer> e:sol.permute(nms)){
            System.out.println(e);
        }
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
}
