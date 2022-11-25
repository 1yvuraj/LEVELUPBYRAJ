public class cut {
    //Matrix Chain Multiplication
    //screenshoot dekho agar samje nhi to
    //dekh agar hum 0 pe cut lagte hai to problem boi rhe gi ki ak tarf empty or ak tarf pura arry
    //same as length-1 pe cut is lia hi nhi lagya
    //jab si increse or ei decrese tab gap wala lagta hai
    static int matrixMultiplication(int N, int arr[])
    {
        // code here
        int[][]dp=new int[arr.length+1][arr.length+1];
        String[][]sdp=new int[arr.length+1][arr.length+1];
        return help(arr,0,arr.length-1,dp,sdp);
    }
    static int help(int arr[],int si,int ei,int[][]dp,String[][]sdp)
    {
         if(ei-si==1)return dp[si][ei]=0;
         if(dp[si][ei]!=0)return dp[si][ei];
         int ans=(int)1e9;
         for(int cut=si+1;cut<ei;cut++){
             int left=help(arr,si,cut,dp,sdp);
             int right=help(arr,cut,ei,dp,sdp);
             ans=Math.min(ans,left+arr[si]*arr[ei]*arr[cut]+right);
         }
         return dp[si][ei]=ans;
    }
}
//tabo
static int matrixMultiplication(int N, int arr[])
{
    // code here
    int[][]dp=new int[arr.length+1][arr.length+1];
    
    return help(arr,0,arr.length-1,dp);
}
static int help(int arr[],int SI,int EI,int[][]dp)
{
    for(int gap=0;gap<arr.length;gap++){
        for(int  si=0,ei=gap;ei<arr.length;si++,ei++){
          if(ei-si==1){
              dp[si][ei]=0;
              continue;
          } 
         int ans=(int)1e9;
         for(int cut=si+1;cut<ei;cut++){
             int left=dp[si][cut];
             int right=dp[cut][ei];
             ans=Math.min(ans,left+arr[ei]*arr[si]*arr[cut]+right);
         }
          dp[si][ei]=ans;
        }
    }
    return dp[SI][EI];
     
}
//print bhi kia hai upper wala
//Brackets in Matrix Chain Multiplication
static String matrixChainOrder(int arr[],int n)
    {
        // code here
        int[][]dp=new int[arr.length+1][arr.length+1];
        String[][]sdp=new String[arr.length+1][arr.length+1];
        int ans=help(arr,0,arr.length-1,dp,sdp);
        return sdp[0][arr.length-1];
    }
    static int help(int arr[],int si,int ei,int[][]dp,String[][]sdp)
    {
         if(ei-si==1){
             sdp[si][ei]=(char)(si+'A')+"";
             return dp[si][ei]=0;
         }
         if(dp[si][ei]!=0)return dp[si][ei];
         int ans=(int)1e9;
         String a="";
         for(int cut=si+1;cut<ei;cut++){
             int left=help(arr,si,cut,dp,sdp);
             int right=help(arr,cut,ei,dp,sdp);
             if(left+arr[si]*arr[ei]*arr[cut]+right<ans){
             ans=left+arr[si]*arr[ei]*arr[cut]+right;
             a= "("+sdp[si][cut]+sdp[cut][ei]+")";
             }
         }
         sdp[si][ei]=a;
         return dp[si][ei]=ans;
    }
    //Minimum and Maximum values of an expression with * and +
    //cut sign pe lage ga
    public static class pairmm {
        int min = (int) 1e9;
        int max = 0;

        pairmm() {

        }

        pairmm(int val) {
            this.min = this.max = val;
        }
    }

    public static pairmm evaluateMinMax(pairmm leftRes, pairmm rightRes, char operator) {
        pairmm pair = new pairmm();
        if (operator == '+') {
            pair.min = leftRes.min + rightRes.min;
            pair.max = leftRes.max + rightRes.max;
        } else if (operator == '*') {
            pair.min = leftRes.min * rightRes.min;
            pair.max = leftRes.max * rightRes.max;
        }
        return pair;
    }

    public static pairmm minMax(String str, int si, int ei, pairmm[][] dp) {
        if (si == ei) {
            return dp[si][ei] = new pairmm((str.charAt(si) - '0'));
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        pairmm myRes = new pairmm();
        for (int cut = si + 1; cut < ei; cut += 2) {
            pairmm leftRes = minMax(str, si, cut - 1, dp);
            pairmm rightRes = minMax(str, cut + 1, ei, dp);
            pairmm pair = evaluateMinMax(leftRes, rightRes, str.charAt(cut));

            myRes.min = Math.min(myRes.min, pair.min);
            myRes.max = Math.max(myRes.max, pair.max);
        }

        return dp[si][ei] = myRes;
    }

    public static void minMax() {
        String str = "1+2*3+4*5";
        int n = str.length();
        pairmm[][] dp = new pairmm[n][n];

        pairmm res = minMax(str, 0, n - 1, dp);

        System.out.println("Min value: " + res.min);
        System.out.println("Max value: " + res.max);
    }
    // Burst Balloons
    //SS DEKHO
    public int Burst Balloons(int[] nums) {
        int[][]dp=new int[nums.length+1][nums.length+1];
        return help(nums,0,nums.length-1,dp);
    }
    public int help(int[] nums,int si,int ei,int[][]dp) {
        if(dp[si][ei]!=0)return dp[si][ei];
        int leftlimit=si==0?1:nums[si-1];
        int rightlimit=ei==nums.length-1?1:nums[ei+1];
        int max=-(int)1e9;
        for(int cut=si;cut<=ei;cut++){
            int left=cut==si?0:help(nums,si,cut-1,dp);
            int right=cut==ei?0:help(nums,cut+1,ei,dp);
            
            max=Math.max(max,left+right+leftlimit*rightlimit*nums[cut]);
        }
        return dp[si][ei]=max;
    }
    
    //Boolean Parenthesization
    //Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
    // Input: N = 7
    // S = T|T&F^T
    // Output: 4
    // Explaination: The expression evaluates 
    // to true in 4 ways ((T|T)&(F^T)), 
    // (T|(T&(F^T))), (((T|T)&F)^T) and (T|((T&F)^T)).
    //ss delkho
    public static class pairBoolen {
        long totalTrue = 0;
        long totalFalse = 0;

        pairBoolen(long totalTrue, long totalFalse) {
            this.totalFalse = totalFalse;
            this.totalTrue = totalTrue;
        }

        pairBoolen() {

        }
    }

    public static void evaluateTrue(pairBoolen left, pairBoolen right, pairBoolen res, char operator) {
        long mod = 1003;
        long totalTF = ((left.totalTrue + left.totalFalse) * (right.totalTrue + right.totalFalse)) % mod;
        long totalTrue = 0, totalFalse = 0;
        if (operator == '|') {
            totalFalse = (left.totalFalse * right.totalFalse) % mod;
            totalTrue = (totalTF - totalFalse + mod) % mod;
        } else if (operator == '^') {
            totalTrue = (left.totalFalse * right.totalTrue) % mod + (left.totalTrue * right.totalFalse) % mod;
            totalFalse = (totalTF - totalTrue + mod) % mod;

        } else if (operator == '&') {
            totalTrue = (left.totalTrue * right.totalTrue) % mod;
            totalFalse = (totalTF - totalTrue + mod) % mod;
        }
        //plus is lia kis kuki and se kuch ans aye ga or se kuch ans aye ga is lia 
        //sab ko add
        res.totalFalse = (res.totalFalse + totalFalse) % mod;
        res.totalTrue = (res.totalTrue + totalTrue) % mod;
    }

    public static pairBoolen countWays(String S, int si, int ei, pairBoolen[][] dp) {
        if (si == ei) {
            char ch = S.charAt(si);
            int t = ch == 'T' ? 1 : 0;
            int f = ch == 'F' ? 1 : 0;
            return dp[si][ei] = new pairBoolen(t, f);
        }
        if (dp[si][ei] != null) {
            return dp[si][ei];
        }

        pairBoolen res = new pairBoolen();
        for (int cut = si + 1; cut < ei; cut += 2) {
            pairBoolen lres = countWays(S, si, cut - 1, dp);
            pairBoolen rres = countWays(S, cut + 1, ei, dp);

            evaluateTrue(lres, rres, res, S.charAt(cut));
        }

        return dp[si][ei] = res;
    }

    static int countWays(int N, String S) {
        pairBoolen[][] dp = new pairBoolen[N][N];
        return (int) countWays(S, 0, N - 1, dp).totalTrue;
    }
