public static int coinChangeCombination_IN(int[] coins, int tar, int idx, String psf) {
    if (tar == 0) {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    for (int i = idx; i < coins.length; i++) {
        if (tar - coins[i] >= 0) {
            count += coinChangeCombination_IN(coins, tar - coins[i], i, psf + coins[i] + " ");
        }
    }
    return count;
}
public static int permutation(int[] arr, int tar, int[] dp) {
    if (tar == 0)
        return dp[tar] = 1;
    if (dp[tar] != -1)
        return dp[tar];
    int count = 0;
    for (int ele : arr) {
        if (tar - ele >= 0)
            count += permutation(arr, tar - ele, dp);
    }

    return dp[tar] = count;
}
//tabo
public static int permutation_DP(int[] arr, int Tar, int[] dp) {
    dp[0] = 1;
    for (int tar = 1; tar <= Tar; tar++) {
        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0)
                count += dp[tar - ele];
        }
        dp[tar] = count;
    }
    return dp[Tar];
}

public static int coinChangePermutation_Sin(int[] coins, int tar, String psf) {
    if (tar == 0) {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    for (int i = 0; i < coins.length; i++) {
        if (coins[i] > 0 && tar - coins[i] >= 0) {
            int val = coins[i];
            coins[i] = -coins[i];
            count += coinChangePermutation_Sin(coins, tar - val, psf + val + " ");
            coins[i] = -coins[i];
        }
    }

    return count;
}

public static int coinChangeCombination_Sin(int[] coins, int tar, int idx, String psf) {
    if (tar == 0) {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    for (int i = idx; i < coins.length; i++) {
        if (tar - coins[i] >= 0) {
            count += coinChangeCombination_Sin(coins, tar - coins[i], i + 1, psf + coins[i] + " ");
        }
    }
    return count;
}
//Coin Change
// Input: coins = [1,2,5], amount = 11
// Output: 3
// Explanation: 11 = 5 + 5 + 1

public int coinChange(int[] coins, int target) {
    int[] dp = new int[target + 1];
    Arrays.fill(dp, -1);
    int ans = coinChangeCombination_IN(coins, target, "", dp);
    return ans == 1000000001 ? -1 : ans;
}

public static int coinChangeCombination_IN(int[] coins, int tar, String psf, int[] dp) {
    if (tar == 0) {
        System.out.println(psf);
        return 0;
    }
    if (dp[tar] != -1) return dp[tar];
    int count = (int) 1e9;
    for (int i = 0; i < coins.length; i++) {
        if (tar - coins[i] >= 0) {
            count = Math.min(count, coinChangeCombination_IN(coins, tar - coins[i], psf + coins[i] + " ", dp));
        }
    }
    return dp[tar] = count + 1;
}
//Subset Sum Problem
// N = 6
// arr[] = {3, 34, 4, 12, 5, 2}
// sum = 9
// Output: 1 
// Explanation: Here there exists a subset with
// sum = 9, 4+3+2 = 9.

class Solution{
    static Boolean isSubsetSum(int N, int coins[], int tar){
       Boolean[][]dp=new Boolean[coins.length+1][tar+1];
       return coinChangePermutation_Sin(coins,0,tar,dp);
    }
    public static boolean coinChangePermutation_Sin(int[] coins, int idx ,int tar,Boolean[][]dp) {
      if(tar==0)return true;
      if(idx>=coins.length || tar<0)return false;
      if(dp[idx][tar]!=null)return dp[idx][tar];
      boolean res=false;
      if(tar-coins[idx]>=0){
          res=res||coinChangePermutation_Sin(coins,idx+1,tar-coins[idx],dp);
      }
      res=res||coinChangePermutation_Sin(coins,idx+1,tar,dp);
      return dp[idx][tar]=res;
    }
}
//for loop se
class Solution{
    static Boolean isSubsetSum(int N, int coins[], int tar){
       Boolean[][]dp=new Boolean[coins.length+1][tar+1];
       return coinChangePermutation_Sin(coins,0,tar,dp);
    }
    public static boolean coinChangePermutation_Sin(int[] coins, int idx ,int tar,Boolean[][]dp) {
      if(tar==0)return true;
      if(idx>=coins.length || tar<0)return false;
      if(dp[idx][tar]!=null)return dp[idx][tar];
      boolean res=false;
      for(int i=idx;i<coins.length;i++){
          res=res||coinChangePermutation_Sin(coins,i+1,tar-coins[idx],dp);
      }
      return dp[idx][tar]=res;
    }
}

//0 - 1 Knapsack Problem
// N = 3
// W = 4
// values[] = {1,2,3}
// weight[] = {4,5,1}
// Output: 3
//O(N*W)
    static int knapSack(int W, int wt[], int val[], int n) 
    { 
       Integer[][]dp=new Integer[W+1][n+1];
       return coinChangePermutation_Sin(W,wt,n,val,n,dp);
     
    } 
    static int Knapsack 01 (int W, int wt[], int idx,int val[], int n,Integer[][]dp) 
    { 
      if(idx==0 || W==0)return 0;
      if(idx<0 || W<0)return -(int)1e9;
      if(dp[W][idx]!=null)return dp[W][idx];
      int res=-(int)1e9;
      if(W-wt[idx-1]>=0){
          res=Math.max(res,coinChangePermutation_Sin(W-wt[idx-1],wt,idx-1,val,n,dp))+val[idx-1];
      }
      res=Math.max(res,coinChangePermutation_Sin(W,wt,idx-1,val,n,dp));
      return dp[W][idx]=res;
    } 
//tabo
static int Knapsack 01(int W, int wt[], int val[], int n) {
    int[][] dp = new int[W + 1][n + 1];
    return coinChangePermutation_Sin(W, wt, n, val, n, dp);
}

static int Knapsack 01(int W, int wt[], int IDX, int val[], int n, int[][] dp) {
    for (int w = 0; w <= W; w++) {
        for (int idx = 0; idx <= IDX; idx++) {
            if (idx == 0 || w == 0) {
                dp[w][idx] = 0;
                continue;
            }
            int res = -(int) 1e9;
            if (w - wt[idx - 1] >= 0) {
                res = Math.max(res, dp[w - wt[idx - 1]][idx - 1]) + val[idx - 1];
            }
            res = Math.max(res, dp[w][idx - 1]);
            dp[w][idx] = res;
        }
    }
    return dp[W][IDX];
}

//Find number of solutions of a linear equation of n variables
//Given a linear equation of n variables, find number of non-negative integer solutions of it. For example, let the given equation be “x + 2y = 5”, solutions of this equation are “x = 1, y = 2”, “x = 5, y = 0” and “x = 3, y = 1”. It may be assumed that all coefficients in given equation are positive integers.
//cpmbination ka code hi hai

public static int coinChangeCombination_IN(int[] coins, int tar, int idx, String psf) {
    if (tar == 0) {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    for (int i = idx; i < coins.length; i++) {
        if (tar - coins[i] >= 0) {
            count += coinChangeCombination_IN(coins, tar - coins[i], i, psf + coins[i] + " ");
        }
    }
    return count;
}
//Knapsack with Duplicate Items
static int knapSack(int N, int W, int val[], int wt[])
{
   Integer[][]dp=new Integer[W+1][N+1];
   return Knapsack01(W,wt,N,val,dp);
}
static int Knapsack-with-dublicate(int W, int wt[], int idx,int val[],Integer[][]dp) 
{ 
  if(idx==0 || W==0)return 0;
  if(idx<0 || W<0)return -(int)1e9;
  if(dp[W][idx]!=null)return dp[W][idx];
  int res=-(int)1e9;
  if(W-wt[idx-1]>=0){
      res=Math.max(res,Knapsack01(W-wt[idx-1],wt,idx,val,dp))+val[idx-1];
  }
  res=Math.max(res,Knapsack01(W,wt,idx-1,val,dp));
  return dp[W][idx]=res;
} 
//416. Partition Equal Subset Sum
public boolean canPartition(int[] nums) {
    int sum=0;
    for(int val:nums)sum+=val;
    if(sum%2!=0)return false;
    int tar=sum/2;
    Boolean[][]dp=new Boolean[tar+1][nums.length+1];
    return coinChangeCombination_Sin(nums,tar,nums.length,dp);
}
public  boolean coinChangeCombination_Sin(int[] coins, int tar, int idx, Boolean[][]dp) {
if (idx==0 || tar == 0) {
   
    return tar==0?true:false;
}
if(tar<0)return false;
if(dp[tar][idx]!=null)return dp[tar][idx];
boolean count = false;

    if (tar - coins[idx -1] >= 0) {
        count = count || coinChangeCombination_Sin(coins, tar - coins[idx-1], idx-1,dp);
    }
     count = count || coinChangeCombination_Sin(coins, tar, idx -1,dp);

return dp[tar][idx]=count;
}

//Target Sum
// Input: nums = [1,1,1,1,1], target = 3
// Output: 5
// Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
// -1 + 1 + 1 + 1 + 1 = 3
// +1 - 1 + 1 + 1 + 1 = 3
// +1 + 1 - 1 + 1 + 1 = 3
// +1 + 1 + 1 - 1 + 1 = 3
// +1 + 1 + 1 + 1 - 1 = 3

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0, n = nums.length;
        for (int ele : nums) sum += ele;
        if (target > sum || target < -sum) return 0;

        int[][] dp = new int[2 * sum + 1][n + 1];
        for (int[] d : dp) Arrays.fill(d, -1);

        return findTargetSumWays(nums, sum + target, n, sum, dp);
    }
    public int findTargetSumWays(int[] nums, int tar, int idx, int sum, int[][] dp) {
        if (idx == 0) {
            return tar == sum ? 1 : 0;
        }
        if (dp[tar][idx] != -1) return dp[tar][idx];
        int count = 0;
        if ((tar - nums[idx - 1]) >= 0) count += findTargetSumWays(nums, tar - nums[idx - 1], idx - 1, sum, dp);
        if ((tar + nums[idx - 1]) <= 2 * sum) count += findTargetSumWays(nums, tar + nums[idx - 1], idx - 1, sum, dp);
        return dp[tar][idx] = count;
    }
}
//698. Partition to K Equal Sum Subsets
//k*2^n
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int max = 0;
        for (int val : nums) {
            sum += val;
            max = Math.max(max, val);
        }
        if (sum % k != 0 || max > sum / k) return false;
        return check(nums, 0, 0, sum / k, k);
    }
    public boolean check(int[] nums, int idx, int sumsf, int tar, int k) {
        if (k == 0) return true;
        if (sumsf > tar) return false;
        if (tar == sumsf) {
            return check(nums, 0, 0, tar, k - 1);
        }
        boolean res = false;
        for (int i = idx; i < nums.length; i++) {
            if (nums[i] == -1) continue;

            int val = nums[i];
            nums[i] = -1;
            res = res || check(nums, i + 1, sumsf + val, tar, k);
            nums[i] = val;
        }
        return res;
    }
}

//688. Knight Probability in Chessboard
//https://leetcode.com/problems/knight-probability-in-chessboard/submissions/
//k move chalna hai haar direction me or 
// Input: n = 3, k = 2, row = 0, column = 0
// Output: 0.06250
// Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
// From each of those positions, there are also two moves that will keep the knight on the board.
// The total probability the knight stays on the board is 0.0625.
int[][] dir = { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 } };

    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n + 1][n + 1];
        return knightProbability_(n, k, row, column, dp);
    }

    public double knightProbability_(int n, int k, int row, int column, double[][][] dp) {
        if (k == 0) return 1.0;
        if (dp[k][row][column] != 0.0) return dp[k][row][column];
        double count = 0.0;
        for (int d = 0; d < 8; d++) {
            int r = row + dir[d][0];
            int c = column + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < n) {
                count += knightProbability_(n, k - 1, r, c, dp);
            }
        }
        //sun sabne apni prob bta dia ab mare se bo prob tak 1/8 hogi ab count jo aya us se multiple kar do
        //multiple kuki sab matlkb 8 prob me 1/8 hogi
        return dp[k][row][column] = count*1/8 ;
    }
