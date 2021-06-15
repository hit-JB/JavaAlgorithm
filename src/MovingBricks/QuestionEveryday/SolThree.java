package MovingBricks.QuestionEveryday;

import java.util.*;

public class SolThree {
    public static void main(String[] args){
        SolThree sol = new SolThree();
        int[] nums = {1,4,3,2,5,2};
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for(int i=1;i<nums.length;i++)
        {
            temp.next = new ListNode(nums[i]);
            temp = temp.next;
        }
        temp.next = null;
        temp = head;
       temp = sol.partition(head,3);
        while (temp!=null)
        {
            System.out.println(temp.val);
            temp = temp.next;
        }

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
    public List<List<Integer>> combineByMath(int[] nums,int k)
    {
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
    public boolean dfsExist(char[][] board,String word,int row,int column,int[][] map)
    {
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
        return null;
    }

}
