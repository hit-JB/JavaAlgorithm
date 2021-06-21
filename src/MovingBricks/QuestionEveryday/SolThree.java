package MovingBricks.QuestionEveryday;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SolThree {
    public static void main(String[] args){
        SolThree sol = new SolThree();
        int[] profit = {1,2,3,4,5};
        System.out.println(sol.maxProfitIII(profit));

    }
    public void sortColors(int[] nums) {
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
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfsCombine(n,k,1,ret,temp);
        return ret;
    }
    public void dfsCombine(int n,int count,int k,List<List<Integer>> ret , List<Integer> temp){
        if(temp.size() == count)
        {
            ret.add(new ArrayList<>(temp));
            return;
        }
        if( k == n+1)
            return;
        for(int i=k;i<=n ;i++)
        {
            temp.add(i);
            dfsCombine(n,count,i+1,ret,temp);
            temp.remove(temp.size()-1);
        }
    }
    public List<List<Integer>> combineByMath(int[] nums,int k) {
        if(k==0)
        {
             return new ArrayList<>(new ArrayList<>());
        }
        else if(k==1)
        {
            List<List<Integer>> ret_n_ = new ArrayList<>();
            List<Integer> temp =new ArrayList<>();
            for(int e:nums)
            {
                temp.add(e);
                ret_n_.add(new ArrayList<>(temp));
                temp.clear();
            }
            return ret_n_;
        }
        else if(k==nums.length)
        {
            List<List<Integer>> ret_n_ = new ArrayList<>();
            List<Integer> temp =new ArrayList<>();
            for(int e:nums)
            {
                temp.add(e);
            }
            ret_n_.add(new ArrayList<>(temp));
            return ret_n_;
        }
        else {
            int[] _nums = new int[nums.length - 1];
            System.arraycopy(nums, 1, _nums, 0, _nums.length);
            List<List<Integer>> ret_n_ = combineByMath(_nums,k-1), ret_n =combineByMath(_nums,k),
            ret = new ArrayList<>();
            for(List<Integer> e : ret_n_)
                e.add(nums[0]);
            ret.addAll(ret_n);
            ret.addAll(ret_n_);
            return ret;
            }
        }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<>());
        for(int i=1;i<=nums.length;i++)
        {
            ret.addAll(combineByMath(nums,i));
        }

        return ret;
    }
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j] == word.charAt(0)) {
                    int[][] map =new int[board.length][board[0].length];
                    map[i][j] =1;
                    if (dfsExist(board, word, i, j,map))
                        return true;
                }
            }
        return false;
    }
    public boolean dfsExist(char[][] board,String word,int row,int column,int[][] map) {
        if(board[row][column] != word.charAt(0))
            return false;
        if(board[row][column] == word.charAt(0) && word.length() ==1)
            return true;
        boolean ret = false;

        for(int[] direction :new int[][]{{-1,0},{0,1},{1,0},{0,-1}})
        {
            int directionRow = direction[0],directionColumn = direction[1];
            if(row + directionRow >= map.length || row + directionRow < 0||column + directionColumn >= map[0].length ||
                    column + directionColumn <0||map[row + directionRow][column + directionColumn] == 1)
                continue;
            map[row + directionRow][column + directionColumn] = 1;
            ret = ret || dfsExist(board, word.substring(1),row+directionRow,column+
                    directionColumn,map);
            map[row + directionRow][column + directionColumn] = 0;
        }

        return ret;
    }
    public int largestRectangleArea(int[] heights) {
      int[] leftMin = new int[heights.length],rightMin = new int[heights.length];
      leftMin[0]= 0;rightMin[rightMin.length-1] = 0;
      Stack<Integer> S = new Stack<>();
      int i=0;
      while(i<heights.length)
      {
          while(!S.isEmpty()&&heights[S.peek()] >= heights[i]) {
              S.pop();
          }
          leftMin[i] = S.isEmpty()?-1:S.peek();
          S.push(i);
          i++;
      }
      i=heights.length-1;
      S.clear();
      while(i>=0)
      {
          while(!S.isEmpty()&&heights[S.peek()] >= heights[i]){
              S.pop();
          }
          rightMin[i] = S.isEmpty()?heights.length:S.peek();
          S.push(i);
          i--;
      }
      int max=0;
      for( i=0;i<heights.length;i++)
          max = Math.max(max,heights[i] * (rightMin[i] - leftMin[i] -1));
      return max;
    }
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0)
            return 0;
        int[][] heights = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
               if(i==0)
                   heights[i][j] = matrix[i][j] == '1'?1:0;
               else
                   heights[i][j] = matrix[i][j] =='0'?0:
                           1 + heights[i-1][j];
            }
        }
        int max = 0;
        for(int[] e:heights)
            max = Math.max(max,largestRectangleArea(e));
        return max;
    }
    public int peakIndexInMountainArray(int[] arr) {
        int start = 0,end = arr.length-1;
        int mid = start + (end-start) /2;
        while(!(arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]))
        {
            if(arr[mid] > arr[mid-1])
            {
                start = mid;
                mid = start + (end -start) /2;
            }
            else
            {
                end = mid;
                mid = start + (end -start) /2;
            }
        }
        return mid;
    }
    public int fineMax(int[] arr,int start,int end)
    {
       return 0;
    }
    public ListNode partition(ListNode head, int x) {
        ListNode node = head;
        List<ListNode> lowThanX = new ArrayList<>();
        List<ListNode> greaterR = new ArrayList<>();

        while(node!=null)
        {
            if(node.val < x)
                lowThanX.add(node);
            else
                greaterR.add(node);
            node = node.next;
        }
        ListNode ret = new ListNode();
        node = ret;
        for(int i=0;i<lowThanX.size();i++)
        {
            ret.next = lowThanX.get(i);
            ret = ret.next;
        }
        for (ListNode listNode : greaterR) {
            ret.next = listNode;
            ret = ret.next;
        }
        ret.next = null;
        return node.next;
    }
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>((int) Math.pow(2,n));
        if(n==0) {
            ret.add(0);
            return ret;
        }
        if(n==1)
        {
            ret.add(0);
            ret.add(1);
            return ret;
        }
        List<Integer> ret_ = grayCode(n-1);
        int index = (int)Math.pow(2,n-1);
        int e;
        for(int i=0;i<ret_.size();i++)
        {
            e = ret_.get(i);
            ret.add(e);
        }
        for(int i=ret_.size()-1;i>=0;i--) {
            e = ret_.get(i);
            ret.add(e+index);
        }
        return ret;
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> Nums = new ArrayList<>();
        for(int i =0;i<nums.length;i++)
        {
            Nums.add(nums[i]);
        }
        Nums.sort(Integer::compare);
        for(int i=0;i<nums.length;i++)
            nums[i] = Nums.get(i);
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        ret.add(new ArrayList<>(temp));
        for(int i=1;i<=nums.length;i++) {
            temp.clear();
            dfsSubset(nums, i, 0, ret, temp);
        }

        return ret;
    }
    public void dfsSubset(int[] nums,int num,int k,List<List<Integer>> ret,List<Integer> temp) {
        if(k==nums.length)
        {
            if(temp.size()==num)
                ret.add(new ArrayList<>(temp));
            return;
        }
        if(temp.size() == num)
        {
            ret.add(new ArrayList<>(temp));
            return;
        }
        int i =k;
        while(i<nums.length)
        {
            temp.add(nums[i]);
            dfsSubset(nums,num,i+1,ret,temp);
            temp.remove(temp.size()-1);
            while(i<nums.length-1 && nums[i] == nums[i+1])
                i++;
            i++;
        }
    }
    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];

        for(int i=0;i<dp.length;i++)
            dp[i][i] = piles[i];
        for(int i = dp.length-2;i>=0;i--)
        {
            for(int j=i+1;j<dp[0].length;j++)
            {
                dp[i][j] = Math.max(piles[j] - dp[i][j-1],piles[i] -dp[i+1][j]);
            }
        }
        return dp[0][dp.length-1] > 0;
    }
    public int numDecodings(String s) {
        if(s.charAt(0) == '0')
            return 0;
        if(s.length()==1)
            return 1;
        if(s.length()==2)
        {
            if(s.charAt(0)>'2' ||s.charAt(0)=='2' && s.charAt(1) > '6'||s.charAt(1) == '0')
            {
                if(s.charAt(1) =='0' && s.charAt(0) >'2')
                    return 0;
                else
                    return 1;
            }
            else
                return 2;
        }
        int[] dp = new int[s.length()];
        dp[0] = 1;
        dp[1] = numDecodings(s.substring(0,2));//s.charAt(2)=='0'?s.charAt(1) > 2?0:1:numDecodings(s.substring(0,2));
        if(dp[1] == 0)
            return 0;
        for(int i=2;i<s.length();i++)
        {
            if(s.charAt(i-1)>'2' || s.charAt(i-1)=='2' && s.charAt(i) > '6'||s.charAt(i-1) =='0'
            ||s.charAt(i) == '0')
            {
                if(s.charAt(i) =='0' && s.charAt(i-1) =='0')
                    return 0;
                if(s.charAt(i) =='0')
                    if(s.charAt(i-1) <= '2')
                        dp[i] = dp[i-2];
                    else
                       return 0;
                else
                    dp[i] = dp[i-1];
            }

            else
                dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[s.length()-1];
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> ret =  new ArrayList<>();
        int[] count = new int[1];


        StringBuilder stringBuilder = new StringBuilder();
        dfsAddresses(s,0,count,ret,stringBuilder);
        return ret;
    }
    public void dfsAddresses(String s,int k,int[] count,List<String> strings,StringBuilder stringBuilders) {
        if(k==s.length() || count[0] ==4)
        {
            if(count[0] == 4 && k== s.length()) {

                strings.add(stringBuilders.toString());
            }
            return;
        }
        int i=k+1;
        while(i<=s.length()&&i<=k+3)
        {
            String s_ = s.substring(k,i);
            int length = stringBuilders.length();
            if(isValidAddress(s_)) {
                if(count[0] <= 2)
                    stringBuilders.append(s_).append(".");
                else
                    stringBuilders.append(s_);
                count[0]++;
                dfsAddresses(s, i,count, strings, stringBuilders);
                stringBuilders.replace(length, stringBuilders.length(), "");
                count[0]--;
            }
            i++;
        }
    }
    public boolean isValidAddress(String s) {
        int num = 0;
        if(s.length()>1 && s.charAt(0) =='0' || s.length() > 3)
            return false;
        for(char e: s.toCharArray())
            num = (e-48) + num * 10;
        return num <= 255 && num >=0;
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> S = new Stack<>();
        S.push(root);
        TreeNode top;
        while(!S.isEmpty()&&root!=null)
        {
            while(root!=null)
            {
                S.push(root);
                root = root.left;
            }
            top = S.pop();
            ret.add(top.val);
            if(top.right!=null)
                S.push(top.right);
        }
        return ret;
    }
    public void dfsInorder(TreeNode root,List<Integer> ret) {
        if(root ==null)
            return;
        dfsInorder(root.left,ret);
        ret.add(root.val);
        dfsInorder(root.right,ret);
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length()!= s1.length()+s2.length())
            return false;
        if(s1.length()==0)
            return s3.equals(s2);
        if(s2.length()==0)
            return s3.equals(s1);
        boolean a = s3.charAt(s3.length() - 1) == s1.charAt(s1.length() - 1);
        boolean b = s3.charAt(s3.length() - 1) == s2.charAt(s2.length() - 1);
        String s1_ = s1.substring(0,s1.length()-1),
                s2_ = s2.substring(0,s2.length()-1),
                s3_ = s3.substring(0,s3.length()-1);
        if(a && b)
            return isInterleave(s1_,s2,s3_)||
                    isInterleave(s1,s2_,s3_);
        else if(a)
            return isInterleave(s1_,s2,s3_);
        else if(b)
            return isInterleave(s1,s2_,s3_);
        else
            return false;
    }
    public boolean isInterleaveByDp(String s1,String s2,String s3) {
        if(s3.length()!= s1.length()+s2.length())
            return false;
        if(s1.length()==0)
            return s3.equals(s2);
        if(s2.length()==0)
            return s3.equals(s1);
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        for(int j=1;j<dp[0].length;j++)
            dp[0][j] = s3.substring(0,j).equals(s2.substring(0,j));
        for(int i=1;i<dp.length;i++)
        {
            dp[i][0] = s3.substring(0,i).equals(s1.substring(0,i));
            for(int j=1;j<dp[0].length;j++)
            {
                dp[i][j] = s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j] ||
                        s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1];
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    public boolean isValidBST(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfsInorder(root,ret);
        for(int i=1;i<ret.size();i++) {
            if (ret.get(i) <= ret.get(i - 1))
                return false;
        }
        return true;
    }
    public int numTrees(int n) {
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        else
        {
            int count = 0;
            for(int i=1;i<=n;i++)
            {
                int left = numTrees(i-1),right = numTrees(n-i);
                if(left ==0)
                    count += right;
                else if(right==0)
                    count += left;
                else
                    count += left * right;
            }
            return count;
        }

    }
    public int numTreesDp(int n) {
        if(n==1)
            return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=1;i<n;i++)
        {
            dp[i] += 2 * dp[i-1];
            for(int j=1;j<=i-1;j++)
            {
                dp[i] += dp[j-1] * dp[i-j-1];
            }
        }
        return dp[n-1];
    }
    public List<TreeNode> generateTrees(int n) {
        return null;
    }
    public boolean search(int[] nums, int target) {
//        if(nums[0] == nums[nums.length-1])
//            return nums[0] == target;
        if(nums.length==1 )
            return nums[0] == target;
        int start = 0,end = nums.length-1;
        int mid = start + (end-start) /2;
        while(!(nums[mid] >= nums[mid-1] && nums[mid] > nums[mid+1]))
        {
            if(nums[mid] < nums[start])
            {
                end = mid;
                mid = start + (end -start) /2;
            }
            else
            {
                start = mid;
                mid = start + (end -start) /2;
            }
        }
        int i,j;
        if(nums[0] > target) {
            i = mid + 1;j =  nums.length - 1;
        }
        else {
            i = 0;j = mid;
        }

            mid = i+(j-i) /2;
            while(j-i>1)
            {
                if(nums[mid] < target)
                {
                    i = mid;
                    mid = i+(j-i) /2;
                }
                 else if(nums[mid] > target)
                 {
                     j = mid;
                     mid = i+(j-i) /2;
                 }
                else
                    return true;
            }
            return nums[i] == target && nums[j] ==  target;
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;
        if(p!=null && q !=null &&
                p.val == q.val)
        {
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        }
        else {
            return false;
        }
    }
    public String smallestGoodBase(String n) {
        long nVal = Long.parseLong(n);
        int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));
        for (int m = mMax; m > 1; m--) {
            int k = (int) Math.pow(nVal, 1.0 / m);
            long mul = 1, sum = 1;
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }
            if (sum == nVal) {
                return Integer.toString(k);
            }
        }
        return Long.toString(nVal - 1);

    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0.;
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null)
        {
            List<Integer> temp= new ArrayList<>();
            List<List<Integer>> ret  = new ArrayList<>();
            ret.add(temp);
            return ret;
        }
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> Q = new ConcurrentLinkedQueue<>();
        List<Integer> temp =new ArrayList<>();
        Q.add(root);
        TreeNode first = null;
        TreeNode top;
        do{
           int count = 0;
           do
            {
                top = Q.remove();
                temp.add(top.val);
                if(top.left !=null)
                {
                    count +=1;
                    first = count==1? top.left:first;
                    Q.add(top.left);
                }
                if(top.right!=null)
                {
                    count +=1;
                    first = count==1?top.right:first;
                    Q.add(top.right);
                }
            }while(!Q.isEmpty() && Q.peek()!=first);
           ret.add(new ArrayList<>(temp));
           temp.clear();
        }while (! Q.isEmpty());
        return ret;
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null)
        {
            List<Integer> temp= new ArrayList<>();
            List<List<Integer>> ret  = new ArrayList<>();
            ret.add(temp);
            return ret;
        }
        ArrayDeque<TreeNode> Q = new ArrayDeque<TreeNode>();
        Q.addFirst(root);
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> ret = new ArrayList<>();
        boolean flag = true;
        while(!Q.isEmpty())
        {
            int size = Q.size();
            for(int i=0;i<size;i++)
            {
                if(flag)
                {
                    TreeNode top = Q.removeFirst();
                    temp.add(top.val);
                    if(top.left!=null)
                        Q.addLast(top.left);
                    if(top.right!= null)
                        Q.addLast(top.right);
                }
                else
                {
                    TreeNode top = Q.removeLast();
                    temp.add(top.val);
                    if(top.right!=null)
                        Q.addFirst(top.right);
                    if(top.left!=null)
                        Q.addFirst(top.left);
                }
            }
            ret.add(new ArrayList<>(temp));
            temp.clear();
            flag = !flag;
        }
        return ret;
    }
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return leftEqualRight(root.left,root.right);
    }
    public boolean leftEqualRight(TreeNode tree1,TreeNode tree2) {
        if(tree1 == null && tree2==null)
            return true;
        if(tree1==null || tree2 ==null || tree1.val!=tree2.val)
            return false;
        return leftEqualRight(tree1.left,tree2.right) && leftEqualRight(
                tree1.right,tree2.left
        );
    }
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        int[] count =new int[1],max = new int[1];
        dfsInorderCount(root,count,max);
        return max[0];

    }
    public void dfsInorderCount(TreeNode root,int[] count,int[] max) {
        if(root ==null) {
            max[0] = Math.max(max[0], count[0]);
            return;
        }
        count[0]++;
        dfsInorderCount(root.left,count,max);
        count[0]--;
        count[0]++;
        dfsInorderCount(root.right,count,max);
        count[0]--;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0)
            return null;
        int val = preorder[0];
        int i=0;
        while(inorder[i] != val)
            i++;
        int[] preLeft = new int[i],preRight = new int[inorder.length-1-i];
        System.arraycopy(preorder,1,preLeft,0,preLeft.length);
        System.arraycopy(preorder,i+1,preRight,0,preRight.length);
        int[] left = new int[i],right = new int[inorder.length-1-i];
        System.arraycopy(inorder,0,left,0,left.length);
        System.arraycopy(inorder,i+1,right,0,right.length);
        return new TreeNode(val,buildTree(preLeft,left),buildTree(preRight,right));
    }
    public TreeNode buildTreeII(int[] inorder, int[] postorder) {
        if(inorder.length==0)
            return null;
        int val = postorder[postorder.length-1];
        int i=0;
        while(inorder[i] != val)
            i++;
        int[] left_inorder= new int[i],right_inorder = new int[inorder.length-1-i],
                left_postorder = new int[i],right_postorder = new int[inorder.length-1-i];
        System.arraycopy(inorder,0,left_inorder,0,left_inorder.length);
        System.arraycopy(inorder,i+1,right_inorder,0,right_inorder.length);
        System.arraycopy(postorder,0,left_postorder,0,left_postorder.length);
        System.arraycopy(postorder,i,right_postorder,0,right_inorder.length);
        return new TreeNode(val,buildTreeII(left_inorder,left_postorder),buildTreeII(right_inorder,right_postorder));
    }
    public int maxLength(List<String> arr) {
        if(arr.size()==1)
            return arr.get(0).length();
        int[] max = new int[1];
        dfsLength(arr,0,max,"");
        return max[0];
    }
    public void dfsLength(List<String> arr,int k, int[] max,String s) {
        int[]  count = new int[26];
        for(int j = 0;j<s.length();j++)
        {
            int index = s.charAt(j) - 97;
            count[index]++;
            if(count[index] > 1)
                return;
        }
        max[0] = Math.max(max[0],s.length());
        if(k==arr.size())
        {
            return;
        }
        for(int i=k;i<arr.size();i++)
        {
            String ss = arr.get(i);
            dfsLength(arr,i+1,max,s+ss);
        }
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>(),left_ret ,right_ret ;
        List<Integer> temp = new ArrayList<>();
        if(root == null)
            return ret;
        left_ret = levelOrderBottom(root.left);
        right_ret = levelOrderBottom(root.right);
        temp.add(root.val);
        if(left_ret.size() ==0)
        {

           right_ret.add(temp);

           return right_ret;
        }
        else if(right_ret.size() ==0)
        {
            left_ret.add(temp);
            return left_ret;
        }
        else{
            int size = Math.min(left_ret.size(),right_ret.size());
            if(size == left_ret.size()) {
                for (int i = 1; i <= size; i++) {
                    List<Integer> var = left_ret.get(left_ret.size()-i);
                    var.addAll(right_ret.get(right_ret.size() - i));
                    right_ret.set(right_ret.size()-i,var
                            );
                }
                right_ret.add(temp);
                return right_ret;
            }
            else{
                for (int i = 1; i <= size; i++) {
                    left_ret.get(left_ret.size() - i).addAll(right_ret.get(right_ret.size() - i));
                }
                left_ret.add(temp);
                return left_ret;
            }

        }
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length==1)
            return new TreeNode(nums[0]);
        if(nums.length==2)
            return new TreeNode(nums[1],new TreeNode(nums[0]),null);
        int mid = nums.length / 2;
        int[] left = new int[mid],right = new int[nums.length-mid-1];
        System.arraycopy(nums,0,left,0,left.length);
        System.arraycopy(nums,mid+1,right,0,right.length);
        return new TreeNode(nums[mid],sortedArrayToBST(left),sortedArrayToBST(right));
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
      if(root==null)
          return false;
      else
          return dfsPathSum(root,targetSum);
    }
    public boolean dfsPathSum(TreeNode root,int targetSum) {
        if(root == null) {
            return targetSum == 0;
        }
        if(root.left!=null || root.right!=null) {
            if(root.left!=null && root.right!=null)
            return hasPathSum(root.left, targetSum - root.val)
                    || hasPathSum(root.right, targetSum - root.val);
            else if (root.left == null)
                return hasPathSum(root.right, targetSum - root.val);
            else
                return hasPathSum(root.left,targetSum-root.val);
        }
        else
            return root.val == targetSum;


    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> ret =new ArrayList<>();
        if(root == null)
            return ret;

        List<Integer> temp = new ArrayList<>();
        dfsRetSum(root,targetSum,temp,ret);
        return ret;
    }
    public void dfsRetSum(TreeNode root ,int targetSum,List<Integer> temp,List<List<Integer>> ret) {
        if(root.left==null && root.right== null)
        {
            if(root.val == targetSum) {
                temp.add(root.val);
                ret.add(new ArrayList<>(temp));
                temp.remove(temp.size()-1);
            }
            return;
        }
        if(root.left!=null)
        {
            temp.add(root.val);
            dfsRetSum(root.left,targetSum-root.val,temp,ret);
            temp.remove(temp.size()-1);
        }
        if(root.right!=null)
        {
            temp.add(root.val);
            dfsRetSum(root.right,targetSum-root.val,temp,ret);
            temp.remove(temp.size()-1);
        }
    }
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        dp[0][0] =1;
        for(int i=1;i<dp.length;i++)
        {
            dp[i][0] = 1;
            for(int j=1;j<dp[0].length && j<=i;j++)
            {
                char s_c = s.charAt(i-1),t_c = t.charAt(j-1);
                if(s_c == t_c)
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    public Node connect(Node root) {
        if(root == null || root.left == null && root.right == null)
            return root;
       Queue<Node> Q = new ConcurrentLinkedQueue<>();
       Q.add(root);
       while(!Q.isEmpty())
       {
           Node top = null;
           int size = Q.size();
           for(int i=0;i<size;i++)
           {
               Node topNext = top;
               top = Q.remove();
               if(topNext!=null)
                   topNext.next = top;
               if(top.left!=null)
                   Q.add(top.left);
               if(top.right!=null)
                   Q.add(top.right);
           }
           top.next = null;
       }
       return root;
    }
    public Node connectByRecurrent(Node root) {
        constructNext(root);
        return root;
    }
    public void constructNext(Node root) {
        if(root.left == null)
            return;
        Node head = root;
        Node headPrev = null;
        while(head!=null)
        {
            head.left.next = head.right;
            headPrev = head;
            head = head.next;
            headPrev.right.next = head == null?null:head.left;
        }
        constructNext(root.left);
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.get(triangle.size()-1).size();
        if(size==1)
            return triangle.get(0).get(0);
        int[][] dp = new int[triangle.size()][size];
        dp[0][0] = triangle.get(0).get(0);
        for(int i=1;i<triangle.size();i++)
        {
            size = triangle.get(i).size();
            List<Integer> temp = triangle.get(i);
            dp[i][0] = dp[i-1][0] + temp.get(0);
            for(int j=1;j<size-1;j++)
            {
                int e = temp.get(j);
                dp[i][j] = Math.min(dp[i-1][j]+e,dp[i-1][j-1] + e);
            }
            dp[i][size-1] = dp[i-1][size-2] + temp.get(size-1);
        }
        int min = Integer.MAX_VALUE;
        for(int e:dp[dp.length-1])
            min = Math.min(min,e);
        return  min;
    }
    public int maxProfit(int[] prices) {
        Stack<Integer> S = new Stack<>();
        int max = Integer.MIN_VALUE;
        for(int e:prices)
        {
            if(S.isEmpty())
                S.push(e);
            else
            {
                int top = S.peek();
                max = Math.max(max,e-top);
                if(e < top)
                {
                    S.pop();
                    S.push(e);
                }
            }
        }
        return Math.max(max, 0);
    }
    public int maxProfitII(int[] prices) {
        int max = 0;
        int i=0;
        int start,end;
        while(i<prices.length-1)
        {
            while(i+1 < prices.length && prices[i+1]<prices[i])
                i++;
            start = i;
            while(i+1 < prices.length && prices[i+1] >=prices[i])
                i++;
            end = i;
            max += prices[end]
                    - prices[start];
        }
        return max;
    }
    public int maxProfitIII(int[] prices) {
        if(prices.length==0)
            return 0;
        int[] dpFirst = new int[prices.length+1],
                dpSecond = new int[prices.length+1];
        dpFirst[0] = 0;
        dpFirst[1] = 0;
        int min = prices[0];
        for(int i=1;i<prices.length;i++)
        {
            min = Math.min(prices[i],min);
            dpFirst[i+1] = Math.max(prices[i]-min,dpFirst[i]);
        }
        dpSecond[0] = dpFirst[dpFirst.length-1];
        dpSecond[dpSecond.length-1] = 0;
        dpSecond[dpSecond.length-2] = 0;
        int max = prices[prices.length-1];
        for(int i = prices.length-2;i>0;i--)
        {
            max = Math.max(max,prices[i]);
            dpSecond[i] = Math.max(dpSecond[i+1],max - prices[i]);
        }
        max = 0;
        for(int i=0;i<dpFirst.length;i++)
            max = Math.max(dpFirst[i] + dpSecond[i],max);
        return max;
    }
}
