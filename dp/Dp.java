//Fibonacci Number
//2^n
public int fib(int n) {
    int[]dp=new int[n+1];
    return help(n,dp);
}
 public int help(int n,int[]dp) {
    if(n==0)return dp[n]=0;
    if(n==1)return dp[n]=1;
    if(dp[n]!=0)return dp[n];
    int f1=help(n-1,dp)+help(n-2,dp);
    return dp[n]=f1;
}
//tabo
public int fib(int n) {
    int[]dp=new int[n+1];
    return help(n,dp);
}
 public int help(int N,int[]dp) {
    for(int n=0;n<=N;n++){
    if(n<=1){
        dp[n]=n;
        continue;
    }
    dp[n]=dp[n-1]+dp[n-2];
    }
    return dp[N];
}
//Unique Paths
// T(n) = O(2^max(m,n)), 
int[][]dir={{1,0},{0,1}};
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return help(0,0,m - 1, n - 1, dp);
    }
    public int help( int sr,int sc,int er, int ec, int[][] dp) {
        if(sr==er && sc==ec)return 1;
        if(dp[sr][sc]!=0)return dp[sr][sc];
        int count=0;
        for(int []d:dir){
            int r=sr+d[0];
            int c=sc+d[1];
            if(r>=0 && c>=0 && r<=er && c<=ec){
                count+=help(r,c,er,ec,dp);
            }
        }
       return dp[sr][sc]=count;
    
}
//tabo
public int help(int SR, int SC, int ER, int EC, int[][] dp) {
    for (int sr = ER; sr >= SR; sr--) {
        for (int sc = EC; sc >= SC; sc--) {
            if (sr == ER && sc == EC) {
                dp[sr][sc] = 1;
                continue;
            }
            int count = 0;
            for (int[] d : dir) {
                int r = sr + d[0];
                int c = sc + d[1];
                if (r >= 0 && c >= 0 && r <= ER && c <= EC) {
                    count += dp[r][c];
                }
            }
            dp[sr][sc] = count;
        }
    }
    return dp[SR][SC];
}
//Unique Paths II
int[][] dir = { { 1, 0 }, { 0, 1 } };
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    if (obstacleGrid[0][0] == 1) return 0;
    int[][] dp = new int[m][n];
    return help(0, 0, m - 1, n - 1, dp, obstacleGrid);
}

public int help(int SR, int SC, int ER, int EC, int[][] dp, int[][] obstacleGrid) {
    for (int sr = ER; sr >= SR; sr--) {
        for (int sc = EC; sc >= SC; sc--) {
            if (sr == ER && sc == EC) {
                dp[sr][sc] = 1;
                continue;
            }
            int count = 0;
            for (int[] d : dir) {
                int r = sr + d[0];
                int c = sc + d[1];
                if (r >= 0 && c >= 0 && r <= ER && c <= EC && obstacleGrid[r][c] != 1) {
                    count += dp[r][c];
                }
            }
            dp[sr][sc] = count;
        }
    }
    return dp[SR][SC];
}
// Climbing Stairs
// Input: n = 2
// Output: 2
// Explanation: There are two ways to climb to the top.
// 1. 1 step + 1 step
// 2. 2 steps
class Solution {
    public int climbStairs(int n) {
       int[]dp=new int[n+1];
       return help(n,dp); 
    }
    public int help(int n,int[]dp) {
      if(n==0)return 1;
      if(n<0)return 0;
      if(dp[n]!=0)return dp[n];
      return dp[n]=help(n-1,dp)+help(n-2,dp);
    }
}
// 746. Min Cost Climbing Stairs
//is me bola hai ki agar 0 se ya 1 se dono chale per min cost kis me mile gi
//or 0 to m jana hai 
class Solution {
    int[][] dir = { { 1, 0 }, { 0, 1 } };
    public int minCostClimbingStairs(int[]obstacleGrid) {
        int m = obstacleGrid.length;
        int[]dp = new int[m+1];
        return Math.min(help(0,m,dp, obstacleGrid),help(1,m,  new int[m], obstacleGrid));
    }

    public int help(int idx,int er,int[] dp, int[]obstacleGrid) {
       if(idx>=er)return 0;
       //if(idx>er)return (int)1e9;
       if(dp[idx]!=0)return dp[idx];
       int l=help(idx+1,er,dp,obstacleGrid);
        int r=help(idx+2,er,dp,obstacleGrid);
        return dp[idx]=Math.min(l,r)+obstacleGrid[idx];
    }
}
//agar jump wala hota 

public int help(int SR, int SC, int ER, int EC, int[][] dp, int[][] obstacleGrid) {
    for (int sr = ER; sr >= SR; sr--) {
        for (int sc = EC; sc >= SC; sc--) {
            if (sr == ER && sc == EC) {
                dp[sr][sc] = 1;
                continue;
            }
            int count = 0;
            for (int[] d : dir) {
                for(int rad=1;rad<=Math.min(ER,EC);rad++)
                int r = sr + d[0];
                int c = sc + d[1];
                if (r >= 0 && c >= 0 && r <= ER && c <= EC) {
                    count += dp[r][c];
                }
            }
            dp[sr][sc] = count;
        }
    }
    return dp[SR][SC];
}
//dice
//boi ki dice hai to 0 to n-1 kitne way hai
//memo
class Solution {
    int[][] dir = { { 1, 0 }, { 0, 1 } };
    public int minCostClimbingStairs(int[]obstacleGrid) {
        int m = obstacleGrid.length;
        int[]dp = new int[m];
        return help(0,m-1,dp, obstacleGrid);
    }

    public int help(int idx,int er,int[] dp, int[]obstacleGrid) {
       if(idx==er)return 1;
       int count=0;
       if(dp[idx]!=0)return dp[idx];
       for(int i=1;i<=6;i++){
          if(idx+i<=er)
          count+=help(idx+i,er,dp,obstacleGrid);
       }
       return dp[idx]=count;
    }
}
//tabo
class Solution {
    int[][] dir = { { 1, 0 }, { 0, 1 } };
    public int minCostClimbingStairs(int[]obstacleGrid) {
        int m = obstacleGrid.length;
        int[]dp = new int[m];
        return help(0,m-1,dp, obstacleGrid);
    }

    public int help(int SR,int ER,int[] dp, int[]obstacleGrid) {
        for(int i=ER;i>=0;i--){
            if(i==Er){
                dp[i]=1;
                continue;
            }
            int count=0;
            for(int i=1;i<=6;i++){
                if(idx+i<=er)
                count+=dp[idx+i];
             }
             dp[idx]=count;
        }
        return dp[SR];
       
    }
}
//91. Decode Ways
//boi kitne waya hai number ko alfabalte me karne ke
//https://leetcode.com/problems/decode-ways/
public int numDecodings(String s) {
    int[] dp = new int[s.length() + 1];
    Arrays.fill(dp, -1);
    return help(s, 0, dp);
}

public int help(String s, int idx, int[] dp) {
    if (idx == s.length()) return dp[idx] = 1;
    if (dp[idx] != -1) return dp[idx];
    char ch = s.charAt(idx);
    int count = 0;
    if (ch == '0') return 0;
    count += help(s, idx + 1, dp);
    if (idx < s.length() - 1) {
        char ch1 = s.charAt(idx + 1);
        int num = (ch - '0') * 10 + (ch1 - '0');
        if (num <= 26) {
            count += help(s, idx + 2, dp);
        }
    }
    return dp[idx] = count;
}
//tabo
public int numDecodings(String s) {
    int[] dp = new int[s.length() + 1];
    Arrays.fill(dp, -1);
    return help(s, 0, dp);
}
public int help(String s, int IDX, int[] dp) {
    for (int idx = s.length(); idx >= 0; idx--) {
        if (idx == s.length()) {
            dp[idx] = 1;
            continue;
        }
        char ch = s.charAt(idx);
        int count = 0;
        if (ch == '0') {
            dp[idx] = 0;
            continue;
        }
        count += dp[idx + 1];
        if (idx < s.length() - 1) {
            char ch1 = s.charAt(idx + 1);
            int num = (ch - '0') * 10 + (ch1 - '0');
            if (num <= 26) {
                count += dp[idx + 2];
            }
        }
        dp[idx] = count;
    }
    return dp[IDX];
}
//Gold Mine Problem
static int maxGold(int n, int m, int M[][])
    {
        int[][]dp=new int[n][m];
        for(int []d:dp)Arrays.fill(d,-1);
        int max=-(int)1e9;
        for(int i=0;i<n;i++){
            max=Math.max(max,help(i,0,n,m,M,dp));
        }
        return max;
    }
    static int help(int sr,int sc ,int n,int m, int M[][],int[][]dp)
    {
        if(sr<0 || sr>=n ||sc>=m)return -(int)1e9;
        if(sc==m-1)return M[sr][sc];
        if(dp[sr][sc]!=-1)return dp[sr][sc];
        int l=help(sr-1,sc+1,n,m,M,dp);
        int r=help(sr,sc+1,n,m,M,dp);
        int d=help(sr+1,sc+1,n,m,M,dp);
        return dp[sr][sc]=Math.max(l,Math.max(r,d))+M[sr][sc];
  }
//dir se
static int[][]dir={{0,1},{-1,1},{1,1}};
static int maxGold(int n, int m, int M[][])
{
    int[][]dp=new int[n][m];
    for(int []d:dp)Arrays.fill(d,-1);
    int max=-(int)1e9;
    for(int i=0;i<n;i++){
        max=Math.max(max,help(i,0,n,m,M,dp));
    }
    return max;
}
static int help(int sr,int sc ,int n,int m, int M[][],int[][]dp)
{
    if(sr<0 || sr>=n ||sc>=m)return -(int)1e9;
    if(sc==m-1)return M[sr][sc];
    if(dp[sr][sc]!=-1)return dp[sr][sc];
    int max=-(int)1e9;
    for(int[]d:dir){
        int r=sr+d[0];
        int c=sc+d[1];
        if(r>=0 && c>=0 && r<n && c<m){
            max=Math.max(max,help(r,c,n,m,M,dp));
        }
    }
    return dp[sr][sc]=max+M[sr][sc];
}
//suno dp yha ku nhi lag sakti kuki jase 4 call ho ya ye wala to is me ak point pe ane ke multiple way
//hai to ans bhi different hoge
//It is not possible to solve this problem using dynamic programming similar to the previous problem because here current state depends not only on the right and bottom cells but also on the left and upper cells. 
//Maximum path sum in matrix
//flood fill me to way hote hai to dp to hoti nhi visit wala hi hota hai
static int[][]dir={{1,0},{1,-1},{1,1}};
static int maximumPath(int N, int M[][])
{
    // code here
 int n=M.length;
 int m=M[0].length;
 int[][]dp=new int[n][m];

int max=-(int)1e9;
for(int i=0;i<m;i++){
    max=Math.max(max,help(0,i,n,m,M,dp));
}
return max;
}
static int help(int sr,int sc ,int n,int m, int M[][],int[][]dp)
{
if(sr<0 || sr>=n ||sc>=m)return -(int)1e9;
if(sr==n-1)return M[sr][sc];
if(dp[sr][sc]!=0)return dp[sr][sc];
int max=-(int)1e9;
for(int[]d:dir){
    int r=sr+d[0];
    int c=sc+d[1];
    if(r>=0 && c>=0 && r<n && c<m){
        max=Math.max(max,help(r,c,n,m,M,dp));
    }
}
return dp[sr][sc]=max+M[sr][sc];
}

//friends pairing 
int mod=(int)1e9+7;
public long countFriendsPairings(int n) 
{  long[]dp=new long[n+1]; 
   return help(n,dp);
}
public long help(int N,long[]dp) 
{ 
   for(int n=0;n<=N;n++){
   if(n==0){
       dp[n]= 1;
       continue;
   }
   long self=dp[n-1]%mod;
   long group=n>=2?(dp[n-2]*(n-1))%mod:0;
    dp[n]=(self+group)%mod;
   }
   return dp[N];
}

//goldmine path print
public static int goldMine(int[][] arr, int sr, int sc, int[][] dir, int[][] dp) {
    int n = arr.length, m = arr[0].length;
    if (sc == m - 1)
        return dp[sr][sc] = arr[sr][sc];

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int maxGold = 0;
    for (int[] d : dir) {
        int r = sr + d[0];
        int c = sc + d[1];

        if (r >= 0 && c >= 0 && r < n && c < m) {
            maxGold = Math.max(maxGold, goldMine(arr, r, c, dir, dp) + arr[sr][sc]);
        }
    }

    return dp[sr][sc] = maxGold;
}
public static void goldMine_backEngg(int[][] dp, int[][] dir, int sr, int sc, String asf) {
    if (sc == dp[0].length - 1) {
        System.out.println(asf + "(" + sr + ", " + sc + ") ");
        return;
    }
    int maxGold = 0;
    int idx = -1;
    for (int d = 0; d < dir.length; d++) {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length && dp[r][c] > maxGold) {
            maxGold = dp[r][c];
            idx = d;
        }
    }

    if (idx != -1) {
        int r = sr + dir[idx][0], c = sc + dir[idx][1];
        goldMine_backEngg(dp, dir, r, c, asf + "(" + sr + ", " + sc + ") ");

    }
}

public static void goldMine() {
    int[][] arr = { { 10, 33, 13, 15 }, { 22, 21, 04, 1 }, { 5, 0, 2, 3 }, { 0, 6, 14, 2 } };
    int[][] dir = { { 0, 1 }, { 1, 1 }, { -1, 1 } };
    int n = arr.length, m = arr[0].length;
    int[][] dp = new int[n][m];

    for (int[] d : dp)
        Arrays.fill(d, -1);

    int maxGold = 0;
    int rIdx = 0;
    for (int r = 0; r < n; r++) {
        int ans = goldMine(arr, r, 0, dir, dp);
        if (ans > maxGold) {
            maxGold = ans;
            rIdx = r;
        }
    }

    goldMine_backEngg(dp, dir, rIdx, 0, "");

    System.out.println(maxGold);
}

     